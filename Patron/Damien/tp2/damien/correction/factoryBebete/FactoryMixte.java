package tp2.damien.correction.factoryBebete;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import tp2.damien.correction.bebetes.Bebete;
import tp2.damien.correction.bebetes.BebeteEmergente;
import tp2.damien.correction.bebetes.BebeteHasard;
import tp2.damien.correction.visu.Champ;

public class FactoryMixte extends BebeteFactory{

	Champ champ;
	
	public FactoryMixte(Champ leChamp){		
		champ = leChamp;		
	}
	
	@Override
	public ArrayList<Bebete> getBebetes(Champ champ, int nb) {
		
		ArrayList<Bebete> nouvBebetes = new ArrayList<Bebete>();
		Random generateur = new Random();
		
		// unicit� des couleurs des b�b�tes, juste l� pour faire joli...
		double racineCubiqueDuNombreDeBebetes = Math.pow((double) nb, 1.0 / 3.0);
		double etapeDeCouleur = (1.0 / racineCubiqueDuNombreDeBebetes);
		float r = 0.0f;
		float g = 0.0f;
		float b = 0.0f;
		for (int i = 0; i < nb; i++) {
			int x = (int) (generateur.nextFloat() * champ.getLargeur());
			
			if (x > champ.getLargeur() - Bebete.TAILLEGRAPHIQUE)
				x -= Bebete.TAILLEGRAPHIQUE;
			
			int y = (int) (generateur.nextFloat() * champ.getHauteur());
			
			if (y > champ.getHauteur() - Bebete.TAILLEGRAPHIQUE)
				y -= Bebete.TAILLEGRAPHIQUE;
			
			double direction =  (generateur.nextFloat() * 2 * Math.PI);
			
			double vitesse = Math.max(2, generateur.nextDouble() * vitesseMax);
			
			r += etapeDeCouleur;
			
			if (r > 1.0) {
				r -= 1.0f;
				g += etapeDeCouleur;
				if (g > 1.0) {
					g -= 1.0f;
					b += etapeDeCouleur;
					if (b > 1.0)
						b -= 1.0f;
				}
			}
			
			// Le fameux de la capture impossible du joker ?
			Bebete b1 = new BebeteEmergente(champ, x, y, direction, vitesse, new Color(r, g, b));
			if(i%2 == 0) b1 = new BebeteHasard(champ, x, y, direction, vitesse, new Color(r, g, b));
			nouvBebetes.add(b1);
		}
		
		return nouvBebetes;
	}

}
