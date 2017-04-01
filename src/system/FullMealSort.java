package system;

import restaurant.FullMeal;

public class FullMealSort extends SortPolicy {
	
	private FullMeal fullMeal;

	public FullMealSort(FullMeal fullMeal) {
		super();
		this.setFullMeal(fullMeal);
	}
	
	public FullMealSort(FullMeal fullMeal, int count){
		super(count);
		this.setFullMeal(fullMeal);
	}

	public FullMeal getFullMeal() {
		return fullMeal;
	}

	public void setFullMeal(FullMeal fullMeal) {
		this.fullMeal = fullMeal;
	}


}
