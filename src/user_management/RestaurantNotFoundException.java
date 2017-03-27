package user_management;

public class RestaurantNotFoundException extends Exception{
	private String message;
	
	public RestaurantNotFoundException(String restaurantName){
		this.setMessage("There exist no restaurant with that name : "+restaurantName);
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}






