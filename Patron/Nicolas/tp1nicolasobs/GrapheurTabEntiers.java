package tp1nicolasobs;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * La classe abstraite <code>GrapheurTabEntiers</code> implmente
 * les mcanismes de base pour dessiner des valeurs entires
 * situes dans un tableau que l'on passe au constructeur.
 * Cette classe implmente l'interface Observer ; elle observe
 * le tableau et se raffiche si le tableau est modifi�.
 * <p/>
 * Cette classe est faite pour tre sous-classe !
 * <p/>
 *
 * @author Michel Buffa
 * @version 10 , 15/09/99
 */
final public class GrapheurTabEntiers extends JComponent implements Observer
{
    private static final long serialVersionUID = 3079676956820109707L;

    private IVueTableau vue;
    private ITableauEntier modele;

    /**
     * Instantiates a new Grapheur tab entiers.
     *
     * @param vue    the vue
     * @param modele the modele
     */
    public GrapheurTabEntiers(IVueTableau vue, ITableauEntier modele)
    {
        super();
        this.vue = vue;
        this.modele = modele;
        setSize(getPreferredSize());
        this.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    /**
     * Retourne la taille souhaite
     */
    public Dimension getPreferredSize()
    {
        return getMinimumSize();
    }

    /**
     * Retourne la taille minimale.
     */
    public Dimension getMinimumSize()
    {
        return new Dimension(200, 200);
    }

    /**
     * Methode qui implemente l'interface Observer.
     * Le parametre attendu arg doit etre un tableau
     * d'entiers. Sitot le tableau recu, on appelle <code>repaint()</code>
     * pour redessiner le graphe.
     *
     * @param o   L'objet Observable qui a provoque l'appel de la methode <code>update</code>
     * @param arg Le tableau d'entier passe en parametre par l'objet Observable
     */
    @Override
    public void update(Observable o, Object arg)
    {
        //TODO A completer

        repaint();

    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        vue.dessine(this, g, modele);
    }
}
