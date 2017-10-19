package com.nareen.booking;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TicketServiceImpl implements TicketService {
	
	Venue venue = new Venue(8,10,10000);
	ConcurrentHashMap<Integer, SeatHold> heldSeatsMap = new ConcurrentHashMap<>();

	@Override
	public int numSeatsAvailable() {
		// TODO Auto-generated method stub
		return venue.getNumberOfSeatsAvailable();
	}

	@Override
	public SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		// TODO Auto-generated method stub
		SeatHold seatHold = null;
		if(numSeats <= numSeatsAvailable())
		{			
			List<Seat> seatsHeld = new ArrayList<>();
			for(List<Seat> row : venue.getSeats())
			{
				for(Seat seat : row)
				{
					if(seat.isAvailable())
					{
						if(seat.getHoldTime() > 0)
						{
							venue.incrementSeats();
						}
						seat.setHold(venue.getHoldDuration());
						seatsHeld.add(seat);
						numSeats--;
						venue.decrementSeats();
						if(numSeats == 0)
						{
							break;
						}
					}
				}
				if(numSeats == 0)
				{
					break;
				}
			}
			seatHold = new SeatHold(customerEmail, seatsHeld);	
			int resvId = (int)(System.currentTimeMillis()/1000+Math.random()*1000);
			seatHold.setReservationId(resvId);
			heldSeatsMap.put(resvId, seatHold);
		}
		return seatHold;
	}

	@Override
	public String reserveSeats(int seatHoldId, String customerEmail) {
		String reservationId = "";
		SeatHold seatHold = heldSeatsMap.get(seatHoldId);
		if(seatHold != null)
		{
			for(Seat seat : seatHold.getSeats())
			{
				try
				{
					seat.reserve();
				}
				catch(Exception e)
				{
					reservationId = "FAILED_TO_RESERVE";
					//Add these seats to avaliable list
					venue.numberOfSeatsAvailable += seatHold.getSeats().size();
				}
			}
			reservationId = "R" + seatHoldId;
			seatHold.setEmailId(customerEmail);
			seatHold.markAsReserved();
		}
		return reservationId;
	}
	
	public void display()
	{
		venue.display();
	}

}
