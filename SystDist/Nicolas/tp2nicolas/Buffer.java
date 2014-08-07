package tp2nicolas;

/**
 * The type Buffer.
 *
 * @author Nicolas          Date : 30/09/13 09:35
 */
public class Buffer
{
    private Integer integer;

    /**
     * Get integer.
     *
     * @return the integer
     */
    public synchronized int get()
    {
        while(this.integer == null)
        {
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        int result = this.integer;
        this.integer = null;

        this.notifyAll();

        return result;
    }

    public synchronized void put(int value)
    {
        while(this.integer != null)
        {
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }

        this.integer = value;

        this.notifyAll();
    }


}
