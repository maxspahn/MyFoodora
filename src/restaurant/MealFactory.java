package restaurant;

public class MealFactory extends ItemFactory{

	public MealFactory() {
		// TODO Auto-generated constructor stub
	}

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

	@Override
	SingleItem createSingleItem(String singleItemType, String name) {
		return null;
	}

}
