package restaurant;

public class MainDish extends SingleItem{

	public MainDish(String name) {
		super(name);
	}
	
	public boolean equals(Object object){
		if(object instanceof MainDish){
			MainDish mainDish = (MainDish) object;
			if(mainDish.getName().equalsIgnoreCase(this.getName())){
				return true;
			}
		}
		return false;
	}

}
