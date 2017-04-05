package user_management;

/** Exception which appears when the user enter a kind of contact which does not exist.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class KindOfContactDoesNotExistException extends Exception{
	private String message;
	
	public KindOfContactDoesNotExistException(){
		this.setMessage("You cannot be notified through this way.");
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
