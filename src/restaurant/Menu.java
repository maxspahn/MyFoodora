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
			this.addItem("Starter", "Tomatoes");
			this.addPriceTag("Tomatoes", 2.90);
			this.addItem("Dessert", "Pineapple");
			this.addPriceTag("Pineapple", 3.10);
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
			this.addItem("Halfmeal", "Exotic");
			this.addItemToMeal("exotic"	, "pineapple");
			this.addItemToMeal("exotic", "pizza fungi");
			this.addItem("halfmeal", "chef's");
			this.addItemToMeal("chef's", "quiche");
			this.addItemToMeal("chef's", "Boeuf Bourguignon");
			
			
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int searchIndexMeal(String mealName)throws ItemDoesNotExist{
		int indexMeal = -1;

		for (int i = 0; i < this.meals.size(); i++) {
			if(this.meals.get(i).getName().equalsIgnoreCase(mealName)){
				indexMeal = i;
				break;
			}
		}
		if(indexMeal == -1){ throw new ItemDoesNotExist(mealName);}
		return indexMeal;
	}
	
	public int searchIndexSingleItem(String singleItemName) throws ItemDoesNotExist{
		int indexSingleItem = -1;

		for (int i = 0; i < this.singleItems.size(); i++) {
			if(this.singleItems.get(i).getName().equalsIgnoreCase(singleItemName)){
				indexSingleItem = i;
				break;
			}
		}
		if(indexSingleItem == -1){ throw new ItemDoesNotExist(singleItemName);}
		return indexSingleItem;
	}
	
	public void addItemToMeal(String mealName, String singleItemName) throws WrongItemAdded, ItemDoesNotExist{
		this.getMeal(mealName).addItem(this.getSingleItem(singleItemName));		
	}
	
	public void removeItemFromMeal(String mealName, String singleItemName) throws WrongItemRemoved, ItemDoesNotExist {
		this.getMeal(mealName).removeItem(this.getSingleItem(singleItemName));
	}
	
	public Meal getMeal(String mealName) throws ItemDoesNotExist{ 
		int indexMeal = this.searchIndexMeal(mealName);
		return this.getMeals().get(indexMeal);
		
	}
	
	public SingleItem getSingleItem(String singleItemName){
		try{
			int indexSingleItemIndex = this.searchIndexSingleItem(singleItemName);
			return this.getSingleItems().get(indexSingleItemIndex);
		}
		
		catch(ItemDoesNotExist e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public void addPriceTag(String singleItemName, double price){
		try{
			int indexSingleItem = this.searchIndexSingleItem(singleItemName);
			this.getSingleItems().get(indexSingleItem).setPrice(price);
		}
		catch (ItemDoesNotExist e){
			System.out.println(e.getMessage());
		}
	}
	
	public void addItem(String itemType, String name) throws WrongItemAdded{
		if(itemType.equalsIgnoreCase("Starter") || itemType.equalsIgnoreCase("mainDish") || itemType.equalsIgnoreCase("dessert")){
			this.getSingleItems().add(this.getSingleItemFactory().createSingleItem(itemType, name));
		}
		else if(itemType.equalsIgnoreCase("FullMeal") || itemType.equalsIgnoreCase("HalfMeal")){
			this.getMeals().add(this.getMealFactory().createMeal(itemType, name));
		}
		else{
			throw new WrongItemAdded();
		}
	}
	
	public void setMealOfTheWeek(String mealName){
		try{
			int indexMeal = this.searchIndexMeal(mealName);
			this.getMeals().get(indexMeal).setMealOfTheWeek(true);
		}
		catch (ItemDoesNotExist e){
			System.out.println(e.getMessage());
		}
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
