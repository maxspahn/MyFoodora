package system;

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
