package system;

/**Exception which appears when a user tries to remove an item from an order which does not contain it.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class ItemNotInOrderException extends Exception{
	private String message;

	public ItemNotInOrderException() {
		this.setMessage("This item is not in the order.");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
