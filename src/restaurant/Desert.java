package restaurant;

/** Desert implementation. Attention the name is incorrectly spelled!
 * 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class Desert extends SingleItem{

	/** Constructor.
	 * @param name The name of the dessert.
	 */
	public Desert(String name) {
		super(name);
	}
	
	/** Overriding of the equals method to assure the good comparison of two objects. 
	 * 
	 **/
	public boolean equals(Object object){
		if(object instanceof Desert){
			Desert desert = (Desert) object;
			if(desert.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}
	
	

}
