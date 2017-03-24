package restaurant;

public class SingleItemFactory extends ItemFactory{

	public SingleItemFactory() {
		// TODO Auto-generated constructor stub
	}

	@Override
	Meal getMeal(String mealType, String name) {
		return null;
	}

	@Override
	SingleItem getSingleItem(String singleItemType, String name) {
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
