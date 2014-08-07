package tp2nicolas;

/**
 * The type Simple race condition.
 *
 * @author Nicolas Date : 30/09/13 08:51
 */
public class SimpleRaceCondition implements Runnable
{
    private int c;

    /**
     * Instantiates a new Simple race condition.
     *
     * @param c the c
     */
    public SimpleRaceCondition(int c)
    {
        this.c = c;
    }

    /**
     * Increment void.
     */
    public synchronized void increment()
    {
        this.c += c;
    }

    /**
     * Decrement void.
     */
    public synchronized void decrement()
    {
        this.c -= c;
    }

    /**
     * Do some work.
     */
    public void doSomeWork()
    {
        Thread t1 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0 ; i < 10000 ; i++)
                {
                    SimpleRaceCondition.this.increment();
                }
            }
        });
        Thread t2 = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                for(int i = 0 ; i < 10000 ; i++)
                {
                    SimpleRaceCondition.this.decrement();
                }
            }
        });
        t1.start();
        t2.start();

        try
        {
            t1.join();
            t2.join();
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


        System.out.println("C = " + this.c);
    }

    @Override
    public void run()
    {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public static void main(String args[])
    {
        SimpleRaceCondition sm = new SimpleRaceCondition(0);
        sm.doSomeWork();
    }
}
