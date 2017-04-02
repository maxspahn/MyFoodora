package restaurant;

/** SingleItemFactory is part of the abstract factory pattern to add items to the menu. You can create singleItems depending on their name.
 * @author maxspahn
 * @author jeremyaugot
 */
public class SingleItemFactory extends ItemFactory{

	/** Default constructor.
	 * 
	 */
	public SingleItemFactory() {
		super();
	}

	/** This factory cannot create a meal.
	 * @param mealType
	 * @param name
	 * @return Always a null-pointer.
	 */
	@Override
	Meal createMeal(String mealType, String name) {
		return null;
	}

	/** Creates a singleItem depending on the type that is given.
	 * @param singleItemType Either Starter, MainDish or Dessert.
	 * @param name Name of the newly created item.	 * 
	 */
	@Override
	SingleItem createSingleItem(String singleItemType, String name) {
		if(singleItemType.equalsIgnoreCase("Starter")){
			return new Starter(name);
		}
		else if(singleItemType.equalsIgnoreCase("MainDish")){
			return new MainDish(name);
		}
		else if(singleItemType.equalsIgnoreCase("Dessert")){
			return new Desert(name);
		}
		return null;
	}

}
