package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageInputText extends JFrame{
private String message;
	
	public MessageInputText(String title, String input){
		this.message = JOptionPane.showInputDialog(this,input,title,JOptionPane.QUESTION_MESSAGE);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
