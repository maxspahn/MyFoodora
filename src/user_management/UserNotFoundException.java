package user_management;

public class UserNotFoundException extends Exception {
	private String userName;
	public UserNotFoundException(String userName){
		System.out.println("There is no user with that user name: "+userName);
		this.userName = userName;
	}
	
	public String getMessage(){
		return "There is no user with that user name: "+userName;
	}
}
