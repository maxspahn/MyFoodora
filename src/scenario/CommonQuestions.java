package scenario;

import java.util.Scanner;

import system.MyFoodora;

public class CommonQuestions {
	
	public String name;
	public String userName;
	public int [] adress = {0,0};
	public String password;
	public Scanner scanner = new Scanner(System.in);
	

	public void askCommonQuestions(MyFoodora myFoodora){
		// 2.
		System.out.println("Name");
		name = scanner.nextLine();
		System.out.println("Your adress x-Coordinate");
		int x = scanner.nextInt();
		System.out.println("Your adress y-Coordinate");
		int y = scanner.nextInt();
		adress[0] = x;
		adress[1] = y;
		
		scanner.nextLine();
		// 3.
		String exit = "x";		
		while (!exit.equals(password) || exit.equalsIgnoreCase("new")){
			System.out.println("Your password");
			password = scanner.nextLine();
			System.out.println("Please repeat your password, 'new' if you want a new password");
			exit = scanner.nextLine();			
		}
	}

}
