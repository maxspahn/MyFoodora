package GUI;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Stack;

import javax.swing.JFrame;


public class PanelCreator{
	Launch launch;
	
	private JFrame frame;
	//Panels
	private PanelFirstPage panelFirstPage;
	private PanelLogin panelLogin;
	private PanelRegister panelRegister;
	private PanelRegisterManager panelRegisterManager;
	private PanelRegisterCustomer panelRegisterCustomer;
	private PanelRegisterCourier panelRegisterCourier;
	private PanelRegisterRestaurant panelRegisterRestaurant;
	private PanelCustomer panelCustomer;
	private PanelCustomerOrder panelCustomerOrder;
	private PanelRestaurant panelRestaurant;
	private PanelAddSingleItem panelAddSingleItem;
	private PanelCreateMeal panelCreateMeal;
	private PanelManager panelManager;
	private PanelChooseDate panelChooseDate;
	private PanelCourier panelCourier;
	
	//JMenuBars
	private MenuBarCustomer menuBarCustomer;
	private MenuBarRestaurant menuBarRestaurant;
	private MenuBarManager menuBarManager;
	private MenuBarCourier menuBarCourier;
	
	private Stack activatedPanels;

	private double coeffHeight;

	private double coeffWidth;
	
	
	public PanelCreator(Launch launch){
		this.launch = launch;
		this.activatedPanels = new Stack();
		
		//Create frame according to the screen size
		GraphicsEnvironment gEnv = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		GraphicsDevice defaultDevice = gEnv.getDefaultScreenDevice();
		int height = defaultDevice.getDefaultConfiguration().getBounds().height;
		int width = defaultDevice.getDefaultConfiguration().getBounds().width;
		this.coeffHeight = height/1824.0;
		this.coeffWidth = width/2736.0;
		this.frame = new JFrame(defaultDevice.getDefaultConfiguration());
		frame.setVisible(true);
	}

	public void createFirstPagePanel(){
		this.panelFirstPage = new PanelFirstPage(coeffHeight,coeffWidth);
		this.panelFirstPage.getLogin().addActionListener(launch.new LoginListener());
		this.panelFirstPage.getRegister().addActionListener(launch.new RegisterListener());
		this.activatedPanels.add(this.panelFirstPage);
		frame.add(this.panelFirstPage);
		this.panelFirstPage.setVisible(true);
	}
	
	public void createLoginPanel(){
		this.panelLogin = new PanelLogin(coeffHeight,coeffWidth);
		this.panelLogin.getOkLogin().addActionListener(launch.new OkLoginListener());
		this.panelLogin.getBack().addActionListener(launch.new BackListener());
		this.panelLogin.setVisible(false);
	}
	
	public void createRegisterPanel(){
		this.panelRegister = new PanelRegister(coeffHeight,coeffWidth);
		this.panelRegister.getBack().addActionListener(launch.new BackListener());
		this.panelRegister.getNext().addActionListener(launch.new NextListener());
		this.panelRegister.setVisible(false);
	}
	
	public void createRegisterManagerPanel(){
		this.panelRegisterManager = new PanelRegisterManager(coeffHeight,coeffWidth);
		this.panelRegisterManager.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterManager.getRegister().addActionListener(launch.new RegisterManagerListener());
		this.panelRegisterManager.setVisible(false);
	}
	
	public void createRegisterCustomerPanel(){
		this.panelRegisterCustomer = new PanelRegisterCustomer(coeffHeight,coeffWidth);
		this.panelRegisterCustomer.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterCustomer.getRegister().addActionListener(launch.new RegisterCustomerListener());
		this.panelRegisterCustomer.setVisible(false);
	}
	
	public void createRegisterRestaurantPanel(){
		this.panelRegisterRestaurant = new PanelRegisterRestaurant(coeffHeight,coeffWidth);
		this.panelRegisterRestaurant.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterRestaurant.getRegister().addActionListener(launch.new RegisterRestaurantListener());
		this.panelRegisterRestaurant.setVisible(false);
	}
	
