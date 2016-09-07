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
 * Junit tests to check if application fails if all seats are reserved.
 */
public class TestFindAndHoldAllSeats extends TestCase {

	int numberOfSeatsReserved = 0;
	int numberOfSeatReserved1 = 0;
			
	/*
	 * testing if holding all seats create any error.
	 * Assuming total seats as 6250 and holding two time as 5454 and 796.
	 */
    public void testCheckHoldAllSeats(){
        TheatreTicketService ticketService = new TheatreTicketService();
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold hold = ticketService.findAndHoldSeats(5454, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        if(hold != null) {
	        Set<SeatLevelWrapper> holdSet = hold.getHoldSet();
	        numberOfSeatsReserved = holdSet.size();
	        assertTrue(numberOfSeatsReserved == 5454);
        }

        SeatHold hold1 = ticketService.findAndHoldSeats(796, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        if(hold1 != null) {
	        Set<SeatLevelWrapper> holdSet1 = hold1.getHoldSet();
	        numberOfSeatReserved1 = holdSet1.size();
	        assertTrue(numberOfSeatReserved1 == 796);
        }
        
        for (VenueLevel venueLevel : VenueLevel.values()){
            assertTrue(venueLevel.getAvailableSeats() != 567);
        }
        assertFalse(numberOfSeatReserved1 == 456);
        assertFalse(numberOfSeatsReserved == 5467);
    }
}
