package user_management;

import java.util.Date;

public class PhysicalUser extends User {
	private String surname;
	private Date birthdayDate;
	
	public PhysicalUser(String name, String userName, String passWord, String phone,
			String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.surname = "";
		this.setBirthdayDate(0,0,0);
	}

	//getters
	public String getSurname() {
		return surname;
	}
	public Date getBirthdayDate() {
		return birthdayDate;
	}

	//setters
	public void setBirthdayDate(int day, int month, int year) {
		Date birthDate = new Date(year - 1900, month -1 , day);
		this.birthdayDate = birthDate;
	}	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	
}
