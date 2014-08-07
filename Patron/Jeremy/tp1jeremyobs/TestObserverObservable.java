package tp1jeremyobs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.WindowConstants;

public class TestObserverObservable implements ActionListener {
	
	private TableauEntierEtendu modele;
	
	public TableauEntierEtendu getModele() {
		return modele;
	}


	public void setModele(TableauEntierEtendu modele) {
		this.modele = modele;
	}


	private JFrame f = new JFrame();
	private JPanel p_haut = new JPanel();
	private JPanel p_bas = new JPanel();
	private JButton b_random = new JButton("random");
	private JButton b_new_interne = new JButton("nouvelle fenetre interne");
	private JButton b_new_externe = new JButton("nouvelle fenetre externe");
	private JComboBox liste;
	private String[] choix = new String[]{"bar","camembert"} ;
	
	private Timer t = new Timer(1000,this);
	
	
	private List <GrapheurTabEntiers> fenetresInternes =  new ArrayList<GrapheurTabEntiers>();
	
	
	public TestObserverObservable(){
		
		
		p_haut.setLayout(new GridLayout(1, 4));
		//final GridLayout grid_p_bas = new GridLayout();
		p_bas.setLayout(new GridLayout());
		
		liste = new JComboBox(choix);
		//VUES
		VueBar vue = new VueBar();	
		VueCamembert vue_cam = new VueCamembert();
		//MODELE
		this.modele = new TableauEntierEtendu();	
		//CONTROLEUR
		
		GrapheurTabEntiers controleur = new GrapheurTabEntiers(vue, modele);
		GrapheurTabEntiers controleur_cam = new GrapheurTabEntiers(vue_cam, modele);
		
		modele.addObserver(controleur);
		modele.addObserver(controleur_cam);
		
		fenetresInternes.add(controleur);

				
		f.setSize(640, 480);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.setLayout(new BorderLayout());
		

		
		b_random.addActionListener(new BoutonRandom(modele));
		b_new_interne.addActionListener(new BoutonInterne(this));
		b_new_externe.addActionListener(new BoutonExterne(this));
		
		//System.out.println(grid_p_bas.getColumns());
		p_haut.add(b_random);
		p_haut.add(b_new_interne);
		p_haut.add(b_new_externe);
		p_haut.add(liste);
		p_bas.add(controleur);
		//p_bas.add(controleur_cam);
		

		f.add(p_haut, BorderLayout.NORTH);
		f.add(p_bas, BorderLayout.CENTER);
		
		t.setRepeats(true);
		t.start();
		f.setVisible(true);
		
	}
	// Ajouter des fenetres internes
	//
	public void addFrame(GrapheurTabEntiers controleur){
		fenetresInternes.add(controleur);
		f.remove(p_bas);
		
		
		p_bas = new JPanel();
		p_bas.setLayout(new GridLayout());
		for (GrapheurTabEntiers val : fenetresInternes) {
			p_bas.add(val);
		}
		f.add(p_bas, BorderLayout.CENTER);
		f.pack();
	}
	
	// Ajouter des fenetres externes
	//
	public void addFrames(GrapheurTabEntiers controleur){
		
			JFrame fe = new JFrame("fenetre externe ");
			JPanel p = new JPanel(new BorderLayout());
			fe.setSize(640, 480);
			fe.setLayout(new BorderLayout());
			p.add(controleur);
			fe.add(p, BorderLayout.CENTER);
			fe.setVisible(true);
		
	}
	
	public static void main(String[] args){
		new TestObserverObservable();

		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.modele.melange();
	}

}
