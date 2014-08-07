package tp2jeremy.bebetes.factory;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tp2jeremy.bebetes.Bebete;
import tp2jeremy.bebetes.BebeteHasard;
import tp2jeremy.bebetes.ChampDeBebetes;

public class MixteFactory extends BebeteFactory{
	private ChampDeBebetes champ;

	public MixteFactory(ChampDeBebetes champ){
		this.champ = champ;
	}
	
	@Override
	public Bebete getBebete() {
		/*Random generateur = new Random();	
		int x = (int) (generateur.nextFloat() * champ.getLargeur());
		if (x > champ.getLargeur() - Bebete.TAILLEGRAPHIQUE)
			x -= Bebete.TAILLEGRAPHIQUE;
		int y = (int) (generateur.nextFloat() * champ.getHauteur());
		if (y > champ.getHauteur() - Bebete.TAILLEGRAPHIQUE)
			y -= Bebete.TAILLEGRAPHIQUE;
		double direction =  (generateur.nextFloat() * 2 * Math.PI);
		double vitesse = Math.max(2, generateur.nextDouble() * champ.vitesseMax);
		
		return new BebeteHasard(champ, x, y, direction, vitesse,
				new Color(0, 0, 255));*/
		return null;
	}

	@Override
	public ArrayList<Bebete> getBebetes(int n) {
		
		return null;
	}
}
