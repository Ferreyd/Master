package tp1jeremy;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server
{

    private int port;

    public Server(int port)
    {
        this.port = port;
    }

    public void execute()
    {
        ServerSocket server;
        Socket client;

        BufferedReader in;
        PrintWriter out;
        try
        {
            server = new ServerSocket(port);
            System.out.println("Le serveur est lance et ecoute sur le port  " + this.port + " ...");
            client = server.accept();
            System.out.println("Client connecte venant de" + client.getInetAddress() + " utilisant le port :  " +
                               client.getPort());

            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream());

            while(true)
            {
                String str = in.readLine();
                if(str.equals("stop")) break;
                System.out.println("affiche : " + str);

                out.println("Serveur accuse reception de " + str);
                out.flush();
            }

            in.close();
            out.close();
            client.close();

        }
        catch(Exception e)
        {
            System.out.println("Erreur serveur = " + e);
        }


    }

    public static void main(String[] args)
    {
        int port = 8088;
        String host = "localhost";

        Server s = new Server(port);
        s.execute();
    }

}
