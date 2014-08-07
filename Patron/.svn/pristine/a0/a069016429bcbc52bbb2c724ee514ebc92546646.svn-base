package tp2.damien.correction.strategybebete;

import tp2.damien.correction.bebetes.Bebete;

public class Hasard implements Strategy {

	public static final int nbTourChgt = 30; // nombre de tours entre chaque changement au hasard de direction et de vitesse
	protected int nbTour=0; // nombre de tours de la b�b�tes depuis le pr�c�dent changement de direction et de vitesse

	
	@Override
	public void calcule(Bebete bebete) {
		nbTour++;
		nbTour %= nbTourChgt;
		
		// sauf si cela fait "nbTourChgt" iterations, cela ne fait rien. 
		bebete.setProchaineVitesse(bebete.getVitesseCourante());
		bebete.setProchaineDirection(bebete.getDirectionCourante());
		
		if (nbTour == 0) { // c'est le moment de changer de direction et de vitesse
			bebete.setProchaineVitesse(bebete.getVitesseCourante() +
			((Math.random() * 2) - 1));
			if (bebete.getProchaineVitesse() < 3f) {
				bebete.setProchaineVitesse(3f);
			} else if (bebete.getProchaineVitesse() > 10f) {
				bebete.setProchaineVitesse(10f);
			}

			bebete.setProchaineDirection( (bebete.getProchaineDirection() +	(Math.random() * Math.PI / 2) - (Math.PI / 4)) % (Math.PI * 2));

			if (bebete.getProchaineDirection() < 0) {
				bebete.setProchaineDirection( (Math.PI * 2) - bebete.getProchaineDirection());
			}
		}
		
	}

	@Override
	public void applique(Bebete bebete) {
		
		bebete.setVitesseCourante(bebete.getProchaineVitesse());
    	
		bebete.setDirectionCourante(bebete.getProchaineDirection());
    	        
		bebete.setX(bebete.getX() + (bebete.getVitesseCourante() * Math.cos( bebete.getDirectionCourante())));
		bebete.setY(bebete.getY() + (bebete.getVitesseCourante() * Math.sin( bebete.getDirectionCourante())));
        
        boolean doubleRebond = ( (bebete.getX() < 0)             && (bebete.getY() < 0)            ) || 
        					   ( (bebete.getX() > bebete.getChamp().getLargeur()) && (bebete.getY() >  bebete.getChamp().getHauteur())) ||
        					   ( (bebete.getX() >  bebete.getChamp().getLargeur()) && (bebete.getY() <0)             ) ||
        					   ( (bebete.getX() < 0)             && (bebete.getY() >  bebete.getChamp().getHauteur()));
        
        if (bebete.getX() < 0) {
        	bebete.setX( -(bebete.getX()));
            if ((bebete.getProchaineDirection() >= Math.PI/2) && (bebete.getProchaineDirection() <= 3*Math.PI/2)) 
            	{
            	bebete.setDirectionCourante( (Math.PI - bebete.getProchaineDirection())) ;
                if (bebete.getDirectionCourante() < 0) bebete.setDirectionCourante( Math.PI * 2+bebete.getDirectionCourante());
            	}
                                
        } else if (bebete.getX() > bebete.getChamp().getLargeur()) {
            bebete.setX( 2 * bebete.getChamp().getLargeur() - bebete.getX());
            if ((bebete.getProchaineDirection() <= Math.PI/2) || (bebete.getProchaineDirection() >= 3*Math.PI/2)) 
        	{
            	bebete.setDirectionCourante( (Math.PI - bebete.getProchaineDirection())) ;
                if (bebete.getDirectionCourante() < 0) bebete.setDirectionCourante( Math.PI * 2+bebete.getDirectionCourante());
        	}
                             
        } 
        
        if (bebete.getY() < 0) {
        	bebete.setY( -( bebete.getY()));
            if (bebete.getProchaineDirection() > Math.PI ) 
        	{
            	if (doubleRebond) bebete.setDirectionCourante(  (Math.PI * 2 - bebete.getDirectionCourante()));
            	else bebete.setDirectionCourante( (Math.PI * 2 - bebete.getProchaineDirection()));
        	}
        } else if (bebete.getY() > bebete.getChamp().getHauteur()) {
        	bebete.setY( 2 * bebete.getChamp().getHauteur() - bebete.getY());
            if (bebete.getProchaineDirection() < Math.PI ) 
        	{
            	if (doubleRebond) bebete.setDirectionCourante(  (Math.PI * 2 - bebete.getDirectionCourante()));
            	else bebete.setDirectionCourante(  (Math.PI * 2 - bebete.getProchaineDirection()));
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
