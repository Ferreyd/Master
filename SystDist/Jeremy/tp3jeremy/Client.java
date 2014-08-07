package tp3jeremy;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

	public static void main(String[] args){
		IRemote r;
		try {
			r = (IRemote)Naming.lookup("rmi://localhost:8888/myremoteobject");
			System.out.println("Avant appel echo client");
			r.echo();
			System.out.println("Apres appel echo client");
			r.echo(42);
			System.out.println("IP où se trouve l'objet distant : "+ r.getIP());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
