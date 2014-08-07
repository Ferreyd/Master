package tp2nicolas.bebetes.usine;

import tp2nicolas.bebetes.ChampDeBebetes;

/**
 * The type Ecosystem factory.
 * @author Nicolas          Date : 04/12/13 17:59
 */
public class EcosystemFactory
{

    private ChampDeBebetes champs;

    public EcosystemFactory(ChampDeBebetes champs)
    {
        this.champs = champs;
    }

    /**
     * Gets ecosystem.
     *
     * @param n the n
     * @return the ecosystem
     */
    public BebeteFactory getEcosystem(int n)
    {
        BebeteFactory usine;
        switch(n)
        {
            case 0: usine = new EmergenteFactory(champs); break;
            case 1: usine = new MixteBebeteFactory(); break;
            default:
                System.out.println("trompé !");
        }
        return null;
    }


}
