package tp2.damien.correction.bebetes.decorateur;

import java.awt.Graphics;

import tp2.damien.correction.bebetes.Bebete;


public class BebeteAvecNom extends DecorateurBebete{
	
	String nom;
	
	public BebeteAvecNom(Bebete labebete, String lenom) {
		super(labebete);
		// TODO Auto-generated constructor stub
		nom = lenom;
	}
	
	@Override
	public void seDessine(Graphics g) {
		
		bebete.seDessine(g);
		g.drawString(nom, (int)bebete.getX(), (int)bebete.getY());
		
	}

	

}
