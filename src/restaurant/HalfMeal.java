package restaurant;

/** HalfMeal is one type of meal, it extends the abstract Meal Class.
 * @author maxspahn
 *
 */
public class HalfMeal extends Meal{

	public HalfMeal(String name) {
		super(name);
	}

	@Override
	public void addItem(SingleItem item) throws WrongItemAdded {
		if(item instanceof Starter && this.getStarter() == null && this.getDesert() == null) {
			this.setStarter((Starter) item);
			this.checkVegieGluten(item);
			if(this.getMainDish() != null){
				this.setComplete(true);
			}
		}
		else if(item instanceof MainDish && this.getMainDish() == null) {
			this.setMainDish((MainDish) item);
			this.checkVegieGluten(item);
			if(this.getStarter() != null || this.getDesert() != null){
				this.setComplete(true);
			}
		}
		else if(item instanceof Desert && this.getStarter() == null && this.getDesert() == null) {
			this.setDesert((Desert) item);
			this.checkVegieGluten(item);
			if(this.getMainDish() != null){
				this.setComplete(true);
			}
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
		if (this.getMainDish() != null){price += this.getMainDish().getPrice();}
		return price * 0.95;
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
