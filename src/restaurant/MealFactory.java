package restaurant;

public class MealFactory extends ItemFactory{

	public MealFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	Meal getMeal(String mealType,String name) {
		if(mealType.equalsIgnoreCase("FullMeal")){
			return new FullMeal(name);
		}
		else if(mealType.equalsIgnoreCase("HalfMeal")){
			return new HalfMeal(name);
		}
		return null;
	}

	@Override
	SingleItem getSingleItem(String singleItemType, String name) {
		return null;
	}

}
