package restaurant;

public class WrongItemRemoved extends Exception {
	
	private String message;

	public WrongItemRemoved(String singleItemName) {
		this.setMessage("You cannot remove this item : " + singleItemName);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
}
