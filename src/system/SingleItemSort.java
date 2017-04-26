package system;

import java.io.Serializable;

import restaurant.SingleItem;

/**Sort the list of single items according to the chosen sort policy. Extends the SortPolicy class. 
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public class SingleItemSort extends SortPolicy implements Serializable{
	
	private SingleItem singleItem;

	/**Constructor.
	 * @param singleItem SingleItem.
	 */
	public SingleItemSort(SingleItem singleItem) {
		super();
		this.setSingleItem(singleItem);
	}
	
	/**Constructor
	 * @param singleItem SingleItem.
	 * @param count Integer.
	 */
	public SingleItemSort(SingleItem singleItem, int count){
		super(count);
		this.setSingleItem(singleItem);
	}

	/**
	 * @return SingleItem.
	 */
	public SingleItem getSingleItem() {
		return singleItem;
	}

	/**
	 * @param singleItem SingleItem.
	 */
	public void setSingleItem(SingleItem singleItem) {
		this.singleItem = singleItem;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String s = new String();
		s = this.getSingleItem().toString() + " count: " + this.getCount();
		return s;
	}
	
	

}
