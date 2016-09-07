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
 * JUnit test for finding and holding seats.
 */
public class TestFindAndHoldSeats extends TestCase{	

	/*
	 * Checking availability after holding seats. 
	 */
    public void testCheckAvailableSeatsAfterHolding(){
        TheatreTicketService ticketService1 = new TheatreTicketService();
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold hold = ticketService1.findAndHoldSeats(567, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        Set<SeatLevelWrapper> holdSet2 = hold.getHoldSet();
        int numberOfReservations = holdSet2.size();
        
        SeatHold hold1 = ticketService1.findAndHoldSeats(879, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        Set<SeatLevelWrapper> holdSet1 = hold1.getHoldSet();
        int numberOfReservations1 = holdSet1.size();
        
        int availableSeat1 = VenueLevel.ORCHESTRA.getAvailableSeats();
        int availableSeat = VenueLevel.BALCONY1.getAvailableSeats();
        
        assertTrue(availableSeat1 == 0);
        assertTrue(numberOfReservations == 567);
        assertTrue(numberOfReservations1 == 879);
        assertFalse(availableSeat == 1600);
    }
    
    /*
     * Checking what happen if we want to hold more than capacity.
     */
    public void testCheckHoldMoreThanCapacity(){
        TheatreTicketService ticketService = new TheatreTicketService();
        int oldAvailableSeats = VenueLevel.BALCONY1.getAvailableSeats();
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold seatHold = ticketService.findAndHoldSeats(567, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        int newAvailableSeats = VenueLevel.BALCONY1.getAvailableSeats();
        assertTrue(oldAvailableSeats != newAvailableSeats + 100);
        assertFalse(oldAvailableSeats == 1600);
        assertTrue(seatHold != null);
    }
}
