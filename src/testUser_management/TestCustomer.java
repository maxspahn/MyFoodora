package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;


import system.MyFoodora;
import user_management.Customer;
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

}