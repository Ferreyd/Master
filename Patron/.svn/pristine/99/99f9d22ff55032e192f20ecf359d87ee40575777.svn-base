package tp3nicolas.bebetes.usine;

import tp3nicolas.bebetes.Bebete;
import tp3nicolas.bebetes.ChampDeBebetes;

import java.util.ArrayList;

/**
 * @author Nicolas
 *         Date : 20/11/13 17:26
 */
public abstract class BebeteFactory
{

    public abstract Bebete getBebete();

    public static BebeteFactory getDefaultFactory(ChampDeBebetes champs)
    {
        return new EmergenteFactory(champs);
    }

    public abstract ArrayList<Bebete> getBebetes(int n);


}
