package system;

import java.io.Serializable;

import restaurant.HalfMeal;

/**Sort the list of half meals according to the chosen sort policy. Extends the SortPolicy class. 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class HalfMealSort extends SortPolicy implements Serializable{
	
	private HalfMeal halfMeal;

	/**Constructor.
	 * @param halfMeal HalfMeal.
	 * @param count Integer.
	 */
	public HalfMealSort(HalfMeal halfMeal, int count) {
		super(count);
		this.setHalfMeal(halfMeal);
	}

	/**
	 * @return HalfMeal.
	 */
	public HalfMeal getHalfMeal() {
		return halfMeal;
	}

	/**
	 * @param halfMeal HalfMeal.
	 */
	public void setHalfMeal(HalfMeal halfMeal) {
		this.halfMeal = halfMeal;
	}

}
