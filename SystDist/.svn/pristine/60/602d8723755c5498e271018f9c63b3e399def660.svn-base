package tp5jeremy.chord;

import java.rmi.RemoteException;

public class DirectoryImpl implements Directory {

    private final Tracker tracker;

    public DirectoryImpl(Tracker tracker) {
        this.tracker = tracker;
    }

    @Override
    public void put(String name, String phoneNumber) throws RemoteException {
        this.findIndexer(name).put(name, phoneNumber);
    }

    @Override
    public String get(String name) throws RemoteException {
        return this.findIndexer(name).get(name);
    }

    private final Peer findIndexer(String name) throws RemoteException {
        return this.tracker.getRandomPeer().findSuccessor(new Key(name));
    }

}
