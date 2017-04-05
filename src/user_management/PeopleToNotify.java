package user_management;

/** Interface which contains the update method used for the Observer pattern.
 * @author maxspahn
 * @author jeremyaugot
 *
 */
public interface PeopleToNotify {
	public void update(String notification);
}
