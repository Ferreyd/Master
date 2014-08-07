package tp2damien.factory;

import tp2damien.bebetes.Bebete;

public abstract class BebeteFactory {

	public Bebete formerBebete()
    {
        Bebete bebete = this.creerBebete();
        return bebete;
    }

    public abstract Bebete creerBebete();;
}
