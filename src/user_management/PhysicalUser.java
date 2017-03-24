package user_management;

import java.util.Date;

public class PhysicalUser extends User {
	private String surname;
	private Date birthdayDate;
	
	public PhysicalUser(String name, String userName, String passWord, String phone,
			String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.surname = "";
		this.birthdayDate = new Date(0,0,0);
	}

	//getters
	public String getSurname() {
		return surname;
	}
	public Date getBirthdayDate() {
		return birthdayDate;
	}

	//setters
	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
