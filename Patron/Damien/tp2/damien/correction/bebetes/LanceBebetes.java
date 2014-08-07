package tp2.damien.correction.bebetes;

/**
 *
 * @author  collet
 * @author fmallet Enl�ve l'h�ritage de JFrame inutile.
 * @version 1.1
 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;

import tp2.damien.correction.Ecosystem.EcosystemFactory;
import tp2.damien.correction.factoryBebete.BebeteFactory;
import tp2.damien.correction.factoryBebete.FactoryEmergente;
import tp2.damien.correction.factoryBebete.FactoryMixte;


public class LanceBebetes implements ActionListener {
	
	//Attribut obligatoire pour le actionPerformed()
	 private ChampDeBebetes champ;
	 private EcosystemFactory ecoSytem;
	 
	 public LanceBebetes(){
		 champ = new ChampDeBebetes(640,480,200);
		 
		 ecoSytem = new EcosystemFactory(champ);
		 champ.setFabrique(ecoSytem.getEcosystem(""));
		 
		 String[] listeType = ecoSytem.getAll();
	    	JComboBox listeTypeBebete = new JComboBox(listeType);
	    	listeTypeBebete.addActionListener(this);
	
	    	JFrame frame = new JFrame("Champ de b�b�tes");
	    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	frame.getContentPane().add(champ, BorderLayout.CENTER);
	        frame.getContentPane().add(listeTypeBebete, BorderLayout.NORTH);
	        champ.demarre();    	
	    	frame.pack();
	    	frame.setVisible(true);
	 }
	
    public static void main (String args[]) {    
    	LanceBebetes appli = new LanceBebetes();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JComboBox cb = (JComboBox)e.getSource();
        String type = (String)cb.getSelectedItem();
        
        System.out.println(type);
        champ.setFabrique(ecoSytem.getEcosystem(type));       
	}

}
