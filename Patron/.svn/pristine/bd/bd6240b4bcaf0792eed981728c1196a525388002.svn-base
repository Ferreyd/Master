package tp1nicolasobs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicolas
 *         Date : 16/10/13 17:26
 */
public class BoutonRandom implements ActionListener
{
    private TestObserverObservable fenetre;

    public BoutonRandom(TestObserverObservable fenetre)
    {
        this.fenetre = fenetre;
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        fenetre.getModele().melange();
        fenetre.getFen().repaint();
    }
}
