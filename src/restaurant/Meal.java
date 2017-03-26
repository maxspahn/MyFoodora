package restaurant;


public abstract class Meal {	

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
	
	abstract public void addItem(SingleItem item) throws WrongItemAdded;
	
	abstract public void removeItem(SingleItem item) throws WrongItemRemoved;
	
	public void checkVegieGluten(SingleItem item){
		if(!item.isGlutenFree()){
			this.setGlutenFree(false);
		}
		if(!item.isVegetarian()){
			this.setVegetarian(false);
		}
	}

	public static int getCounter() {
		return counter;
	}

	public static void setCounter(int counter) {
		Meal.counter = counter;
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

	public Starter getStarter() {
		return starter;
	}

	public void setStarter(Starter starter) {
		this.starter = starter;
	}

	public MainDish getMainDish() {
		return mainDish;
	}

	public void setMainDish(MainDish mainDish) {
		this.mainDish = mainDish;
	}

	public Desert getDesert() {
		return desert;
	}

	public void setDesert(Desert desert) {
		this.desert = desert;
	}
	
	public abstract double getPrice();

	public boolean isMealOfTheWeek() {
		return mealOfTheWeek;
	}

	public void setMealOfTheWeek(boolean mealOfTheWeek) {
		this.mealOfTheWeek = mealOfTheWeek;
	}

	
	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String toString(){
		String s = new String();
		s = this.getName() + " consists of: ";
		if(this.getStarter() != null) { s += this.getStarter().getName() + " ";}
		if(this.getMainDish() != null) {s += this.getMainDish().getName() + " ";}
		if(this.getDesert() != null) {s += this.getDesert().getName() + " ";}
		s += "and costs " + this.getPrice();
		return s;
		
	}
	
	public boolean equals(Object object){
		if(object instanceof Meal){
			Meal meal = (Meal) object;
			if(meal.getName().equals(this.getName())){
				return true;
			}
		}
		return false;
		
	}

	public boolean isComplete() {
		return isComplete;
	}

	protected void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	
	
}
