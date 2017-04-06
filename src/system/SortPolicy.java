package system;

/**Allows restaurants and managers to sort the shipped orders according to different criterias: most/least ordered half-meal, most/least ordered item "à la carte".
 * Implements Comparable<SortPolicy>.
 * @author jeremyaugot
 * @author maxspahn
 *
 */
public abstract class SortPolicy implements Comparable<SortPolicy>{
	
	private int count;

	public SortPolicy() {
		this.setCount(0);
	}
	
	public SortPolicy(int count){
		this.setCount(count);
	}
	
	/**Compare this sorting policy to another one.
	 * @param policy SortPolicy to be compared.
	 * @return 1 or -1 according to the object's position in the list.
	 */
	public int compareTo(SortPolicy policy){
		if(policy.getCount() < this.getCount()){
			return -1;
		}
		else{ return 1;}
	}

	/**
	 * @return Integer count.
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count Integer.
	 */
	public void setCount(int count) {
		this.count = count;
	}

}
