package rocketBase;

import static org.junit.Assert.*;

import org.junit.Test;

import exceptions.RateException;

public class rate_test {
	
	@Test
	public void getRateTest() {
		// Checked to see if a known credit score returns a known interest rate
		try {
			assert(RateBLL.getRate(800) == 3.5);
			assert(RateBLL.getRate(750) == 3.75);
			assert(RateBLL.getRate(700) == 4);
			assert(RateBLL.getRate(650) == 4.5);
			assert(RateBLL.getRate(600) == 5);
		} catch (RateException e) {
			e.printStackTrace();
		}
	}
	
	@Test (expected = RateException.class)
	public void getRateExceptionTest() throws RateException {
		// Checked to see if a RateException is thrown if there are no rates for a given credit score
		RateBLL.getRate(100);
	}
	
	@Test
	public void getPaymentTest() {
		assertEquals(RateBLL.getPayment(0.04/12,360,300000,0,false),1432.25,0.1);
	}

}