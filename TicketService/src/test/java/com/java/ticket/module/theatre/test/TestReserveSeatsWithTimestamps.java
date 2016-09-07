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
public class TestReserveSeatsWithTimestamps extends TestCase{
	
	public void testReserveSeatsWithTimeStamp(){
        TheatreTicketService ticketService = new TheatreTicketService();
        Optional<Integer> minLevel = Optional.ofNullable(null);
        Optional<Integer> maxLevel = Optional.ofNullable(null);
        
        SeatHold hold = ticketService.findAndHoldSeats(567, minLevel, maxLevel, "kakireddy.divya@gmail.com");
        try{
            Thread.sleep(200);
        }
        catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        SeatHold hold1 = ticketService.findAndHoldSeats(567, minLevel, maxLevel, "kakireddy.divya@gmail.com");

        int availableSeats = VenueLevel.ORCHESTRA.getAvailableSeats();
        int availableSeats1 = VenueLevel.MAIN.getAvailableSeats();
        int availableSeats2 = VenueLevel.BALCONY1.getAvailableSeats();
        int availableSeats3 = VenueLevel.BALCONY2.getAvailableSeats();

        assertFalse(availableSeats == 435);
        assertFalse(availableSeats1 == 2324);
        assertFalse(availableSeats2 == 897);
        assertFalse(availableSeats3 == 1139);
        }
}
