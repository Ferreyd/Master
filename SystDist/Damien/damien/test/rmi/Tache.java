package damien.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Tache extends Remote {
	
	int addition(int a, int b) throws RemoteException;

}
