package tp2jeremy;

public class Producer extends Thread{
	
	private int id;
	private Buffer b;

	public Producer(Buffer b, int id){
		this.b = b;
		this.id = id;
	}
	/*
	 * 
	 *
	 */
	public void run(){
		for (int i = 0; i<100 ; i++){
			b.put(i);
			System.out.println("N° " + i + " Producer  : " + id + " Valeur : " + i);
			// Appel sur le buffer
			//
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
