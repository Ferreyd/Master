package tests;

import application.DataGenerator;
import application.GuideMichelin;
import application.GuideMichelinImpl;
import chord.*;
import exceptions.AlreadyRegisteredException;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Nicolas
 *         Date : 22/01/14 18:01
 */
public class TestCoherence
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

        String str = "";

        Map<String, String> restaurantsTest2 = new HashMap<String, String>();
        restaurantsTest2.put("Le Bistrot Gourmand", "Allouette");
        restaurantsTest2.put("Jean-Francois Issautier", "Poirreaux en sauce");
        restaurantsTest2.put("Le Mas Candille", "Candide sur pavé de saumon");
        restaurantsTest2.put("Le Park 45", "Le 45eme menu");

        System.out.println("Ancien Menus");
        str += "\n" + "/Ajoute des anciens menus\\";
        for(Map.Entry<String, String> restaurant : restaurantsTest2.entrySet())
        {
            System.out.println("Le restaurant : " + restaurant.getKey() + " a pour plat : " + guideMichelin.get
                    (restaurant.getKey()));

        }

        restaurantsTest2 = new HashMap<String, String>();
        restaurantsTest2.put("Chez Lulu", "Courgette crues");
        restaurantsTest2.put("Tata martine", "Potiron aux endives");
        restaurantsTest2.put("La grande gerbe", "Hamberger sauce ketchup");
        restaurantsTest2.put("Tata", "Yoyo");

        System.out.println("\n/***Ajouts des nouveaux menus***\\");


        for(Map.Entry<String, String> restaurant : restaurantsTest2.entrySet())
        {
            guideMichelin.put(restaurant.getKey(), restaurant.getValue());
        }

        System.out.println("\n/***Nouveaux menus stockés dans les pairs***\\");

        for(Map.Entry<String, String> restaurant : restaurantsTest2.entrySet())
        {
            System.out.println("Le restaurant de ma liste " + restaurant.getKey() + " a pour plat dans la guide " +
                               "Michelin :  " + guideMichelin.get(restaurant.getKey()));

        }


        System.out.println();


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
