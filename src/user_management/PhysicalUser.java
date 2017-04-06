package user_management;

import java.io.Serializable;
import java.util.Date;

/** A physical user has a name, surname, birthdaydate, username, password, phone number, email and an adress.
 * It extends the User class.
 * 
 * @author maxspahn
 * @author jeremyaugot
 */
public class PhysicalUser extends User implements Serializable {
	private String surname;
	private Date birthdayDate;
	
	public PhysicalUser(String name, String userName, String passWord, String phone,
			String email, int[] adress){
		super(name, userName, passWord, phone, email, adress);
		this.surname = "";
		this.setBirthdayDate(0,0,0);
	}


	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}

	/**
	 * @return the birthdayDate
	 */
	public Date getBirthdayDate() {
		return birthdayDate;
	}


	/**
	 * @param day Integer
	 * @param month Integer
	 * @param year Integer
	 */
	public void setBirthdayDate(int day, int month, int year) {
		Date birthDate = new Date(year - 1900, month -1 , day);
		this.birthdayDate = birthDate;
	}


	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}	
	
	
}
