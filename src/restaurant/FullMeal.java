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
		
		if(this.getStarter() != null && this.getMainDish() != null && this.getDesert() != null){
			this.setComplete(true);
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
		return price;
	}

	@Override
	public void removeItem(SingleItem item) throws WrongItemRemoved {
		
		if(item instanceof Starter && this.getStarter().equals(item)){
			this.setStarter(null);
			this.setComplete(false);
		}
		else if(item instanceof MainDish && this.getMainDish().equals(item)){
			this.setMainDish(null);
			this.setComplete(false);
		}
		else if(item instanceof Desert && this.getDesert().equals(item)){
			this.setDesert(null);
			this.setComplete(false);
		}
		else{
			throw new WrongItemRemoved(item.getName());
		}
		
	}
	
	

}
