package tp1jeremyobs;

import java.util.*;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.BevelBorder;

/**
 * La classe abstraite <code>GrapheurTabEntiers</code> implmente 
 * les mcanismes de base pour dessiner des valeurs entires 
 * situes dans un tableau que l'on passe au constructeur.
 * Cette classe implmente l'interface Observer ; elle observe 
 * le tableau et se raffiche si le tableau est modifi�.
 *<P>
 *Cette classe est faite pour tre sous-classe !
 *<P>
 *
 * @author  Michel Buffa
 * @version 11, 1/10/09 (F. Mallet)
 * @version 10, 15/09/99
 */
final public class GrapheurTabEntiers extends JComponent implements Observer {
	private static final long serialVersionUID = 3079676956820109707L;

	private IVueTableau vue;
	private ITableauEntier modele;
	
	public GrapheurTabEntiers(IVueTableau vue, ITableauEntier modele) {
		super();
		this.vue = vue;
		this.modele = modele;
		setSize(getPreferredSize());
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));		
	}

	/**
	 * Retourne la taille souhaite 
	 */
	public Dimension getPreferredSize() {
		return getMinimumSize();
	}

	/**
	 * Retourne la taille minimale.
	 */
	public Dimension getMinimumSize() {
		return new Dimension(200, 200);
	}

	/** M�thode qui impl�mente l'interface Observer. 
	 * Le param�tre attendu arg doit �tre un tableau
	 * d'entiers. Sitt le tableau re�u, on appelle <code>repaint()</code> 
	 * pour redessiner le graphe.
	 * 
	 * @param o L'objet Observable qui a provoqu� l'appel de la m�thode <code>update</code>
	 * @param arg Le tableau d'entier pass� en param�tre par l'objet Observable
	 */
	@Override
	public void update(Observable o, Object arg) {
		//TODO A compl�ter
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		vue.dessine(this, g, modele);
	}
}
