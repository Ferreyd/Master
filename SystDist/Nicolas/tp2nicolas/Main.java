package tp2nicolas;

/**
 * @author Nicolas
 *         Date : 06/10/13 21:13
 */
public class Main
{
    public static void main(String[] args)
    {
        Buffer buffer = new Buffer();

        Producer p1 = new Producer(buffer, 1);
        Producer p2 = new Producer(buffer, 2);
        Producer p3 = new Producer(buffer, 3);

        Consumer c1 = new Consumer(buffer, 1);
        Consumer c2 = new Consumer(buffer, 2);
        Consumer c3 = new Consumer(buffer, 3);

        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
        c3.start();
    }

}
