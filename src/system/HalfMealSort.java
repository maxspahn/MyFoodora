package system;

import restaurant.HalfMeal;

public class HalfMealSort extends SortPolicy{
	
	private HalfMeal halfMeal;

	public HalfMealSort(HalfMeal halfMeal, int count) {
		super(count);
		this.setHalfMeal(halfMeal);
	}

	public HalfMeal getHalfMeal() {
		return halfMeal;
	}

	public void setHalfMeal(HalfMeal halfMeal) {
		this.halfMeal = halfMeal;
	}

}
