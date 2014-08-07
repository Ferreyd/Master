package tp2.damien.correction.factoryBebete;

import java.util.ArrayList;

import tp2.damien.correction.bebetes.Bebete;
import tp2.damien.correction.visu.Champ;

public abstract class  BebeteFactory {
	//Attributs
	public static final double vitesseMax = 10f;
	
	//Methodes
	public abstract ArrayList<Bebete> getBebetes(Champ champ, int nb);
	
	public static BebeteFactory getDefault(Champ champ){
		return new FactoryHasard(champ);
	}
	
	
}
