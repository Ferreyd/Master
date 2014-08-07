package tp2nicolas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Nicolas
 *         Date : 06/10/13 21:45
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
}
