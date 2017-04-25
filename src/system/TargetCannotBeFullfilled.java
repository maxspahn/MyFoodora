package system;

public class TargetCannotBeFullfilled extends Exception{
	private String message;
	
	public TargetCannotBeFullfilled(){
		this.setMessage("There is no value of the fee which can fulfill the target policy");
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

}
