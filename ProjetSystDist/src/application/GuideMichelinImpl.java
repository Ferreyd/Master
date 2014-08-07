package application;

import chord.Key;
import chord.Peer;
import chord.Tracker;

import java.rmi.RemoteException;


/**
 * This implementation of the {@link GuideMichelin} stores its entries in a
 * distributed manner. It uses the Chord peer-to-peer network to distribute
 * storage and to retrieve content.
 */
public class GuideMichelinImpl implements GuideMichelin
{

    /**
     * Tracker used to locate the peers that store the entries
     */
    private final Tracker tracker;


    public GuideMichelinImpl(Tracker tracker)
    {
        this.tracker = tracker;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void put(String restaurant, String dailySpecial) throws RemoteException
    {
        // Storing an entry in our peer-to-peer network simply consist in
        // asking the peer that is responsible for the restaurant key to store
        // the couple (restaurant, daily special)
        this.findIndexer(restaurant).put(restaurant, dailySpecial);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String get(String restaurant) throws RemoteException
    {
        // Getting a content in our peer-to-peer network simply consist in
        // asking the peer that is responsible for the restaurant key to return
        // its associated value
        return this.findIndexer(restaurant).get(restaurant);
    }

    /**
     * Stores a new entry (or an updated entry) in the GuideMichelin with a replica on r successor
     *
     * @param restaurant   Name of the restaurant to list in the GuideMichelin
     * @param dailySpecial Name of the daily special that offers the dish
     * @param r            nombre de sucesseur du pair
     * @throws java.rmi.RemoteException
     */
    @Override
    public void put(String restaurant, String dailySpecial, int r) throws RemoteException
    {
        this.findIndexer(restaurant).put(restaurant, dailySpecial);
    }

    /**
     * Locates the peer that must store a content whose key is the restaurant
     * name.
     *
     * @param restaurant
     * @return The peer that is responsible for this key
     * @throws RemoteException
     */
    private final Peer findIndexer(String restaurant) throws RemoteException
    {
        return this.tracker.getRandomPeer().findSuccessor(new Key(restaurant));
    }

    /**
     * Retourne un pair en fonction de son nom de restautant
     *
     * @param restaurant
     * @return
     * @throws RemoteException
     */
    @Override
    public Peer getPeer(String restaurant) throws RemoteException
    {
        Peer peer = this.tracker.getRandomPeer();
        if(peer.get(restaurant) != null)
        {
            return peer;
        }else if(peer.getReplicat(restaurant) != null)
        {
            return peer;
        }else return peer.findSuccessor(new Key(restaurant));
    }


}
