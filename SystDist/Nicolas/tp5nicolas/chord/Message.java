package tp5nicolas.chord;


import java.io.Serializable;
import java.rmi.RemoteException;

public interface Message extends Serializable
{

    void handle(Peer peer) throws RemoteException;

}
