package com.java.ticket.module.theatre;

/**
 * @author Divya Kaki Reddy
 * Check for held seats
 */
public class CheckHolds extends SeatState {

    private Seat seat;

    /**
     * Constructor with one parameter newSeat for which the we need to hold.
     * @param newSeat.
     */
    public CheckHolds(Seat newSeat){
        seat = newSeat;
    }

    /**
     * reserving a seat when it is held.
     */
    @Override
    public void reserve() {
        seat.setSeatState(seat.getSeatReserved());
    }

    /**
     * holding a seat when it is already held.
     */
    @Override
    public void hold() {
        System.out.println("There already exists a hold on the seat.");
    }

    /**
     * releasing the held seat.
     */
    @Override
    public void releaseSeat() {
        seat.setSeatState(seat.getSeatAvailable());

    }
}
