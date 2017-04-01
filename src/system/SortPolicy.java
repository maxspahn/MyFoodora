package system;

public abstract class SortPolicy implements Comparable<SortPolicy>{
	
	private int count;

	public SortPolicy() {
		this.setCount(0);
	}
	
	public SortPolicy(int count){
		this.setCount(count);
	}
	
	public int compareTo(SortPolicy s){
		if(s.getCount() < this.getCount()){
			return -1;
		}
		else{ return 1;}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
