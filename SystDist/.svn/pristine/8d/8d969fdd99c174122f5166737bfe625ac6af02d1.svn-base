package tp3nicolas.client;


import tp3nicolas.server.IRemote;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client
{

    public static void main(String[] args)
    {
        try
        {
            IRemote distante = (IRemote) Naming.lookup("rmi://localhost/myremoteobject");

            System.out.println("Client.main() before echo remote call");
            distante.echo();
            System.out.println("Client.main() after echo remote call");

            distante.echo(7);
            System.out.println("Remote object IP: " + distante.getRemoteAddress());
        }
        catch(MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch(RemoteException e)
        {
            e.printStackTrace();
        }
        catch(NotBoundException e)
        {
            e.printStackTrace();
        }
    }

}
