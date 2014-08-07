package tp4nicolas.chord;

import tp4nicolas.chord.exceptions.AlreadyRegisteredException;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main
{

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException,
            AlreadyRegisteredException, NotBoundException, InterruptedException
    {

        int rmiRegistryPort = 13222;

        new TrackerImpl(rmiRegistryPort);

        Tracker tracker = (Tracker) Naming.lookup("//localhost:" + rmiRegistryPort + "/tracker");

        createPeers(tracker);

        Thread.sleep(2000);
        System.out.println("\nTurn around after first stabilization");
        turnAround(tracker.getRandomPeer());

        Thread.sleep(2000);
        System.out.println("\nTurn around after second stabilization");
        turnAround(tracker.getRandomPeer());

        Thread.sleep(6000);
        System.out.println("\nTurn around after third stabilization");
        turnAround(tracker.getRandomPeer());

        String name = "John Doe";
        String phoneNumber = "0123456789";

        Directory directory = new DirectoryImpl(tracker);
        directory.put(name, phoneNumber);

        System.out.println("\nPhone number found for name '" + name + "': " + directory.get(name));
    }

    private static void createPeers(Tracker tracker) throws RemoteException, AlreadyRegisteredException
    {
        for(int i = 0 ; i < 5 ; i++)
        {
            Peer p = new PeerImpl(new Identifier(i * 100));

            if(i == 0)
            {
                System.out.println("Ring created by " + p.getId());
                p.create();
            }else
            {
                Peer randomPeer = tracker.getRandomPeer();
                System.out.println("Added " + p.getId() + " from " + randomPeer.getId() + " that points to " +
                                   randomPeer.getSuccessor().getId());

                p.join(randomPeer);
            }

            tracker.register(p);
        }
    }

    private static void turnAround(Peer landmarkPeer) throws RemoteException
    {
        System.out.println("\nStarted turn around from " + landmarkPeer.getId());

        Peer nextPeer = landmarkPeer;

        do
        {
            nextPeer = nextPeer.getSuccessor();

            System.out.println("Visited " + nextPeer.getId() + " that has " + nextPeer.getPredecessor().getId() + " " +
                               "as predecessor and " + nextPeer.getSuccessor().getId() + " as successor");
        }
        while(!nextPeer.equals(landmarkPeer));
    }

}
