package tests;

import application.DataGenerator;
import application.GuideMichelin;
import application.GuideMichelinImpl;
import chord.*;
import exceptions.AlreadyRegisteredException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Nicolas
 *         Date : 22/01/14 18:38
 */
public class TestMort
{

    /**
     * Number of peers that will be injected in the network
     */
    private static final int NB_PEERS = 10;

    /**
     * Port number of RMI registry
     */
    private static final int RMI_REGISTRY_PORT = 1099;
    private static GuideMichelin guideMichelin;

    /**
     * @param args Not used
     * @throws Exception
     */
    public static void main(String[] args) throws Exception
    {

        // A tracker is created
        new TrackerImpl(RMI_REGISTRY_PORT);

        Tracker tracker = (Tracker) Naming.lookup("rmi://localhost:" + RMI_REGISTRY_PORT + "/tracker");

        // A Chord network is initialized
        createNetwork(tracker);

        // All the peers in the network are listed
        Thread.sleep((long) (Math.log(NB_PEERS) * 1000));
        System.out.println("\nTurn around after first stabilization");
        turnAround(tracker.getRandomPeer());

        Thread.sleep((long) (Math.log(NB_PEERS) * 1000));
        System.out.println("\nTurn around after second stabilization");
        turnAround(tracker.getRandomPeer());

        // A GuideMichelin is created. It will use the Chord network
        GuideMichelin guideMichelin = new GuideMichelinImpl(tracker);

        // Some data are added to the {@link GuideMichelin}
        DataGenerator dataGenerator = new DataGenerator(10);
        Map<String, String> newData;

        for(int i = 0 ; i < 10 ; i++)
        {
            newData = dataGenerator.getNewData();
            for(Map.Entry<String, String> entry : newData.entrySet())
            {
                guideMichelin.put(entry.getKey(), entry.getValue(), 5);
            }
        }

        // The peers are listed again with the data they store
        Thread.sleep(2000);
        System.out.println("\nTurn around after adding data");
        turnAround(tracker.getRandomPeer());

        // Some data are requested from the GuideMichelin
        String[] restaurants = {"Le Bistrot Gourmand" , "Auberge de la Madone" , "toto"};

        for(String restaurant : restaurants)
        {
            System.out.println("\nRestaurant '" + restaurant + "' - Daily special: '" + guideMichelin.get(restaurant)
                               + "'");
        }

        System.out.println("TEST\n");

        /**
         * On compte le nombre de réplicat
         */
        HashMap<String[][], Integer> compteur = new HashMap<String[][], Integer>();
        Peer fin = tracker.getRandomPeer();
        Peer peer = fin.getSuccessor();



        System.out.println("Fin = " + fin.getId() + "Peer = " + peer.getId());


        while(!fin.equals(peer))  //Je parcours la boucle
        {
            Set cleReplicat = peer.getReplicat().keySet();
            Iterator itReplicat = cleReplicat.iterator();
            while(itReplicat.hasNext())  // je parcours la map de replicats
            {
                String cle = (String) itReplicat.next();
                Set cleCpt = compteur.keySet();
                Iterator itCpt = cleCpt.iterator();

                String[][] matrix = {{cle , peer.getReplicat().get(cle)}};
               //System.out.println(peer.getReplicat().get(cle) + " ==  " + cleCp[0][0] );
                if(peer.getReplicat().get(cle).compareTo(String.valueOf(matrix[0])) == 0) //Si le nom du menu est déjà present dans
                // la boucle alors on incrément
                {

                    compteur.put(matrix, compteur.get(cleCpt) + 1);
                }else  //Sinon on rajoute le plat et on met le compteur  à un
                {
                    compteur.put(matrix, 1);
                }

               // System.out.println(cle + " " +  peer.getReplicat().get(cle));


            }
            System.out.println("Paire " + peer.getId());
            peer = peer.getSuccessor();
        }

        System.out.println("Avant la mort = ");
        System.out.println(compteur.size());

        /**
         * Mort d'un pair
         */

        tracker.getRandomPeer().die();


        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        HashMap<String[][], Integer> compteur2 = new HashMap<String[][], Integer>();
        Peer fin2 = tracker.getRandomPeer();
        Peer peer2 = fin.getSuccessor();

        while(!fin2.equals(peer2))  //Je parcours la boucle
        {
            Set cleReplicat = peer2.getReplicat().keySet();
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
                    if(peer2.getReplicat().get(cle).compareTo(cleCp) == 0) //Si le nom du menu est déjà present dans
                    // la boucle alors on incrément
                    {
                        compteur2.put(matrix, compteur2.get(cleCp) + 1);
                    }else  //Sinon on rajoute le plat et on met le compteur  à un
                    {
                        compteur2.put(matrix, 1);
                    }
                }

            }
            peer2 = peer2.getSuccessor();
        }


        if(compteur.size() != compteur2.size())
        {
            System.out.println("La réplication a échoué suite à la mort d'un paire");

        }
        if(compteur.equals(compteur2))
        {
            System.out.println("Réplication correcte, autant de données à la fin qu'au début");
        }else System.err.println("Réplication non opérationnel");

    }

    /**
     * Creates a network composed of NB_PEERS peers.
     *
     * @param tracker The tracker that is going to keep track of the peers
     * @throws RemoteException
     * @throws exceptions.AlreadyRegisteredException If a peer tries to register more
     *                                               than once
     */
    private static void createNetwork(Tracker tracker) throws RemoteException, AlreadyRegisteredException
    {
        for(int i = 0 ; i < NB_PEERS ; i++)
        {
            Peer p = new PeerImpl(new Identifier(i * 100));
            if(i == 0)
            {
                System.out.println("Ring created by " + p.getId());
                p.create();
            }else
            {
                // The new peer is inserted in the network using a random peer
                // that already belongs to the network. This random peer is
                // retrieved thanks to the tracker.
                Peer randomPeer = tracker.getRandomPeer();
                System.out.println("Added " + p.getId() + " from " + randomPeer.getId() + " that points to " +
                                   randomPeer.getSuccessor().getId());
                p.join(randomPeer);
            }

            tracker.register(p);
        }
    }

    /**
     * This method run through the entire Chord network and print each
     * encountered peer.
     *
     * @param landmarkPeer The peer from which the turn starts
     * @throws RemoteException
     */
    private static void turnAround(Peer landmarkPeer) throws RemoteException
    {
        System.out.println("\nStarted turn around from " + landmarkPeer.getId());
        Peer nextPeer = landmarkPeer;

        do
        {
            nextPeer = nextPeer.getSuccessor();
            System.out.println("Visited " + nextPeer.describe());

        }
        while(!nextPeer.equals(landmarkPeer));
    }

}
