package system;

/**Exception which appears when someone tries to save an order which is not complete yet.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class OrderNotCompletException extends Exception{
	
	private String message;

	public OrderNotCompletException() {
		this.setMessage("You cannot finish the order because it is not paid/finished");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
