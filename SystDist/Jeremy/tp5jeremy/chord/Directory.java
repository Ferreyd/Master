package tp5jeremy.chord;

import java.rmi.RemoteException;

public interface Directory {

    void put(String name, String phoneNumber) throws RemoteException;

    String get(String name) throws RemoteException;

}
