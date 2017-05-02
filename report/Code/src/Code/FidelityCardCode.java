package Code;

public class FidelityCardCode {
	
	public void setFidelityCard(String name) throws FidelityCardDoesNotExistException {
		if (this.fidelityCard instanceof PointFidelityCard){
			System.out.println("You will lose all your points.");
			if(name.equalsIgnoreCase("basicFidelityCard")){
				...
			}else if(name.equalsIgnoreCase("lotteryFidelityCard"){
				...
			}else if(name.equalsIgnoreCase("pointFidelityCard"){
				...
			}
			else{throw new FidelityCardDoesNotExistException();}
		}
	}

}
