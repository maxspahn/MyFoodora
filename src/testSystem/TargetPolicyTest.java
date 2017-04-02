package testSystem;

import static org.junit.Assert.*;

import org.junit.Test;


import system.MyFoodora;
import system.TargetProfit_Markup;
import system.TargetProfit_ServiceFee;

public class TargetPolicyTest {

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
		m.changeFeesAccordingToPolicy(markup, service);
		assertTrue(m.getDelivery_cost() == 3.0);
		
		// possible to compute positive values
		markup = 0.5;
		m.changeFeesAccordingToPolicy(markup, service);
		assertTrue(profit == m.getTargetCommands() * ( m.getMarkup_percentage() * average + m.getService_fee() - m.getDelivery_cost()));
		
	}
	
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
		m.changeFeesAccordingToPolicy(service, delivery);
		assertTrue(m.getDelivery_cost() == 3.0);
		
		// possible to compute positive values
		service = 0.5;
		m.changeFeesAccordingToPolicy(service, delivery);
		assertTrue(profit == m.getTargetCommands() * ( m.getMarkup_percentage() * average + m.getService_fee() - m.getDelivery_cost()));
	}
	
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
		m.changeFeesAccordingToPolicy(markup, delivery);
		assertTrue(m.getDelivery_cost() == 3.0);
		
		// possible to compute positive values
		delivery = 10;
		m.changeFeesAccordingToPolicy(markup, delivery);
		assertTrue(profit == m.getTargetCommands() * ( m.getMarkup_percentage() * average + m.getService_fee() - m.getDelivery_cost()));
	}
}
