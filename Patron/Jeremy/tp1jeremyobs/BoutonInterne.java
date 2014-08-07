package tp1jeremyobs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonInterne implements ActionListener{

	private TestObserverObservable fenetre;

	public BoutonInterne(TestObserverObservable fenetre){
		this.fenetre = fenetre;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		VueBar vue = new VueBar();
		GrapheurTabEntiers controleur = new GrapheurTabEntiers(vue, fenetre.getModele());
		fenetre.getModele().addObserver(controleur);
		fenetre.addFrame(controleur);
	}
	
	
}
