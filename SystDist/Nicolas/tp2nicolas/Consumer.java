package tp2nicolas;

/**
 * @author Nicolas
 *         Date : 06/10/13 21:10
 */
public class Consumer
{
    private Buffer buffer;
    private int primitif;

    public Consumer(Buffer buffer, int primitif)
    {
        this.buffer = buffer;
        this.primitif = primitif;
    }

    public void run()
    {
        int valeur = 0;

        for(int i = 0 ; i < 100 ; i++)
        {
            valeur = buffer.get();
            System.out.println("N° " + i + " Consummer : " + primitif + " Valeur : " + valeur);
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


    public void start()
    {
        //To change body of created methods use File | Settings | File Templates.
        run();

    }
}
