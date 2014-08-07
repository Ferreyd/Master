package tp2jeremy;

public class Buffer {

	private Integer n;
	
	public synchronized int get(){
		
		// Si le buffer est null, l'appel de get doit mettre le thread en attente (wait())
        while(this.n == null)
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
		// Retourne la valeur contenue dans le buffer puis reveille les threads en attente
		// d'ecriture dans le buffer (notifyAll())
		int resultat = this.n;
		this.n = null;
		this.notifyAll();
		return resultat;
		
	}
	
	public synchronized void put(Integer value){
		// Tant que le buffer est plein, on bloque le thread
		while(this.n != null){
            try
            {
                this.wait();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        }
		// Affecte la valeur value au buffer s'il est vide
		this.n = value;
		
		this.notifyAll();
	}
}
