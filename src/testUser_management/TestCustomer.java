package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import system.*;
import user_management.Customer;
import user_management.FidelityCardDoesNotExistException;
import user_management.User;

/**Test the Customer class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TestCustomer {

	/**Test if the spam agreement and the name are correct. 
	 * 
	 */
	@Test
	public void testConstructor() {
		int [] coords = {10,4};
		Customer maxspahn = new Customer("Spahn", "maxspahn", "wer123ich", "0768483649", "maxspahn3@yahoo.de", coords);
		assertTrue(maxspahn.getName().equalsIgnoreCase("spahn"));
		assertTrue(!maxspahn.getSpamAgreement());
	}
	
	/**Test if the set of a new basic fidelity card is correct. 
	 * 
	 */
	@Test
	public void testSetPointFidelityCard() {
		int [] coords = {10,4};
		Customer maxspahn = new Customer("Spahn", "maxspahn", "wer123ich", "0768483649", "maxspahn3@yahoo.de", coords);
		try {
			maxspahn.setFidelityCard("pointfidelitycard");
			assertTrue(maxspahn.getFidelityCard() instanceof PointFidelityCard);
		} catch (FidelityCardDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Test if the set of a new lottery fidelity card is correct. 
	 * 
	 */
	@Test
	public void testSetLotteryFidelityCard() {
		int [] coords = {10,4};
		Customer maxspahn = new Customer("Spahn", "maxspahn", "wer123ich", "0768483649", "maxspahn3@yahoo.de", coords);
		try {
			maxspahn.setFidelityCard("lotteryfidelitycard");
			assertTrue(maxspahn.getFidelityCard() instanceof LotteryFidelityCard);
		} catch (FidelityCardDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**Test if the set of a new basic fidelity card is correct. 
	 * 
	 */
	@Test
	public void testSetBasicFidelityCard() {
		int [] coords = {10,4};
		Customer maxspahn = new Customer("Spahn", "maxspahn", "wer123ich", "0768483649", "maxspahn3@yahoo.de", coords);
		try {
			maxspahn.setFidelityCard("pointfidelitycard"); //Because a new user has a basic fidelity card by default
			maxspahn.setFidelityCard("basicfidelitycard");
			assertTrue(maxspahn.getFidelityCard() instanceof BasicFidelityCard);
		} catch (FidelityCardDoesNotExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}