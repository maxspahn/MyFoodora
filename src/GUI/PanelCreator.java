package GUI;

import java.util.Stack;

import javax.swing.JFrame;

import GUI.Launch.RegisterManagerListener;

public class PanelCreator extends JFrame{
	Launch launch;
	//Panels
	private PanelFirstPage panelFirstPage;
	private PanelLogin panelLogin;
	private PanelRegister panelRegister;
	private PanelRegisterManager panelRegisterManager;
	private PanelRegisterCustomer panelRegisterCustomer;
	private PanelRegisterCourier panelRegisterCourier;
	private PanelRegisterRestaurant panelRegisterRestaurant;
	private PanelCustomer panelCustomer;
	
	//JMenuBars
	private MenuBarCustomer menuBarCustomer;
	
	private Stack activatedPanels;
	
	
	public PanelCreator(Launch launch){
		this.launch = launch;
		this.activatedPanels = new Stack();
		pack();
		setVisible(true);
	}

	public void createFirstPagePanel(){
		this.panelFirstPage = new PanelFirstPage();
		this.panelFirstPage.getLogin().addActionListener(launch.new LoginListener());
		this.panelFirstPage.getRegister().addActionListener(launch.new RegisterListener());
		this.activatedPanels.add(this.panelFirstPage);
		add(this.panelFirstPage);
	}
	
	public void createLoginPanel(){
		this.panelLogin = new PanelLogin();
		this.panelLogin.getOkLogin().addActionListener(launch.new OkLoginListener());
		this.panelLogin.getBack().addActionListener(launch.new BackListener());
		this.panelLogin.setVisible(false);
	}
	
	public void createRegisterPanel(){
		this.panelRegister = new PanelRegister();
		this.panelRegister.getBack().addActionListener(launch.new BackListener());
		this.panelRegister.getNext().addActionListener(launch.new NextListener());
		this.panelRegister.setVisible(false);
	}
	
	public void createRegisterManagerPanel(){
		this.panelRegisterManager = new PanelRegisterManager();
		this.panelRegisterManager.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterManager.getRegister().addActionListener(launch.new RegisterManagerListener());
		this.panelRegisterManager.setVisible(false);
	}
	
	public void createRegisterCustomerPanel(){
		this.panelRegisterCustomer = new PanelRegisterCustomer();
		this.panelRegisterCustomer.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterCustomer.getRegister().addActionListener(launch.new RegisterCustomerListener());
		this.panelRegisterCustomer.setVisible(false);
	}
	
	public void createRegisterRestaurantPanel(){
		this.panelRegisterRestaurant = new PanelRegisterRestaurant();
		this.panelRegisterRestaurant.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterRestaurant.getRegister().addActionListener(launch.new RegisterRestaurantListener());
		this.panelRegisterRestaurant.setVisible(false);
	}
	
	public void createRegisterCourierPanel(){
		this.panelRegisterCourier = new PanelRegisterCourier();
		this.panelRegisterCourier.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterCourier.getRegister().addActionListener(launch.new RegisterCourierListener());
		this.panelRegisterCourier.setVisible(false);
	}
	
	public void createCustomerPanel(){
		this.menuBarCustomer = new MenuBarCustomer();
		this.panelCustomer = new PanelCustomer();
		//Add listeners to the buttons of panelCustomer
		this.panelCustomer.getOrder().addActionListener(launch.new OrderListener());
		this.panelCustomer.getLogout().addActionListener(launch.new LogoutListener());
		
		//Add listeners to the buttons of menuBarCustomer
		this.menuBarCustomer.getBasicFidelityCardItem().addActionListener(launch.new  BasicFidelityCardListener());
		this.menuBarCustomer.getPointFidelityCardItem().addActionListener(launch.new PointFidelityCardListener());
		this.menuBarCustomer.getLotteryFidelityCardItem().addActionListener(launch.new LotteryFidelityCardListener());
		this.panelCustomer.setVisible(false);
	}
	
	public void addLoginPanel(){
		add(this.panelLogin);
		this.panelLogin.setVisible(true);
		this.activatedPanels.add(this.panelLogin);
	}
	
	public void addRegisterPanel(){
		add(this.panelRegister);
		this.panelRegister.setVisible(true);
		this.activatedPanels.add(this.panelRegister);
	}
	
	public void addRegisterManagerPanel(){
		add(this.panelRegisterManager);
		this.panelRegisterManager.setVisible(true);
		this.activatedPanels.add(this.panelRegisterManager);
	}
	
	public void addRegisterCustomerPanel(){
		add(this.panelRegisterCustomer);
		this.panelRegisterCustomer.setVisible(true);
		this.activatedPanels.add(this.panelRegisterCustomer);
	}
	
	public void addRegisterRestaurantPanel(){
		add(this.panelRegisterRestaurant);
		this.panelRegisterRestaurant.setVisible(true);
		this.activatedPanels.add(this.panelRegisterRestaurant);
	}
	
	public void addRegisterCourierPanel(){
		add(this.panelRegisterCourier);
		this.panelRegisterCourier.setVisible(true);
		this.activatedPanels.add(this.panelRegisterCourier);
	}
	
	public void addCustomerPanel(){
		setJMenuBar(this.menuBarCustomer);
		this.panelCustomer.setVisible(true);
		add(this.panelCustomer);
		this.activatedPanels.add(this.panelCustomer);
	}
	
	public void createAllPanels(){
		this.createFirstPagePanel();
		this.createLoginPanel();
		this.createRegisterPanel();
		this.createRegisterManagerPanel();
		this.createRegisterCustomerPanel();
		this.createRegisterRestaurantPanel();
		this.createRegisterCourierPanel();
		this.createCustomerPanel();
		
	}
	

	/**
	 * @return the panelFirstPage
	 */
	public PanelFirstPage getPanelFirstPage() {
		return panelFirstPage;
	}

	/**
	 * @return the panelLogin
	 */
	public PanelLogin getPanelLogin() {
		return panelLogin;
	}

	/**
	 * @return the panelRegister
	 */
	public PanelRegister getPanelRegister() {
		return panelRegister;
	}

	/**
	 * @return the panelRegisterManager
	 */
	public PanelRegisterManager getPanelRegisterManager() {
		return panelRegisterManager;
	}

	/**
	 * @return the panelRegisterCustomer
	 */
	public PanelRegisterCustomer getPanelRegisterCustomer() {
		return panelRegisterCustomer;
	}

	/**
	 * @return the panelRegisterCourier
	 */
	public PanelRegisterCourier getPanelRegisterCourier() {
		return panelRegisterCourier;
	}

	/**
	 * @return the panelRegisterRestaurant
	 */
	public PanelRegisterRestaurant getPanelRegisterRestaurant() {
		return panelRegisterRestaurant;
	}

	/**
	 * @return the activatedPanels
	 */
	public Stack getActivatedPanels() {
		return activatedPanels;
	}

	/**
	 * @return the panelCustomer
	 */
	public PanelCustomer getPanelCustomer() {
		return panelCustomer;
	}

	/**
	 * @return the menuBarCustomer
	 */
	public MenuBarCustomer getMenuBarCustomer() {
		return menuBarCustomer;
	}
		
	
}
