package com.java.ticket.module.theatre;

/**
 * @author Divya Kaki Reddy
 * Checks for available seats
 */
public class CheckAvailability extends SeatState {

    private Seat seat;

    /**
     * constructor with one parameter seat which set the seat available.
     * @param seat.
     */
    public CheckAvailability(Seat seat) {
        this.seat = seat;
    }


    /**
     * reserving seat if it is held.
     */
    @Override
    public void reserve() {
        System.out.println("Seat should be held first to reserve.");
    }

    /**
     * holding the seats which are available.
     */
    @Override
    public void hold() {

    }

    /**
     * releasing the seat which was reserved/held.
     */
    @Override
    public void releaseSeat() {

    }
}
