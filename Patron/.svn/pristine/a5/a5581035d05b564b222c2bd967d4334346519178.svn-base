package tp2.damien.correction.strategyadapter;

import java.util.ArrayList;
import java.util.List;

import tp2.damien.correction.bebetes.Bebete;
import tp2.damien.correction.strategybebete.Emergent;
import tp2.damien.correction.strategybebete.Strategy;

import culdesac.ChampDeBebetes;
import culdesac.Etat;
import culdesac.Fuite;
import culdesac.MaBebete;

public class Agoraphobe implements Strategy{
	
	Peureuse peureuse = new Peureuse();
	Emergent emergent = new Emergent();
	
	boolean modePeureuse;
	
	@Override
	public void calcule(Bebete bebete) {
		// TODO Auto-generated method stub
		
		 Fuite f = new Fuite();
		 List<? extends Bebete> listBebete = (List<? extends Bebete>) bebete.getChosesVues();
		 
		 if(listBebete.size() < 10){
			 emergent.calcule(bebete);
			 modePeureuse = false;
		 }
		 else{
			 peureuse.calcule(bebete);
			 modePeureuse = true;
		 }
		 
		
	}

	@Override
	 public void applique(Bebete bebete){
		  
		if(modePeureuse) peureuse.applique(bebete);
		if(!modePeureuse) emergent.applique(bebete);
		  
	  }  

}
