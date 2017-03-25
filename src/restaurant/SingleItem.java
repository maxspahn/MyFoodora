package restaurant;

public abstract class SingleItem {
	
	private double price;
	private static int counter = 1;
	private int id;
	private String name;
	private boolean vegetarian;
	private boolean glutenFree;
	
	

	public SingleItem(String name) {
		this.setGlutenFree(false);
		this.setName(name);
		this.setVegetarian(false);
		this.setId(SingleItem.getCounter());
		counter++;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public void setVegetarian(boolean vegetarian) {
		this.vegetarian = vegetarian;
	}

	public boolean isGlutenFree() {
		return glutenFree;
	}

	public void setGlutenFree(boolean glutenFree) {
		this.glutenFree = glutenFree;
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		SingleItem.counter = counter;
	}
	
	public String toString(){
		String s = new String();
		s = this.getName() + " at a price of " + this.getPrice();
		return s;
	}
	
}
