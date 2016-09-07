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
 * JUnit Test to check the availability of seats.
 */
public class TestCheckAvailabilityAfterHoldingThread extends TestCase {

	/*
     * Checking availability after consecutive holds.
     */
    public void testCheckAvailableSeatsAfterHoldingThread(){
        TheatreTicketService theatreTicketService = new TheatreTicketService();
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold hold = theatreTicketService.findAndHoldSeats(567, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        SeatHold hold1 = theatreTicketService.findAndHoldSeats(897, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        Set<SeatLevelWrapper> holdSet = hold.getHoldSet();
        Set<SeatLevelWrapper> holdSet1 = hold1.getHoldSet();
        
        int numberOfReservations1 = holdSet.size();        
        int availableSeats1 = VenueLevel.values()[0].getAvailableSeats();
        assertTrue(numberOfReservations1 == 567);
        assertTrue(availableSeats1 == 0);
        
        int numberOfReservations = holdSet1.size();
        int availableSeats = VenueLevel.values()[1].getAvailableSeats();
        
        assertTrue(numberOfReservations == 897);
        assertTrue(availableSeats == 1786);
    }
}
