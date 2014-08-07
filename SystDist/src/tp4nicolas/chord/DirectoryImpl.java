package tp4nicolas.chord;

import java.rmi.RemoteException;

/**
 * The type Directory impl.
 */
public class DirectoryImpl implements Directory
{

    /**
     * The Tracker.
     */
    private final Tracker tracker;

    /**
     * Instantiates a new Directory impl.
     *
     * @param tracker the tracker
     */
    public DirectoryImpl(Tracker tracker)
    {
        this.tracker = tracker;
    }

    /**
     * Put void.
     *
     * @param name        the name
     * @param phoneNumber the phone number
     * @throws RemoteException the remote exception
     */
    @Override
    public void put(String name, String phoneNumber) throws RemoteException
    {
        this.findIndexer(name).put(name, phoneNumber);
    }

    /**
     * Get string.
     *
     * @param name the name
     * @return the string
     * @throws RemoteException the remote exception
     */
    @Override
    public String get(String name) throws RemoteException
    {
        return this.findIndexer(name).get(name);
    }

    /**
     * Find indexer.
     *
     * @param name the name
     * @return the peer
     * @throws RemoteException the remote exception
     */
    private final Peer findIndexer(String name) throws RemoteException
    {
        return this.tracker.getRandomPeer().findSuccessor(new Key(name));
    }

}
