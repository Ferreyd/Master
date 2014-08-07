package tp2damien.decorateurs;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import tp2damien.bebetes.Bebete;
import tp2damien.visu.Champ;
import tp2damien.visu.Positionnable;

public class DecorateurBebete extends Bebete {
	
	public DecorateurBebete(Bebete bebete) {
		super();
		this.bebete = bebete;
	}

	protected Bebete bebete;

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return bebete.getX();
	}

	@Override
	public void setX(double x) {
		// TODO Auto-generated method stub
		bebete.setX(x);
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return bebete.getY();
	}

	@Override
	public void setY(double y) {
		// TODO Auto-generated method stub
		bebete.setY(y);
	}

	@Override
	public Champ getChamp() {
		// TODO Auto-generated method stub
		return bebete.getChamp();
	}

	@Override
	public double getVitesseCourante() {
		// TODO Auto-generated method stub
		return bebete.getVitesseCourante();
	}

	@Override
	public void setVitesseCourante(double vitesseCourante) {
		// TODO Auto-generated method stub
		bebete.setVitesseCourante(vitesseCourante);
	}

	@Override
	public double getDirectionCourante() {
		// TODO Auto-generated method stub
		return bebete.getDirectionCourante();
	}

	@Override
	public void setDirectionCourante(double directionCourante) {
		// TODO Auto-generated method stub
		bebete.setDirectionCourante(directionCourante);
	}

	@Override
	public Color getCouleur() {
		// TODO Auto-generated method stub
		return bebete.getCouleur();
	}

	@Override
	public void seDessine(Graphics g) {
		// TODO Auto-generated method stub
		bebete.seDessine(g);
	}

	@Override
	public void calculeDeplacementAFaire() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void effectueDeplacement() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getChampDeVue() {
		// TODO Auto-generated method stub
		return bebete.getChampDeVue();
	}

	@Override
	public int getLongueurDeVue() {
		// TODO Auto-generated method stub
		return bebete.getLongueurDeVue();
	}

	@Override
	public void setLongueurDeVue(int lDV) {
		// TODO Auto-generated method stub
		bebete.setLongueurDeVue(lDV);
	}

	@Override
	public void setChampDeVue(double cDV) {
		// TODO Auto-generated method stub
		bebete.setChampDeVue(cDV);
	}

	@Override
	public List<? extends Positionnable> getChosesVues() {
		// TODO Auto-generated method stub
		return bebete.getChosesVues();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return bebete.equals(obj);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return bebete.toString();
	}

}
