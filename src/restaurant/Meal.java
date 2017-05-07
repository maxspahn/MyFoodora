package restaurant;

import java.io.Serializable;

/** Meal is the superClass of every possible combination of items, that are supposed to be a kind of meal (i.e. FullMeal, HalfMeal) 
 * A Meal-object always stores information about its status (glutenFree, vegetarian, mealOfTheWeek, discount, complete). 
 * For every meal, the method to check if an added item is glutenFree and/or vegetarian and modifies the status of the meal 
 * depending on that.
 * Process to create a Meal : Creating of an empty meal. Adding singleItems to complete the meal.
 *  
 * @author maxspahn
 * @author jeremyaugot
 */
public abstract class Meal implements Serializable{	

	private static int counter = 1;
	private int id;
	private String name;
	private boolean vegetarian;
	private boolean glutenFree;
	private Starter starter;
	private MainDish mainDish;
	private Desert desert;
	private boolean mealOfTheWeek;
	private double discount;
	private boolean isComplete;

	/** Constructor. Creates a empty new Meal with a given name.
	 * @param name
	 */
	public Meal(String name) {
		this.setGlutenFree(true);
		this.setName(name);
		this.setVegetarian(true);
		this.setId(Meal.getCounter());
		Meal.counter++;
		this.setMealOfTheWeek(false);
		this.setDiscount(0.1);
		this.setComplete(false);
	}
	
	/** Adds an item to the meal, must be implemented for every kind of meal.
	 * Method must check if a given singleItem is valid for the meal (the type must not exist already).
	 * Sets the status to complete if all needed types (i.e. Starter) have been added.
	 * @param item SingleItem to add.
	 * @throws WrongItemAdded
	 */
	abstract public void addItem(SingleItem item) throws WrongItemAdded;
	
	/** Removes an item from the meal, must be implemented for every kind of meal.
	 * Method must check if a given singleItem is existant in the meal, otherwise it throws an Exception.
	 * Sets the status to incomplete.
	 * @param item SingleItem to remove
	 * @throws WrongItemRemoved
	 */
	abstract public void removeItem(SingleItem item) throws WrongItemRemoved;
	
	/** Auxiliary method to check if an added item is vegetarian and/or glutenfree.
	 * If it is not the case the status of the meal is changed.
	 * @param item Newly added item.
	 */
	public void checkVegieGluten(SingleItem item){
		if(!item.isGlutenFree()){
			this.setGlutenFree(false);
		}
		if(!item.isVegetarian()){
			this.setVegetarian(false);
		}
	}

	
	/** Gets the price for the item. Is implemented differently for every meal type, in order to allow different discounts (instead of 5%).
	 * @return Price of the meal.
	 */
	public abstract double getPrice();


	/** Overriding to print a meal object. The components are displayed in different lines. The price is in the last line.
	 * @return String with the information.
	 */
	public String toString(){
		String s = new String();
		s = this.getName()+" (";
		if(this.getStarter() != null) { s += "Starter : " + this.getStarter().getName() + " ";}
		if(this.getMainDish() != null) {s += "MainDish : " + this.getMainDish().getName() + " ";}
		if(this.getDesert() != null) {s += "Dessert : " + this.getDesert().getName() + " ";}
		s+=")"+" - "+this.getPrice()+"€";
		return s;
		
	}

	/**
	 * @return the counter
	 */
	public static int getCounter() {
		return counter;
	}

	/**
	 * @param counter the counter to set
	 */
	public static void setCounter(int counter) {
		Meal.counter = counter;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the vegetarian
	 */
	public boolean isVegetarian() {
		return vegetarian;
	}

	/**
	 * @param vegetarian the vegetarian to set
	 */
	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	/**
	 * @return the glutenFree
	 */
	public boolean isGlutenFree() {
		return glutenFree;
	}

	/**
	 * @param glutenFree the glutenFree to set
	 */
	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	/**
	 * @return the starter
	 */
	public Starter getStarter() {
		return starter;
	}

	/**
	 * @param starter the starter to set
	 */
	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	/**
	 * @return the mainDish
	 */
	public MainDish getMainDish() {
		return mainDish;
	}

	/**
	 * @param mainDish the mainDish to set
	 */
	public void setMainDish(MainDish mainDish) {
		this.mainDish = mainDish;
	}

	/**
	 * @return the desert
	 */
	public Desert getDesert() {
		return desert;
	}

	/**
	 * @param desert the desert to set
	 */
	public void setDesert(Desert desert) {
		this.desert = desert;
	}

	/**
	 * @return the mealOfTheWeek
	 */
	public boolean isMealOfTheWeek() {
		return mealOfTheWeek;
	}

	/**
	 * @param mealOfTheWeek the mealOfTheWeek to set
	 */
	public void setMealOfTheWeek(boolean mealOfTheWeek) {
		this.mealOfTheWeek = mealOfTheWeek;
	}

	/**
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the isComplete
	 */
	public boolean isComplete() {
		return isComplete;
	}

	/**
	 * @param isComplete the isComplete to set
	 */
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	
	
}
