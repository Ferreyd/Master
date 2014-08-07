package tp2.damien.correction.Ecosystem;

import tp2.damien.correction.factoryBebete.*;
import tp2.damien.correction.visu.Champ;

import java.util.ArrayList;

public class EcosystemFactory {
	
	private Champ champ;
	ArrayList<String> types = new ArrayList<String>();
	
	public EcosystemFactory(Champ lechamp){
		champ = lechamp;
		types.add("emergente");
		types.add("hasard");
		types.add("mixte");
		types.add("peureuse");
		types.add("agoraphobe");
	}
	
	
	public BebeteFactory getEcosystem(String factory){
		
		BebeteFactory fab;

        if(factory.equals("emergente"))
        {
            fab = new FactoryEmergente(champ);

        }else if(factory.equals("hasard"))
        {
            fab = new FactoryHasard(champ);

        }else if(factory.equals("mixte"))
        {
            fab = new FactoryMixte(champ);

        }else if(factory.equals("peureuse"))
        {
            fab = new FactoryPeureuse(champ);

        }else if(factory.equals("agoraphobe"))
        {
            fab = new FactoryAgoraphobe(champ);

        }else
        {
            fab = BebeteFactory.getDefault(champ);

        }
		
		return fab;
		
	}
	
	public String[] getAll(){
		
		String[] ret = new String[types.size()];
		
		return types.toArray(ret);		
	}
}
