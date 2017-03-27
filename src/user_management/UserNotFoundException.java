package user_management;

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
