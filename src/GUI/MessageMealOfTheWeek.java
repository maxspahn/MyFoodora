package GUI;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import system.MyFoodora;
import user_management.*;

public class MessageMealOfTheWeek extends JFrame{
	private String choice;
	
	public MessageMealOfTheWeek(Restaurant rest){
		int size = rest.getMenu().getMeals().size();
		String[] displayedList = new String[size];
		for (int i = 0; i < size; i++) {
			displayedList[i] = rest.getMenu().getMeals().get(i).getName();
		}
		this.choice = (String) JOptionPane.showInputDialog(this,"Choose the meal of the week","Edit menu",JOptionPane.QUESTION_MESSAGE,null,displayedList,displayedList[0]);
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
		new MessageMealOfTheWeek(myFoodora.getListRestaurant().get(0));
	}
	
}
