package tp1jeremy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client
{
    private String host;
    private int port;

    /**
     * @param host
     * @param port
     */
    public Client(String host, int port)
    {
        this.host = host;
        this.port = port;
    }

    //Ouverture du socket vers le serveur
    public void execute()
    {
        Socket client;
        BufferedReader in;
        PrintWriter out;
        try
        {
            client = new Socket(this.host, this.port);
            System.out.println("Client Launch");
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream());
            String str = "";
            for(int i = 0 ; i < 10 ; i++)
            {
                out.println("Message " + (i + 1));
                out.flush();
                str = in.readLine();
                System.out.println(str);
            }
            System.out.println("stop");
            out.println("stop");
            in.close();
            out.close();
            client.close();
        }
        catch(Exception e)
        {
            System.out.println("erreur client " + e);
        }

    }

    public static void main(String[] args)
    {
        int port = 8088;
        String host = "localhost";

        Client c1 = new Client(host, port);
        c1.execute();
    }
}