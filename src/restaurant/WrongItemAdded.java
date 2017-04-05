package restaurant;



/** Exception which appears when a user try to add an item to a meal even if it is not possible.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public class WrongItemAdded extends Exception{

	public WrongItemAdded() {
		System.out.println("WrongItemAdded");
	}

}
