package damien.test.rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject implements Tache {

	protected Server() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int addition(int a, int b) throws RemoteException {
		return a + b;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		try {
			Registry registry = LocateRegistry.createRegistry(1092);
			System.out.println("registry");
			
			Server obj = new Server();
			Naming.rebind("rmi://localhost/Addition", obj);
			System.out.println("serveur pret");
		}
		
		catch(Exception e) {
			System.err.println("Erreur :" + e.getMessage());
		}

	}

}
