package chord;

import application.GuideMichelin;
import application.GuideMichelinImpl;
import exceptions.AlreadyRegisteredException;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/**
 * This class implements the {@link Tracker} interface. This implementation
 * use a list of peers to keep track of the peers that belong to the network.
 */
public class TrackerImpl extends UnicastRemoteObject implements Tracker
{

    /**
     * Default serialization ID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of peers that belong to the network
     */
    private final List<Peer> peers;

    /**
     * Used for random picking in the peer list
     */
    private final Random randomGenerator;


    public TrackerImpl(int port) throws RemoteException, MalformedURLException, AlreadyBoundException
    {
        this.peers = new ArrayList<Peer>();
        this.randomGenerator = new Random();
        // The tracker is a remotely accessible object: bind it to an RMI 
        // registry so that we can retrieve it at a well known address
        Registry registry = LocateRegistry.createRegistry(port);
        registry.bind("tracker", this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized void register(Peer peer) throws AlreadyRegisteredException, RemoteException
    {
        if(this.peers.contains(peer))
        {
            throw new AlreadyRegisteredException(peer.getId());
        }

        this.peers.add(peer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Peer getRandomPeer() throws RemoteException
    {
        if(this.peers.isEmpty())
        {
            return null;
        }

        return this.peers.get(this.randomGenerator.nextInt(this.peers.size()));
    }

    /**
     * Verifie la coherence des pairs
     * Compte le nombre de réplicat et si le nombre de réplicat est inférieur à replicatmax + 1 on remet le replicat
     * perdue dans un nouveau peer
     *
     * @throws RemoteException
     */
    public void verifieCoherence() throws RemoteException
    {
        Map<String[][], Integer> compteur = new HashMap<String[][], Integer>();
        Peer peer = this.getRandomPeer();

        while(peer.getSuccessor() != null)  //Je parcours la boucle
        {
            Set cleReplicat = peer.getReplicat().keySet();
            Iterator itReplicat = cleReplicat.iterator();
            while(itReplicat.hasNext())  // je parcours la map de replicats
            {
                String cle = (String) itReplicat.next();
                Set cleCpt = compteur.keySet();
                Iterator itCpt = cleCpt.iterator();

                String[][] matrix = {{cle , peer.getReplicat().get(cle)}};

                while(itCpt.hasNext()) // je parcours la map de compteur
                {
                    String cleCp = (String) itCpt.next();
                    if(peer.getReplicat().get(cle).compareTo(cleCp) == 0) //Si le nom du menu est déjà present dans
                    // la boucle alors on incrément
                    {
                        compteur.put(matrix, compteur.get(cleCp) + 1);
                    }else  //Sinon on rajoute le plat et on met le compteur  à un
                    {
                        compteur.put(matrix, 1);
                    }
                }
                peer = peer.getSuccessor();
            }
        }
        GuideMichelin guideMichelin = new GuideMichelinImpl(TrackerImpl.this);

        Set cles = compteur.keySet();
        Iterator it = cles.iterator();
        while(it.hasNext())
        {
            String cle = (String) it.next();
            if(compteur.get(cle) < PeerImpl.REPLICATMAX + 1)
            {
                guideMichelin.put(trouveRestaurent(cle), cle, PeerImpl.REPLICATMAX);
            }

        }
    }

    /**
     * Trouve un restaurant par rapport à son nom de plat
     *
     * @param daily le plat
     * @return le restaurant
     */
    public String trouveRestaurent(String daily) throws RemoteException
    {
        String restaurant = "";
        Peer peer = getRandomPeer();
        Peer fin = peer;
        while(!peer.equals(fin) || restaurant != "")
        {
            Set cles = peer.getDirectory().keySet();
            Iterator it = cles.iterator();
            while(it.hasNext())
            {
                String cle = (String) it.next();
                if(peer.getDirectory().get(cle).compareTo(daily) == 0)
                {
                    restaurant = cle;
                }
            }
        }
        return restaurant;
    }

}
