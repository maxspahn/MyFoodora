package restaurant;

public abstract class ItemFactory {

	abstract Meal createMeal(String mealType, String name);
	abstract SingleItem createSingleItem(String singleItemType, String name);

}
