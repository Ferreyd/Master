package tp2.damien.correction.bebetes;

import java.awt.Color;

import tp2.damien.correction.strategybebete.Hasard;
import tp2.damien.correction.visu.Champ;

public class BebeteHasard extends Bebete {

	
	public BebeteHasard(Champ c, int x, int y, double dC, double vC, Color col) {
		champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = vC;
		couleur = col;
		strategy = new Hasard();

		prochaineDirection =dC;
		prochaineVitesse = vC;
	}

	/*@Override
	public void calculeDeplacementAFaire() {
		nbTour++;
		nbTour %= nbTourChgt;
		
		// sauf si cela fait "nbTourChgt" iterations, cela ne fait rien. 
		prochaineVitesse = vitesseCourante;
		prochaineDirection = directionCourante;
		
		if (nbTour == 0) { // c'est le moment de changer de direction et de vitesse
			prochaineVitesse = vitesseCourante +
			((Math.random() * 2) - 1);
			if (prochaineVitesse < 3f) {
				prochaineVitesse = 3f;
			} else if (prochaineVitesse > 10f) {
				prochaineVitesse = 10f;
			}

			prochaineDirection =  (prochaineDirection +	(Math.random() * Math.PI / 2) - (Math.PI / 4)) % (Math.PI * 2);

			if (prochaineDirection < 0) {
				prochaineDirection =  (Math.PI * 2) - prochaineDirection;
			}
		}

	}*/

	/*@Override
	public void effectueDeplacement() {
vitesseCourante = prochaineVitesse;
    	
        directionCourante = prochaineDirection;
    	        
        x += (vitesseCourante * Math.cos( directionCourante));
        y += (vitesseCourante * Math.sin( directionCourante));
        
        boolean doubleRebond = ( (x < 0)             && (y < 0)            ) || 
        					   ( (x > champ.getLargeur()) && (y > champ.getHauteur())) ||
        					   ( (x > champ.getLargeur()) && (y <0)             ) ||
        					   ( (x < 0)             && (y > champ.getHauteur()));
        
        if (x < 0) {
            x = -x;
            if ((prochaineDirection >= Math.PI/2) && (prochaineDirection <= 3*Math.PI/2)) 
            	{
            	directionCourante =  (Math.PI - prochaineDirection) ;
                if (directionCourante < 0) directionCourante = Math.PI * 2+directionCourante;
            	}
                                
        } else if (x > champ.getLargeur()) {
            x = 2 * champ.getLargeur() - x;
            if ((prochaineDirection <= Math.PI/2) || (prochaineDirection >= 3*Math.PI/2)) 
        	{
            	directionCourante =  (Math.PI - prochaineDirection) ;
                if (directionCourante < 0) directionCourante = Math.PI * 2+directionCourante;
        	}
                             
        } 
        
        if (y < 0) {
            y = -y;
            if (prochaineDirection > Math.PI ) 
        	{
            	if (doubleRebond) directionCourante =  (Math.PI * 2 - directionCourante);
            	else directionCourante =  (Math.PI * 2 - prochaineDirection);
        	}
        } else if (y > champ.getHauteur()) {
            y = 2 * champ.getHauteur() - y;
            if (prochaineDirection < Math.PI ) 
        	{
            	if (doubleRebond) directionCourante =  (Math.PI * 2 - directionCourante);
            	else directionCourante =  (Math.PI * 2 - prochaineDirection);
        	}
        }
	}*/

	
}
