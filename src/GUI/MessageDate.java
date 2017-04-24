package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import system.MyFoodora;
import user_management.Restaurant;

public class MessageDate extends JFrame{

	private String choice;
	
	public MessageDate(Restaurant rest){
		
	}

	/**
	 * @return the choice
	 */
	public String getChoice() {
		return choice;
	}
	
	public static void main(String[] args) {
		MyFoodora myFoodora = new MyFoodora();
		myFoodora.load();
		new MessageDate(myFoodora.getListRestaurant().get(0));
	}
}
