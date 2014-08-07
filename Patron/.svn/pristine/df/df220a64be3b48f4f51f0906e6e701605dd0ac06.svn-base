package tp2.damien.correction.bebetes;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tp2.damien.correction.bebetes.decorateur.BebeteAvecEcho;
import tp2.damien.correction.bebetes.decorateur.BebeteAvecNom;
import tp2.damien.correction.factoryBebete.BebeteFactory;
import tp2.damien.correction.factoryBebete.FactoryEmergente;
import tp2.damien.correction.factoryBebete.FactoryHasard;
import tp2.damien.correction.factoryBebete.FactoryMixte;
import tp2.damien.correction.simu.Actionnable;
import tp2.damien.correction.simu.Simulateur;
import tp2.damien.correction.visu.Dessinable;
import tp2.damien.correction.visu.Positionnable;
import tp2.damien.correction.visu.VisualisateurAnime;


public class ChampDeBebetes extends VisualisateurAnime {
	private static final long serialVersionUID = 5823608240299297297L;

	BebeteFactory fabrique;

	protected Simulateur simu; // Mariage de convenance !!!

	private int nb;

	public ChampDeBebetes(int largeur, int hauteur, int nb) {
		super(largeur,hauteur);
		setPreferredSize(new Dimension(largeur, hauteur));
		
		//Initialisation de la fabrique par default+ Choix
		fabrique = BebeteFactory.getDefault(this);
		
		this.nb=nb;
		
		
		// Initialisation du mariage de convenance avec le simulateur
		simu = new Simulateur(50);
		peupler();
		
		
	}
	
	/*Changer la fabrique*/
	public void setFabrique(BebeteFactory laFabrique){
		this.fabrique = laFabrique;
		peupler();
	}
	
	protected void peupler()
	{
		List <? extends Bebete> lb = fabriqueBebetes(nb);
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
		
		nouvBebetes = fabrique.getBebetes(this, nb);
		
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
