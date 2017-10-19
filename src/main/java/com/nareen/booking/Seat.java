package com.nareen.booking;

public class Seat {

	public enum SeatStatus
	{
		AVAILABLE("A"),HOLD("H"),RESERVED("R");
		
		String value;		
		
		private SeatStatus(String value)
		{
			this.value = value;
		}
		
		public String getValue()
		{
			return value;
		}
	}
	
	String seatNumber;	
	private volatile SeatStatus status = SeatStatus.AVAILABLE;


	long holdTime;
	int holdDuration;
	
	public Seat(String seatNumber)
	{
		this.seatNumber = seatNumber;
	}
	
	public SeatStatus getStatus() {
		return status;
	}
	
	public long getHoldTime() {
		return holdTime;
	}
	
	public void setHold(int holdDuration)
	{
		synchronized(seatNumber)
		{
			if(status.equals(SeatStatus.AVAILABLE))
			{
				holdTime = System.currentTimeMillis();
				this.holdDuration = holdDuration;
				status = SeatStatus.HOLD;
			}
		}
	}
	
	public boolean isAvailable()
	{
		if(status.equals(SeatStatus.HOLD))
		{
			if(System.currentTimeMillis() - holdTime > holdDuration)
			{
				//System.out.println("Marking the seat as available : " + seatNumber);
				status = SeatStatus.AVAILABLE;				
			}
		}
		
		if(status.equals(SeatStatus.AVAILABLE))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void reserve() throws Exception
	{
		if(status.equals(SeatStatus.HOLD))
		{
			if(System.currentTimeMillis() - holdTime <= holdDuration)
			{
				status = SeatStatus.RESERVED;
			}
			else
			{
				throw new Exception("Hold time expired");
			}
		}
		else
		{
			if(status.equals(SeatStatus.RESERVED))
			{
				throw new Exception("Seat was already reserved");
			}
			else
			{
				throw new Exception("Seat was not held");
			}
		}
	}
	

}
