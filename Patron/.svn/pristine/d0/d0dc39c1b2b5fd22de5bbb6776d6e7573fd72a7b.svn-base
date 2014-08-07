package tp1nicolasobs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicolas
 *         Date : 16/10/13 17:37
 */
public class BoutonExterne implements ActionListener
{

    private TestObserverObservable fenetre;

    public BoutonExterne(TestObserverObservable fenetre)
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
        fenetre.getModele().addObserver(controleur); // permet le mise a jour des donn�es dans toutes les fenetres
        fenetre.ajouteFenetreExterne(controleur);


    }
}
