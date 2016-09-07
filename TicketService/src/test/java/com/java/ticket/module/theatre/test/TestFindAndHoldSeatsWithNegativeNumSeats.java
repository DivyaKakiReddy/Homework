package com.java.ticket.module.theatre.test;

import java.util.Optional;

import com.java.ticket.module.theatre.SeatHold;
import com.java.ticket.module.theatre.TheatreTicketService;

import junit.framework.TestCase;

/**
 * @author Divya Kaki Reddy
 * JUnit Test to hold seats with negative number.
 */
public class TestFindAndHoldSeatsWithNegativeNumSeats extends TestCase {

	/*
     * Checking if we can hold with negative number of seats.
     */
    public void testCheckFindAndHoldSeatsWithNegativeNumSeats(){

        TheatreTicketService ticketService = new TheatreTicketService();
        
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold hold = ticketService.findAndHoldSeats(-10, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        System.out.println();
        SeatHold hold1 = ticketService.findAndHoldSeats(0, minLevel, maxLevel, "kakireddy.divya@gmail.com");

        assertTrue(hold == null);
        assertTrue(hold1 == null);
    }
}
