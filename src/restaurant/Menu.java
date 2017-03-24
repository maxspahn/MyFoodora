package restaurant;

import java.util.ArrayList;

public class Menu {
	
	private ArrayList<SingleItem> singleItems = new ArrayList<>();
	private ArrayList<Meal> meals = new ArrayList<>();
	private String title;
	private SingleItemFactory singleItemFactory;
	private MealFactory mealFactory;
	
	public Menu(){
		this.setTitle("Title");
		this.setMealFactory(new MealFactory());
		this.setSingleItemFactory(new SingleItemFactory());
		this.load();
	}

	public Menu(String title) {
		this.setTitle(title);
		this.setMealFactory(new MealFactory());
		this.setSingleItemFactory(new SingleItemFactory());
		this.load();
	}
	
	public void load(){
		this.loadSingleItems();
		this.loadMeals();
	}
	
	public void loadSingleItems(){
		try {
			this.addItem("Starter", "Soup");
			this.addPriceTag("soup", 3.10);
			this.addItem("Starter", "Ceasar Salad");
			this.addPriceTag("Ceasar Salad", 2.30);
			this.addItem("Starter", "Quiche");
			this.addPriceTag("Quiche", 3.00);
			this.addItem("Starter", "Garlic Bread");
			this.addPriceTag("Garlic Bread", 2.00);
			this.addItem("MainDish", "Tartar");
			this.addPriceTag("Tartar", 9.10);
			this.addItem("MainDish", "Pizza Fungi");
			this.addPriceTag("Pizza Fungi", 6.70);
			this.addItem("MainDish", "Entrecote");
			this.addPriceTag("Entrecote", 11.30);
			this.addItem("MainDish", "Boeuf Bourguignon");
			this.addPriceTag("Boeuf Bourguignon", 9.80);
			this.addItem("Dessert", "Mousse au Chocolat");
			this.addPriceTag("Mousse au Chocolat", 3.00);
			this.addItem("Dessert", "Apple pie");
			this.addPriceTag("Apple pie", 2.50);
			this.addItem("Dessert", "Ice Cream");
			this.addPriceTag("Ice Cream", 2.00);
			this.addItem("Dessert", "Fruit Salad");
			this.addPriceTag("Fruit Salad", 3.10);
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadMeals(){
		try {
			this.addItem("FullMeal", "Basic");
			this.addItemToMeal("Basic", "Ceasar Salad");
			this.addItemToMeal("Basic", "Pizza Fungi");
			this.addItemToMeal("Basic", "Apple Pie");
			this.setMealOfTheWeek("Basic");
			this.addItem("HalfMeal", "Classic");
			this.addItemToMeal("Classic", "Garlic Bread");
			this.addItemToMeal("Classic", "Entrecote");
			
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addItemToMeal(String mealName, String itemName) throws WrongItemAdded{
		int indexMeal = 0;
		int indexItem = 0;
		for (int i = 0; i < this.meals.size(); i++) {
			if(this.meals.get(i).getName().equalsIgnoreCase(mealName)){
				indexMeal = i;
				break;
			}
		}
		for (int i = 0; i < this.singleItems.size(); i++) {
			if(this.singleItems.get(i).getName().equalsIgnoreCase(itemName)){
				indexItem = i;
				break;
			}
		}
		this.getMeals().get(indexMeal).addItem(this.getSingleItems().get(indexItem));
		
	}
	
	public void addPriceTag(String itemName, double price){
		int indexItem = 0;
		for (int i = 0; i < this.singleItems.size(); i++) {
			if(this.singleItems.get(i).getName().equalsIgnoreCase(itemName)){
				indexItem = i;
				break;
			}
		}
		this.getSingleItems().get(indexItem).setPrice(price);		
	}
	
	public void addItem(String itemType, String name) throws WrongItemAdded{
		if(itemType.equalsIgnoreCase("Starter") || itemType.equalsIgnoreCase("mainDish") || itemType.equalsIgnoreCase("dessert")){
			this.getSingleItems().add(this.getSingleItemFactory().getSingleItem(itemType, name));
		}
		else if(itemType.equalsIgnoreCase("FullMeal") || itemType.equalsIgnoreCase("HalfMeal")){
			this.getMeals().add(this.getMealFactory().getMeal(itemType, name));
		}
		else{
			throw new WrongItemAdded();
		}
	}
	
	public void setMealOfTheWeek(String mealName){
		int indexMeal = 0;
		for (int i = 0; i < this.meals.size(); i++) {
			if(this.meals.get(i).getName().equalsIgnoreCase(mealName)){
				indexMeal = i;
				break;
			}
		}
		this.getMeals().get(indexMeal).setMealOfTheWeek(true);
	}
	

	public ArrayList<SingleItem> getSingleItems() {
		return singleItems;
	}

	public void setSingleItems(ArrayList<SingleItem> singleItems) {
		this.singleItems = singleItems;
	}

	public ArrayList<Meal> getMeals() {
		return meals;
	}

	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public SingleItemFactory getSingleItemFactory() {
		return singleItemFactory;
	}

	public void setSingleItemFactory(SingleItemFactory singleItemFactory) {
		this.singleItemFactory = singleItemFactory;
	}

	public MealFactory getMealFactory() {
		return mealFactory;
	}

	public void setMealFactory(MealFactory mealFactory) {
		this.mealFactory = mealFactory;
	}
	

}
