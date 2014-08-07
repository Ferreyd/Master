package tp4jeremy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Server extends Remote{
	void method1(int value);
	void method2(MyObject object);
	Server returnServer() throws RemoteException;
}
