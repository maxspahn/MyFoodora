package system;

import java.io.Serializable;

import restaurant.FullMeal;

/**Sort the list of full meals according to the chosen sort policy. Extends the SortPolicy class. 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class FullMealSort extends SortPolicy implements Serializable {
	
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String s = new String();
		s = this.getFullMeal().toString() + " count: " + this.getCount();
		return s;
	}

}
