package tp3nicolas.server;

import java.rmi.Remote;

public interface IRemote extends Remote
{

    void echo() throws java.rmi.RemoteException;

    void echo(int value) throws java.rmi.RemoteException;

    String getRemoteAddress() throws java.rmi.RemoteException;

}
