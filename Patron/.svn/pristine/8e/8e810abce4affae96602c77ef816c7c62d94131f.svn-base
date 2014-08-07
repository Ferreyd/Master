package tp2jeremy.bebetes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tp2jeremy.bebetes.factory.BebeteFactory;
import tp2jeremy.bebetes.factory.EmergenteFactory;
import tp2jeremy.simu.Actionnable;
import tp2jeremy.simu.Simulateur;
import tp2jeremy.visu.Dessinable;
import tp2jeremy.visu.Positionnable;
import tp2jeremy.visu.VisualisateurAnime;


public class ChampDeBebetes extends VisualisateurAnime {
	private static final long serialVersionUID = 5823608240299297297L;

	public static final double vitesseMax = 10f;

	protected Simulateur simu; // Mariage de convenance !!!

	public ChampDeBebetes(int largeur, int hauteur, int nb) {
		super(largeur,hauteur);
		setPreferredSize(new Dimension(largeur, hauteur));
		List <? extends Bebete> lb = fabriqueBebetes(nb);
		// Initialisation du mariage de convenance avec le simulateur
		simu = new Simulateur(50,lb);
		setDessinables(lb);
	}

	/* Red�finitions pour synchroniser la gestion des 2 threads */

	public void demarre() {
		// on d�marre d'abord la simulation
		simu.demarre();
		super.demarre();
	}

	public void arrete() {
		// on arr�te d'abord la visualisation
		super.arrete();
		simu.arrete();
	}

	public ArrayList<? extends Bebete> fabriqueBebetes(int nb) {
		ArrayList<Bebete> nouvBebetes = new ArrayList<Bebete>();
		/*Random generateur = new Random();
		// unicit� des couleurs des b�b�tes, juste l� pour faire joli...
		double racineCubiqueDuNombreDeBebetes = Math.pow((double) nb, 1.0 / 3.0);
		double etapeDeCouleur = (1.0 / racineCubiqueDuNombreDeBebetes);
		float r = 0.0f;
		float g = 0.0f;
		float b = 0.0f;
		for (int i = 0; i < nb; i++) {
			int x = (int) (generateur.nextFloat() * largeur);
			if (x > largeur - Bebete.TAILLEGRAPHIQUE)
				x -= Bebete.TAILLEGRAPHIQUE;
			int y = (int) (generateur.nextFloat() * hauteur);
			if (y > hauteur - Bebete.TAILLEGRAPHIQUE)
				y -= Bebete.TAILLEGRAPHIQUE;
			double direction =  (generateur.nextFloat() * 2 * Math.PI);
			double vitesse = Math.max(2, generateur.nextDouble() * vitesseMax);
			r += etapeDeCouleur;
			if (r > 1.0) {
				r -= 1.0f;
				g += etapeDeCouleur;
				if (g > 1.0) {
					g -= 1.0f;
					b += etapeDeCouleur;
					if (b > 1.0)
						b -= 1.0f;
				}
			}
			// Le fameux de la capture impossible du joker ?
			nouvBebetes.add(new BebeteEmergente(this, x, y, direction, vitesse,
					new Color(r, g, b)));
		}*/
		BebeteFactory bf = BebeteFactory.getDefaultFactory(this);
		for(int i=0; i< nb; i++){
			nouvBebetes.add(bf.getBebete());
		}
		return nouvBebetes;
		
	}

	public int getNombreDeBebetes() {
		return getPositionnables().size();
	}

	public int getDelaiSimulation() { // D�l�gation
		return simu.getDelaiSimulation();
	}

	public void setDelaiSimulation(int delaiSimu) { // D�l�gation
		simu.setDelaiSimulation(delaiSimu);
	}

	public void nextStep()
	{
		simu.iteration();
	}
	
	@Override
	public void setDessinables(List<? extends Dessinable> dessinables) {
		super.setDessinables(dessinables);
		
		// a cause des deux mariages : une liste pour deux...
		// a condition qu'elles soient compatibles...
		ArrayList<Actionnable> output = new ArrayList<Actionnable>();

		if (dessinables != null) 
			{
			for (Positionnable p : dessinables) {
				if (p instanceof Actionnable)
					output.add((Actionnable)p);
			}
			simu.setActionnables(output);
			}
			
	
	}

}
