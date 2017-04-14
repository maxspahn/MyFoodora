package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JPanel;

import system.*;
import user_management.*;

public class Launch{
	private PanelCreator panelCreator;
	private MyFoodora myFoodora;
	private User currentUser;
	
	public Launch(){
		this.panelCreator = new PanelCreator(this);
		this.myFoodora = new MyFoodora();
		this.myFoodora.load();
	}
	
	public class LoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			panelCreator.getPanelFirstPage().setVisible(false);
			panelCreator.addLoginPanel();			
		}
	}
	public class RegisterListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			panelCreator.getPanelFirstPage().setVisible(false);
			panelCreator.addRegisterPanel();
			
		}
	}
	
	public class OkLoginListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Read what the user has input
			PanelLogin panelLogin = panelCreator.getPanelLogin();
			String userName = panelCreator.getPanelLogin().getTextFieldUserName().getText();
			String passWord = panelCreator.getPanelLogin().getTextFieldPassword().getText();
			
			//Try to login
			try{
				currentUser = myFoodora.loginUser(userName, passWord);
				if(currentUser instanceof Customer){
					panelLogin.setVisible(false);
					panelCreator.addCustomerPanel();
				}
			} catch (WrongUserNameOrPassWordException e1) {
				new ErrorLogin();
			}
		}
	}
	
	public class BackListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Stack activatedPanels = panelCreator.getActivatedPanels();
			if(activatedPanels.size()>1){
			JPanel currentPanel = (JPanel) activatedPanels.pop();
			JPanel lastPanel = (JPanel) activatedPanels.pop();
			currentPanel.setVisible(false);
			lastPanel.setVisible(true);
			activatedPanels.add(lastPanel);}
		}
	}

	public class NextListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			PanelRegister panelRegister = panelCreator.getPanelRegister();
			String userName = panelRegister.getTextFieldUserName().getText();
			String password = panelRegister.getTextFieldPassword().getText();
			if(userName.split(" ").length>1){
				new ErrorUserName();
			}
			else if(userName.equals("")){ //If there is no username
				new ErrorEmptyField("username");
			}
			else if(password.equals("")){ //If there is no password
				new ErrorEmptyField("password");
			}
			else{
				if(myFoodora.getManagerFactory().checkExistenceUserName(userName)){
					new ErrorSameUserName();
				}
				else{
				int userChoice = panelRegister.getUserComboBox().getSelectedIndex();
				if (userChoice==0){
					panelRegister.setVisible(false);
					panelCreator.addRegisterCustomerPanel();
				}
				else if(userChoice==1){
					panelRegister.setVisible(false);
					panelCreator.addRegisterCourierPanel();
				}
				else if(userChoice==2){
					panelRegister.setVisible(false);
					panelCreator.addRegisterManagerPanel();
					
				}
				else{
					panelRegister.setVisible(false);
					panelCreator.addRegisterRestaurantPanel();
				}
			}
			}
			
		}
	}
	
	public class RegisterManagerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageSave save = new MessageSave();
			int choice = save.getChoice();
			if (choice==0){
				PanelRegisterManager panelRegisterManager = panelCreator.getPanelRegisterManager();
				String userName = panelRegisterManager.getTextFieldUserName().getText();
				String passWord = panelRegisterManager.getTextFieldPassword().getText();
				String firstName = panelRegisterManager.getTextFieldFirstName().getText();
				String lastName = panelRegisterManager.getTextFieldLastName().getText();
				String email = panelRegisterManager.getTextFieldEmail().getText();
				String phone = panelRegisterManager.getTextFieldPhone().getText();
				String role = panelRegisterManager.getTextFieldRole().getText();
				if(firstName.equals("")){ //If there is no first name
					new ErrorEmptyField("first name");
				}
				else if(lastName.equals("")){ //If there is no last name
					new ErrorEmptyField("last name");
				}
				else if(role.equals("")){ //If there is no role
					new ErrorEmptyField("role");
				}
				else if(email.equals("")){ //If there is no email or phone
					new ErrorEmptyField("email");
				}
				else if(phone.equals("")){
					new ErrorEmptyField("phone");
				}
				else{
				try {
					int xAdress = Integer.parseInt(panelRegisterManager.getTextFieldAdressX().getText());
					int yAdress = Integer.parseInt(panelRegisterManager.getTextFieldAdressY().getText());
					int[] adress = {xAdress, yAdress};
					myFoodora.getManagerFactory().createAccount(lastName,userName,passWord, phone, email, adress);
					Manager man = (Manager) myFoodora.getUser(userName);
					man.setSurname(firstName);
					man.setRole(role);
					this.backToFirstPage();
					
				} catch (SameUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new ErrorAdress();
				}
				}
			}
			
			else if(choice==1){
				this.backToFirstPage();
			}
		}
		
		public void backToFirstPage(){
			Stack activatedPanels = panelCreator.getActivatedPanels();
			if(activatedPanels.size()>2){
			JPanel currentPanel = (JPanel) activatedPanels.pop(); //Get the current panel
			activatedPanels.pop();
			JPanel firstPanel = (JPanel) activatedPanels.pop(); //Get firstPagePanel
			currentPanel.setVisible(false);
			firstPanel.setVisible(true);
			activatedPanels.add(firstPanel);}
		}
	}
	
	public class RegisterCustomerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageSave save = new MessageSave();
			int choice = save.getChoice();
			if (choice==0){
				PanelRegisterCustomer panelRegisterCustomer = panelCreator.getPanelRegisterCustomer();
				String userName = panelRegisterCustomer.getTextFieldUserName().getText();
				String passWord = panelRegisterCustomer.getTextFieldPassword().getText();
				String firstName = panelRegisterCustomer.getTextFieldFirstName().getText();
				String lastName = panelRegisterCustomer.getTextFieldLastName().getText();
				String email = panelRegisterCustomer.getTextFieldEmail().getText();
				String phone = panelRegisterCustomer.getTextFieldPhone().getText();
				
				if(firstName.equals("")){ //If there is no first name
					new ErrorEmptyField("first name");
				}
				else if(lastName.equals("")){ //If there is no last name
					new ErrorEmptyField("last name");
				}
				else if(email.equals("")&&panelRegisterCustomer.getRadioButtonEmail().isSelected()){ //If there is no email or phone
					new ErrorEmptyField("email");
				}
				else if(phone.equals("")&&panelRegisterCustomer.getRadioButtonPhone().isSelected()){
					new ErrorEmptyField("phone");
				}
				else{
				try {
					int xAdress = Integer.parseInt(panelRegisterCustomer.getTextFieldAdressX().getText());
					int yAdress = Integer.parseInt(panelRegisterCustomer.getTextFieldAdressY().getText());
					int[] adress = {xAdress, yAdress};
					myFoodora.getCustomerFactory().createAccount(lastName,userName,passWord, phone, email, adress);
					Customer cust = (Customer) myFoodora.getUser(userName);
					cust.setSurname(firstName);
					cust.setSpamAgreement(panelRegisterCustomer.getCheckBoxOffers().isSelected());
					//Fidelity card choice
					if(panelRegisterCustomer.getRadioButtonBasic().isSelected()){
						cust.setFidelityCard("basicfidelitycard");
					}else if(panelRegisterCustomer.getRadioButtonPoint().isSelected()){
						cust.setFidelityCard("pointfidelitycard");
					}else if(panelRegisterCustomer.getRadioButtonLottery().isSelected()){
						cust.setFidelityCard("lotteryfidelitycard");
					}
					
					if(panelRegisterCustomer.getRadioButtonEmail().isSelected()){
						cust.setContactForOffers("email");
					} else if( panelRegisterCustomer.getRadioButtonPhone().isSelected()){
						cust.setContactForOffers("phone");
					}else if( panelRegisterCustomer.getRadioButtonLetter().isSelected()){
						cust.setContactForOffers("letter");
					}
					(new RegisterManagerListener()).backToFirstPage();
					
				} catch (SameUserNameException e) {
					e.getMessage();
				} catch (UserNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new ErrorAdress();
				} catch(FidelityCardDoesNotExistException e){
					e.getMessage();
				} catch(KindOfContactDoesNotExistException e){
					e.getMessage();
				}
				}
			}
			
			else if(choice==1){
				(new RegisterManagerListener()).backToFirstPage();
			}
		}
		
	}
	
	public class RegisterRestaurantListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageSave save = new MessageSave();
			int choice = save.getChoice();
			if (choice==0){
				PanelRegisterRestaurant panelRegisterRestaurant = panelCreator.getPanelRegisterRestaurant();
				String userName = panelRegisterRestaurant.getTextFieldUserName().getText();
				String passWord = panelRegisterRestaurant.getTextFieldPassword().getText();
				String name = panelRegisterRestaurant.getTextFieldName().getText();
				String email = panelRegisterRestaurant.getTextFieldEmail().getText();
				String phone = panelRegisterRestaurant.getTextFieldPhone().getText();
				if(name.equals("")){ //If there is no first name
					new ErrorEmptyField("name");
				}
				else if(email.equals("")){ //If there is no email or phone
					new ErrorEmptyField("email");
				}
				else if(phone.equals("")){
					new ErrorEmptyField("phone");
				}
				else{
				try {
					int xAdress = Integer.parseInt(panelRegisterRestaurant.getTextFieldAdressX().getText());
					int yAdress = Integer.parseInt(panelRegisterRestaurant.getTextFieldAdressY().getText());
					int[] adress = {xAdress, yAdress};
					myFoodora.getRestaurantFactory().createAccount(name,userName,passWord, phone, email, adress);
					(new RegisterManagerListener()).backToFirstPage();
				} catch (SameUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new ErrorAdress();
				}
				}
			}
			
			else if(choice==1){
				(new RegisterManagerListener()).backToFirstPage();
			}
			
		}
		
	}
	
	public class RegisterCourierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageSave save = new MessageSave();
			int choice = save.getChoice();
			if (choice==0){
				PanelRegisterCourier panelRegisterCourier = panelCreator.getPanelRegisterCourier();
				String userName = panelRegisterCourier.getTextFieldUserName().getText();
				String passWord = panelRegisterCourier.getTextFieldPassword().getText();
				String firstName = panelRegisterCourier.getTextFieldFirstName().getText();
				String lastName = panelRegisterCourier.getTextFieldLastName().getText();
				String email = panelRegisterCourier.getTextFieldEmail().getText();
				String phone = panelRegisterCourier.getTextFieldPhone().getText();
				if(firstName.equals("")){ //If there is no first name
					new ErrorEmptyField("first name");
				}
				else if(lastName.equals("")){ //If there is no last name
					new ErrorEmptyField("last name");
				}
				else if(email.equals("")){ //If there is no email or phone
					new ErrorEmptyField("email");
				}
				else if(phone.equals("")){
					new ErrorEmptyField("phone");
				}
				else{
				try {
					int xAdress = Integer.parseInt(panelRegisterCourier.getTextFieldAdressX().getText());
					int yAdress = Integer.parseInt(panelRegisterCourier.getTextFieldAdressY().getText());
					int[] adress = {xAdress, yAdress};
					myFoodora.getCourierFactory().createAccount(lastName,userName,passWord, phone, email, adress);
					Courier cour = (Courier) myFoodora.getUser(userName);
					cour.setSurname(firstName);
					cour.setAvailability(panelCreator.getPanelRegisterCourier().getCheckBoxAvailability().isSelected());
					(new RegisterManagerListener()).backToFirstPage();
					
				} catch (SameUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new ErrorAdress();
				}
				}
			}
			
			else if(choice==1){
				(new RegisterManagerListener()).backToFirstPage();
			}
			
		}
		
	}
	
	public class OrderListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class LogoutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public class BasicFidelityCardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			if (cust.getFidelityCard() instanceof BasicFidelityCard){
				panelCreator.getPanelCustomer().getText().setText("You already have a basic fidelity card.");
			}
			else{
				try {
					cust.setFidelityCard("basicfidelitycard");
					panelCreator.getPanelCustomer().getText().setText("Your fidelity card is now a basic one.");
				} catch (FidelityCardDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class PointFidelityCardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer cust = (Customer) currentUser;
			if (cust.getFidelityCard() instanceof PointFidelityCard){
				panelCreator.getPanelCustomer().getText().setText("You already have a point fidelity card.");
			}
			else{
				try {
					cust.setFidelityCard("pointfidelitycard");
					panelCreator.getPanelCustomer().getText().setText("Your fidelity card is now a point one.");
				} catch (FidelityCardDoesNotExistException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	
	public class LotteryFidelityCardListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer cust = (Customer) currentUser;
			if (cust.getFidelityCard() instanceof LotteryFidelityCard){
				panelCreator.getPanelCustomer().getText().setText("You already have a lottery fidelity card.");
			}
			else{
				try {
					cust.setFidelityCard("lotteryfidelitycard");
					panelCreator.getPanelCustomer().getText().setText("Your fidelity card is now a lottery one.");
				} catch (FidelityCardDoesNotExistException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
	/**
	 * @return the panelCreator
	 */
	public PanelCreator getPanelCreator() {
		return panelCreator;
	}


	public static void main(String[] args) {
		Launch launch = new Launch();
		
		launch.getPanelCreator().createAllPanels();
		
	}
}
