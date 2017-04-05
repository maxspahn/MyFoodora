package user_management;

/** Exception which appears when the name of the restaurant does not exist in MyFoodora system.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class RestaurantNotFoundException extends Exception{
	private String message;
	
	public RestaurantNotFoundException(String restaurantName){
		this.setMessage("There exist no restaurant with that name : "+restaurantName);
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}






