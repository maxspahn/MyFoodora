package user_management;

/** Exception which appears when the user cannot connect with this password and username.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class WrongUserNameOrPassWordException extends Exception{
	private String message;
	
	public WrongUserNameOrPassWordException(){
		this.setMessage("Unfortunately your user name or your password is false.");
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
