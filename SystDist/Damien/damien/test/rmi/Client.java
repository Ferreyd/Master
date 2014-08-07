package damien.test.rmi;

import java.rmi.Naming;

public class Client {

	static int res;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			Tache ref = (Tache)Naming.lookup("rmi://localhost:1092/Addition");
			res = ref.addition(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			System.out.println("La somme est : " + res);			
		}
		
		catch(Exception e) {
			System.err.println("Erreur : " + e.getMessage());
		}

	}

}
