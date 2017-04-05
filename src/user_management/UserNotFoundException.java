package user_management;

/** Exception which appears when the name of the user does not exist in MyFoodora system.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class UserNotFoundException extends Exception {
	private String message;
	
	public UserNotFoundException(String userName){
		this.setMessage("There exist no user with that name : "+userName);
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
