package tp4damien.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ServerImpl extends UnicastRemoteObject implements Server {

	protected ServerImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void method1(int value) throws RemoteException {
		System.out.println("Invocation method1()");

	}

	@Override
	public void method2(MyObject mo) throws RemoteException {
		System.out.println("Invocation method2()");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
