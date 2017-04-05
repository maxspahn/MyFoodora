package system;

/**Exception which appears when there is no order in a month.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class NoOrderInMonth extends Exception {
	
	private String message;

	public NoOrderInMonth(int month, int year) {
		this.setMessage("Year : " + year + " Month : " + month);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
