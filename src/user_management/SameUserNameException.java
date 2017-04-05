package user_management;

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
