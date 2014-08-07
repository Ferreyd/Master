package tp1jeremy;

import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    private String host;
    private int port;

    /**
     *
     * @param host
     * @param port
     */
    public Client(String host, int port){
        this.host = host;
        this.port = port;
    }
    
    //Ouverture du socket vers le serveur
    public void execute()
    {
        Socket client;
        PrintWriter out;
        try
        {
            client = new Socket(this.host, this.port);
            System.out.println("Client Launch");
            out = new PrintWriter    (client.getOutputStream(), true);
            out.println("Petit coucou au serveur");

        }catch(Exception e)
        {
            System.out.println("erreur client " + e);
        }
        
    }
    
    public static void main(String[] args)
    {
        int port = 1337;
        String host = "localhost";

        Client c1 = new Client(host,port);
        c1.execute();
    }
}