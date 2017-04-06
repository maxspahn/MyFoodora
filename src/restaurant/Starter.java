package restaurant;

import java.io.Serializable;

/** Starter implementation. Attention the name is incorrectly spelled!
 * 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class Starter extends SingleItem implements Serializable{
	
	/** Constructor. 
	 * @param name Name of the starter.
	 */
	public Starter(String name) {
		super(name);
	}

	/** Overriding of the equals method to assure the good comparison of two objects. 
	 * 
	 **/
	public boolean equals(Object object){
		if(object instanceof Starter){
			Starter starter = (Starter) object;
			if(starter.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}



}
