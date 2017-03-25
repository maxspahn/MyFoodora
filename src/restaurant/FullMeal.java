package restaurant;

public class FullMeal extends Meal{

	public FullMeal(String name) {
		super(name);
	}

	@Override
	public void addItem(SingleItem item) throws WrongItemAdded {
		
		if(item instanceof Starter && this.getStarter() == null){
			Starter starter= (Starter) item;
			this.setStarter(starter);
			this.checkVegieGluten(item);
		}
		else if(item instanceof MainDish && this.getMainDish() == null){
			MainDish mainDish = (MainDish) item;
			this.setMainDish(mainDish);
			this.checkVegieGluten(item);
		}
		else if(item instanceof Desert && this.getDesert() == null){
			Desert desert = (Desert) item;
			this.setDesert(desert);
			this.checkVegieGluten(item);
		}
		else{
			throw new WrongItemAdded();
		}
		
	}

	@Override
	public double getPrice() {
		double price = 0;
		if (this.getStarter()!=null){
		price += this.getStarter().getPrice();}
		if (this.getMainDish()!=null){
		price += this.getMainDish().getPrice();}
		if (this.getDesert()!=null){
		price += this.getDesert().getPrice();}
		if(this.isMealOfTheWeek()) {price *= (1-this.getDiscount());}
		else {price *= 0.95;}
		return price;
	}
	
	

}
