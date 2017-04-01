package user_management;

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
