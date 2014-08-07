/**
 * 
 */
package tp2marc.visu;

import java.util.List;

/**
 * @author collet
 *
 */
public interface Champ {
	
	public int getLargeur();
	
	public int getHauteur();
	
	public List<? extends Positionnable> getPositionnables();

}
