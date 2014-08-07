package tp2nicolas.bebetes.usine;

import tp2nicolas.bebetes.Bebete;
import tp2nicolas.bebetes.BebeteEmergente;
import tp2nicolas.bebetes.ChampDeBebetes;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type Emergente factory.
 *
 * @author Nicolas Date : 20/11/13 18:39
 */
public class EmergenteFactory extends BebeteFactory
{
    private ChampDeBebetes champ;

    public EmergenteFactory(ChampDeBebetes champ){
        this.champ = champ;
    }

    @Override
    public Bebete getBebete() {
        Random generateur = new Random();
        int x = (int) (generateur.nextFloat() * champ.getLargeur());
        if (x > champ.getLargeur() - Bebete.TAILLEGRAPHIQUE)
            x -= Bebete.TAILLEGRAPHIQUE;
        int y = (int) (generateur.nextFloat() * champ.getHauteur());
        if (y > champ.getHauteur() - Bebete.TAILLEGRAPHIQUE)
            y -= Bebete.TAILLEGRAPHIQUE;
        double direction =  (generateur.nextFloat() * 2 * Math.PI);
        double vitesse = Math.max(2, generateur.nextDouble() * champ.vitesseMax);

        return new BebeteEmergente(champ, x, y, direction, vitesse,
                new Color(0, 0, 0));
    }

    @Override
    public ArrayList<Bebete> getBebetes(int n) {
        // TODO Auto-generated method stub
        return null;
    }
}
