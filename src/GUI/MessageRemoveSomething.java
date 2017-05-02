package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import system.Order;

/**The MessageRemoveSomething class display a question message, with a given order containing the lists of the chosen single items and meals. It extends JFrame.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class MessageRemoveSomething extends JFrame{
	private String choice;
	
	public MessageRemoveSomething(Order order){
		int size = order.getSingleItems().size()+order.getMeals().size();
		String[] list = new String[size];
		
		for (int i = 0; i < order.getSingleItems().size(); i++) {
			list[i] = order.getSingleItems().get(i).getName();
		}
		for (int j = order.getSingleItems().size(); j < size; j++){
			list[j] = order.getMeals().get(j-order.getSingleItems().size()).getName();
		}
		this.choice = (String) JOptionPane.showInputDialog(this,"Remove Something from the order","Remove something",JOptionPane.QUESTION_MESSAGE,null, list,list[0]);
	}
	
	public String getChoice(){
		return choice;
	}
}
