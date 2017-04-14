package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
