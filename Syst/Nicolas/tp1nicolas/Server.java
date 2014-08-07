package tp1nicolas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * The type Server.
 * 
 * @author Nicolas Date : 23/09/13 15:46
 */
public class Server
{
	/**
     *
     */
	private int port;

	/**
	 * Instantiates a new Server.
	 * 
	 * @param port
	 *            the port
	 */
	public Server(int port)
	{
		this.port = port;
	}

	/**
	 * Gets port.
	 * 
	 * @return the port
	 */
	public int getPort()
	{
		return port;
	}

	/**
	 * Sets port.
	 * 
	 * @param port
	 *            the port
	 */
	public void setPort(int port)
	{
		this.port = port;
	}

	/**
	 * Execute le serveur
	 */
	public void execute()
	{
		ServerSocket server;
		Socket client;

		BufferedReader in = null;
		try
		{
			server = new ServerSocket(1337);
			System.out.println("Le serveur est lance et ecoute sur le port  "
			        + this.port + " ...");
			client = server.accept();

			System.out.println("Client connecte venant de"
			        + client.getInetAddress() + " utilisant le port :  "
			        + client.getPort());
			client = server.accept();
			messageBienvenue(client);

			in = new BufferedReader(new InputStreamReader(
			        client.getInputStream())); // je prend le stream du client
											   // et je le met dans le lecteur
											   // de stream

			System.out.println("affiche : " + in);

		}
		catch (Exception e)
		{
			System.out.println("Erreur serveur = " + e);
		}

		System.out.println("server launch");
	}

	/**
	 * Envoie un message de bienvenue au client
	 * 
	 * @param client
	 * @throws Exception
	 */
	private void messageBienvenue(Socket client) throws Exception
	{
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
		        client.getOutputStream()));

		writer.write("Connnexion au serveur reussi.\n");
		writer.flush();

	}

	/**
	 * The entry point of application.
	 * 
	 * @param args
	 *            the input arguments
	 */
	public static void main(String[] args)
	{
		Server s = new Server(1337);
		s.execute();
	}

}
