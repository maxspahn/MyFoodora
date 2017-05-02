package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The MessageSetProfile class display a question message, with a given contact (email, phone, adress) to set. It asks the user to enter the new contact. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessageSetProfile extends JFrame{
	private String message;
	
	public MessageSetProfile(String contact){
		this.message = JOptionPane.showInputDialog(this,"New "+contact,"Set profile",JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
}
