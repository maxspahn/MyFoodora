package system;

/**Exception which appears when no courier has been found to deliver an order.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class NoCourierFoundToDeliver extends Exception{
	
	private String message;

	public NoCourierFoundToDeliver() {
		this.setMessage("MyFoodora was not able to allocate a courier to your order");
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
