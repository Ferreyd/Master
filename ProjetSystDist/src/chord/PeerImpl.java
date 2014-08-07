package chord;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Implementation of the {@link Peer} API.
 */
public class PeerImpl extends UnicastRemoteObject implements Comparable<Peer>, Peer
{

    /**
     * Default serialization ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Identifier of the peer in the virtual ring
     */
    private final Identifier id;

    /**
     * Local storage for entries that have an identifier that is managed by
     * the peer
     */
    private final Map<String, String> directory;

    /**
     * Hash map de replicat
     */
    private final Map<String, String> replicat;

    /**
     * Peer that is just before in the virtual ring
     */
    private Peer predecessor;

    /**
     * Peer that is just after in the virtual ring
     */
    private Peer successor;

    /**
     * Successeur du successeur du pair courant
     */
    private Peer superSuccessor;


    public static int REPLICATMAX = 2;

    /**
     * Instantiates a new Peer impl.
     *
     * @param id the id
     * @throws RemoteException the remote exception
     */
    public PeerImpl(Identifier id) throws RemoteException
    {
        this.id = id;
        this.predecessor = this;
        this.successor = this;
        this.directory = new HashMap<String, String>();
        this.replicat = new HashMap<String, String>();

        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(1);
        threadPool.scheduleAtFixedRate(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    // The stabilize method is called periodically to update
                    // the successor and predecessor links of the peer
                    PeerImpl.this.stabilize();
                }
                catch(RemoteException e)
                {
                    e.printStackTrace();
                }
            }
        }, 0, 500, TimeUnit.MILLISECONDS);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void create() throws RemoteException
    {
        this.predecessor = null;
        // The bootstrap of the Chord network requires a self loop
        this.successor = this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void join(Peer landmarkPeer) throws RemoteException
    {
        this.predecessor = null;
        // To join the network, ask a peer that is already in the network to
        // find which peer must be the successor of the joining peer, using
        // the identifier of the joining peer
        this.successor = landmarkPeer.findSuccessor(this.id);
        // The stabilize method will then update all the other links correctly
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Peer findSuccessor(Identifier id) throws RemoteException
    {
        // There is only one peer in the network
        if(this.successor.equals(this))
        {
            return this;
        }
        // The specified identifier is in between the current peer identifier
        // and the successor identifier: the successor is then the peer we are
        // looking for
        if(id.isBetweenOpenClosed(this.id, this.successor.getId()))
        {
            return this.successor;
        }
        // Nothing can be deduced from the specified identifier here:
        // propagate the request in case the successor knows more about it
        else
        {
            return this.successor.findSuccessor(id);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identifier getId() throws RemoteException
    {
        return this.id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Peer getPredecessor() throws RemoteException
    {
        return this.predecessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Peer getSuccessor() throws RemoteException
    {
        return this.successor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setPredecessor(Peer peer) throws RemoteException
    {
        this.predecessor = peer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void setSuccessor(Peer peer) throws RemoteException
    {

        this.successor = peer;
        // this.superSuccessor = this.successor.getSuccessor();

    }

    /**
     * @return Le successeur du successeur
     * @throws java.rmi.RemoteException
     */
    @Override
    public Peer getSuperSuccessor() throws RemoteException
    {
        return this.superSuccessor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj)
    {
        try
        {
            // Two peers are equal if they have the same identifier
            return this.id.equals(((Peer) obj).getId());
        }
        catch(RemoteException e)
        {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode()
    {
        // The hashcode of a peer is the hashcode of its identifier
        return this.id.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Peer p)
    {
        try
        {
            return this.id.compareTo(p.getId());
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void stabilize() throws RemoteException
    {
        // x should be this itself, but it is not always the case, typically
        // if the successor has recently taken a new peer as predecessor
        Peer x = null;
        try
        {
            x = this.successor.getPredecessor();
        }
        catch(NoSuchObjectException e)
        {
            PeerImpl.this.successor = PeerImpl.this.superSuccessor;
            PeerImpl.this.superSuccessor = PeerImpl.this.successor.getSuccessor();
            PeerImpl.this.successor.setPredecessor(PeerImpl.this);
            PeerImpl.this.predecessor.setSuperSuccessor(PeerImpl.this.successor);
        }

        // If x is this itself, then this condition is not valid. This
        // condition is valid if the successor has another peer as predecessor,
        // then in this case we check if this other peer is indeed included in
        // the current identifier and the identifier of the successor. If it
        // is, then it mean that x must be the new successor.
        if(x != null && x.getId().isBetweenOpenOpen(this.id, this.successor.getId()))
        {
            this.successor = x;
        }
        this.superSuccessor = this.successor.getSuccessor();
        // The current peer needs to inform its successor that it is indeed its
        // successor
        this.successor.notify(PeerImpl.this);
    }

    /**
     * @param restaurant
     * @return les donnes des replicats en fonction d'une cle
     */
    @Override
    public String getReplicat(String restaurant) throws RemoteException
    {
        return this.getReplicat().get(restaurant);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void notify(Peer peer) throws RemoteException
    {
        // If a new peer notify itself as a predecessor of the current peer,
        // check if it fits in the interval of the previous predecessor
        // identifier and it own identifier. If yes, take it as predecessor.
        // Otherwise, nothing needs to be done.
        if(this.predecessor == null || peer.getId().isBetweenOpenOpen(this.predecessor.getId(), this.id))
        {
            this.predecessor = peer;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void put(String restaurant, String dailySpecial) throws RemoteException
    {
        this.directory.put(restaurant, dailySpecial);

        Peer peer = this.getSuccessor();

        for(int i = 0 ; i < REPLICATMAX ; i++)
        {
            peer.putReplicat(restaurant, dailySpecial);
            peer = peer.getSuccessor();
        }
    }

    /**
     * Ajouote un replicat
     *
     * @param restaurant
     * @param dailySpecial
     * @throws java.rmi.RemoteException
     */
    @Override
    public void putReplicat(String restaurant, String dailySpecial) throws RemoteException
    {
        this.replicat.put(restaurant, dailySpecial);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String describe() throws RemoteException
    {
        StringBuilder s = new StringBuilder("Peer [id=" + this.id +
                                            ", successor=" + this.successor.getId() + ", " +
                                            "predecessor=" + this.predecessor.getId() + ", values=[");

        int cpt = 0;
        int size = this.directory.size();
        s.append("\nDirectory = ");
        for(Entry<String, String> entry : this.directory.entrySet())
        {
            s.append("(" + entry.getKey() + ";" + entry.getValue() + ")");
            if(++cpt != size)
            {
                s.append(", ");
            }
        }
        s.append("\nReplicat = ");
        for(Entry<String, String> entry : this.replicat.entrySet())
        {
            s.append("(" + entry.getKey() + ";" + entry.getValue() + ")");
            if(++cpt != size)
            {
                s.append(", ");
            }
        }

        s.append("]]");
        s.append("\n");
        return s.toString();
    }

    /**
     * {@inheritDoc}
     */
    public void die() throws RemoteException
    {
        // Removes this from the RMI runtime. It will prevent all RMI calls
        // from executing on this object. A further RMI call on this will
        // cause a java.rmi.NoSuchObjectException.
        UnicastRemoteObject.unexportObject(this, true);
        System.out.println("Peer with id " + this.id + " has died.");
    }


    /**
     * Verifie la coherence des réplicats
     * Ecrits les données sur les pairs desires puis fait une vérification des pairs écrits.
     * Si la données est correcte, alors on passe au successeur, sinon on réecrit la donnée puis on reverifie la donnee
     *
     * @param restaurant
     * @param dailySpecial
     * @param r
     * @throws java.rmi.RemoteException
     */
    @Override
    public void verifieCoherence(String restaurant, String dailySpecial) throws RemoteException
    {
        Peer peer = this; // le peer actuel
        for(int i = 0 ; i < REPLICATMAX ; i++)
        {
            peer.putReplicat(restaurant, dailySpecial);
            peer = this.getSuccessor();
        }

        peer = this;
        for(int i = 0 ; i < REPLICATMAX ; i++)
        {
            if(peer.getReplicat().get(restaurant).compareTo(dailySpecial) != 0)
            {
                peer.getReplicat().put(restaurant, dailySpecial);
            }
            peer = peer.getSuccessor();
        }
    }

    /**
     * Si le successeur est null, alors le successeur de ce pair et son super successeur.
     * Et le predecesseur du superSuccesseur et ce pair
     *
     * @throws RemoteException
     */
    @Override
    public void testPanne() throws RemoteException
    {
        if(this.getSuccessor() == null)
        {
            this.setSuccessor(this.getSuperSuccessor());
            this.getSuperSuccessor().setPredecessor(this);
        }
    }

    /**
     * Verifie sur le successeur est vivant
     *
     * @throws java.rmi.RemoteException
     */
    @Override
    public void heartbeat() throws RemoteException
    {
        try
        {
            if(this.getSuccessor() == null)
            {
                this.getSuccessor().die();
                this.setSuccessor(this.superSuccessor);
            }
        }
        catch(RemoteException e)
        {

            e.printStackTrace();
        }
    }

    /**
     * Retourne une map de compteur de replicat
     *
     * @throws RemoteException
     */
    public Map<String[][], Integer> CompteReplicat() throws RemoteException
    {
        Map<String[][], Integer> compteur = new HashMap<String[][], Integer>();
        Peer peer = this.getSuccessor();

        while(peer.getSuccessor() != null)  //Je parcours la boucle
        {
            Set cleReplicat = peer.getReplicat().keySet();
            Iterator itReplicat = cleReplicat.iterator();
            while(itReplicat.hasNext())  // je parcours la map de replicats
            {
                String cle = (String) itReplicat.next();
                Set cleCpt = compteur.keySet();
                Iterator itCpt = cleCpt.iterator();

                String[][] matrix = {{cle , getReplicat().get(cle)}};

                while(itCpt.hasNext()) // je parcours la map de compteur
                {
                    String cleCp = (String) itCpt.next();
                    if(peer.getReplicat().get(cle).compareTo(cleCp) == 0) //Si le nom du menu est déjà present dans
                    // la boucle alors on incrément
                    {
                        compteur.put(matrix, compteur.get(cleCp) + 1);
                    }else  //Sinon on rajoute le plat et on met le compteur  à zero
                    {
                        compteur.put(matrix, 0);
                    }
                }
                peer = peer.getSuccessor();
            }
        }
        return compteur;
    }

    public void verifieReplicat(Map<String, Integer> compteur)
    {
        Set cles = compteur.keySet();
        Iterator it = cles.iterator();
        while(it.hasNext())
        {
            String cle = (String) it.next();
            if(compteur.get(cle) > (REPLICATMAX + 1))
            {

            }
        }
    }


    @Override
    public Map<String, String> getDirectory()
    {
        return this.directory;
    }

    /**
     * @return les donnes des replicats
     */
    @Override
    public Map<String, String> getReplicat()
    {
        return this.replicat;
    }

    /**
     * Looks for the specified restaurant in the local storage structure and
     * returns the associated daily special.
     *
     * @param restaurant The name of the restaurant that is searched in the local
     *                   storage.
     * @return The name of the daily special associated to the specified restaurant or
     * null if no entry is found for the specified restaurant.
     * @throws java.rmi.RemoteException
     */
    @Override
    public String get(String restaurant) throws RemoteException
    {
        return this.getDirectory().get(restaurant);
    }

    public void setSuperSuccessor(Peer superSuccessor)
    {
        this.superSuccessor = superSuccessor;
    }

}
