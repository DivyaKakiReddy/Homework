package com.java.ticket.module.theatre;

/**
 * @author Divya Kaki Reddy
 * Reserving a seat which is held.
 */
public class CheckReservation extends SeatState {

    private Seat seat;

    /**
     * constructor with one parameter seat to reserve seat.
     * @param seat.
     */
    public CheckReservation(Seat seat) {
        this.seat = seat;
    }

    /**
     * reserving a seat which is in Reserved state
     */
    @Override
    public void reserve() {
        System.out.println("Seat is already reserved.");
    }

    /**
     * holding a seat which is reserved.
     */
    @Override
    public void hold() {
        System.out.println("Already reserved.");
    }

    /**
     * releasing the seat.
     */
    @Override
    public void releaseSeat() {
        System.out.println("Release seat");
    }
}
