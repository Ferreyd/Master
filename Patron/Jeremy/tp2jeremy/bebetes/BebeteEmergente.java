package tp2jeremy.bebetes;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import tp2jeremy.bebetes.strategy.StrategyEmergente;
import tp2jeremy.util.DistancesEtDirections;
import tp2jeremy.visu.Champ;
import tp2jeremy.visu.Dirigeable;
import tp2jeremy.visu.Positionnable;

public class BebeteEmergente extends Bebete {

	public static final double distanceMin = 10f; // En pixels
	
	Dirigeable laPlusProche = null;
	double pasDeCorrection = 5d;
	StrategyEmergente se;

	public BebeteEmergente(Champ c, int x, int y,
			double dC, double vC, Color col) {
		se = new StrategyEmergente();
		champ = c;
		this.x = x;
		this.y = y;
		directionCourante = dC;
		vitesseCourante = vC;
		couleur = col;
		
	}


	@Override
	public void calculeDeplacementAFaire() {
		se.calcule(this);


	}


	public void effectueDeplacement() {
		se.applique(this);
	}



}
