package GUI;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The MessageSave class display a question message to ask if the user wants to save the account. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessageSave extends JFrame{
	private int choice;
	public MessageSave(){
		this.choice = JOptionPane.showOptionDialog(this, "Do you want to save?", "Save",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null,null,null);
	}
	
	public static void main(String[] args) {
		new MessageSave();
	}

	/**
	 * @return the choice
	 */
	public int getChoice() {
		return choice;
	}

}
