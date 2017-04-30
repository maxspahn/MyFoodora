package restaurant;

import java.io.Serializable;

public class ItemFactoryProducer implements Serializable{
	
	
	private MealFactory mealFactory;
	private SingleItemFactory singleItemFactory;
	
	public ItemFactoryProducer(){
		super();
		this.mealFactory = new MealFactory();
		this.singleItemFactory = new SingleItemFactory();
	}

	
	public ItemFactory getFactory(String itemName){
		if(itemName.equalsIgnoreCase("meal")){
			return this.mealFactory;
		}
		else if (itemName.equalsIgnoreCase("singleItem")){
			return this.singleItemFactory;
		}
		else return null;
	}

}
