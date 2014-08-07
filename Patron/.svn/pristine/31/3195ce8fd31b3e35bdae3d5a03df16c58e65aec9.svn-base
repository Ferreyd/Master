package tp2jeremy.bebetes.strategy;

import java.util.ArrayList;
import java.util.List;

import tp2jeremy.bebetes.Bebete;
import tp2jeremy.util.DistancesEtDirections;
import tp2jeremy.visu.Dirigeable;
import tp2jeremy.visu.Positionnable;


public class StrategyEmergente implements Strategy{
	
	public static final double distanceMin = 10f; // En pixels
	private double distancePlusProche = Double.MAX_VALUE;
	double pasDeCorrection = 5d;
	
	Dirigeable laPlusProche = null;
	@Override
	public void applique(Bebete bebete) {
		
		if (distancePlusProche <= distanceMin) {
			// on commence par prendre de l'�cart par "un pas de cote" 
			double rapport = pasDeCorrection/distancePlusProche ;
			double tmp = bebete.getX()+(-laPlusProche.getX() + bebete.getX())*rapport;
			bebete.setX(tmp) ;
			tmp = bebete.getY()+(-laPlusProche.getY() +bebete.getY())*rapport;
			bebete.setY(tmp);

		}
		
		bebete.setVitesseCourante(bebete.getProchaineVitesse());
		bebete.setDirectionCourante(bebete.getProchaineDirection());


			double x = bebete.getX();
			double y = bebete.getY();
			
			x= x + (int) (bebete.getVitesseCourante() * Math.cos((double) bebete.getDirectionCourante()));
			y = y+ (bebete.getVitesseCourante() * Math.sin((double) bebete.getDirectionCourante()));
			//y += (int) (bebete.getVitesseCourante() * Math.sin((double) bebete.getDirectionCourante()));
			
			x = x % bebete.getChamp().getLargeur();
		
			y = y % bebete.getChamp().getHauteur();
			
			if (x < 0) {
				x = x + bebete.getChamp().getLargeur();
			}
			if (y < 0) {
				y = y + bebete.getChamp().getHauteur();
			}
			bebete.setX(x);
			bebete.setY(y);
		
	}

	@Override
	public void calcule(Bebete bebete) {
		
		// calcul des vitesses et directions moyennes, calcul de la distance a la bete la plus proche
		double vit = bebete.getVitesseCourante();
		double dir = bebete.getDirectionCourante();
		double plusPetiteDistance = Double.MAX_VALUE;
		laPlusProche = null;

		// List<? extends Positionnable> lp = getChosesVues();

		List<? extends Dirigeable> betesVues = filtreDirigeables(bebete.getChosesVues());
		for (Dirigeable p : betesVues) {
			vit += p.getVitesseCourante();
			dir += p.getDirectionCourante();
			
			double dist = DistancesEtDirections.distanceDepuisUnPoint(bebete.getX(), bebete.getY(), p.getX(),p.getY());
			if (plusPetiteDistance > dist)
				{
				plusPetiteDistance = dist;
				laPlusProche = p;
				}
		} 
		bebete.setProchaineVitesse(vit / (betesVues.size() + 1));
		bebete.setProchaineDirection(dir / (betesVues.size() + 1));
		this.distancePlusProche = plusPetiteDistance;


		
	}
	
	protected static List<? extends Dirigeable> filtreDirigeables(List<? extends Positionnable> lp) {
		ArrayList<Dirigeable> output = new ArrayList<Dirigeable>();

		for (Positionnable p : lp) {
			if (p instanceof Dirigeable)
				output.add((Dirigeable)p);
		}
		return output;
	}

}
