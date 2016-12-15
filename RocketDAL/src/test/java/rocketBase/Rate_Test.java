package rocketBase;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import rocketDomain.RateDomainModel;

public class Rate_Test {
	ArrayList<RateDomainModel> rates = RateDAL.getAllRates();
	
	@Test
	public void getAllRatestest() {
		System.out.println ("Rates size: " + rates.size());
		assert(rates.size() > 0);
	}
	
	@Test
	public void rateTest() {
		// Checked to see if a known credit score returns a known interest rate
		assertEquals(rates.get(4).getdInterestRate(), 3.50,  0.1);
		assertEquals(rates.get(3).getdInterestRate(), 3.75, 0.1); 
		assertEquals(rates.get(2).getdInterestRate(), 4.00,  0.1); 
		assertEquals(rates.get(1).getdInterestRate(), 4.50,  0.1); 
		assertEquals(rates.get(0).getdInterestRate(), 5.00,  0.1);
		
	}
	
	@Test
	public void rateExceptionTest() {
		// Could not test for RateException here - See RocketBLL.rate_test
	}

}