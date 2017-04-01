package restaurant;

public class Desert extends SingleItem{

	public Desert(String name) {
		super(name);
	}
	
	public boolean equals(Object object){
		if(object instanceof Desert){
			Desert desert = (Desert) object;
			if(desert.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}
	
	

}
