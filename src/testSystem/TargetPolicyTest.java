package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;


import system.MyFoodora;
import system.TargetCannotBeFullfilled;
import system.TargetProfit_Markup;
import system.TargetProfit_ServiceFee;

/**Test the TargetPolicy class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TargetPolicyTest {

	/**Test the delivery cost target. Test the method changeFeesAccordingToPolicy.
	 * 
	 */
	@Test
	public void testDeliveryCostTarget() {
		MyFoodora m = new MyFoodora();
		m.load();		
		
		int profit = 30;
		int targetCommands = 3;
		double markup = 0.1;
		double service = 2;
		
		
		m.setTargetCommands(targetCommands);
		m.setTargetProfit(profit);
		
		double average = m.getAveragePricePerCommand();

		// not possible to compute positive values
		try {
			m.changeFeesAccordingToPolicy(markup, service);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getDelivery_cost() == 3.0);
		
		// possible to compute positive values
		markup = 0.5;
		try {
			m.changeFeesAccordingToPolicy(markup, service);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}		
		double test = ((double) Math.round(100* (average * m.getMarkup_percentage() + m.getService_fee() - profit/targetCommands))/100);
		assertTrue(test == m.getDelivery_cost());
		
		
	}
	
	/**Test the markup target. Test the method changeFeesAccordingToPolicy.
	 * 
	 */
	@Test
	public void testMarkUpTarget(){
		MyFoodora m = new MyFoodora();
		m.load();		
		
		m.setTargetPolicy(new TargetProfit_Markup());
		
		int profit = 30;
		int targetCommands = 3;
		double delivery = 10;
		double service = 50;
		
		
		m.setTargetCommands(targetCommands);
		m.setTargetProfit(profit);
		
		double average = m.getAveragePricePerCommand();
		
		// not possible to compute positive values
		try {
			m.changeFeesAccordingToPolicy(service, delivery);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getMarkup_percentage() == 0.1);
		
		// possible to compute positive values
		service = 0.5;
		try {
			m.changeFeesAccordingToPolicy(service, delivery);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		double test = ((double) Math.round(100*(profit/targetCommands + delivery - service)/average))/100;
		assertTrue(test == m.getMarkup_percentage());
	}
	
	/**Test the service fee target. Test the method changeFeesAccordingToPolicy.
	 * 
	 */
	@Test
	public void serviceFeeTarget(){
		MyFoodora m = new MyFoodora();
		m.load();		
		
		m.setTargetPolicy(new TargetProfit_ServiceFee());
		
		int profit = 30;
		int targetCommands = 3;
		double delivery = 1;
		double markup = 0.9;
		
		
		m.setTargetCommands(targetCommands);
		m.setTargetProfit(profit);
		
		double average = m.getAveragePricePerCommand();
		
		// not possible to compute positive values
		try {
			m.changeFeesAccordingToPolicy(markup, delivery);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		assertTrue(m.getDelivery_cost() == 3.0);
		
		// possible to compute positive values
		delivery = 10;
		try {
			m.changeFeesAccordingToPolicy(markup, delivery);
		} catch (TargetCannotBeFullfilled e) {
			System.out.println(e.getMessage());
		}
		double test = ((double) Math.round(100*(profit/targetCommands + delivery - average * markup)))/100;
		assertTrue(test == m.getService_fee());
	}
}
