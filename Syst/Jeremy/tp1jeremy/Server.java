package tp1jeremy;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;



public class Server {

	private int port;
	
	public Server(int port){
		this.port = port;
	}

    public void execute()
    {
        ServerSocket server;
        Socket client;

        BufferedReader in = null;
        try
        {
            server = new ServerSocket(1337);
            client = server.accept();

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

        }catch(Exception e)
        {
            System.out.println("Erreur serveur = " + e);
        }

        System.out.println("server launch");
    }
    
    public static void main(String[] args)
    {
        int port = 8088;
        String host = "localhost";

        Server s = new Server(1337);
        s.execute();
    }

}
