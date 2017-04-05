package scenario;

import java.util.Scanner;

import system.MyFoodora;
import user_management.Customer;
import user_management.KindOfContactDoesNotExistException;
import user_management.SameUserNameException;
import user_management.User;
import user_management.UserNotFoundException;

public class CustomerQuestions extends CommonQuestions {
	
	public String firstName;
	public boolean spamAgreement;
	public String eMail;
	public String phone;
	public String howTo;
	
	
	

	public User askCustomerQuestions(MyFoodora myFoodora){
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
			if(phone.equals("q")){interrupt = true; continue;}
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
				myFoodora.getCustomerFactory().createAccount(name, userName, password, phone, eMail, adress);
				currentUser = myFoodora.getUser(userName);
			} catch (SameUserNameException e) {
				existant = true;
				System.out.println(e.getMessage());
			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		((Customer) currentUser).setBirthdayDate(day, month, year);
		((Customer) currentUser).setSurname(firstName);
		
		// 4.
		System.out.println("Do you want to be informed about news of your restaurants? (y,n)");
		String spam = scanner.nextLine();
		if(spam.equalsIgnoreCase("y")){
			((Customer) currentUser).setSpamAgreement(true);
			boolean falseinput = true;
			while(falseinput){
				falseinput = false;
				// 5.
				System.out.println("How do you want to be informed about news, Mail ('email'), phone ('phone')");
				howTo = scanner.nextLine();
				try {
					((Customer) currentUser).setContactForOffers(howTo);
				} catch (KindOfContactDoesNotExistException e) {
					falseinput = true;
					e.getMessage();
				}
			}				
		}
		
		
		return currentUser;
		
	}

}
