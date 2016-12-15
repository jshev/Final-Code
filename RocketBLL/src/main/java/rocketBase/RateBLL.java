package rocketBase;

import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.*;

import exceptions.RateException;
import rocketDomain.RateDomainModel;

public class RateBLL {

	private static RateDAL _RateDAL = new RateDAL();
	
	static double getRate(int GivenCreditScore) throws RateException {
		// Completed to the best to my abilities
		ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
		RateDomainModel RDM = new RateDomainModel();
		
		double rate = 0;
		for (RateDomainModel R : rates) {
			if (GivenCreditScore >= R.getiMinCreditScore()) {
				rate = R.getdInterestRate();
			}
		}
		
		if (rate == 0) {
			throw new RateException(RDM);
		}

		return rate;
	}
	
	public static double getPayment(double r, double n, double p, double f, boolean t) {
		/*
		r = rate
		n = number of periods
		p = present value
		f = future value
		t = true (payment at end of period), false (payment at beginning of period)
		*/
		return Math.abs(FinanceLib.pmt(r, n, p, f, t));
	}
}