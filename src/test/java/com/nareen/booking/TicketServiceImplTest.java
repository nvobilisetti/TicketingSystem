package com.nareen.booking;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class TicketServiceImplTest extends TestCase{
	
	public void testNumSeatsAvailable() {
		
		TicketServiceImpl obj=new TicketServiceImpl();
		
		int result =obj.numSeatsAvailable();
		assertTrue(result==80);	
		
	}
	public void testFindAndHoldSeats() {
		TicketServiceImpl obj=new TicketServiceImpl();
		
		SeatHold result=obj.findAndHoldSeats(5, "abc@xyz.com");
		List<Seat> seatList=new ArrayList<Seat>();
		Seat S=new Seat("");
		
		
		
	}

}
