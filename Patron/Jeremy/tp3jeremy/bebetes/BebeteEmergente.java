package tp3jeremy.bebetes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tp3jeremy.util.DistancesEtDirections;
import tp3jeremy.visu.Champ;
import tp3jeremy.visu.Dirigeable;
import tp3jeremy.visu.Positionnable;

public class BebeteEmergente extends Bebete {

	public static final double distanceMin = 10f; // En pixels

	private double distancePlusProche = Double.MAX_VALUE;
	
	Dirigeable laPlusProche = null;
	double pasDeCorrection = 5d;


	public BebeteEmergente(Champ c, int x, int y,
			double dC, double vC, Color col) {
		champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = vC;
		couleur = col;
	}


	@Override
	public void calculeDeplacementAFaire() {
		// calcul des vitesses et directions moyennes, calcul de la distance a la bete la plus proche
		double vit = vitesseCourante;
		double dir = directionCourante;
		double plusPetiteDistance = Double.MAX_VALUE;
		laPlusProche = null;

		// List<? extends Positionnable> lp = getChosesVues();

		List<? extends Dirigeable> betesVues = filtreDirigeables(getChosesVues());
		for (Dirigeable p : betesVues) {
			vit += p.getVitesseCourante();
			dir += p.getDirectionCourante();
			
			double dist = DistancesEtDirections.distanceDepuisUnPoint(this.x, this.y, p.getX(),p.getY());
			if (plusPetiteDistance > dist)
				{
				plusPetiteDistance = dist;
				laPlusProche = p;
				}
		} 
		this.prochaineVitesse = vit / (betesVues.size() + 1);
		this.prochaineDirection = dir / (betesVues.size() + 1);
		this.distancePlusProche = plusPetiteDistance;


	}

	/*
	 * 
	 * si on veut filtrer...
	 * 
	 * pas vraiment de covariance, donc on est oblig� de filtrer la liste pour savoir ce qui remue
	 * de ce qui est potentiellement fixe... 
	 * 
	 */
	protected static List<? extends Dirigeable> filtreDirigeables(List<? extends Positionnable> lp) {
		ArrayList<Dirigeable> output = new ArrayList<Dirigeable>();

		for (Positionnable p : lp) {
			if (p instanceof Dirigeable)
				output.add((Dirigeable)p);
		}
		return output;
	}

	public void effectueDeplacement() {
		
		if (distancePlusProche <= distanceMin) {
			// on commence par prendre de l'�cart par "un pas de cote" 
			double rapport = pasDeCorrection/distancePlusProche ;
			double tmp = getX()+(-laPlusProche.getX() + getX())*rapport;
			setX(tmp) ;
			tmp = getY()+(-laPlusProche.getY() +getY())*rapport;
			setY(tmp);

		}
		
		vitesseCourante = prochaineVitesse;
		directionCourante = prochaineDirection;


			x += (int) (vitesseCourante * Math.cos((double) directionCourante));
			y += (int) (vitesseCourante * Math.sin((double) directionCourante));
			x %= champ.getLargeur();
			y %= champ.getHauteur();
			if (x < 0) {
				x += champ.getLargeur();
			}
			if (y < 0) {
				y += champ.getHauteur();
			}
		
	}



}
