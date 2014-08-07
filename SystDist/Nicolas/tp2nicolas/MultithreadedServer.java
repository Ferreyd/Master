package tp2nicolas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * The type Server.
 *
 * @author Nicolas Date : 23/09/13 15:46
 */
public class MultithreadedServer
{
    // port number to listen on
    private final int port;

    public MultithreadedServer(int port)
    {
        this.port = port;
    }

    public void execute()
    {
        ServerSocket socket = null;
        Socket socketClient = null;

        try
        {
            socket = new ServerSocket(this.port);

            // this socket is a client stub
            System.out.println("Server waiting for a new connection on port " + this.port + " ...");

            // waits for at most 3 different connections and handle
            // each one in a different thread
            for(int i = 0 ; i < 3 ; i++)
            {
                socketClient = socket.accept();

                System.out.println("New client connected on server from " + socketClient.getInetAddress() + " with " +
                                   "port " + socketClient.getPort());

                new Thread(new ClientManager(socketClient)).start();
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if(socket != null)
                {
                    socket.close();
                }

                if(socketClient != null)
                {
                    socketClient.close();
                }
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args)
    {
        if(args.length != 1)
        {
            System.err.println("usage: java " + MultithreadedServer.class.getCanonicalName() + " serverPort");
            System.exit(1);
        }

        // On unix systems you can check that the server is running
        // by executing the following command:
        // lsof -Pi | grep 9999
        try
        {
            new MultithreadedServer(Integer.parseInt(args[0])).execute();
        }
        catch(NumberFormatException e)
        {
            throw new IllegalArgumentException("Invalid port number: " + args[0]);
        }
    }

}
