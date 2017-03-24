package user_management;

public class SameUserNameException extends Exception{
	public SameUserNameException(){
		System.out.println("This user name already exists.");
	}

}
