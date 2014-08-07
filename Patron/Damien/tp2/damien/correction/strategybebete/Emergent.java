package tp2.damien.correction.strategybebete;

import java.util.ArrayList;
import java.util.List;

import tp2.damien.correction.bebetes.Bebete;
import tp2.damien.correction.util.DistancesEtDirections;
import tp2.damien.correction.visu.Dirigeable;
import tp2.damien.correction.visu.Positionnable;

public class Emergent implements Strategy{

	Dirigeable laPlusProche = null;
	
	private double distancePlusProche = Double.MAX_VALUE;
	
	public static final double distanceMin = 10f; // En pixels
	
	double pasDeCorrection = 5d;
	
	
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


			bebete.setX(bebete.getX() + (int) (bebete.getVitesseCourante() * Math.cos((double) bebete.getDirectionCourante())));
			bebete.setY(bebete.getY() + (int) (bebete.getVitesseCourante() * Math.sin((double) bebete.getDirectionCourante())));
			bebete.setX(bebete.getX() % bebete.getChamp().getLargeur());
			bebete.setY(bebete.getY() % bebete.getChamp().getHauteur());
			if (bebete.getX() < 0) {
				bebete.setX(bebete.getX() + bebete.getChamp().getLargeur());
			}
			if (bebete.getY() < 0) {
				bebete.setY(bebete.getY() + bebete.getChamp().getHauteur());
			}
		
	}

}
