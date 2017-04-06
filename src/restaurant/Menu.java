package restaurant;

import java.util.ArrayList;

/**The class provides the listing of singleItems and Meals that one Restaurant offers. The creation of items is done in this class.
 * Functions to edit Meals and Items are stored here.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class Menu {
	
	private ArrayList<SingleItem> singleItems = new ArrayList<>();
	private ArrayList<Meal> meals = new ArrayList<>();
	private String title;
	private SingleItemFactory singleItemFactory;
	private MealFactory mealFactory;
	
	/**Constructor. Includes the creation of the ItemFactories. The default items are loaded.
	 * 
	 */
	public Menu(){
		this.setTitle("Title");
		this.setMealFactory(new MealFactory());
		this.setSingleItemFactory(new SingleItemFactory());
		this.load();
	}

	/**Constructor. Includes the creation of the ItemFactories. The default items are loaded. 
	 * @param title This title is given to the menu.
	 * 
	 */
	public Menu(String title) {
		this.setTitle(title);
		this.setMealFactory(new MealFactory());
		this.setSingleItemFactory(new SingleItemFactory());
		this.load();
	}
	
	/** Load default Items, Meals and SingleItems.
	 * 
	 */
	public void load(){
		this.loadSingleItems();
		this.loadMeals();
	}
	
	/** Load the list of default singleItems, with the price tags added.
	 * 
	 */
	public void loadSingleItems(){
		try {
			this.addItem("Starter", "Soup");
			this.addPriceTag("soup", 3.10);
			this.addItem("Starter", "Ceasar_Salad");
			this.addPriceTag("Ceasar_Salad", 2.30);
			this.addItem("Starter", "Quiche");
			this.addPriceTag("Quiche", 3.00);
			this.addItem("Starter", "Garlic_Bread");
			this.addPriceTag("Garlic_Bread", 2.00);
			this.addItem("MainDish", "Tartar"); 
			this.addPriceTag("Tartar", 9.10);
			this.addItem("MainDish", "Pizza_Fungi");
			this.addPriceTag("Pizza_Fungi", 6.70);
			this.addItem("MainDish", "Entrecote");
			this.addPriceTag("Entrecote", 11.30);
			this.addItem("MainDish", "Boeuf_Bourguignon");
			this.addPriceTag("Boeuf_Bourguignon", 9.80);
			this.addItem("Dessert", "Mousse_au_Chocolat");
			this.addPriceTag("Mousse_au_Chocolat", 3.00);
			this.addItem("Dessert", "Apple_pie");
			this.addPriceTag("Apple_pie", 2.50);
			this.addItem("Dessert", "Ice_Cream");
			this.addPriceTag("Ice_Cream", 2.00);
			this.addItem("Dessert", "Fruit_Salad");
			this.addPriceTag("Fruit_Salad", 3.10);
			this.addItem("Starter", "Tomatoes");
			this.addPriceTag("Tomatoes", 2.90);
			this.addItem("Dessert", "Pineapple");
			this.addPriceTag("Pineapple", 3.10);
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Load the list of default meals, and sets the "basic" meal as the meal of the week.
	 * 
	 */
	public void loadMeals(){
		
		try {
			this.addItem("FullMeal", "Basic");
			this.addItemToMeal("Basic", "Ceasar_Salad");
			this.addItemToMeal("Basic", "Pizza_Fungi");
			this.addItemToMeal("Basic", "Apple_Pie");
			this.setMealOfTheWeek("Basic");
			this.addItem("HalfMeal", "Classic");
			this.addItemToMeal("Classic", "Garlic_Bread");
			this.addItemToMeal("Classic", "Entrecote");
			this.addItem("Halfmeal", "Exotic");
			this.addItemToMeal("exotic"	, "pineapple");
			this.addItemToMeal("exotic", "pizza_fungi");
			this.addItem("halfmeal", "chef's");   
			this.addItemToMeal("chef's", "quiche");
			this.addItemToMeal("chef's", "Boeuf_Bourguignon");
			
			
		} catch (WrongItemAdded e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ItemDoesNotExist e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** Searches the index in the list of meals of the meal with the given name. Throws Exception if not in list.
	 * @param mealName The name of the meal.
	 * @return indexMeal 
	 * @throws ItemDoesNotExist
	 */
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
	
	/** Searches the index in the list of singleItems of the singleItem with the given name. Throws Exception if not in list.
	 * @param singleItemName Name of the singleItem.
	 * @return indexSingleItem 
	 * @throws ItemDoesNotExist
	 */
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
	
	/** Adds an item to a meal.
	 * @param mealName Name of the meal, where the item should be added.
	 * @param singleItemName Name of the singleItem to be added.
	 * @throws WrongItemAdded
	 * @throws ItemDoesNotExist
	 */
	public void addItemToMeal(String mealName, String singleItemName) throws WrongItemAdded, ItemDoesNotExist{
		this.getMeal(mealName).addItem(this.getSingleItem(singleItemName));		
	}
	
	/** Removes an item from a meal.
	 * @param mealName Name of the meal, where the item should be removed.
	 * @param singleItemName Name of the singleItem that should be removed.
	 * @throws WrongItemRemoved
	 * @throws ItemDoesNotExist
	 */
	public void removeItemFromMeal(String mealName, String singleItemName) throws WrongItemRemoved, ItemDoesNotExist {
		this.getMeal(mealName).removeItem(this.getSingleItem(singleItemName));
	}
	
	/** Returns the meal given its name.
	 * @param mealName Name of the meal.
	 * @return Reference to the meal.
	 * @throws ItemDoesNotExist
	 */
	public Meal getMeal(String mealName) throws ItemDoesNotExist { 
		int indexMeal;
		indexMeal = this.searchIndexMeal(mealName);
		return this.getMeals().get(indexMeal);
		
		
	}
	
	/** Returns the singleItem given its name.
	 * @param singleItemName Name of the singleItem.
	 * @return Reference to the singleItem.
	 * @throws ItemDoesNotExist 
	 */
	public SingleItem getSingleItem(String singleItemName) throws ItemDoesNotExist{
		int indexSingleItemIndex = this.searchIndexSingleItem(singleItemName);
		return this.getSingleItems().get(indexSingleItemIndex);
	}
	
	/** Assigns a price to a singleItem.
	 * @param singleItemName Reference of the singleItem.
	 * @param price The price that should be assigned to the item.
	 */
	public void addPriceTag(String singleItemName, double price){
		try{
			int indexSingleItem = this.searchIndexSingleItem(singleItemName);
			this.getSingleItems().get(indexSingleItem).setPrice(price);
		}
		catch (ItemDoesNotExist e){
			System.out.println(e.getMessage());
		}
	}
	
	/** Adds an item to the menu.
	 * @param itemType String corresponding to the type.
	 * @param name The name of the new item.
	 * @throws WrongItemAdded
	 */
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
	
	/** Set the meal of the week.
	 * @param mealName Name of the meal that is set meal of the week.
	 */
	public void setMealOfTheWeek(String mealName){
		try{
			int indexMeal = this.searchIndexMeal(mealName);
			this.getMeals().get(indexMeal).setMealOfTheWeek(true);
		}
		catch (ItemDoesNotExist e){
			System.out.println(e.getMessage());
		}
	}
	
	/** Unset the meal of the week.
	 * @param mealName The name of the meal.
	 */
	public void unsetMealOfTheWeek(String mealName){
		try{
			int indexMeal = this.searchIndexMeal(mealName);
			this.getMeals().get(indexMeal).setMealOfTheWeek(false);
		}
		catch (ItemDoesNotExist e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @return the singleItems
	 */
	public ArrayList<SingleItem> getSingleItems() {
		return singleItems;
	}

	/**
	 * @param singleItems the singleItems to set
	 */
	public void setSingleItems(ArrayList<SingleItem> singleItems) {
		this.singleItems = singleItems;
	}

	/**
	 * @return the meals
	 */
	public ArrayList<Meal> getMeals() {
		return meals;
	}

	/**
	 * @param meals the meals to set
	 */
	public void setMeals(ArrayList<Meal> meals) {
		this.meals = meals;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the singleItemFactory
	 */
	public SingleItemFactory getSingleItemFactory() {
		return singleItemFactory;
	}

	/**
	 * @param singleItemFactory the singleItemFactory to set
	 */
	public void setSingleItemFactory(SingleItemFactory singleItemFactory) {
		this.singleItemFactory = singleItemFactory;
	}

	/**
	 * @return the mealFactory
	 */
	public MealFactory getMealFactory() {
		return mealFactory;
	}

	/**
	 * @param mealFactory the mealFactory to set
	 */
	public void setMealFactory(MealFactory mealFactory) {
		this.mealFactory = mealFactory;
	}
	
	/** Overriding of the default toString method. It displays the title and all the possible items.
	 * @return String containing the information.
	 */
	public String toString(){
		String s = this.getTitle();
		for (SingleItem singleItem : this.getSingleItems()) {
			s += singleItem;
		}
		for (Meal meal : this.getMeals()) {
			s += meal;
		}
		return s;
	}

	
}
