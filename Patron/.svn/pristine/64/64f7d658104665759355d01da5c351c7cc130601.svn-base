package tp2.damien.correction.bebetes;

import java.awt.Color;
import java.awt.Graphics;

import tp2.damien.correction.simu.Actionnable;
import tp2.damien.correction.strategybebete.Strategy;
import tp2.damien.correction.visu.Champ;
import tp2.damien.correction.visu.Dessinable;
import tp2.damien.correction.visu.PerceptionAble;

public abstract class Bebete extends PerceptionAble implements Actionnable, Dessinable {

	protected double champDeVue = (Math.PI / 4); // En radians
	protected int longueurDeVue = 20; // nb de pixel pour la longueur du champ de vision

	protected double x, y;                 // position � l'�cran
	protected double vitesseCourante;      // vitesse en pixels par second (float pour conserver la pr�cision du calcul de vitesse)
	protected double directionCourante;  // en radians [0 - 2 PI[
	protected Color couleur;            // Couleur de remplissage


	protected double prochaineVitesse;      // vitesse en pixels par second (float pour conserver la pr�cision du calcul de vitesse)
	
	protected double prochaineDirection;  // en radians [0 - 2 PI[
	
	Strategy strategy;
	
	

	protected Champ champ;     // Le champ

	/* Impl�mentation de Positionnable */

	@Override
	public double getX() {
		return x;
	}

	@Override
	public void setX(double x) {
		this.x = x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public Champ getChamp() {
		return champ;
	}

	/* Impl�mentation de Dirigeable */

	@Override
	public double getVitesseCourante() {
		return vitesseCourante;
	}

	@Override
	public void setVitesseCourante(double vitesseCourante) {
		this.vitesseCourante = vitesseCourante;
		this.prochaineVitesse = vitesseCourante;
	}

	@Override
	public double getDirectionCourante() {
		return directionCourante;
	}

	@Override
	public void setDirectionCourante(double directionCourante) {
		this.directionCourante = directionCourante;
		this.prochaineDirection = directionCourante;

	}


	/* Impl�mentation de Dessinable */

	@Override
	public Color getCouleur() {
		return couleur;
	}

	@Override
	public void seDessine(Graphics g) {
		int x = (int) this.x;
		int y = (int) this.y;
		
		int CDVDegres = (int) Math.toDegrees(champDeVue);
		g.setColor(couleur);
		g.fillArc(x - (TAILLEGRAPHIQUE/2), y - (TAILLEGRAPHIQUE/2), TAILLEGRAPHIQUE, TAILLEGRAPHIQUE, -(int) Math
				.toDegrees(directionCourante)
				- (CDVDegres / 2), CDVDegres);


	}

	/* Impl�mentation de Actionnable */

	@Override
	public void calculeDeplacementAFaire(){
		strategy.calcule(this);
	}

	@Override
	public void effectueDeplacement(){
		strategy.applique(this);
	}


	/* Impl�mentation de PerceptionAble */

	@Override
	public  double getChampDeVue() {
		// TODO Auto-generated method stub
		return champDeVue;
	}

	@Override
	public  int getLongueurDeVue() {
		// TODO Auto-generated method stub
		return longueurDeVue;
	}

	
	public  void setLongueurDeVue(int lDV) { 
		longueurDeVue = lDV;
	}

	public  void setChampDeVue(double cDV) { 
		champDeVue = cDV;
	}
	
	public boolean estEgal(PerceptionAble perceptionAble){
		return this.equals(perceptionAble);
	}

	public double getProchaineVitesse() {
		return prochaineVitesse;
	}

	public void setProchaineVitesse(double prochaineVitesse) {
		this.prochaineVitesse = prochaineVitesse;
	}
	
	public double getProchaineDirection() {
		return prochaineDirection;
	}

	public void setProchaineDirection(double prochaineDirection) {
		this.prochaineDirection = prochaineDirection;
	}

	public Strategy getStrategy() {
		return strategy;
	}

	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
	}
}
