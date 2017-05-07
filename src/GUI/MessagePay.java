package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**The MessagePay class display a question message, with a given double which represents the price of the order. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessagePay extends JFrame{
	private int choice; 
	public MessagePay(double price){
		String message = "<html>Total price: "+price+"€<br>";
		message+="Do you want to pay?"+"<html>";
		this.choice = JOptionPane.showOptionDialog(this, message, "Pay",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null,null,null);
	}
	/**
	 * @return the choice
	 */
	public int getChoice() {
		return choice;
	}
	
	public static void main(String[] args) {
		new MessagePay(31);
	}
}
