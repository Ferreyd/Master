package tp3jeremy;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObject extends UnicastRemoteObject implements IRemote{

	
	private static final long serialVersionUID = 1L;
	protected RemoteObject() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public void echo() throws java.rmi.RemoteException {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
		System.out.println("Test");
	}
	
    @Override
    public void echo(int value) throws RemoteException {
        System.out.println("Test value=" + value);
    }
    
	public static void main(String[]args) throws RemoteException,
    MalformedURLException, AlreadyBoundException{
		//LocateRegistry.createRegistry(8880);
		 int rmiRegistryPort = 8888;
		 Registry registry = LocateRegistry.createRegistry(rmiRegistryPort);
	     System.out.println("RMI registry listening on port " + rmiRegistryPort);

	     RemoteObject ro = new RemoteObject();
	     registry.bind("myremoteobject", ro);
		
		//ro.echo();
	}
	@Override
	public String getIP() throws RemoteException {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "Unknown";
        }
	}
	
	

}
