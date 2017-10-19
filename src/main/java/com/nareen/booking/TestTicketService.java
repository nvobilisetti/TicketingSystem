package com.nareen.booking;

public class TestTicketService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TicketServiceImpl ts = new TicketServiceImpl();
		ts.display();
		System.out.println("==================== " + ts.numSeatsAvailable() + "===========================");
		
		SeatHold holdId = ts.findAndHoldSeats(5, "aa.xyz@abc.com");
		ts.findAndHoldSeats(4, "aa.xyz@abc.com");
		ts.reserveSeats(holdId.getReservationId(), "aa.xyz@abc.com");
		ts.display();
		System.out.println("==================== " + ts.numSeatsAvailable() + "===========================");
		try {
			
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ts.findAndHoldSeats(1, "aa.xyz@abc.com");
		System.out.println("===============================================");
		ts.display();
		System.out.println("==================== " + ts.numSeatsAvailable() + "===========================");
	}

}