	public void createRegisterCourierPanel(){
		this.panelRegisterCourier = new PanelRegisterCourier(coeffHeight,coeffWidth);
		this.panelRegisterCourier.getBack().addActionListener(launch.new BackListener());
		this.panelRegisterCourier.getRegister().addActionListener(launch.new RegisterCourierListener());
		this.panelRegisterCourier.setVisible(false);
	}
	
	public void createCustomerPanel(){
		this.menuBarCustomer = new MenuBarCustomer();
		this.panelCustomer = new PanelCustomer(this.launch.getMyFoodora(),coeffHeight,coeffWidth);
		//Add listeners to the buttons of panelCustomer
		this.panelCustomer.getChoose().addActionListener(launch.new ChooseListener());
		this.panelCustomer.getLogout().addActionListener(launch.new LogoutListener());
		this.panelCustomer.getShow().addActionListener(launch.new ShowListener());
		
		//Add listeners to the buttons of menuBarCustomer
		this.menuBarCustomer.getBasicFidelityCardItem().addActionListener(launch.new  BasicFidelityCardListener());
		this.menuBarCustomer.getPointFidelityCardItem().addActionListener(launch.new PointFidelityCardListener());
		this.menuBarCustomer.getLotteryFidelityCardItem().addActionListener(launch.new LotteryFidelityCardListener());
		this.menuBarCustomer.getShowFeatures().addActionListener(launch.new ShowFeaturesListener());
		this.menuBarCustomer.getReadNotifications().addActionListener(launch.new ReadNotificationsListener());
		this.menuBarCustomer.getYesOffers().addActionListener(launch.new YesOffersListener());
		this.menuBarCustomer.getNoOffers().addActionListener(launch.new NoOffersListener());
		this.menuBarCustomer.getContactEmail().addActionListener(launch.new ContactEmailListener());
		this.menuBarCustomer.getContactPhone().addActionListener(launch.new ContactPhoneListener());
		this.menuBarCustomer.getContactLetter().addActionListener(launch.new ContactLetterListener());
		this.menuBarCustomer.getHistoryOfOrders().addActionListener(launch.new HistoryOfOrdersListener());
		this.menuBarCustomer.getGetProfile().addActionListener(launch.new ProfileListener());
		this.menuBarCustomer.getSetEmail().addActionListener(launch.new SetEmailListener());
		this.menuBarCustomer.getSetPhone().addActionListener(launch.new SetPhoneListener());
		this.menuBarCustomer.getSetAdress().addActionListener(launch.new SetAdressListener());
		this.panelCustomer.setVisible(false);
	}
	
	public void createCustomerOrderPanel(){
		this.panelCustomerOrder = new PanelCustomerOrder(this.launch.getMyFoodora().getListRestaurant().get(0),coeffHeight,coeffWidth);
		this.panelCustomerOrder.getBack().addActionListener(launch.new BackListener());
		this.panelCustomerOrder.getShowItem().addActionListener(launch.new ShowItemListener());
		this.panelCustomerOrder.getShowMeal().addActionListener(launch.new ShowMealListener());
		this.panelCustomerOrder.getChooseItem().addActionListener(launch.new ChooseItemListener());
		this.panelCustomerOrder.getChooseMeal().addActionListener(launch.new ChooseMealListener());
		this.panelCustomerOrder.getRemoveSomething().addActionListener(launch.new RemoveSomethingListener());
		this.panelCustomerOrder.getPay().addActionListener(launch.new PayListener());
		this.panelCustomerOrder.setVisible(false);
	}
	
