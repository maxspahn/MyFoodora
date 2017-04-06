package restaurant;

import java.io.Serializable;

/** Main Dish is a type of SingleItem and extends the class SingleItem.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class MainDish extends SingleItem implements Serializable{

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
