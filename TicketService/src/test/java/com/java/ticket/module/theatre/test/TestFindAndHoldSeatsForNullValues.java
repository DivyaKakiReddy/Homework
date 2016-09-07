package com.java.ticket.module.theatre.test;

import java.util.Optional;
import java.util.Set;

import com.java.ticket.module.theatre.SeatHold;
import com.java.ticket.module.theatre.SeatLevelWrapper;
import com.java.ticket.module.theatre.TheatreTicketService;
import com.java.ticket.module.theatre.VenueLevel;

import junit.framework.TestCase;

/**
 * @author Divya Kaki Reddy
 * JUnit Test to check hold in different condition.
 */
public class TestFindAndHoldSeatsForNullValues extends TestCase{

	/*
	 * test to hold 0 number  of seats.
	 */
	public void testFindAndHoldZeronumberOfSeats(){

        TheatreTicketService ticketService = new TheatreTicketService();
        int numberOfSeats = 0;
        Optional<Integer> minLevel = Optional.of(1);
        Optional<Integer> maxLevel = Optional.of(2);
        String customerEmail = "kakireddy.divya@gmail.com";
        SeatHold hold = ticketService.findAndHoldSeats(numberOfSeats, minLevel, maxLevel, customerEmail);
        assertTrue(hold == null);
    }
	
	/*
	 * test to hold without email Id.
	 */
    public void testFindAndHoldEmptyCustomerEmail(){

        TheatreTicketService ticketService = new TheatreTicketService();
        Optional<Integer> minLevel = Optional.of(1);
        Optional<Integer> maxLevel = Optional.of(2);
        int numberOfSeats = 52;
        String customerEmail = "";
        SeatHold hold = ticketService.findAndHoldSeats(numberOfSeats, minLevel, maxLevel, customerEmail);
        assertTrue(hold == null);
    }

    /*
     * test to hold without specifying particular level.
     */
    public void testFindAndHoldNullLevels(){

        TheatreTicketService ticketService = new TheatreTicketService();
        int numberOfSeats = 52;
        String customerEmail = "kakireddy.divya@gmail.com";
        SeatHold hold = ticketService.findAndHoldSeats(numberOfSeats, null, null, customerEmail);
        assertTrue(hold == null);
    }	
}
