package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelAddSingleItem extends JPanel{
	private JComboBox<String> typeComboBox;
	private JTextField textFieldName;
	private JTextField textFieldPrice;
	private JCheckBox checkBoxVegetarian;
	private JCheckBox checkBoxGluten;
	private JButton back;
	private JButton OkSingleItem;

	public PanelAddSingleItem(){
		setLayout(null);
		
		//Display a item creation message
		ImageIcon iconRegistration = new ImageIcon("SingleItemCreation.png");
		JLabel label = new JLabel("",iconRegistration,JLabel.CENTER);
		label.setBounds(700, 400, 1300, 200);
		add(label);
		
		//Choice of the kind of single item
		String[] types = {"Starter","Main Dish","Dessert"};
		this.typeComboBox = new JComboBox<String>(types);
		this.typeComboBox.setBounds(1200, 900, 300, 40);
		this.typeComboBox.setBackground(Color.white);
		
		//Display the text field to put the name
		JPanel namePanel = new JPanel();
		namePanel.setBorder(BorderFactory.createTitledBorder("Please define the name"));
		this.textFieldName = new JTextField("",20);
		this.textFieldName.setBackground(Color.lightGray);
		namePanel.add(textFieldName);
		namePanel.setBounds(1200,1000, 300, 60);
		namePanel.setBackground(Color.white);
		
		//Display the text field to put the price
		JPanel pricePanel = new JPanel();
		pricePanel.setBorder(BorderFactory.createTitledBorder("Please define the price"));
		this.textFieldPrice = new JTextField("",20);
		this.textFieldPrice.setBackground(Color.lightGray);
		pricePanel.add(textFieldPrice);
		pricePanel.setBounds(1200,1100, 300, 60);
		pricePanel.setBackground(Color.white);
		
		//Display the choice of the vegetarian
		this.checkBoxVegetarian = new JCheckBox("This single item is vegetarian");
		this.checkBoxVegetarian.setBounds(1200, 1250, 300, 30);
		
		//Display the choice of the vegetarian
		this.checkBoxGluten = new JCheckBox("This single item is gluten-free");
		this.checkBoxGluten.setBounds(1200, 1300, 300, 30);
		
		//Button "Back"
		this.back = new JButton("Back");
		this.back.setBounds(10,10, 100, 40);
		
		//Button "Ok"
		this.OkSingleItem = new JButton("OK");
		this.OkSingleItem.setBounds(1300,1400, 100, 60);
		
		
		add(typeComboBox);
		add(namePanel);
		add(pricePanel);
		add(checkBoxVegetarian);
		add(checkBoxGluten);
		add(this.back);
		add(this.OkSingleItem);
		
	}
	
	public static void main(String[] args) {
		PanelAddSingleItem panel = new PanelAddSingleItem();
		JFrame frame = new JFrame();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * @return the typeComboBox
	 */
	public JComboBox<String> getTypeComboBox() {
		return typeComboBox;
	}

	/**
	 * @return the textFieldName
	 */
	public JTextField getTextFieldName() {
		return textFieldName;
	}

	/**
	 * @return the textFieldPrice
	 */
	public JTextField getTextFieldPrice() {
		return textFieldPrice;
	}

	/**
	 * @return the checkBoxVegetarian
	 */
	public JCheckBox getCheckBoxVegetarian() {
		return checkBoxVegetarian;
	}

	/**
	 * @return the checkBoxGluten
	 */
	public JCheckBox getCheckBoxGluten() {
		return checkBoxGluten;
	}

	/**
	 * @return the back
	 */
	public JButton getBack() {
		return back;
	}

	/**
	 * @return the okSingleItem
	 */
	public JButton getOkSingleItem() {
		return OkSingleItem;
	}
	
}
