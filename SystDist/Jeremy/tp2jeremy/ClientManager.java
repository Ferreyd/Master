package tp2jeremy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Jeremy
 *         Date : 07/10/2013
 */
public class ClientManager implements Runnable
{
    Socket socket;

    public ClientManager(Socket socket)
    {
        this.socket = socket;
    }

    public void manage() throws IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));

        PrintWriter pw = new PrintWriter(this.socket.getOutputStream());

        String line;
        // br.readLine blocks until a message is received
        while((line = br.readLine()) != null)
        {
            if(line.equalsIgnoreCase("stop"))
            {
                break;
            }

            System.out.println("Server received: " + line);

            pw.println(line);
            pw.flush();
        }
    }


    public void run()
    {
        try
        {
            this.manage();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws UnknownHostException, IOException
    {
    	   
    	        /*if(args.length != 1)
    	        {
    	            System.err.println("usage: java " + ClientManager.class.getCanonicalName() + " serverPort");
    	            System.exit(1);
    	        }*/

    	  
    	        try
    	        {
    	           ClientManager c =  new ClientManager(new Socket("localhost", 1337));
    	           c.manage();
    	           
    	        }
    	        catch(NumberFormatException e)
    	        {
    	            throw new IllegalArgumentException("Invalid port number: " + args[0]);
    	        }
    	    
    }
}
