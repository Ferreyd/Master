package tp1jeremyobs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoutonExterne implements ActionListener{

	private TestObserverObservable fenetre;
	
	public BoutonExterne(TestObserverObservable fenetre){
		this.fenetre = fenetre;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		VueBar vue = new VueBar();
		GrapheurTabEntiers controleur = new GrapheurTabEntiers(vue, fenetre.getModele());
		fenetre.getModele().addObserver(controleur); // permet le mise a jour des données dans toutes les fenetres
		fenetre.addFrames(controleur);
	}

	
}
