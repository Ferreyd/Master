package tp2nicolas;

/**
 * @author Nicolas
 *         Date : 30/09/13 09:55
 */
public class Producer extends Thread
{
    private Buffer buffer;
    private int primitif;

    public Producer(Buffer buffer, int primitif)
    {
        this.buffer = buffer;
        this.primitif = primitif;
    }

    public void run()
    {


        for(int i = 0 ; i < 100 ; i++)
        {
            buffer.put(i);
            System.out.println("N° " + i + " Producer  : " + primitif + " Valeur : " + i);
            try
            {
                Thread.sleep((int) (Math.random() * 100));
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }


}


