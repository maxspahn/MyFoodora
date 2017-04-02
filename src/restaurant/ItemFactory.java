package restaurant;

/** ItemFactory is part of the FactoryPattern to create items.
 * Every ItemFactory must provide a function to create a meal or a singleItem. One of them returns null.
 * 
 * @author maxspahn
 * @author jeremyaugot
 */
public abstract class ItemFactory {

	abstract Meal createMeal(String mealType, String name);
	abstract SingleItem createSingleItem(String singleItemType, String name);

}
