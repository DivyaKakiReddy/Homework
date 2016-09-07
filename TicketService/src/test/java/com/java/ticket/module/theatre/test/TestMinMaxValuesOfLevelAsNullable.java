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
public class TestMinMaxValuesOfLevelAsNullable extends TestCase {
	/*
     * test the minimum and maximum level values.
     */
    public void testMinMaxValuesAsNullable(){

        TheatreTicketService ticketService = new TheatreTicketService();
        int oldAvailableSeats = VenueLevel.BALCONY1.getAvailableSeats();
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold hold = ticketService.findAndHoldSeats(500, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        Set<SeatLevelWrapper> holdSet = hold.getHoldSet();
        int numberOfReservation = holdSet.size();
        int newAvailableSeats = VenueLevel.BALCONY1.getAvailableSeats();

        assertFalse(oldAvailableSeats == 9877);
        assertTrue(numberOfReservation == 500);
    }
}
