package scenario;

import java.util.Date;
import java.util.Scanner;

import system.MyFoodora;
import user_management.*;

public class CourierQuestions extends CommonQuestions {

	public String eMail;
	public String phone;
	public String firstName;
	
	
	public User askCourierQuestions(MyFoodora myFoodora){
		
		User currentUser = null;
		System.out.println("Please answer the following question to fill in your account");
		
		System.out.println("First name");
		firstName = scanner.nextLine();
		this.askCommonQuestions(myFoodora);
		
		System.out.println("Your birth date, day");
		int day = scanner.nextInt();
		System.out.println("Your birth date , month");
		int month = scanner.nextInt();
		System.out.println("Your birth date , year");
		int year = scanner.nextInt();
		scanner.nextLine();
		
		// 3.
		boolean interrupt = false;
		do{		
			interrupt = false;
			System.out.println("Phone-Number, type 'q' to interrupt");
			phone = scanner.nextLine();
			if(phone.equals("q")){interrupt = true;continue;}
			System.out.println("Email-adress, type 'q' to interrupt");
			eMail = scanner.nextLine();
			if(eMail.equals("q")){interrupt = true;}
		} while(interrupt);

		boolean existant = true;
		while(existant){
			existant = false;
			try {
				System.out.println("Username");
				userName = scanner.nextLine();
				myFoodora.getCourierFactory().createAccount(name, userName, password, phone, eMail, adress);
				currentUser = myFoodora.getUser(userName);
			} catch (SameUserNameException e) {
				existant = true;
				System.out.println(e.getMessage());
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		((Courier) currentUser).setBirthdayDate(day, month, year);
		((Courier) currentUser).setSurname(firstName);
		
		// 6.
		System.out.println("Do you want to start right now? (y,n)");
		String onDuty = scanner.nextLine();
		if(onDuty.equalsIgnoreCase("y")){((Courier) currentUser).setAvailability(true);}
		
		
		return currentUser;
	}

}
