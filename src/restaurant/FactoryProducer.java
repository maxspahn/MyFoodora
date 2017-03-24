package restaurant;

public class FactoryProducer {

	public FactoryProducer() {
		// TODO Auto-generated constructor stub
	}
	
	public static ItemFactory getFactory(String choice){
		if(choice.equalsIgnoreCase("singleitem")){
			return new SingleItemFactory();
		}
		else if(choice.equalsIgnoreCase("meal")){
			return new MealFactory();
		}
		return null;
	}

}