	public void createRestaurantPanel(){
		this.menuBarRestaurant = new MenuBarRestaurant();
		this.panelRestaurant = new PanelRestaurant(coeffHeight,coeffWidth);
		this.panelRestaurant.setVisible(false);
		//Add listeners to the buttons of panelRestaurant
		this.panelRestaurant.getLogout().addActionListener(launch.new LogoutListener());
		this.panelRestaurant.getOkActionSetMenu().addActionListener(launch.new OkActionSetMenu());
		
		//Add listeners to the buttons of menuBarRestaurant
		this.menuBarRestaurant.getSetEmail().addActionListener(launch.new SetEmailListener());
		this.menuBarRestaurant.getSetPhone().addActionListener(launch.new SetPhoneListener());
		this.menuBarRestaurant.getSetAdress().addActionListener(launch.new SetAdressListener());
		this.menuBarRestaurant.getGetMyMenu().addActionListener(launch.new GetMyMenuListener());
		this.menuBarRestaurant.getGetCurrentGenericDiscount().addActionListener(launch.new GetCurrentGenericDiscountListener());
		this.menuBarRestaurant.getGetCurrentSpecialDiscount().addActionListener(launch.new GetCurrentSpecialDiscountListener());
		this.menuBarRestaurant.getSetNewSpecialDiscount().addActionListener(launch.new SetNewSpecialDiscountListener());
		this.menuBarRestaurant.getGetMyProfile().addActionListener(launch.new ProfileListener());
		this.menuBarRestaurant.getHistorySoldSingleItems().addActionListener(launch.new HistorySoldSingleItemsListener());
		
	}
	
	public void createAddSingleItemPanel(){
		this.panelAddSingleItem = new PanelAddSingleItem(coeffHeight,coeffWidth);
		this.panelAddSingleItem.setVisible(false);
		this.panelAddSingleItem.getBack().addActionListener(launch.new BackListener());
		this.panelAddSingleItem.getOkSingleItem().addActionListener(launch.new OkSingleItemListener());
	}
	
	public void createCreateMealPanel(){
		this.panelCreateMeal = new PanelCreateMeal(this.launch.getMyFoodora().getListRestaurant().get(0),coeffHeight,coeffWidth);
		this.panelCreateMeal.setVisible(false);
		this.panelCreateMeal.getBack().addActionListener(launch.new BackListener());
		this.panelCreateMeal.getShowStarter().addActionListener(launch.new ShowStarterListener());
		this.panelCreateMeal.getShowMainDish().addActionListener(launch.new ShowMainDishListener());
		this.panelCreateMeal.getShowDessert().addActionListener(launch.new ShowDessertListener());
		this.panelCreateMeal.getCreate().addActionListener(launch.new CreateListener());
	}
	
	public void createManagerPanel(){
		this.menuBarManager = new MenuBarManager();
		this.panelManager = new PanelManager(coeffHeight,coeffWidth);
		this.panelManager.setVisible(false);
		
		//Add listeners to the panelManager
		this.panelManager.getLogout().addActionListener(launch.new LogoutListener());
		this.panelManager.getChoosePolicy().addActionListener(launch.new ChoosePolicyListener());
		this.panelManager.getChooseUser().addActionListener(launch.new ChooseUserListener());
		
		//Add the listeners to the menuBarManager
		this.menuBarManager.getSetEmail().addActionListener(launch.new SetEmailListener());
		this.menuBarManager.getSetPhone().addActionListener(launch.new SetPhoneListener());
		this.menuBarManager.getSetAdress().addActionListener(launch.new SetAdressListener());
		this.menuBarManager.getGetProfile().addActionListener(launch.new ProfileListener());
		this.menuBarManager.getShowAllUsers().addActionListener(launch.new ShowAllUsersListener());
		this.menuBarManager.getShowCustomers().addActionListener(launch.new ShowCustomersListener());
		this.menuBarManager.getShowRestaurants().addActionListener(launch.new ShowRestaurantsListener());
		this.menuBarManager.getShowCouriers().addActionListener(launch.new ShowCouriersListener());
		this.menuBarManager.getShowManagers().addActionListener(launch.new ShowManagersListener());
		this.menuBarManager.getMostSellingRestaurant().addActionListener(launch.new MostSellingRestaurantListener());
		this.menuBarManager.getLeastSellingRestaurant().addActionListener(launch.new LeastSellingRestaurantListener());
		this.menuBarManager.getMostActiveCourier().addActionListener(launch.new MostActiveCourierListener());
		this.menuBarManager.getLeastActiveCourier().addActionListener(launch.new LeastActiveCourierListener());
		this.menuBarManager.getGetServiceFeePercentage().addActionListener(launch.new GetServiceFeePercentageListener());
		this.menuBarManager.getGetDeliveryCost().addActionListener(launch.new GetDeliveryCostListener());
		this.menuBarManager.getGetMarkupPercentage().addActionListener(launch.new GetMarkupPercentageListener());
		this.menuBarManager.getSetServiceFeePercentage().addActionListener(launch.new SetServiceFeePercentageListener());
		this.menuBarManager.getSetDeliveryCost().addActionListener(launch.new SetDeliveryCostListener());
		this.menuBarManager.getSetMarkupPercentage().addActionListener(launch.new GetMarkupPercentageListener());
		this.menuBarManager.getGetDeliveryPolicy().addActionListener(launch.new GetDeliveryPolicyListener());
		this.menuBarManager.getSetDeliveryPolicy().addActionListener(launch.new SetDeliveryPolicyLiistener());
		this.menuBarManager.getTotalIncome().addActionListener(launch.new GetTotalIncomeListener());
		this.menuBarManager.getTotalIncomePerCustomer().addActionListener(launch.new GetTotalIncomePerCustomerListener());
		this.menuBarManager.getProfit().addActionListener(launch.new GetProfitListener());
	}
	
