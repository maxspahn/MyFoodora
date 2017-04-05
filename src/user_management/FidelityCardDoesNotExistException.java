package user_management;

/** Exception which appears when the user enter the name of a fidelity card which does not exist.
 * 
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class FidelityCardDoesNotExistException extends Exception{
	private String message;
	
	public FidelityCardDoesNotExistException(){
		this.setMessage("This fidelity card does not exist.");
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
