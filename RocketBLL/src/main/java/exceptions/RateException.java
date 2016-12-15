package exceptions;

import rocketDomain.RateDomainModel;

public class RateException extends Exception {
	//	Added RateRomainModel as an attribute
	//	Created a RateException constructor, passing in RateDomainModel
	//	Created a getter for RateRomainModel
	
	private RateDomainModel RDM;

	public RateDomainModel getRateDomainModel() {
		return RDM;
	}
	
	public RateException(RateDomainModel RDM){
		this.RDM = RDM;
		System.out.print("No rate is found.");
	}
	
}