package tp1nicolas;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Nicolas
 *         Date : 23/09/13 16:35
 */
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

    public void execute()
    {
        Socket client;
        try
        {
            client = new Socket(this.host, this.port);
            BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter pw = new PrintWriter(client.getOutputStream());

            for(int i = 0 ; i < 10 ; i++)
            {
                pw.println("Message " + (i + 1));
                pw.flush();

                // expects one line from the server to ack the message
                String line = br.readLine();

                System.out.println("Serveur accuse réception de: " + line);

                try
                {
                    Thread.sleep(1000);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }


        }
        catch(Exception e)
        {
            System.out.println("erreur client " + e);
        }
        System.out.println("Client Launch");
    }

    public static void main(String[] args)
    {
        int port = 1337;
        String host = "localhost";

        Client c1 = new Client(host, port);

        c1.execute();
    }


}
