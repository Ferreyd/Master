package tp2nicolas;

/**
 * The type Talkative.
 *
 * @author Nicolas          Date : 30/09/13 08:42
 */
public class Talkative implements Runnable
{
    private int entier;

    /**
     * Instantiates a new Talkative.
     *
     * @param entier the entier
     */
    public Talkative(int entier)
    {
        this.entier = entier;
    }

    @Override
    public void run()
    {
        for(int i = 0 ; i < 100 ; i++)
        {
            System.out.println("i = " + i + " entier = " + this.entier);
        }

    }

    /**
     * Main void.
     *
     * @param args the args
     */
    public static void main(String args[])
    {
        Talkative ta = new Talkative(5);

        for(int i = 0 ; i < 10 ; i++)
        {
            Thread t = new Thread(ta);
            t.start();
        }
    }
}
