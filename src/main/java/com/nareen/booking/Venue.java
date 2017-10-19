package com.nareen.booking;

import java.util.ArrayList;
import java.util.List;

public class Venue {

	volatile int numberOfSeatsAvailable;
	List<List<Seat>> seats;
	int holdDuration;
	
	public int getNumberOfSeatsAvailable() {
		return numberOfSeatsAvailable;
	}
	
	public void decrementSeats()
	{
		--numberOfSeatsAvailable;
	}
	
	public void incrementSeats()
	{
		++numberOfSeatsAvailable;
	}
	
	public int getHoldDuration() {
		return holdDuration;
	}

	public List<List<Seat>> getSeats() {
		return seats;
	}
	
	public Venue(int rows, int columns, int holdDuration)
	{
		seats = new ArrayList<List<Seat>>();
		this.holdDuration = holdDuration;
		for(int i=0; i< rows; i++)
		{
			List<Seat> row = new ArrayList<>();
			char rowId = (char)('A' + i);
			for(int j=0; j<columns; j++)
			{
				Seat x = new Seat("" + rowId + (j+1));
				row.add(x);
				numberOfSeatsAvailable++;
			}
			seats.add(row);
		}
	}
	
	public void display()
	{
		for(List<Seat> row : getSeats())
		{
			for(Seat seat : row)
			{
				if(seat.isAvailable())
				{
					if(seat.getHoldTime() > 0)
					{
						incrementSeats();
					}
				}
				System.out.print("["+seat.seatNumber + " - " + seat.getStatus().getValue()+"]");
			}
			System.out.println();
		}
	}
	

}
