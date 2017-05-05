package commandLineTool;

public class OrderDoesNotExist extends Exception {
	
	private String message;
	
	public OrderDoesNotExist(String orderName) {
		this.setMessage("Order, " + orderName + ", does not exist");
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}


}
