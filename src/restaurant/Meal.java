package restaurant;


public abstract class Meal{	

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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desert == null) ? 0 : desert.hashCode());
		long temp;
		temp = Double.doubleToLongBits(discount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (glutenFree ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + (isComplete ? 1231 : 1237);
		result = prime * result
				+ ((mainDish == null) ? 0 : mainDish.hashCode());
		result = prime * result + (mealOfTheWeek ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((starter == null) ? 0 : starter.hashCode());
		result = prime * result + (vegetarian ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Meal other = (Meal) obj;
		if (desert == null) {
			if (other.desert != null)
				return false;
		} else if (!desert.equals(other.desert))
			return false;
		if (Double.doubleToLongBits(discount) != Double
				.doubleToLongBits(other.discount))
			return false;
		if (glutenFree != other.glutenFree)
			return false;
		if (id != other.id)
			return false;
		if (isComplete != other.isComplete)
			return false;
		if (mainDish == null) {
			if (other.mainDish != null)
				return false;
		} else if (!mainDish.equals(other.mainDish))
			return false;
		if (mealOfTheWeek != other.mealOfTheWeek)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (starter == null) {
			if (other.starter != null)
				return false;
		} else if (!starter.equals(other.starter))
			return false;
		if (vegetarian != other.vegetarian)
			return false;
		return true;
	}
	

	public boolean isComplete() {
		return isComplete;
	}

	protected void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	
	
	
}
