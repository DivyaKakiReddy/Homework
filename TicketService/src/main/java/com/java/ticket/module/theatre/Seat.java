package com.java.ticket.module.theatre;


/**
 * @author Divya Kaki Reddy 
 * describes seat objects in a VenueLevel
 */
public class Seat {

    private SeatState available;
    private SeatState reserved;
    private SeatState held;
    private SeatState seatState;
    
    /**
     * constructor to initialize required classes.
     */
    public Seat(){
        available = new CheckAvailability(this);
        reserved = new CheckReservation(this);
        held = new CheckHolds(this);
        seatState = available;
    }

    /**
     * getter for seatState
     * @return seatState which represents the current state of seat
     */
    public SeatState getSeatState() {
        return seatState;
    }

    /**
     * setter for seatState
     * @param newSeatState set state of seat
     */
    void setSeatState(SeatState newSeatState){
        this.seatState = newSeatState;
    }

    /**
     * reserve the seat thus making it unavailable.
     */
    public void reserveSeat(){
        seatState.reserve();
    }

    /**
     * hold the seat.
     */
    public void hold(){
        seatState.hold();
    }

    /**
     * release the seat after particular time.
     */
    public void releaseSeat(){
        seatState.releaseSeat();
    }

    /**
     * returns the state reserved.
     * @return reserved state
     */
    public SeatState getSeatReserved(){
        return reserved;
    }

    /**
     * returns the state held
     * @return held state
     */
    public SeatState getSeatHeld(){
        return held;
    }

    /**
     * returns the available state.
     * @return available state.
     */
    public SeatState getSeatAvailable(){
        return available;
    }
}
