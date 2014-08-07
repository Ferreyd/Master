package tp2nicolas.visu;

import tp2nicolas.util.DistancesEtDirections;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  collet
 */
public abstract class PerceptionAble implements Dirigeable {
	
	public abstract double getChampDeVue();
	
	public abstract int getLongueurDeVue();

	public List<? extends Positionnable> getChosesVues() {
		ArrayList<Positionnable> output = new ArrayList<Positionnable>();


		for(Positionnable p : getChamp().getPositionnables()) {

			if (p != this) {
				double dirAngle = DistancesEtDirections.directionDepuisUnPoint(p, getX(), getY(), getDirectionCourante());
				if (Math.abs(dirAngle) < (getChampDeVue() / 2)) {
					if (DistancesEtDirections.distanceDepuisUnPoint(p.getX(), p.getY(), getX(), getY()) <= getLongueurDeVue()) {
						output.add(p);
					}
				}
			}
		}
		return output;
	}
	
}
