package system;

import restaurant.SingleItem;

public class SingleItemSort extends SortPolicy{
	
	private SingleItem singleItem;

	public SingleItemSort(SingleItem singleItem) {
		super();
		this.setSingleItem(singleItem);
	}
	
	public SingleItemSort(SingleItem singleItem, int count){
		super(count);
		this.setSingleItem(singleItem);
	}

	public SingleItem getSingleItem() {
		return singleItem;
	}

	public void setSingleItem(SingleItem singleItem) {
		this.singleItem = singleItem;
	}
	
	

}
