package tp4nicolas.chord;

import tp4nicolas.chord.exceptions.AlreadyRegisteredException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Tracker extends Remote
{

    void register(Peer peer) throws AlreadyRegisteredException, RemoteException;

    Peer getRandomPeer() throws RemoteException;

}
