package restaurant;

/** ItemFactory is part of the FactoryPattern to create items.
 * Every ItemFactory must provide a function to create a meal or a singleItem. One of them returns null.
 * 
 * @author maxspahn
 * @author jeremyaugot
 */
public abstract class ItemFactory {

	/** Create a new meal.
	 * @param mealType String which contains the type of the meal.
	 * @param name String which contains the name of the meal.
	 * @return Meal(mealType, name).
	 */
	abstract Meal createMeal(String mealType, String name);
	
	/** Create a new single item.
	 * @param singleItemType String which contains the type of the single item.
	 * @param name String which contains the name of the single item.
	 * @return
	 */
	abstract SingleItem createSingleItem(String singleItemType, String name);

}
