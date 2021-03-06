package system;

import java.io.Serializable;

public class TargetCannotBeFullfilled extends Exception{
	private String message;
	
	public TargetCannotBeFullfilled(){
		this.setMessage("There is no value for the fee which can fulfill the target policy");
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
