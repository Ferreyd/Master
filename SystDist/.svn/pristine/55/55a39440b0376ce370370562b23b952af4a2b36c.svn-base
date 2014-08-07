package tp2jeremy;


public class Consumer extends Thread{
	
	private int id;
	private Buffer b;

	public Consumer(Buffer b, int id){
		this.b = b;
		this.id = id;
	}
	/*
	 * 
	 *
	 */
	public void run(){
		int valeur = 0;
		for (int i = 0; i<100 ; i++){
			valeur = b.get();
			System.out.println("N° " + i + " Consumer  : " + id + " Valeur : " + valeur);
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
