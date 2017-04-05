package user_management;

/** Exception which appears when a user is trying to choose a username which already exists.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class SameUserNameException extends Exception{
	
	private String message;
	
	public SameUserNameException(){
		this.setMessage("This user name already exists.");
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

}
