package user_management;

public class FidelityCardDoesNotExistException extends Exception{
	private String message;
	
	public FidelityCardDoesNotExistException(){
		this.setMessage("This fidelity card does not exist.");
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
