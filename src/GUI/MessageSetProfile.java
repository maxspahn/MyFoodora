package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
