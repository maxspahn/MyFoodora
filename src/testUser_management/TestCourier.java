package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import user_management.Courier;
import user_management.Customer;

/**Test the Courier class.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class TestCourier {

	/**Test if 2 couriers can have the same ID, and if the adress is correct.
	 * 
	 */
	@Test
	public void test() {
		int[] coords = {4,-2};
		Courier peter = new Courier("Peter", "peter94", "markus", "01802233", "peterlustig3@gmail.com", coords );
		assertTrue(peter.getAdress()[0] == 4);
		int [] coords1 = {10,4};
		
		Customer maxspahn = new Customer("Spahn", "maxspahn", "wer123ich", "0768483649", "maxspahn3@yahoo.de", coords1);
		assertTrue(maxspahn.getID() != peter.getID());
		
	}

}
