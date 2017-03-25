package testUser_management;

import static org.junit.Assert.*;

import org.junit.Test;

import user_management.Courier;
import user_management.Customer;

public class TestCourier {

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
