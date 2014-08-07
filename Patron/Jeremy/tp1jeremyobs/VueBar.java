package tp1jeremyobs;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

/** La classe VueBar dessine les barres de l'histogramme
 * @author : moi
 */
public class VueBar implements IVueTableau{

	@Override
	public void dessine(Component c, Graphics g, ITableauEntier modele) {
		// TODO Auto-generated method stub
		
		int nbElem = modele.getLongueur();
		//largeur de la barre
		int w = c.getWidth()/nbElem;
		
		//largeur du tableau
		int width = c.getWidth();
		//hauteur du tableau
		int height = c.getHeight();
		
		double rapport = (double) height/(double) modele.getMaximum();
		
		for(int i = 0; i < nbElem ; i++){
			int h = (int) (rapport * ((double) modele.getValeur(i)));
			if(i % 2 == 0) g.setColor(Color.blue);
			else g.setColor(Color.gray);
			g.fillRect(i*w, height - h, w, h);
		}
		
	}

}
