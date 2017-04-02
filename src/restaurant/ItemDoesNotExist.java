package restaurant;

/** Exception if the user try to access to an item that does not exists.
 * 
 * @author maxspahn
 *
 */
public class ItemDoesNotExist extends Exception {
	
	private String message;

	/** Constructor. The name of the wrong item is included is included in the message.
	 * 
	 * @param itemName Name of the item that the user tried to use.
	 */
	public ItemDoesNotExist(String itemName) {
		this.setMessage("There exist no item with that name : " + itemName);
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
