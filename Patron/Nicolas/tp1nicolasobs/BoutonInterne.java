package tp1nicolasobs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicolas
 *         Date : 16/10/13 17:15
 */
public class BoutonInterne implements ActionListener
{
    private TestObserverObservable fenetre;

    /**
     * Creates a button with no set text or icon.
     */
    public BoutonInterne(TestObserverObservable fenetre)
    {
        this.fenetre = fenetre;
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e)
    {
        VueBar vue = new VueBar();
        GrapheurTabEntiers controleur = new GrapheurTabEntiers(vue, fenetre.getModele());
        fenetre.getModele().addObserver(controleur);
        fenetre.ajouteFenetreInterne(controleur);
    }
}
