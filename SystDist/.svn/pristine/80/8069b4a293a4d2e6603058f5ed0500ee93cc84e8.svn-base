package tp2jeremy;

public class SimpleRaceCondition{
	
	private int c;
	public SimpleRaceCondition(int c){
		this.c = c;
	}
	
	public synchronized void increment(){
		this.c += c ;
	}
	
	public synchronized void decrement(){
		this.c -= c;
	}
	
	public void doSomeWork(){
		Thread t1 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i< 10000; i++){
					increment();
				}	
			}
			
		});
		
		Thread t2 = new Thread(new Runnable(){

			@Override
			public void run() {
				for(int i = 0; i< 10000; i++){
					decrement();
				}	
			}
			
		});
        t1.start();
        t2.start();
		try{
			t1.join();
			t2.join();
		}
		catch(Exception e){
			e.getMessage();
		}
		System.out.println("c = "+ c );
	}

	
	public static void main(String[] args){
		SimpleRaceCondition s = new SimpleRaceCondition(0);
		s.doSomeWork();
	}
	
}
