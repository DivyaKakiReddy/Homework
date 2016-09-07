package com.java.ticket.module.theatre;


/**
 * @author Divya Kaki Reddy
 * describe different states in ticket service
 */
public abstract class SeatState {

	/*
	 * To reserve seats.
	 */
    protected void reserve(){
    }

    /*
     * To hold the seats.
     */
    protected void hold(){
    }

    /*
     * To release the seats which was held/reserved.
     */
    protected void releaseSeat(){
    }
}