	public void createChooseDatePanel(){
		this.panelChooseDate = new PanelChooseDate("",coeffHeight,coeffWidth);
		this.panelChooseDate.setVisible(false);
		this.panelChooseDate.getOkDate().addActionListener(launch.new OkDateListener());
		this.panelChooseDate.getBack().addActionListener(launch.new BackListener());
	}
	
	public void createCourierPanel(){
		this.panelCourier = new PanelCourier(coeffHeight,coeffWidth);
		this.panelCourier.setVisible(false);
		this.menuBarCourier = new MenuBarCourier();
		
		//Add listeners to the panelCourier
		this.panelCourier.getLogout().addActionListener(launch.new LogoutListener());
		this.panelCourier.getDeliverOrder().addActionListener(launch.new DeliverOrderListener());
		
		//Add listeners to the menuBarCourier
		this.menuBarCourier.getGetProfile().addActionListener(launch.new ProfileListener());
		this.menuBarCourier.getSetEmail().addActionListener(launch.new SetEmailListener());
		this.menuBarCourier.getSetPhone().addActionListener(launch.new SetPhoneListener());
		this.menuBarCourier.getSetAdress().addActionListener(launch.new SetAdressListener());
		this.menuBarCourier.getOnDuty().addActionListener(launch.new OnDutyListener());
		this.menuBarCourier.getOffDuty().addActionListener(launch.new OffDutyListener());
		this.menuBarCourier.getGetAvailabilityStatus().addActionListener(launch.new GetAvailabilityStatusListener());
		this.menuBarCourier.getUnregister().addActionListener(launch.new UnregisterListener());
	}
	
	public void addLoginPanel(){
		frame.add(this.panelLogin);
		this.panelLogin.setVisible(true);
		this.activatedPanels.add(this.panelLogin);
	}
	
	public void addRegisterPanel(){
		frame.add(this.panelRegister);
		this.panelRegister.setVisible(true);
		this.activatedPanels.add(this.panelRegister);
	}
	
	public void addRegisterManagerPanel(){
		frame.add(this.panelRegisterManager);
		this.panelRegisterManager.setVisible(true);
		this.activatedPanels.add(this.panelRegisterManager);
	}
	
	public void addRegisterCustomerPanel(){
		frame.add(this.panelRegisterCustomer);
		this.panelRegisterCustomer.setVisible(true);
		this.activatedPanels.add(this.panelRegisterCustomer);
	}
	
	public void addRegisterRestaurantPanel(){
		frame.add(this.panelRegisterRestaurant);
		this.panelRegisterRestaurant.setVisible(true);
		this.activatedPanels.add(this.panelRegisterRestaurant);
	}
	
