package restaurant;

public class HalfMeal extends Meal{

	public HalfMeal(String name) {
		super(name);
	}

	@Override
	public void addItem(SingleItem item) throws WrongItemAdded {
		if(item instanceof Starter && this.getStarter() == null && this.getDesert() == null) {
			this.setStarter((Starter) item);
			this.checkVegieGluten(item);
		}
		else if(item instanceof MainDish && this.getMainDish() == null) {
			this.setMainDish((MainDish) item);
			this.checkVegieGluten(item);
		}
		else if(item instanceof Desert && this.getStarter() == null && this.getDesert() == null) {
			this.setStarter((Starter) item);
			this.checkVegieGluten(item);
		}
		else{
			throw new WrongItemAdded();
		}
		
	}

	@Override
	public double getPrice() {
		double price = 0;
		if(this.getStarter() != null) {price += this.getStarter().getPrice();}
		if(this.getDesert() != null) {price += this.getDesert().getPrice();}
		price += this.getMainDish().getPrice();
		if(this.isMealOfTheWeek()) {price *= (1-this.getDiscount());}
		else {price *= 0.95;}
		return price;
	}

}
