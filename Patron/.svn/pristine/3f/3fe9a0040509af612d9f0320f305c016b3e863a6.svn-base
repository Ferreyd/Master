package tp1nicolasobs;

import java.awt.*;

/**
 * @author Nicolas
 *         Date : 16/10/13 17:47
 */
public class VueCamenbert implements IVueTableau
{
    private TestObserverObservable fenetre;

    @Override
    public void dessine(Component c, Graphics g, ITableauEntier modele)
    {
        int somme = 0;
        int[] cumul = new int[modele.getLongueur()];

        for(int i = 0 ; i < modele.getLongueur() ; i++)
        {
            cumul[i] = somme;
            somme = somme + modele.getValeur(i);
        }
        int angle_debut = 0;
        int angle = 0;

        for(int i = 0 ; i < modele.getLongueur() ; i++)
        {
            angle_debut = (360 * cumul[i]) / somme;
            angle = (360 * modele.getValeur(i)) / somme;
            if((i / 2) == 0) g.setColor(Color.BLUE);
            else g.setColor(Color.RED);
            g.fillArc(0, 0, c.getWidth(), c.getHeight(), angle_debut, angle);
        }

    }
}
