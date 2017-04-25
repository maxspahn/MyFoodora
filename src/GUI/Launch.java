package GUI;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import restaurant.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Stack;
import java.util.TreeSet;

import javax.swing.JFrame;
import javax.swing.JPanel;

import system.*;
import user_management.*;

public class Launch{
	private PanelCreator panelCreator;
	private MyFoodora myFoodora;
	private User currentUser;
	private Order currentOrder;
	private String orderedItems;
	
	public Launch(){
		this.panelCreator = new PanelCreator(this);
		this.myFoodora = new MyFoodora();
		this.myFoodora.load();
		this.orderedItems = "<html>";
	}
	
	public void backToFirstPage(){
		Stack activatedPanels = panelCreator.getActivatedPanels();
		if(activatedPanels.size()>2){
		JPanel currentPanel = (JPanel) activatedPanels.pop(); //Get the current panel
		activatedPanels.pop();
		JPanel firstPanel = (JPanel) activatedPanels.pop(); //Get firstPagePanel
		currentPanel.setVisible(false);
		if(firstPanel instanceof PanelManager){
			panelCreator.getMenuBarManager().setVisible(true);
		}
		firstPanel.setVisible(true);
		activatedPanels.add(firstPanel);}
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
					panelCreator.createCustomerPanel();
					Customer cust = (Customer) currentUser;
					panelLogin.setVisible(false);
					panelCreator.addCustomerPanel();
					panelCreator.getPanelCustomer().getText().setText("Welcome "+ cust.getName()+" "+cust.getSurname());
				}
				else if(currentUser instanceof Restaurant){
					Restaurant rest = (Restaurant) currentUser;
					panelLogin.setVisible(false);
					panelCreator.addRestaurantPanel();
					panelCreator.getPanelRestaurant().getText().setText("Welcome "+ rest.getName());
				}
				else if(currentUser instanceof Manager){
					Manager man = (Manager) currentUser;
					panelLogin.setVisible(false);
					panelCreator.addManagerPanel();
					panelCreator.getPanelManager().getText().setText("Welcome "+man.getName()+" "+man.getSurname());
				}
			} catch (WrongUserNameOrPassWordException e1) {
				new Error("Error login","Unfortunately your username or your password is wrong.");
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
			if (lastPanel instanceof PanelCustomer){
				panelCreator.getMenuBarCustomer().setVisible(true);
				currentOrder = null;
				panelCreator.getPanelCustomerOrder().getTextOrderPanel().setText("");
				panelCreator.getPanelCustomerOrder().getTextCustomerPanel().setText("");
				orderedItems = "<html>";
				panelCreator.getPanelCustomer().getText().setText("Welcome "+currentUser.getName()+" "+((Customer)currentUser).getSurname());
			}
			else if(lastPanel instanceof PanelRestaurant){
				panelCreator.getMenuBarRestaurant().setVisible(true);
				panelCreator.getPanelCustomer().getText().setText("Welcome "+currentUser.getName());
				panelCreator.getPanelCreateMeal().getTextCreateMealPanel().setText("");
			}
			else if(lastPanel instanceof PanelManager){
				panelCreator.getMenuBarManager().setVisible(true);
				panelCreator.getPanelManager().getText().setText("Welcome "+currentUser.getName()+" "+((Manager)currentUser).getSurname());
			}
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
				new Error("Error Username","Spaces in the username are not allowed.");
			}
			else if(userName.equals("")){ //If there is no username
				new Error("Empty Field Error","The field username must be completed.");
			}
			else if(password.equals("")){ //If there is no password
				new Error("Empty Field Error","The field password must be completed.");
			}
			else{
				if(myFoodora.getManagerFactory().checkExistenceUserName(userName)){
					new Error("Same username Error","This username already exists.");
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
					new Error("Empty Field Error","The field first name must be completed.");
				}
				else if(lastName.equals("")){ //If there is no last name
					new Error("Empty Field Error","The field last name must be completed.");
				}
				else if(role.equals("")){ //If there is no role
					new Error("Empty Field Error","The field role must be completed.");
				}
				else if(email.equals("")){ //If there is no email or phone
					new Error("Empty Field Error","The field email must be completed.");
				}
				else if(phone.equals("")){
					new Error("Empty Field Error","The field phone must be completed.");
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
					backToFirstPage();
					
				} catch (SameUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new Error("Adress Error","The adress coordinates must be integers.");
				}
				}
			}
			
			else if(choice==1){
				backToFirstPage();
			}
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
					new Error("Empty Field Error","The field first name must be completed.");
				}
				else if(lastName.equals("")){ //If there is no last name
					new Error("Empty Field Error","The field last name must be completed.");
				}
				else if(email.equals("")&&panelRegisterCustomer.getRadioButtonEmail().isSelected()){ //If there is no email or phone
					new Error("Empty Field Error","The field email must be completed.");
				}
				else if(phone.equals("")&&panelRegisterCustomer.getRadioButtonPhone().isSelected()){
					new Error("Empty Field Error","The field phone must be completed.");
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
					backToFirstPage();
					
				} catch (SameUserNameException e) {
					e.getMessage();
				} catch (UserNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new Error("Adress Error","The adress coordinates must be integers.");
				} catch(FidelityCardDoesNotExistException e){
					e.getMessage();
				} catch(KindOfContactDoesNotExistException e){
					e.getMessage();
				}
				}
			}
			else if(choice==1){
				backToFirstPage();
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
					new Error("Empty Field Error","The field name must be completed.");
				}
				else if(email.equals("")){ //If there is no email or phone
					new Error("Empty Field Error","The field email must be completed.");
				}
				else if(phone.equals("")){
					new Error("Empty Field Error","The field phone must be completed.");
				}
				else{
				try {
					int xAdress = Integer.parseInt(panelRegisterRestaurant.getTextFieldAdressX().getText());
					int yAdress = Integer.parseInt(panelRegisterRestaurant.getTextFieldAdressY().getText());
					int[] adress = {xAdress, yAdress};
					myFoodora.getRestaurantFactory().createAccount(name,userName,passWord, phone, email, adress);
					backToFirstPage();
				} catch (SameUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new Error("Adress Error","The adress coordinates must be integers.");
				}
				}
			}
			else if(choice==1){
				backToFirstPage();
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
					new Error("Empty Field Error","The field first name must be completed.");
				}
				else if(lastName.equals("")){ //If there is no last name
					new Error("Empty Field Error","The field last name must be completed.");
				}
				else if(email.equals("")){ //If there is no email or phone
					new Error("Empty Field Error","The field email must be completed.");
				}
				else if(phone.equals("")){
					new Error("Empty Field Error","The field phone must be completed.");
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
					backToFirstPage();
					
				} catch (SameUserNameException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UserNotFoundException e){
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch(NumberFormatException e){
					new Error("Adress Error","The adress coordinates must be integers.");
				}
				}
			}
			
			else if(choice==1){
				backToFirstPage();
			}
		}
	}
	
	public class ChooseListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int selectedIndex = panelCreator.getPanelCustomer().getActivatedRestaurantNamelist().getSelectedIndex();
			Restaurant selectedRestaurant = panelCreator.getPanelCustomer().getActivatedRestaurantList()[selectedIndex];
			panelCreator.getPanelCustomerOrder().setChosenRestaurant(selectedRestaurant);
			panelCreator.getPanelCustomer().setVisible(false);
			panelCreator.getMenuBarCustomer().setVisible(false);
			panelCreator.addCustomerOrderPanel();
			
			//Create a new order
			currentOrder = new Order((Customer) currentUser, selectedRestaurant);
		}
	}
	
	public class LogoutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			currentUser = null;
			backToFirstPage();
			panelCreator.setJMenuBar(null);
			if(currentUser instanceof Customer){
			panelCreator.getMenuBarCustomer().setVisible(false);}
			else if(currentUser instanceof Restaurant){
				panelCreator.getMenuBarRestaurant().setVisible(false);
			}
			else if(currentUser instanceof Manager){
				panelCreator.getMenuBarManager().setVisible(false);
			}
			}
	}
	
	
	public class ShowListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//Which restaurant has been selected
			int selectedIndex = panelCreator.getPanelCustomer().getActivatedRestaurantNamelist().getSelectedIndex();
			Restaurant selectedRestaurant = panelCreator.getPanelCustomer().getActivatedRestaurantList()[selectedIndex];
			
			//Display the adress and the menu
			String display = "<html>Restaurant: "+selectedRestaurant.getName()+"<br><br>";
			display += "Adress: {"+selectedRestaurant.getAdress()[0]+";"+selectedRestaurant.getAdress()[1]+"}<br><br>";
			String[] menuList = selectedRestaurant.getMenu().toString().split("\r\n");
			for(String s : menuList){
				display += s + "<br>";
			}
			display +="<br>-Meal of the week: <br>";
			String mealOfTheWeek = "";
			for(Meal m : selectedRestaurant.getMenu().getMeals()){
				if(m.isMealOfTheWeek()){mealOfTheWeek=m.getName();}
			}
			display += mealOfTheWeek+"<html>";
			panelCreator.getPanelCustomer().getText().setText(display);
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
					panelCreator.getPanelCustomer().getText().setText("Your fidelity card is now a point one. You have 0 point.");
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
	
	public class ShowFeaturesListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			if(cust.getFidelityCard() instanceof BasicFidelityCard){
				panelCreator.getPanelCustomer().getText().setText("You can access to special offers that are provided by the restaurants.");
			}else if(cust.getFidelityCard() instanceof PointFidelityCard){
				PointFidelityCard card = (PointFidelityCard) cust.getFidelityCard();
				panelCreator.getPanelCustomer().getText().setText("<html>You cannot access to special offers provided by the restaurants."
						+ "Instead, you will earn 1 point each 10� spent. Once you have reached 100 points you will have 10% discount on the next order.<br>"
						+ "You have "+card.getPoints()+" points.");
			}else{
				panelCreator.getPanelCustomer().getText().setText("You have a certain probability to gain a meal for free each day.");
			}	
		}
	}
	
	public class ReadNotificationsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			String[] notifications = cust.getNotifications().split("\r\n");
			String display = "<html>";
			for (String s : notifications){
				display += s + "<br>";
			}
			display += "<html>";
			panelCreator.getPanelCustomer().getText().setText(display);	
		}
	}
	
	public class YesOffersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			if(cust.getSpamAgreement()){
				panelCreator.getPanelCustomer().getText().setText("You already can receive special offers.");
			}
			else{
			cust.setSpamAgreement(true);
			panelCreator.getPanelCustomer().getText().setText("You will now be aware of special offers.");}
		}
	}
	
	public class NoOffersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer cust = (Customer) currentUser;
			if(cust.getSpamAgreement()){
				cust.setSpamAgreement(false);
				panelCreator.getPanelCustomer().getText().setText("You will no longer receive notifications.");
			}
			else{
				panelCreator.getPanelCustomer().getText().setText("You already do not receive special offers.");
			}
		}
	}
	
	public class ContactEmailListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			if(cust.getContactForOffers().equalsIgnoreCase("email")){
				panelCreator.getPanelCustomer().getText().setText("You already receive notifications by email.");
			}else if(cust.getEmail().equalsIgnoreCase("")){
				panelCreator.getPanelCustomer().getText().setText("You have not given your email. Please do it in the section 'Set my profile'.");
			}
			else{
				try {
					cust.setContactForOffers("email");
					panelCreator.getPanelCustomer().getText().setText("You will now be contacted by email at this adress: "+cust.getEmail()+".");;
				} catch (KindOfContactDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class ContactPhoneListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			if(cust.getContactForOffers().equalsIgnoreCase("phone")){
				panelCreator.getPanelCustomer().getText().setText("You already receive notifications by phone.");
			}else if(cust.getPhone().equalsIgnoreCase("")){
				panelCreator.getPanelCustomer().getText().setText("You have not given your phone. Please do it in the section 'Set my profile'.");
			}else{
				try {
					cust.setContactForOffers("phone");
					panelCreator.getPanelCustomer().getText().setText("You will now be contacted by phone at this number: "+cust.getPhone()+".");;
				} catch (KindOfContactDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
	}
	
	public class ContactLetterListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Customer cust = (Customer) currentUser;
			if(cust.getContactForOffers().equalsIgnoreCase("letter")){
				panelCreator.getPanelCustomer().getText().setText("You already receive notifications by letter.");
			} else{
				try {
					cust.setContactForOffers("letter");
					panelCreator.getPanelCustomer().getText().setText("You will now be contacted by letter at this adress: {"+cust.getAdress()[0]+";"+cust.getAdress()[1]+"}.");;
				} catch (KindOfContactDoesNotExistException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class HistoryOfOrdersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Customer cust = (Customer) currentUser;
			ArrayList<Order> historyOfOrders = cust.getHistoryOfOrders();
			if(historyOfOrders.size()==0){
				panelCreator.getPanelCustomer().getText().setText("You have never ordered.");
			}else{
				String display = "<html>";
				for (Order order : historyOfOrders){
					String orderLine = order.getName()+" contained:<br>";
					for(SingleItem singleItem : order.getSingleItems()){
						orderLine += singleItem.getName()+"<br>";
					}
					orderLine += "Restaurant: "+order.getRestaurant().getName()+"<br>";
					orderLine += "Total price: "+order.getBill()+"<br>";
					try {
						orderLine +="Date: "+order.getCompleteDay()+"/"+order.getCompleteMonth()+"/"+order.getCompleteYear()+"<br>";
					} catch (OrderNotCompletException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					display += orderLine + "<br>";
				}
				display += "<html>";
				panelCreator.getPanelCustomer().getText().setText(display);
			}
		}
	}
	
	public class ProfileListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String display = "<html>";
			String name = currentUser.getName();
			String userName = "Username: "+currentUser.getUserName()+"<br>";
			String password = "Password: "+currentUser.getPassWord()+"<br>";
			String  adress = "Adress: {"+currentUser.getAdress()[0]+";"+currentUser.getAdress()[1]+"}<br>";
			String email = "Email: "+currentUser.getEmail()+"<br>";
			String phone = "Phone: "+currentUser.getPhone()+"<br>";
			if(currentUser instanceof Customer){
				Customer cust = (Customer) currentUser;
				display += name + " " + cust.getSurname()+"<br>"+userName+password+adress+email+phone;
				display+= "Fidelity card: "+cust.getFidelityCard()+"<br>";
				if(cust.getSpamAgreement()){display+= "Reception of offers: Yes<html>";}
				else{display+= "Reception of offers: Yes<html>";}
				panelCreator.getPanelCustomer().getText().setText(display);
				}
			else if(currentUser instanceof Restaurant){
				display +=name+"<br>"+userName+password+adress+email+phone;
				display += "Generic discount: 0.05<br>";
				display += "Special discount: "+((Restaurant)currentUser).getDiscount()+"<html>";
				panelCreator.getPanelRestaurant().getText().setText(display);
			}
			else if(currentUser instanceof Manager){
				Manager man = (Manager) currentUser;
				display += name + " " + man.getSurname()+"<br>"+userName+password+adress+email+phone;
				display +="Role: "+man.getRole()+"<html>";
				panelCreator.getPanelManager().getText().setText(display);
			}
		}
	}
	
	public class SetEmailListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {		
			MessageSetProfile message = new MessageSetProfile("email");
			String newEmail = message.getMessage();
			if(newEmail!=null){ //If the button "Annuler" is pressed
			if (newEmail.equals("")){new Error("Empty Field Error","The field email must be completed.");}
			else{
				currentUser.setEmail(newEmail);
				if(currentUser instanceof Customer){
				panelCreator.getPanelCustomer().getText().setText("Your new email is: "+newEmail+".");}
				else if(currentUser instanceof Restaurant){panelCreator.getPanelRestaurant().getText().setText("Your new email is: "+newEmail+".");}
				else if(currentUser instanceof Manager){panelCreator.getPanelManager().getText().setText("Your new email is: "+newEmail+".");
			}
		}}
	}
	}
	
	public class SetPhoneListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MessageSetProfile message = new MessageSetProfile("phone number");
			String newPhone = message.getMessage();
			if(newPhone!=null){//If the button "Annuler" is pressed
			if (newPhone.equals("")){new Error("Empty Field Error","The field phone must be completed.");}
			else{
				currentUser.setPhone(newPhone);
				if(currentUser instanceof Customer){
				panelCreator.getPanelCustomer().getText().setText("Your new phone number is: "+newPhone+".");}
				else if(currentUser instanceof Manager){panelCreator.getPanelManager().getText().setText("Your new phone number is: "+newPhone+".");}
				else if(currentUser instanceof Restaurant){panelCreator.getPanelRestaurant().getText().setText("Your new phone number is: "+newPhone+".");
			}
		}}
	}
	}
	
	public class SetAdressListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			MessageSetProfile message = new MessageSetProfile("adress - X coordinate");
			try{
			int newX = Integer.parseInt(message.getMessage());
			MessageSetProfile message2 = new MessageSetProfile("adress - Y coordinate");
			int newY = Integer.parseInt(message2.getMessage());
			int[] newAdress = {newX, newY};
			currentUser.setAdress(newAdress);
			if(currentUser instanceof Customer){
			panelCreator.getPanelCustomer().getText().setText("Your new adress is: {"+newX+","+newY+"}.");}
			else if(currentUser instanceof Manager){panelCreator.getPanelManager().getText().setText("Your new adress is: {"+newX+","+newY+"}.");}
			else if(currentUser instanceof Restaurant){panelCreator.getPanelRestaurant().getText().setText("Your new adress is: {"+newX+","+newY+"}.");}
			}catch(NumberFormatException e1){
				new Error("Adress Error","The adress coordinates must be integers.");
			}
		}
	}
	
	public class ShowItemListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index_Item = panelCreator.getPanelCustomerOrder().getItemNamelist().getSelectedIndex();
			Restaurant rest = panelCreator.getPanelCustomerOrder().getChosenRestaurant();
			SingleItem selectedSingleItem = rest.getMenu().getSingleItems().get(index_Item);
			String display = "<html>"+selectedSingleItem+"<br>";
			display+="Is vegetarian: ";
			if(selectedSingleItem.isVegetarian()){display+="Yes<br>";}
			else{display+="No<br>";}
			display +="Is gluten-free: ";
			if(selectedSingleItem.isGlutenFree()){display+="Yes<br>";}
			else{display+="No<br>";}
			panelCreator.getPanelCustomerOrder().getTextCustomerPanel().setText(display);
		}
	}
	
	public class ShowMealListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index_Meal = panelCreator.getPanelCustomerOrder().getMealNamelist().getSelectedIndex();
			Restaurant rest = panelCreator.getPanelCustomerOrder().getChosenRestaurant();
			Meal selectedMeal = rest.getMenu().getMeals().get(index_Meal);
			String display = "<html>"+selectedMeal+"<br>";
			display+="Is vegetarian: ";
			if(selectedMeal.isVegetarian()){display+="Yes<br>";}
			else{display+="No<br>";}
			display +="Is gluten-free: ";
			if(selectedMeal.isGlutenFree()){display+="Yes<br>";}
			else{display+="No<br>";}
			panelCreator.getPanelCustomerOrder().getTextCustomerPanel().setText(display);
		}
	}
	
	public class ChooseItemListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index_Item = panelCreator.getPanelCustomerOrder().getItemNamelist().getSelectedIndex();
			Restaurant rest = panelCreator.getPanelCustomerOrder().getChosenRestaurant();
			SingleItem selectedSingleItem = rest.getMenu().getSingleItems().get(index_Item);
			try {
				currentOrder.AddSingleItemToOrder(selectedSingleItem.getName(), 1);
				orderedItems += selectedSingleItem + "<br>";
				String totalPrice = "<br>" + "Total price: " +currentOrder.getBill()+"<html>";
				panelCreator.getPanelCustomerOrder().getTextOrderPanel().setText(orderedItems+totalPrice);
			} catch (ItemDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class ChooseMealListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int index_Meal = panelCreator.getPanelCustomerOrder().getMealNamelist().getSelectedIndex();
			Restaurant rest = panelCreator.getPanelCustomerOrder().getChosenRestaurant();
			Meal selectedMeal = rest.getMenu().getMeals().get(index_Meal);
			try {
				currentOrder.AddMealToOrder(selectedMeal.getName(), 1);
				orderedItems += selectedMeal +"<br>";
				String totalPrice = "<br>" + "Total price: " +currentOrder.getBill()+"<html>";
				panelCreator.getPanelCustomerOrder().getTextOrderPanel().setText(orderedItems+totalPrice);
			} catch (ItemDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public class RemoveSomethingListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//If there is nothing to remove
			if(currentOrder.getSingleItems().size()+currentOrder.getMeals().size()==0){
				new Error("Empty Order Error","Your order is empty.");
			}else{
			MessageRemoveSomething message = new MessageRemoveSomething(currentOrder);
			String choice = message.getChoice();
			if(choice!=null){
			try {
				currentOrder.RemoveItemFromOrder(choice);
				orderedItems = "<html>";
				for (SingleItem singleItem : currentOrder.getSingleItems()){
					orderedItems += singleItem+"<br>";
				}
				for(Meal meal : currentOrder.getMeals()){
					orderedItems += meal+"<br>";
				}
				String totalPrice = "<br>Total price: "+currentOrder.getBill();
				panelCreator.getPanelCustomerOrder().getTextOrderPanel().setText(orderedItems+totalPrice);
			} catch (ItemDoesNotExist e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ItemNotInOrderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
		}
		}		
	}
	
	public class PayListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			//If the order is empty
			if(currentOrder.getSingleItems().size()+currentOrder.getMeals().size()==0){
				new Error("Empty Order Error","Your order is empty.");
			}else{
				MessagePay message = new MessagePay(currentOrder.getBill());
				int choice = message.getChoice();
				if(choice==0){ //Want to pay
					boolean courierFound = false; //Boolean to know if a courier has been found
					while(!courierFound){
					try {
						myFoodora.setCourierToOrder(currentOrder);
						myFoodora.closeOrder(currentOrder);
						
						//Back to customer panel
						PanelCustomerOrder currentPanel = (PanelCustomerOrder) panelCreator.getActivatedPanels().pop();
						PanelCustomer lastPanel = (PanelCustomer) panelCreator.getActivatedPanels().pop();
						currentPanel.setVisible(false);
						lastPanel.setVisible(true);
						panelCreator.getActivatedPanels().add(lastPanel);
						panelCreator.getMenuBarCustomer().setVisible(true);
						currentOrder = null;
						panelCreator.getPanelCustomerOrder().getTextOrderPanel().setText("");
						panelCreator.getPanelCustomerOrder().getTextCustomerPanel().setText("");
						orderedItems = "<html>";
						panelCreator.getPanelCustomer().getText().setText("You will receive your order in a few minutes.");
						courierFound = true;
					} catch (NoCourierFoundToDeliver e) {
						courierFound = false;
					} catch (OrderNotCompletException e){
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
				}
			}
		}
	}
	
	public class GetMyMenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Restaurant rest = (Restaurant) currentUser;
			String[] menuList = rest.getMenu().toString().split("\r\n");
			String display = "<html>";
			for(String s : menuList){
				display += s + "<br>";
			}
			display +="<br>-Meal of the week: <br>";
			String mealOfTheWeek = "";
			for(Meal m : rest.getMenu().getMeals()){
				if(m.isMealOfTheWeek()){mealOfTheWeek=m.getName();}
			}
			display += mealOfTheWeek+"<html>";
			panelCreator.getPanelRestaurant().getText().setText(display);
		}
	}
	
	public class GetCurrentGenericDiscountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			panelCreator.getPanelRestaurant().getText().setText("Your current generic discount is: 0.05.");
		}
	}
	
	public class GetCurrentSpecialDiscountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			double discount = ((Restaurant) currentUser).getDiscount();
			panelCreator.getPanelRestaurant().getText().setText("Your current special discount (for the meal of the week) is: "+discount+".");
		}
	}
	
	public class SetNewSpecialDiscountListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 MessageSetProfile message = new MessageSetProfile("special discount");
			 try{
			 double discount = Double.parseDouble(message.getMessage());
			 ((Restaurant) currentUser).setDiscount(discount);
			 panelCreator.getPanelRestaurant().getText().setText("Your special discount (for the meal of the week) is now: "+discount+".");}
			 catch (NumberFormatException e1){new Error("Discount Error","The discount has to be a double");}
		}
	}
	
	public class HistorySoldSingleItemsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String display = "<html>";
			TreeSet<SingleItemSort> singleItems = (TreeSet<SingleItemSort>) ((Restaurant)currentUser).getDeliverdSingleItems().clone();
			while(singleItems.size()!=0){
				SingleItemSort singleItemSort = singleItems.pollFirst();
				display += singleItemSort.getSingleItem()+" has been sold "+singleItemSort.getCount()+" times.<br>";
			}
			display+="<html>";
			panelCreator.getPanelRestaurant().getText().setText(display);
			System.out.println(((Restaurant)currentUser).getDeliverdSingleItems().size());
			while(((Restaurant)currentUser).getDeliverdSingleItems().size()!=0){
			System.out.println(((Restaurant)currentUser).getDeliverdSingleItems().pollFirst().getSingleItem().getName());}
		}
	}
	
	public class OkActionSetMenu implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int selectedIndex = panelCreator.getPanelRestaurant().getActionsToEditMenu().getSelectedIndex();
			Restaurant rest = (Restaurant) currentUser;
			if(selectedIndex==0){ //Add a single item
				panelCreator.getPanelRestaurant().setVisible(false);
				panelCreator.getJMenuBar().setVisible(false);
				panelCreator.addAddSingleItemPanel();
			}
			else if(selectedIndex==1){ //Create a meal
				panelCreator.getPanelRestaurant().setVisible(false);
				panelCreator.getJMenuBar().setVisible(false);
				panelCreator.addCreateMealPanel();
			}
			else if(selectedIndex==2){  //Remove a single item
				MessageRemoveSomethingFromMenu message = new MessageRemoveSomethingFromMenu(rest, "singleitem");
				try {
					if(message.getChoice()!=null){
					SingleItem singleItemToRemove = rest.getMenu().getSingleItem(message.getChoice());
					//Search all the meals containing this single item
					ArrayList<Meal> mealsToRemove = new ArrayList<Meal>();
					if(singleItemToRemove instanceof Starter){
						for(Meal m: rest.getMenu().getMeals()){
							if(m.getStarter()!=null&&m.getStarter().equals(singleItemToRemove)){mealsToRemove.add(m);}
						}
					}
					else if(singleItemToRemove instanceof MainDish){
						for(Meal m: rest.getMenu().getMeals()){
							if(m.getMainDish().equals(singleItemToRemove)){mealsToRemove.add(m);}
						}
					}
					else{
						for(Meal m:rest.getMenu().getMeals()){
							if(m.getDesert()!=null&&m.getDesert().equals(singleItemToRemove)){mealsToRemove.add(m);}
						}
					}
					
					//Remove all the meals containing the single item
					String displayMeals = "<html>";
					for(Meal m:mealsToRemove){
						rest.getMenu().getMeals().remove(m);
						displayMeals += m.getName()+"<br>";
					}
					//Remove the single item
					rest.getMenu().getSingleItems().remove(singleItemToRemove);
					panelCreator.getPanelRestaurant().getText().setText("<html>The single item "+singleItemToRemove.getName()+" has been removed from the menu.<br>"
							+"All the meals containing this single items have been removed as well:<br>"+displayMeals+"<html>");
					}
					} catch (ItemDoesNotExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(selectedIndex==3){  //Remove a meal
				MessageRemoveSomethingFromMenu message = new MessageRemoveSomethingFromMenu(rest, "meal");
				try {
					if(message.getChoice()!=null){
					Meal mealToRemove = rest.getMenu().getMeal(message.getChoice());
					rest.getMenu().getMeals().remove(mealToRemove);
					panelCreator.getPanelRestaurant().getText().setText("The meal "+mealToRemove.getName()+" has been removed from the menu.");
					}
					} catch (ItemDoesNotExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{  //Set the meal of the week
				MessageMealOfTheWeek message = new MessageMealOfTheWeek(rest);
				try {
					if(message.getChoice()!=null){
					rest.setMealOfTheWeek(message.getChoice());
					panelCreator.getPanelRestaurant().getText().setText("The meal of the week is now: "+message.getChoice()+".");
					}
				} catch (ItemDoesNotExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public class OkSingleItemListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Restaurant rest = (Restaurant)currentUser;
			PanelAddSingleItem panel = panelCreator.getPanelAddSingleItem();
			String typeChoice = (String) panel.getTypeComboBox().getSelectedItem();
			String name = panel.getTextFieldName().getText();
			if(name.equals("")){new Error("Empty Field Error","The field name has to be completed.");}
			else{
			try {
				rest.createNewItem(typeChoice, name);
				SingleItem newItem = rest.getMenu().getSingleItem(name);
				newItem.setPrice(Double.parseDouble(panel.getTextFieldPrice().getText()));
				newItem.setVegetarian(panel.getCheckBoxVegetarian().isSelected());
				newItem.setGlutenFree(panel.getCheckBoxGluten().isSelected());
				
				JPanel currentPanel = (JPanel) panelCreator.getActivatedPanels().pop();
				JPanel lastPanel = (JPanel) panelCreator.getActivatedPanels().pop();
				currentPanel.setVisible(false);
				lastPanel.setVisible(true);
				panelCreator.getMenuBarRestaurant().setVisible(true);
				panelCreator.getActivatedPanels().add(lastPanel);
				panelCreator.getPanelRestaurant().getText().setText("The single item "+name+" has been added to your menu.");
			} catch (WrongItemAdded e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e){new Error("Price Error","The price has to be a double.");
			} catch (ItemDoesNotExist e){
				e.printStackTrace();
			}			
		}}
	}
	
	public class ShowStarterListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int choice = panelCreator.getPanelCreateMeal().getStarterComboBox().getSelectedIndex();
			if(choice>0){ //Because the first item in the list is ""
				Starter chosenStarter = ((Restaurant)currentUser).getMenu().getStarters().get(choice-1);
				displayFeaturesItem(chosenStarter);
			}
		}
	}
	
	public class ShowMainDishListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int choice = panelCreator.getPanelCreateMeal().getMainDishComboBox().getSelectedIndex();
			if(choice>0){ //Because the first item in the list is ""
				MainDish chosenMainDish = ((Restaurant)currentUser).getMenu().getMainDishes().get(choice-1);
				displayFeaturesItem(chosenMainDish);
			}
		}
	}
	
	public class ShowDessertListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = panelCreator.getPanelCreateMeal().getDessertComboBox().getSelectedIndex();
			if(choice>0){ //Because the first item in the list is ""
				Desert chosenMainDish = ((Restaurant)currentUser).getMenu().getDesserts().get(choice-1);
				displayFeaturesItem(chosenMainDish);
		}
	}
	}
	
	public void displayFeaturesItem(SingleItem item){
		String display = "<html>";
		display += "Name: "+item.getName()+"<br>";
		display += "Price: "+item.getPrice()+"�<br>";
		display += "Is vegetarian: ";
		if(item.isVegetarian()){display += "Yes<br>";}
		else{display += "No<br>";}
		display += "Is gluten-free: ";
		if(item.isVegetarian()){display += "Yes<br>";}
		else{display += "No<br>";}
		display +="<html>";
		panelCreator.getPanelCreateMeal().getTextCreateMealPanel().setText(display);
	}
	
	public class CreateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			int choiceStarter = panelCreator.getPanelCreateMeal().getStarterComboBox().getSelectedIndex();
			int choiceMainDish = panelCreator.getPanelCreateMeal().getMainDishComboBox().getSelectedIndex();
			int choiceDessert = panelCreator.getPanelCreateMeal().getDessertComboBox().getSelectedIndex();
			Restaurant rest = (Restaurant)currentUser;
			//Creation of a full meal
			if(choiceStarter>0&&choiceMainDish>0&&choiceDessert>0){
				SingleItem starter = rest.getMenu().getStarters().get(choiceStarter-1);
				SingleItem mainDish = rest.getMenu().getMainDishes().get(choiceMainDish-1);
				SingleItem dessert = rest.getMenu().getDesserts().get(choiceDessert-1);
				MessageInputText message = new MessageInputText("Meal creation","Name of the meal");
				if(message.getMessage()==null){new Error("Empty field Error","The field meal name has to be completed");}
				else{
				try {
					rest.createNewFullMeal(message.getMessage(), starter.getName(), mainDish.getName(), dessert.getName());
					this.back(message.getMessage());
				} catch (WrongItemAdded | ItemDoesNotExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}
			//Creation of a half meal
			else if(choiceMainDish>0&&choiceStarter>0&&choiceDessert==0){
				Starter starter = rest.getMenu().getStarters().get(choiceStarter-1);
				SingleItem mainDish = rest.getMenu().getMainDishes().get(choiceMainDish-1);
				MessageInputText message = new MessageInputText("Meal creation","Name of the meal");
				if(message.getMessage()==null){new Error("Empty field Error","The field meal name has to be completed");}
				else{
				try {
					rest.createNewHalfMeal(message.getMessage(), mainDish.getName(), starter.getName());
					this.back(message.getMessage());
				} catch (WrongItemAdded | ItemDoesNotExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}else if(choiceMainDish>0&&choiceStarter==0&&choiceDessert>0){
				Desert dessert = rest.getMenu().getDesserts().get(choiceDessert-1);
				SingleItem mainDish = rest.getMenu().getMainDishes().get(choiceMainDish-1);
				MessageInputText message = new MessageInputText("Meal creation","Name of the meal");
				if(message.getMessage()==null){new Error("Empty field Error","The field meal name has to be completed");}
				else{
				try {
					rest.createNewHalfMeal(message.getMessage(), mainDish.getName(), dessert.getName());
					this.back(message.getMessage());
				} catch (WrongItemAdded | ItemDoesNotExist e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			}
			else{ //Any other combination is not allowed
				new Error("Meal creation Error","A meal has to contain at least 2 items, including a main dish.");
			}
		}
		
		public void back(String mealName){
			JPanel currentPanel = (JPanel) panelCreator.getActivatedPanels().pop();
			JPanel lastPanel = (JPanel) panelCreator.getActivatedPanels().pop();
			currentPanel.setVisible(false);
			lastPanel.setVisible(true);
			panelCreator.getMenuBarRestaurant().setVisible(true);
			panelCreator.getActivatedPanels().add(lastPanel);
			panelCreator.getPanelCreateMeal().getTextCreateMealPanel().setText("");
			panelCreator.getPanelRestaurant().getText().setText("The meal "+mealName+" has been created and added to your menu.");
		}
	}
	
	public class ShowAllUsersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String display = "<html>";
			for(User us : myFoodora.getListUsers()){
				display += us + "<br>";
			}
			display +="<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class ShowCustomersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String display = "<html>";
			for(Customer us : myFoodora.getListCustomer()){
				display += us + "<br>";
			}
			display +="<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class ShowRestaurantsListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String display = "<html>";
			for(Restaurant us : myFoodora.getListRestaurant()){
				display += us + "<br>";
			}
			display +="<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class ShowCouriersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String display = "<html>";
			for(Courier us : myFoodora.getListCourier()){
				display += us + "<br>";
			}
			display +="<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class ShowManagersListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String display = "<html>";
			for(Manager us : myFoodora.getListManager()){
				display += us + "<br>";
			}
			display +="<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class MostSellingRestaurantListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Restaurant rest = ((Manager)currentUser).mostSellingRestaurant();
			String display = "<html>The most selling restaurant is "+rest.getName()+" with a total selling of "+rest.getTotalSelling()+"�.<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class LeastSellingRestaurantListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Restaurant rest = ((Manager)currentUser).leastSellingRestaurant();
			String display = "<html>The least selling restaurant is "+rest.getName()+" with a total selling of "+rest.getTotalSelling()+"�.<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class MostActiveCourierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Courier cour = ((Manager)currentUser).mostActiveCourier();
			String display = "<html>The most active courier is "+cour.getName()+" "+cour.getSurname()+" with "+cour.getCountDeliveredOrder()+" delivered orders.<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class LeastActiveCourierListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Courier cour = ((Manager)currentUser).leastActiveCourier();
			String display = "<html>The least active courier is "+cour.getName()+" "+cour.getSurname()+" with "+cour.getCountDeliveredOrder()+" delivered orders.<html>";
			panelCreator.getPanelManager().getText().setText(display);
		}
	}
	
	public class GetServiceFeePercentageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			double serviceFee = myFoodora.getService_fee();
			panelCreator.getPanelManager().getText().setText("The service-fee percentage is: "+serviceFee+".");	
		}
	}
	
	public class GetMarkupPercentageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			double markup = myFoodora.getMarkup_percentage();
			panelCreator.getPanelManager().getText().setText("The markup percentage is: "+markup+".");	
		}
	}
	
	public class GetDeliveryCostListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			double deliveryCost = myFoodora.getDelivery_cost();
			panelCreator.getPanelManager().getText().setText("The delivery cost is: "+deliveryCost +".");	
		}
	}
	
	public class SetServiceFeePercentageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageInputText message = new MessageInputText("Set fee Message", "Set the new service-fee percentage");
			try{
			double fee = Double.parseDouble(message.getMessage());
			myFoodora.setService_fee(fee);
			panelCreator.getPanelManager().getText().setText("The new service-fee percentage is: "+fee+".");
			}catch(NumberFormatException e){
				new Error("Fee error", "The service-fee percentage has to be a double");
			}
		}
	}
	
	public class SetMarkupPercentageListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageInputText message = new MessageInputText("Set fee Message", "Set the new markup percentage");
			try{
			double fee = Double.parseDouble(message.getMessage());
			myFoodora.setMarkup_percentage(fee);
			panelCreator.getPanelManager().getText().setText("The new markup percentage is: "+fee+".");
			}catch(NumberFormatException e){
				new Error("Fee error", "The markup percentage has to be a double");
			}
		}
	}
	
	public class SetDeliveryCostListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			MessageInputText message = new MessageInputText("Set fee Message", "Set the new delivery cost");
			try{
			double fee = Double.parseDouble(message.getMessage());
			myFoodora.setDelivery_cost(fee);
			panelCreator.getPanelManager().getText().setText("The new delivery cost is: "+fee+".");
			}catch(NumberFormatException e){
				new Error("Fee error", "The sdelivery cost has to be a double");
			}
		}
	}
	
	public class GetDeliveryPolicyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String delivery = myFoodora.getDeliveryPolicy().toString();
			panelCreator.getPanelManager().getText().setText("The current delivery policy is: "+delivery+".");
		}	
	}
	
	public class SetDeliveryPolicyLiistener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String[] list = {"Fastest delivery policy", "Fair delivery policy"};
			MessageList message = new MessageList("Delivery Policy Message", "Choose the new delivery policy", list);
			String choice = message.getChoice();
			if(choice.equalsIgnoreCase("Fastest delivery policy")){
				if(myFoodora.getDeliveryPolicy() instanceof FastestDelivery){
					panelCreator.getPanelManager().getText().setText("The current delivery policy is already a fastest one.");
				}else{
					myFoodora.setDeliveryPolicy(new FastestDelivery());
					panelCreator.getPanelManager().getText().setText("The delivery policy is now a fastest one.");
				}
			}else if(choice.equalsIgnoreCase("Fair delivery policy")){
				if(myFoodora.getDeliveryPolicy() instanceof FairDelivery){
					panelCreator.getPanelManager().getText().setText("The current delivery policy is already a fair one.");
				}else{
					myFoodora.setDeliveryPolicy(new FairDelivery());
					panelCreator.getPanelManager().getText().setText("The delivery policy is now a fair one.");
				}
			}
		}
	}
	
	public class GetTotalIncomeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			panelCreator.getPanelManager().setVisible(false);
			panelCreator.addChooseDatePanel();
			panelCreator.getPanelChooseDate().setAction("totalIncome");
			panelCreator.getMenuBarManager().setVisible(false);
		}
	}
	
	public class GetTotalIncomePerCustomerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
		panelCreator.getPanelManager().setVisible(false);
		panelCreator.addChooseDatePanel();
		panelCreator.getPanelChooseDate().setAction("totalIncomePerCustomer");
		panelCreator.getMenuBarManager().setVisible(false);
		}
	}
	
	public class GetProfitListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			panelCreator.getPanelManager().setVisible(false);
			panelCreator.addChooseDatePanel();
			panelCreator.getPanelChooseDate().setAction("profit");
			panelCreator.getMenuBarManager().setVisible(false);
		}
	}
	public class OkDateListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			PanelChooseDate panel = panelCreator.getPanelChooseDate();
			int day1 = panel.getDay1ComboBox().getSelectedIndex()+1;
			int month1 = panel.getMonth1ComboBox().getSelectedIndex()+1;
			int year1 = panel.getYear1ComboBox().getSelectedIndex()+2014;
			int day2 = panel.getDay2ComboBox().getSelectedIndex()+1;
			int month2 = panel.getMonth2ComboBox().getSelectedIndex()+1;
			int year2 = panel.getYear2ComboBox().getSelectedIndex()+2014;
			panel.setVisible(false);
			panelCreator.addManagerPanel();
			if(panelCreator.getPanelChooseDate().getAction().equalsIgnoreCase("totalIncome")){
				try {
					double[] result = ((Manager)currentUser).computeTotalIncomeAndProfitOverPeriod(day1, month1, year1, day2, month2, year2);
					panelCreator.getPanelManager().getText().setText("The total income between the dates "+day1+"/"+month1+"/"+year1+" and "+day2+"/"+month2+"/"+year2+" is: "+result[0]+"�.");
				} catch (OrderNotCompletException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else if (panelCreator.getPanelChooseDate().getAction().equalsIgnoreCase("totalIncomePerCustomer")){
				try {
					double result = ((Manager)currentUser).computeIncomePerCustomerOverPeriod(day1, month1, year1, day2, month2, year2);
					panelCreator.getPanelManager().getText().setText("The total income per customer between the dates "+day1+"/"+month1+"/"+year1+" and "+day2+"/"+month2+"/"+year2+" is: "+result+"�.");
				} catch (OrderNotCompletException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			}else if(panelCreator.getPanelChooseDate().getAction().equalsIgnoreCase("profit")){
				try {
					double[] result = ((Manager)currentUser).computeTotalIncomeAndProfitOverPeriod(day1, month1, year1, day2, month2, year2);
					panelCreator.getPanelManager().getText().setText("The profit between the dates "+day1+"/"+month1+"/"+year1+" and "+day2+"/"+month2+"/"+year2+" is: "+result[1]+"�.");
				} catch (OrderNotCompletException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			panelCreator.getPanelChooseDate().setAction("");
			panelCreator.getActivatedPanels().pop();
			panelCreator.getActivatedPanels().pop();
		}
	}
	
	public class ChoosePolicyListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = panelCreator.getPanelManager().getActionsPolicyList().getSelectedIndex();
			PanelManager panel = panelCreator.getPanelManager();
			Manager man = (Manager)currentUser;
			if (choice==0){ //Get current target policy
				TargetPolicy targetPolicy = myFoodora.getTargetPolicy();
				String targetPolicyString;
				if(targetPolicy instanceof TargetProfit_DeliveryCost){targetPolicyString = "target profit delivery cost";}
				else if(targetPolicy instanceof TargetProfit_Markup){targetPolicyString = "target profit markup percentage";}
				else{targetPolicyString = "target profit service-fee percentage";}
				panel.getText().setText("The current target policy is: "+targetPolicyString+".");
			}else if(choice==1){
				String[] actions= {"Target profit delivery cost","Target profit markup percentage","Target profit service-fee percentage"};
				MessageList message = new MessageList("Target policy Message", "Choose the new target policy", actions);
				if(message.getChoice()!=null){
				if(message.getChoice().equalsIgnoreCase("Target profit delivery cost")){
					if(myFoodora.getTargetPolicy() instanceof TargetProfit_DeliveryCost){
						panel.getText().setText("Your target policy is already a delivery cost target policy.");
					}else{
					myFoodora.setTargetPolicy(new TargetProfit_DeliveryCost());
					panel.getText().setText("The new target policy is: Target profit delivery cost.");}
				} else if(message.getChoice().equalsIgnoreCase("Target profit markup percentage")){
					if(myFoodora.getTargetPolicy() instanceof TargetProfit_Markup){
						panel.getText().setText("Your target policy is already a markup percentage target policy.");
					}else{
					myFoodora.setTargetPolicy(new TargetProfit_Markup());
					panel.getText().setText("The new target policy is: Target profit markup percentage.");}
				} else if(message.getChoice().equalsIgnoreCase("Target profit service-fee percentage")){
					if(myFoodora.getTargetPolicy() instanceof TargetProfit_ServiceFee){
						panelCreator.getPanelManager().getText().setText("Your target policy is already a service-fee percentage target policy.");
					}else{
					myFoodora.setTargetPolicy(new TargetProfit_ServiceFee());
					panel.getText().setText("The new target policy is: Target profit service-fee percentage.");}
				}
			}}else if(choice==2){
				try{
				MessageInputText message1 = new MessageInputText("Set fee Message", "Give the markup percentage value");
				double markup_percentage = Double.parseDouble(message1.getMessage());
				MessageInputText message2 = new MessageInputText("Set fee Message", "Give the delivery cost value");
				double delivery_cost = Double.parseDouble(message2.getMessage());
				man.setServiceFeeAccordingTargetPolicy(markup_percentage, delivery_cost);
				double serviceFee = myFoodora.getService_fee();
				panel.getText().setText("The computed service-fee percentage is: "+serviceFee+".");
				}catch(NumberFormatException e1){
					new Error("Fee Error", "The fees have to be double.");
				}
			}else if(choice==3){
				try{
					MessageInputText message1 = new MessageInputText("Set fee Message", "Give the service-fee percentage value");
					double service_fee = Double.parseDouble(message1.getMessage());
					MessageInputText message2 = new MessageInputText("Set fee Message", "Give the delivery cost value");
					double delivery_cost = Double.parseDouble(message2.getMessage());
					man.setMarkupAccordingTargetPolicy(service_fee, delivery_cost);
					double markup = myFoodora.getMarkup_percentage();
					panel.getText().setText("The computed markup percentage is: "+markup+".");
					}catch(NumberFormatException e1){
						new Error("Fee Error", "The fees have to be double.");
					}
			}else if(choice==4){
				try{
					MessageInputText message1 = new MessageInputText("Set fee Message", "Give the service-fee percentage value");
					double service_fee = Double.parseDouble(message1.getMessage());
					MessageInputText message2 = new MessageInputText("Set fee Message", "Give the markup percentage value");
					double markup_percentage = Double.parseDouble(message2.getMessage());
					man.setDeliveryCostAccordingTargetPolicy(markup_percentage, service_fee);
					double delivery = myFoodora.getDelivery_cost();
					panel.getText().setText("The computed delivery cost is: "+delivery+".");
					}catch(NumberFormatException e1){
						new Error("Fee Error", "The fees have to be double.");
					}
			}
		}
	}
	
	public class ChooseUserListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			int choice = panelCreator.getPanelManager().getActionsUsersList().getSelectedIndex();
			PanelManager panel = panelCreator.getPanelManager();
			Manager man = (Manager)currentUser;
			if (choice==0){
				panel.setVisible(false);
				panelCreator.getJMenuBar().setVisible(false);
				panelCreator.addRegisterPanel();
			}else if(choice==1){
				int size = myFoodora.getListUsers().size();
				String[] usersName = new String[size];
				for (int i = 0; i < size; i++) {
					usersName[i] = myFoodora.getListUsers().get(i).getUserName();
				}
				MessageList message = new MessageList("Remove user Message", "Select the user you want to remove", usersName);
				String choiceUser = message.getChoice();
				User userToRemove = null;
				if(choiceUser!=null){
					for (int i = 0; i < size; i++) {
						if(myFoodora.getListUsers().get(i).getUserName().equalsIgnoreCase(choiceUser)){
							userToRemove = myFoodora.getListUsers().get(i);
						}
					}
					myFoodora.getListUsers().remove(userToRemove);
					if(userToRemove instanceof Manager){
						myFoodora.getListManager().remove(userToRemove);
					}else if(userToRemove instanceof Customer){
						myFoodora.getListCustomer().remove(userToRemove);
					}else if(userToRemove instanceof Courier){
						myFoodora.getListCourier().remove(userToRemove);
					}else{
							myFoodora.getListRestaurant().remove(userToRemove);
						}
					panel.getText().setText("The user "+choiceUser+" has been removed from the system.");
					}
			}else if(choice==3){
				ArrayList<User> activatedUsers = new ArrayList<User>();
				for (int i = 0; i < myFoodora.getListUsers().size(); i++) {
					User user = myFoodora.getListUsers().get(i);
					if(user.isActivated()){activatedUsers.add(user);}
				}
				String[] activatedUsersName = new String[activatedUsers.size()];
				for (int i = 0; i < activatedUsersName.length; i++) {
					activatedUsersName[i] = activatedUsers.get(i).getUserName();
				}
				MessageList message = new MessageList("Deactivate User Message", "Select the user you want to deactivate.", activatedUsersName);
				String choiceUser = message.getChoice();
				if(choiceUser!=null){
					int index = -1;
					for (int i = 0; i < activatedUsersName.length; i++) {
						if(activatedUsersName[i].equalsIgnoreCase(choiceUser)){index = i;}
					}
					activatedUsers.get(index).setActivated(false);
					System.out.println(myFoodora.getListRestaurant().get(0).isActivated());
					panel.getText().setText("The user "+choiceUser+" is now deactivated.");
				}
			}else if(choice==2){
				ArrayList<User> deactivatedUsers = new ArrayList<User>();
				for (int i = 0; i < myFoodora.getListUsers().size(); i++) {
					User user = myFoodora.getListUsers().get(i);
					if(!user.isActivated()){deactivatedUsers.add(user);}
				}
				String[] deactivatedUsersName = new String[deactivatedUsers.size()];
				for (int i = 0; i < deactivatedUsersName.length; i++) {
					deactivatedUsersName[i] = deactivatedUsers.get(i).getUserName();
				}
				MessageList message = new MessageList("Activate User Message", "Select the user you want to activate.", deactivatedUsersName);
				String choiceUser = message.getChoice();
				if(choiceUser!=null){
					int index = -1;
					for (int i = 0; i < deactivatedUsersName.length; i++) {
						if(deactivatedUsersName[i].equalsIgnoreCase(choiceUser)){index = i;}
					}
					deactivatedUsers.get(index).setActivated(true);
					panel.getText().setText("The user "+choiceUser+" is now activated.");
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

	/**
	 * @return the myFoodora
	 */
	public MyFoodora getMyFoodora() {
		return myFoodora;
	}



	public static void main(String[] args) {
		Launch launch = new Launch();
		launch.getPanelCreator().createAllPanels();
		GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice dDev = gEnv.getDefaultScreenDevice();
		
		int w = (int)dDev.getDefaultConfiguration().getBounds().getWidth();
		System.out.println(w);
		
	}
}
