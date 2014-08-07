package tp2.damien.correction.bebetes.decorateur;

import java.awt.Graphics;

import tp2.damien.correction.bebetes.Bebete;


public class BebeteAvecEcho extends DecorateurBebete{

	int iteration;
	private int r;
	
	public BebeteAvecEcho(Bebete labebete) {
		super(labebete);
		// TODO Auto-generated constructor stub
		iteration=0;
	}
	
	@Override
	public void seDessine(Graphics g) {
		
		
		bebete.seDessine(g);

		g.drawOval((int)bebete.getX() - (r/2), (int)bebete.getY() - (r/2), r, r);
		if(iteration %20 == 0){
			r = r+5;
		}
		if(iteration == 30) iteration = 0;
		if (r > Bebete.TAILLEGRAPHIQUE*3) r=15;
		iteration++;
	}

}
