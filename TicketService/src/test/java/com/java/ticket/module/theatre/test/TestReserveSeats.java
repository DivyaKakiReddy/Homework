package com.java.ticket.module.theatre.test;

import java.util.Optional;

import com.java.ticket.module.theatre.SeatHold;
import com.java.ticket.module.theatre.TheatreTicketService;
import com.java.ticket.module.theatre.VenueLevel;

import junit.framework.TestCase;

/**
 * @author Divya Kaki Reddy
 * JUnit Test to check the reservation of seats.
 */
public class TestReserveSeats extends TestCase {

	/*
	 * checking confirmation code with 0 reservations.
	 */
	public void testReserveSeatsZeroHoldId(){
        TheatreTicketService ticketService = new TheatreTicketService();
        String confirmationCode = ticketService.reserveSeats(0, "kakireddy.divya@gmail.com");
        assertTrue(confirmationCode == null);
    }

	/*
	 * Checking confirmation code without passing EMail Id.
	 */
    public void testReserveSeatsNullEmail(){
        TheatreTicketService ticketService = new TheatreTicketService();
        String confirmationCode = ticketService.reserveSeats(0, null);
        assertTrue(confirmationCode == null);
    }
}
