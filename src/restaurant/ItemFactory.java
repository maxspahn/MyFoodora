package restaurant;

public abstract class ItemFactory {

	abstract Meal getMeal(String mealType, String name);
	abstract SingleItem getSingleItem(String singleItemType, String name);

}
