package tp2jeremy.bebetes.factory;

import java.util.ArrayList;

import tp2jeremy.bebetes.ChampDeBebetes;

public class EcosystemFactory {

	private ArrayList<BebeteFactory> bebeteFactory;
	private ChampDeBebetes champ;
	
	public EcosystemFactory() {
		bebeteFactory = new ArrayList<BebeteFactory>();
		bebeteFactory.add(new EmergenteFactory(champ));
		bebeteFactory.add(new HasardFactory(champ));
	}
	
	public BebeteFactory getEcosystem(){
		return null;
		
	}
}
