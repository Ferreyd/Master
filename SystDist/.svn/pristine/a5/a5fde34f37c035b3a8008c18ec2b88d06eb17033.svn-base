package tp5jeremy.chord;

import java.rmi.Remote;
import java.rmi.RemoteException;

import tp5jeremy.chord.exceptions.AlreadyRegisteredException;

public interface Tracker extends Remote {

    void register(Peer peer) throws AlreadyRegisteredException, RemoteException;

    Peer getRandomPeer() throws RemoteException;

}