	public void addRegisterCourierPanel(){
		frame.add(this.panelRegisterCourier);
		this.panelRegisterCourier.setVisible(true);
		this.activatedPanels.add(this.panelRegisterCourier);
	}
	
	public void addCustomerPanel(){
		this.menuBarCustomer.setVisible(true);
		frame.setJMenuBar(this.menuBarCustomer);
		this.panelCustomer.setVisible(true);
		frame.add(this.panelCustomer);
		this.activatedPanels.add(this.panelCustomer);
	}
	
	public void addCustomerOrderPanel(){
		frame.add(this.panelCustomerOrder);
		this.panelCustomerOrder.setVisible(true);
		this.activatedPanels.add(this.panelCustomerOrder);
	}
	
	public void addRestaurantPanel(){
		this.menuBarRestaurant.setVisible(true);
		frame.setJMenuBar(this.menuBarRestaurant);
		this.panelRestaurant.setVisible(true);
		frame.add(this.panelRestaurant);
		this.activatedPanels.add(this.panelRestaurant);
	}
	
	public void addAddSingleItemPanel(){
		frame.add(this.panelAddSingleItem);
		this.panelAddSingleItem.setVisible(true);
		this.activatedPanels.add(this.panelAddSingleItem);
	}
	
	public void addCreateMealPanel(){
		frame.add(this.panelCreateMeal);
		this.panelCreateMeal.setVisible(true);
		this.activatedPanels.add(this.panelCreateMeal);
	}
	
	public void addManagerPanel(){
		this.menuBarManager.setVisible(true);
		frame.setJMenuBar(this.menuBarManager);
		this.panelManager.setVisible(true);
		frame.add(this.panelManager);
		this.activatedPanels.add(this.panelManager);
	}
	
	public void addChooseDatePanel(){
		this.panelChooseDate.setVisible(true);
		frame.add(this.panelChooseDate);
		this.activatedPanels.add(this.panelChooseDate);
	}
	
	public void addCourierPanel(){
		this.menuBarCourier.setVisible(true);
		frame.setJMenuBar(this.menuBarCourier);
		this.panelCourier.setVisible(true);
		frame.add(this.panelCourier);
		this.activatedPanels.add(this.panelCourier);
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
		this.createCustomerOrderPanel();
		this.createRestaurantPanel();
		this.createAddSingleItemPanel();
		this.createCreateMealPanel();
		this.createManagerPanel();
		this.createChooseDatePanel();
		this.createCourierPanel();
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

	/**
	 * @return the panelCustomerOrder
	 */
	public PanelCustomerOrder getPanelCustomerOrder() {
		return panelCustomerOrder;
	}

	/**
	 * @return the panelRestaurant
	 */
	public PanelRestaurant getPanelRestaurant() {
		return panelRestaurant;
	}

	/**
	 * @return the menuBarRestaurant
	 */
	public MenuBarRestaurant getMenuBarRestaurant() {
		return menuBarRestaurant;
	}

	/**
	 * @return the panelAddSingleItem
	 */
	public PanelAddSingleItem getPanelAddSingleItem() {
		return panelAddSingleItem;
	}

	/**
	 * @return the panelCreateMeal
	 */
	public PanelCreateMeal getPanelCreateMeal() {
		return panelCreateMeal;
	}

	/**
	 * @return the panelManager
	 */
	public PanelManager getPanelManager() {
		return panelManager;
	}

	/**
	 * @return the menuBarManager
	 */
	public MenuBarManager getMenuBarManager() {
		return menuBarManager;
	}

	/**
	 * @return the panelChooseDate
	 */
	public PanelChooseDate getPanelChooseDate() {
		return panelChooseDate;
	}

	/**
	 * @return the panelCourier
	 */
	public PanelCourier getPanelCourier() {
		return panelCourier;
	}

	/**
	 * @return the menuBarCourier
	 */
	public MenuBarCourier getMenuBarCourier() {
		return menuBarCourier;
	}

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}
			
}
