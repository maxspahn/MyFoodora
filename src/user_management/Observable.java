package user_management;

/** Interface which contains the notifyObservers method used for the Observer pattern.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public interface Observable {
	public void notifyObservers(String message);
}
