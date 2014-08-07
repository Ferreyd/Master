package tp4jeremy;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server{

	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void method1(int value) {
		System.out.println("ServerImpl.method1()");

	}

	@Override
	public void method2(MyObject object) {
		System.out.println("ServerImpl.method2()");

	}
	
    @Override
    public Server returnServer() throws RemoteException {
        return this;
    }

	public static void main(String[] args) throws RemoteException,
	MalformedURLException, AlreadyBoundException, NotBoundException {
		int rmiRegistryPort = 1099;

		// the rmiregistry can either be created programmatically or started
		// manually before each run if you have some time to waste
		Registry registry = LocateRegistry.createRegistry(rmiRegistryPort);
		System.out.println("RMI registry listening on port " + rmiRegistryPort);

		Server server = new ServerImpl();
		registry.bind("server", server);

		Server serverStub = (Server) Naming.lookup("rmi://localhost/server");

		serverStub.method1(7);
		serverStub.method2(new MyObject());
		//serverStub.method3();
		//serverStub.method4();
		//serverStub.test();

		Server serverReturned = serverStub.returnServer();

		System.out.println("ServerImpl.main() serverReturned has type "
				+ serverReturned.getClass());
	}

}
