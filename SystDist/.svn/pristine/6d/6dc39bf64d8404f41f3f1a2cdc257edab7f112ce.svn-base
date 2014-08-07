package tp2jeremy;

public class Talkative implements Runnable{

	private int n;
	
	public Talkative(int n){
		this.n = n;
	}

	@Override
	public void run() {
		for (int i = 0 ; i<100 ; i++)
			System.out.println("i = " + i + " entier = " + this.n);
	}
	/*Les threads ne s'executent pas en même temps*/
	public static void main(String[] args){
		Talkative talk = new Talkative(25);
		for (int j = 0; j<10; j++){
			Thread t = new Thread(talk);
			t.start();
		}
	}
	
}
