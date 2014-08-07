package tp3nicolas.server;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.AlreadyBoundException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RemoteObject extends UnicastRemoteObject implements IRemote
{

    private static final long serialVersionUID = 1L;

    protected RemoteObject() throws RemoteException
    {
        super();
    }

    @Override
    public void echo() throws java.rmi.RemoteException
    {
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("RemoteObject.echo()");
    }

    @Override
    public void echo(int value) throws RemoteException
    {
        System.out.println("RemoteObject.echo() value=" + value);
    }

    @Override
    public String getRemoteAddress() throws RemoteException
    {
        try
        {
            return InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException e)
        {
            return "Unknown";
        }
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException
    {
        if(System.getSecurityManager() == null)
        {
            System.setSecurityManager(new RMISecurityManager());
        }

        int rmiRegistryPort = 1099;

        // the rmiregistry can either be created programmatically or started
        // manually before each run if you have some time to waste
        Registry registry = LocateRegistry.createRegistry(rmiRegistryPort);
        System.out.println("RMI registry listening on port " + rmiRegistryPort);

        RemoteObject remoteObject = new RemoteObject();
        registry.bind("myremoteobject", remoteObject);
    }

}
