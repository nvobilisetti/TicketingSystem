package com.nareen.booking;

import java.util.List;

public class SeatHold {

	List<Seat> seats;
	String emailId;
	Integer reservationId;
	boolean reserved;
	
	public SeatHold(String emailId, List<Seat> seats)
	{
		this.emailId = emailId;
		this.seats = seats;
	}
	
	public List<Seat> getSeats() {
		return seats;
	}

	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public void setReservationId(Integer reservationID)
	{
		this.reservationId = reservationID;
	}
	
	public void markAsReserved()
	{
		this.reserved = true;
	}
	
	public Integer getReservationId()
	{
		return this.reservationId;
	}


}
