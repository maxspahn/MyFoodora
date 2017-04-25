package system;

public class TargetCannotBeFullfilled extends Exception{
	
	private String message;

	public TargetCannotBeFullfilled() {
		this.setMessage("The target cannot be fullfilled with the values given, the fees have not been changed");
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
