package rocketServer;

import java.io.IOException;

import exceptions.RateException;
import netgame.common.Hub;
import rocketBase.RateBLL;
import rocketData.LoanRequest;


public class RocketHub extends Hub {

	private RateBLL _RateBLL = new RateBLL();
	
	public RocketHub(int port) throws IOException {
		super(port);
	}

	@Override
	protected void messageReceived(int ClientID, Object message) {
		System.out.println("Message Received by Hub");
		
		if (message instanceof LoanRequest) {
			resetOutput();
			
			LoanRequest lq = (LoanRequest) message;
			
			//	Determined the rate with the given credit score
			//	Determined if payment
			//	Updated and sent lq back to the caller(s)
			
			try {
				lq.setdRate(RateBLL.getRate(lq.getiCreditScore()));
				
				double rate = lq.getdRate() / 1200;
				double numOperiods = lq.getiTerm() * 12;
				double pv = lq.getdAmount() - lq.getiDownPayment();
				lq.setdPayment(RateBLL.getPayment(rate, numOperiods, pv, 0, false));
				
				sendToAll(lq);
			} catch (RateException e) {
				e.printStackTrace();
				sendToAll(e);
			}
		}
	}
}