package user_management;

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
