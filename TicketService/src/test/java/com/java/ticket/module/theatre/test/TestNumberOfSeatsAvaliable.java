package com.java.ticket.module.theatre.test;

import java.util.Optional;

import com.java.ticket.module.theatre.TheatreTicketService;
import com.java.ticket.module.theatre.VenueLevel;

import junit.framework.TestCase;

/**
 * @author Divya Kaki Reddy
 * JUnit test for seats availability.
 */
public class TestNumberOfSeatsAvaliable extends TestCase {

	/*
	 *Checking Boundry values. 
	 */
	public void testBogusIndexNumSeatsAvailableWithBogusValue() {
		TheatreTicketService ticketService = new TheatreTicketService();
	    int numSeatsAvailable = ticketService.numberOfSeatsAvailable(Optional.of(new Integer(0)));
	    int totalNumberOfSeats = 0;
	    for (VenueLevel v : VenueLevel.values()) {
	         totalNumberOfSeats += v.getAvailableSeats();
	    }
	    assertTrue(numSeatsAvailable == -1);
	}
}
