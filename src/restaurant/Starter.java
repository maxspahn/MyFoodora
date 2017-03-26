package restaurant;

public class Starter extends SingleItem{

	public Starter(String name) {
		super(name);
	}
	
	public boolean equals(Object object){
		if(object instanceof Starter){
			Starter starter = (Starter) object;
			if(starter.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}



}
