package tp2.damien.correction.strategyadapter;
import java.util.ArrayList;
import java.util.List;

import tp2.damien.correction.bebetes.Bebete;
import tp2.damien.correction.strategybebete.Strategy;
import tp2.damien.correction.visu.Positionnable;
import culdesac.ChampDeBebetes;
import culdesac.Etat;
import culdesac.Fuite;
import culdesac.MaBebete;


public class Peureuse implements Strategy{
	Etat prochainEtat;
	
	 public void calcule(Bebete bebete){
		 Fuite f = new Fuite();
		 List<? extends Bebete> listBebete = (List<? extends Bebete>) bebete.getChosesVues();
		 ArrayList<MaBebete> listMaBebete = new ArrayList<MaBebete>();
		 
		 culdesac.ChampDeBebetes champ = new ChampDeBebetes(bebete.getChamp().getLargeur(), bebete.getChamp().getHauteur());
		 
		 for(Bebete p : listBebete){
			 listMaBebete.add(new MaBebete(champ, p.getX(), p.getY(), p.getDirectionCourante(), p.getVitesseCourante(), p.getCouleur()));
		 }
		 
		 Etat etatCourant = new Etat();
		 etatCourant.x = bebete.getX();
		 etatCourant.y = bebete.getY();
		 etatCourant.vitesse = bebete.getVitesseCourante();
		 etatCourant.direction = bebete.getDirectionCourante();
				 
		 prochainEtat = f.agit(listMaBebete, etatCourant, null, 20f, null);
	 } 
	  
	  public void applique(Bebete bebete){
		  
		  bebete.setX(prochainEtat.x);	
		  bebete.setY(prochainEtat.y);	
		  bebete.setVitesseCourante((prochainEtat.vitesse));	
		  bebete.setDirectionCourante((prochainEtat.direction));	
		 
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