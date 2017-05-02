package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The MessageInputText class display a question message, with a given title and input message to display. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
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
