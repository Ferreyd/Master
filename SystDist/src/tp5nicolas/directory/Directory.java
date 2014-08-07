package tp5nicolas.directory;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Directory extends Remote
{

    void put(String name, String phoneNumber) throws RemoteException;

    String get(String name) throws RemoteException;

}
