package restaurant;

/** Exception which appears when a user try to remove an item to a meal even if it is not possible.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
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
