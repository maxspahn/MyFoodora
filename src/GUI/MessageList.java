package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import user_management.Restaurant;

/**The MessageList class display a question message, with a given title and message, and the list to display, containing the elements to choose. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessageList extends JFrame{
	
private String choice;
	
	public MessageList(String title, String message, String[] list){
		this.choice = (String) JOptionPane.showInputDialog(this,message,title,JOptionPane.QUESTION_MESSAGE,null,list,null);
	}

	/**
	 * @return the choice
	 */
	public String getChoice() {
		return choice;
	}

}
