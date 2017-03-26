package restaurant;

public class ItemDoesNotExist extends Exception {
	
	private String message;

	public ItemDoesNotExist(String itemName) {
		this.setMessage("There exist no item with that name : " + itemName);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
