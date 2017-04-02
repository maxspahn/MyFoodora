package restaurant;

/** MealFactory is part of the abstract factory pattern to add items to the menu. You can create meals depending on their name.
 * @author maxspahn
 * @author jeremyaugot
 */
public class MealFactory extends ItemFactory{

	public MealFactory() {
		// TODO Auto-generated constructor stub
	}


	/** Creates a meal depending on the type that is given.
	 * @param mealType Either FullMeal or HalfMeal.
	 * @param name Name of the newly created item.	 * 
	 */
	@Override
	Meal createMeal(String mealType,String name) {
		if(mealType.equalsIgnoreCase("FullMeal")){
			return new FullMeal(name);
		}
		else if(mealType.equalsIgnoreCase("HalfMeal")){
			return new HalfMeal(name);
		}
		return null;
	}

	/** This factory cannot create a singleItem.
	 * @param mealType
	 * @param name
	 * @return Always a null-pointer.
	 */
	@Override
	SingleItem createSingleItem(String singleItemType, String name) {
		return null;
	}

}
