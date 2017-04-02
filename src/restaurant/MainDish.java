package restaurant;

/** Main Dish is a type of SingleItem and extends the class SingleItem.
 * @author maxspahn
 *
 */
public class MainDish extends SingleItem{

	/**Constructor.
	 * 
	 * @param name Name of the MainDish.
	 */
	public MainDish(String name) {
		super(name);
	}
	
	 /** Compare to MainDish according to their name.
	  */
	public boolean equals(Object object){
		if(object instanceof MainDish){
			MainDish mainDish = (MainDish) object;
			if(mainDish.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}

}
