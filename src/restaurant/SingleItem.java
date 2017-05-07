package restaurant;

import java.io.Serializable;

/** SingleItem provides information about the price, the type, the name and the id of singleItem.
 * @author maxspahn
 * @author jeremyaugot
 */
public abstract class SingleItem implements Serializable{
	
	private double price;
	private static int counter = 1;
	private int id;
	private String name;
	private boolean vegetarian;
	private boolean glutenFree;
	
	

	/** Constructor. Default values are not-glutenFree, not vegetarian.
	 * @param name The name of the new Item.
	 */
	public SingleItem(String name) {
		this.setGlutenFree(false);
		this.setName(name);
		this.setVegetarian(false);
		this.setId(SingleItem.getCounter());
		counter++;
	}
	
	/**Overriding of the default toString function. 
	 * @return Name + price.
	 */
	public String toString(){
		String s = new String();
		s = this.getName() + " - " + this.getPrice() + "€";
		return s;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
		SingleItem.counter = counter;
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
	

	
	
	
}
