package restaurant;

import java.io.Serializable;

/** FullMeal is an extension to the Meal class.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class FullMeal extends Meal implements Serializable{
	

	public FullMeal(String name) {
		super(name);
	}

	
	/** Adds an item to the fullmeal. Checks if this item-type already exists in the meal and modifies the glutenFree and vegetarian status.
	 * In case that a wrong item has been added, an Exception is added.
	 * 
	 * @param item SingleItem to be added.
	 * @throws WrongItemAdded
	 * 
	 */
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

	/** Calculates the price of the meal, the sum of the ingredients with the reduction of 5%.
	 * 
	 */
	@Override
	public double getPrice() {
		double price = 0;
		if (this.getStarter()!=null){
		price += this.getStarter().getPrice();}
		if (this.getMainDish()!=null){
		price += this.getMainDish().getPrice();}
		if (this.getDesert()!=null){
		price += this.getDesert().getPrice();}
		price *= 0.95;
		price = ((double) Math.round(price * 100))/100;
		return price;
	}

	/** Removes one singleItem from the meal, and sets the complete-status to false. Checks if the item that is removed, is in the meal.
	 * 
	 * @param item SingleItem to be removed.
	 * @throws WrongItemRemoved
	 */
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
