package tp5jeremy.chord;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import tp5jeremy.chord.exceptions.AlreadyRegisteredException;

public class Main {

    public static void main(String[] args) throws RemoteException,
            MalformedURLException, AlreadyBoundException,
            AlreadyRegisteredException, NotBoundException, InterruptedException {

        int rmiRegistryPort = 13222;

        new TrackerImpl(rmiRegistryPort);

        final Tracker tracker =
                (Tracker) Naming.lookup("rmi://localhost:" + rmiRegistryPort
                        + "/tracker");

        createPeers(tracker);

        Thread.sleep(5000);

        System.out.println("\nTurn around after stabilization");
        turnAround(tracker.getRandomPeer());

        Peer p = tracker.getRandomPeer();

        System.out.println("\nElection started from " + p.getId());
        p.startElection();

        System.out.println("end");
    }

    private static void createPeers(Tracker tracker) throws RemoteException,
            AlreadyRegisteredException {
        for (int i = 0; i < 5; i++) {
            Peer p = new PeerImpl(new Identifier(i * 100));

            if (i == 0) {
                System.out.println("Ring created by " + p.getId());
                p.create();
            } else {
                Peer randomPeer = tracker.getRandomPeer();
                System.out.println("Added " + p.getId() + " from "
                        + randomPeer.getId() + " that points to "
                        + randomPeer.getSuccessor().getId());

                p.join(randomPeer);
            }

            tracker.register(p);
        }
    }

    private static void turnAround(Peer landmarkPeer) throws RemoteException {
        System.out.println("\nStarted turn around from " + landmarkPeer.getId());

        Peer nextPeer = landmarkPeer;

        do {
            nextPeer = nextPeer.getSuccessor();

            System.out.println("Visited " + nextPeer.getId() + " that has "
                    + nextPeer.getPredecessor().getId()
                    + " as predecessor and " + nextPeer.getSuccessor().getId()
                    + " as successor");
        } while (!nextPeer.equals(landmarkPeer));
    }

}