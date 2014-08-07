package tp2jeremy.bebetes;

import java.awt.Color;

import tp2jeremy.visu.Champ;

public class BebeteHasard extends Bebete {

	public static final int nbTourChgt = 30; // nombre de tours entre chaque changement au hasard de direction et de vitesse
	protected int nbTour; // nombre de tours de la b�b�tes depuis le pr�c�dent changement de direction et de vitesse

	public BebeteHasard(Champ c, int x, int y,
			double dC, double vC, Color col) {
		champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = vC;
		couleur = col;
		nbTour = 0;

		setProchaineDirection(dC);
		setProchaineVitesse(vC);
	}

	@Override
	public void calculeDeplacementAFaire() {
		nbTour++;
		nbTour %= nbTourChgt;
		
		// sauf si cela fait "nbTourChgt" iterations, cela ne fait rien. 
		setProchaineVitesse(vitesseCourante);
		setProchaineDirection(directionCourante);
		
		if (nbTour == 0) { // c'est le moment de changer de direction et de vitesse
			setProchaineVitesse(vitesseCourante +
			((Math.random() * 2) - 1));
			if (getProchaineVitesse() < 3f) {
				setProchaineVitesse(3f);
			} else if (getProchaineVitesse() > 10f) {
				setProchaineVitesse(10f);
			}

			setProchaineDirection((getProchaineDirection() +	(Math.random() * Math.PI / 2) - (Math.PI / 4)) % (Math.PI * 2));

			if (getProchaineDirection() < 0) {
				setProchaineDirection((Math.PI * 2) - getProchaineDirection());
			}
		}

	}

	@Override
	public void effectueDeplacement() {
vitesseCourante = getProchaineVitesse();
    	
        directionCourante = getProchaineDirection();
    	        
        x += (vitesseCourante * Math.cos( directionCourante));
        y += (vitesseCourante * Math.sin( directionCourante));
        
        boolean doubleRebond = ( (x < 0)             && (y < 0)            ) || 
        					   ( (x > champ.getLargeur()) && (y > champ.getHauteur())) ||
        					   ( (x > champ.getLargeur()) && (y <0)             ) ||
        					   ( (x < 0)             && (y > champ.getHauteur()));
        
        if (x < 0) {
            x = -x;
            if ((getProchaineDirection() >= Math.PI/2) && (getProchaineDirection() <= 3*Math.PI/2)) 
            	{
            	directionCourante =  (Math.PI - getProchaineDirection()) ;
                if (directionCourante < 0) directionCourante = Math.PI * 2+directionCourante;
            	}
                                
        } else if (x > champ.getLargeur()) {
            x = 2 * champ.getLargeur() - x;
            if ((getProchaineDirection() <= Math.PI/2) || (getProchaineDirection() >= 3*Math.PI/2)) 
        	{
            	directionCourante =  (Math.PI - getProchaineDirection()) ;
                if (directionCourante < 0) directionCourante = Math.PI * 2+directionCourante;
        	}
                             
        } 
        
        if (y < 0) {
            y = -y;
            if (getProchaineDirection() > Math.PI ) 
        	{
            	if (doubleRebond) directionCourante =  (Math.PI * 2 - directionCourante);
            	else directionCourante =  (Math.PI * 2 - getProchaineDirection());
        	}
        } else if (y > champ.getHauteur()) {
            y = 2 * champ.getHauteur() - y;
            if (getProchaineDirection() < Math.PI ) 
        	{
            	if (doubleRebond) directionCourante =  (Math.PI * 2 - directionCourante);
            	else directionCourante =  (Math.PI * 2 - getProchaineDirection());
        	}
        }
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}
}
