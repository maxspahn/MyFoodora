package system;

import restaurant.FullMeal;

/**Sort the list of full meals according to the chosen sort policy. Extends the SortPolicy class. 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class FullMealSort extends SortPolicy {
	
	private FullMeal fullMeal;

	/**Constructor.
	 * @param fullMeal FullMeal. 
	 */
	public FullMealSort(FullMeal fullMeal) {
		super();
		this.setFullMeal(fullMeal);
	}
	
	/**Constructor.
	 * @param fullMeal FullMeal.
	 * @param count Integer.
	 */
	public FullMealSort(FullMeal fullMeal, int count){
		super(count);
		this.setFullMeal(fullMeal);
	}

	/**
	 * @return FullMeal.
	 */
	public FullMeal getFullMeal() {
		return fullMeal;
	}

	/**
	 * @param fullMeal FullMeal.
	 */
	public void setFullMeal(FullMeal fullMeal) {
		this.fullMeal = fullMeal;
	}


}
