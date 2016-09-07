package com.java.ticket.module.theatre;

/**
 * @author Divya Kaki Reddy
 * Wraps Seats and levels in venue.
 */
public class SeatLevelWrapper {

    Seat seat;
    VenueLevel venueLevel;

    /*
     * Constructor for SeatLevelWrapper with two parameters.
     * @param venueLevel, object of class VenueLevel.
     * @param seat, object of class Seat.
     */
    SeatLevelWrapper(VenueLevel venueLevel, Seat seat){
        this.venueLevel = venueLevel;
        this.seat = seat;
    }

    /*
     * getter for seat.
     * @return seat object.
     */
    public Seat getSeat() {
        return seat;
    }

    /*
     * setter for seat class.
     * @param seat object.
     */
    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    /*
     * getting for venue's level.
     * @return venueLevel object.
     */
    public VenueLevel getVenueLevel() {
        return venueLevel;
    }

    /*
     * setter for venueLevel class.
     * @param venueLevel object.
     */
    public void setVenueLevel(VenueLevel venueLevel) {
        this.venueLevel = venueLevel;
    }
}
