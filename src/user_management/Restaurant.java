package user_management;

import java.io.Serializable;
import java.util.TreeSet;

import restaurant.*;
import system.FullMealSort;
import system.HalfMealSort;
import system.SingleItemSort;

/** Implementation of Restaurant, extends the lUser class. It has a menu, the worth of total sellings, and lists of full meals, half meals and single items.
 * Implements the Observable interface.
 * A restaurant can send notifications to customers thanks to an observer pattern. 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class Restaurant extends User implements Observable, Serializable{
	//list of all the products (single items and meals) of the restaurant
	private Menu menu;  
	private double discount; //Discount for the meal of the week
	
	//Attributes for the observer pattern
	private boolean changed;
	private double totalSelling;


	private TreeSet<FullMealSort> deliveredFullMeals;
	private TreeSet<HalfMealSort> deliveredHalfMeals;
	private TreeSet<SingleItemSort> deliverdSingleItems;
	

	/**Constructor.
	 * By default, the restaurant has a total selling worth of 0, and a discount equals to 10%.
	 * 
	 */
	public Restaurant(String name, String userName, String passWord, String phone, String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.menu = new Menu();
		this.discount = 0.1;
		//this.allCustomers = new ArrayList<Customer>();
		this.changed = false;
		this.totalSelling = 0;
		this.setDeliverdSingleItems(new TreeSet<SingleItemSort>());
		this.setDeliveredFullMeals(new TreeSet<FullMealSort>());
		this.setDeliveredHalfMeals(new TreeSet<HalfMealSort>());
	}
	
	/**
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}
	
	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @return the changed
	 */
	public boolean isChanged() {
		return changed;
	}

	/**
	 * @param discount
	 */
	public void setDiscount(double discount){
		this.discount = discount;
		
		//Send a notification to the customers to tell them that there is a new discount
		this.changed = true;
		this.notifyObservers("The restaurant "+this.getName()+" offers a discount of "+this.discount+" on all the items of the menu.");
	}

	public String toString(){	
		int x = this.getAdress()[0];
		int y = this.getAdress()[1];
		return "ID"+this.getID()+"\t : "+this.getName()+", Username : "+this.getUserName()+", "+this.getEmail()+", "+this.getPhone()+", Adress:{ "+x+" ; "+y+" }";
	}
	
	/**Set the meal of the week.
	 * @param mealName String which contains the name of the meal.
	 * @throws ItemDoesNotExist if mealName does not exist in the menu of the restaurant.
	 */
	public void setMealOfTheWeek(String mealName) throws ItemDoesNotExist{
		for (int i = 0; i < this.menu.getMeals().size(); i++) {
			this.menu.getMeals().get(i).setMealOfTheWeek(false);
		}
		
		this.menu.getMeal(mealName).setMealOfTheWeek(true);
		this.menu.getMeal(mealName).setDiscount(this.discount);
		
		//Send a notification to the customers to tell them that the meal of the week has changed
		this.changed = true;
		this.notifyObservers("The restaurant "+this.getName()+" has a new meal of the week: "+mealName);
		
	}

	@Override
	public void notifyObservers(String message) {
		if (this.changed){
			for (Customer cust : this.getMyFoodora().getListCustomer()){
				if (cust.getSpamAgreement()){
					cust.update(message);
				}
			}
			this.changed = false;
		}
	}
	
	/**
	 * @param message
	 */
	public void setMessage(String message){
		this.changed = true;
	}
	

	/** Create a new full meal.
	 * @param fullMealName String which contains the name of the full meal to create.
	 * @param starterName String which contains the name of the starter to add to the menu.
	 * @param mainDishName String which contains the name of the main dish to add to the menu.
	 * @param dessertName String which contains the name of the dessert to add to the menu.
	 * @throws WrongItemAdded if there is already an item of the same type.
	 * @throws ItemDoesNotExist if an item does not exist in the menu of the restaurant.
	 */
	public void createNewFullMeal(String fullMealName, String starterName, String mainDishName, String dessertName) throws WrongItemAdded, ItemDoesNotExist{
		this.menu.addItem("FullMeal", fullMealName);
		this.menu.addItemToMeal(fullMealName, starterName);
		this.menu.addItemToMeal(fullMealName, mainDishName);
		this.menu.addItemToMeal(fullMealName, dessertName);
		System.out.println("The full meal "+fullMealName+" has been created and added to the menu. It contains:"+"\r\n"
		+"Starter: "+starterName+"\r\n"
		+"Main dish: "+mainDishName+"\r\n"
		+"Desert: "+dessertName);
		
	}
	
	/** Create a new half meal.
	 * @param halfMealName String which contains the name of the half meal to create.
	 * @param mainDishName String which contains the name of the main dish to add to the menu.
	 * @param secondItemName String which contains the name of the second item to add. It can be a starter or a dessert.
	 * @throws WrongItemAdded if there is already an item of the same type.
	 * @throws ItemDoesNotExist if an item does not exist in the menu of the restaurant.
	 */
	public void createNewHalfMeal(String halfMealName, String mainDishName, String secondItemName) throws WrongItemAdded, ItemDoesNotExist{
		this.menu.addItem("HalfMeal", halfMealName);
		this.menu.addItemToMeal(halfMealName, mainDishName);
		this.menu.addItemToMeal(halfMealName, secondItemName);
		System.out.println("The full meal "+halfMealName+" has been created and added to the menu. It contains:"+"\r\n"
		+"Main dish: "+mainDishName+"\r\n"
		+"and: "+secondItemName);
		
	}
	
	/** Create a new item.
	 * @param itemType String which contains the type of the item: a fullMeal, halfMeal, a starter, mainDish or a dessert.
	 * @param name String which contains the name of the item.
	 * @throws WrongItemAdded if there is already an item of the same type.
	 */
	public void createNewItem(String itemType, String name) throws WrongItemAdded{
		this.menu.addItem(itemType, name);
	}


	/**
	 * @return double totalSelling
	 */
	public double getTotalSelling() {
		return totalSelling;
	}

	/**
	 * @param totalSelling
	 */
	public void setTotalSelling(double totalSelling) {
		this.totalSelling = totalSelling;
	}

	/**
	 * @return TreeSet<FullMealSort>
	 */
	public TreeSet<FullMealSort> getDeliveredFullMeals() {
		return deliveredFullMeals;
	}

	/**
	 * @param deliveredFullMeals
	 */
	public void setDeliveredFullMeals(TreeSet<FullMealSort> deliveredFullMeals) {
		this.deliveredFullMeals = deliveredFullMeals;
	}

	/**
	 * @return TreeSet<HalfMealSort>
	 */
	public TreeSet<HalfMealSort> getDeliveredHalfMeals() {
		return deliveredHalfMeals;
	}

	/**
	 * @param deliveredHalfMeals
	 */
	public void setDeliveredHalfMeals(TreeSet<HalfMealSort> deliveredHalfMeals) {
		this.deliveredHalfMeals = deliveredHalfMeals;
	}

	/**
	 * @return TreeSet<SingleItemSort>
	 */
	public TreeSet<SingleItemSort> getDeliverdSingleItems() {
		return deliverdSingleItems;
	}

	/**
	 * @param deliverdSingleItems
	 */
	public void setDeliverdSingleItems(TreeSet<SingleItemSort> deliverdSingleItems) {
		this.deliverdSingleItems = deliverdSingleItems;
	}
	
	
	
}
