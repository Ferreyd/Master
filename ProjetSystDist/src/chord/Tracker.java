package chord;

import exceptions.AlreadyRegisteredException;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface defines the API of a tracker. It is supposed to keep track
 * of peers that joined and leaved the network.
 */
public interface Tracker extends Remote
{

    /**
     * Register a new peer in the structure maintained by the tracker.
     *
     * @param peer The peer that has just joined the network
     * @throws exceptions.AlreadyRegisteredException If the specified peer is already
     *                                               registered
     * @throws RemoteException
     */
    void register(Peer peer) throws AlreadyRegisteredException, RemoteException;

    /**
     * @return A peer that belongs to the network, randomly
     * @throws RemoteException
     */
    Peer getRandomPeer() throws RemoteException;


    /**
     * Verifie la coherence des pairs
     * Compte le nombre de réplicat et si le nombre de réplicat est inférieur à replicatmax + 1 on remet le replicat
     * perdue dans un nouveau peer
     *
     * @throws RemoteException
     */
    public void verifieCoherence() throws RemoteException;

    /**
     * Trouve un restaurant par rapport à son nom de plat
     *
     * @param daily le plat
     * @return le restaurant
     */
    public String trouveRestaurent(String daily) throws RemoteException;

}
