package tp5nicolas.chord;


import tp5nicolas.chord.exceptions.AlreadyRegisteredException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Tracker extends Remote
{

    void register(Peer peer) throws AlreadyRegisteredException, RemoteException;

    Peer getRandomPeer() throws RemoteException;

}
