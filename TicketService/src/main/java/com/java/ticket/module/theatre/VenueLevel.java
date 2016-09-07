package com.java.ticket.module.theatre;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Divya Kaki Reddy
 * Enum class keeping all the levels in the venue.
 */
public enum VenueLevel {

	// 4 different levels in the venue
    ORCHESTRA(1, "ORCHESTRA", 100.00, 25, 50),
    MAIN(2, "MAIN", 75.00, 20, 100),
    BALCONY1(3, "BALCONY 1", 50.00, 15, 100),
    BALCONY2(4, "BALCONY 2", 40.00, 15, 100);

    private final int level;
    private final String levelName;
    private final double price;
    private final int rows;
    private final int numberOfSeatsPerRow;
    private int availableSeats;
    Seat[][] seatingArrangement;
    private int firstRemainingSeat;
    private int maxSeats;


    /**
     * Constructor for the VenueLevel with 5 parameters.
     * @param level, one level among 4 in  the venue..
     * @param levelName, name of the level in the venue.
     * @param price, price per ticket with respective to level.
     * @param rows, number of rows in particular level.
     * @param numberOfSeatsPerRow, number of seats per row in particular level.
     */
    VenueLevel(int level, String levelName, double price, int rows, int numberOfSeatsPerRow) {
        this.level = level;
        this.levelName = levelName;
        this.price = price;
        this.rows = rows;
        this.numberOfSeatsPerRow = numberOfSeatsPerRow;
        this.seatingArrangement = new Seat[rows][numberOfSeatsPerRow];
        this.availableSeats = rows * numberOfSeatsPerRow;
        this.firstRemainingSeat = 0;
        this.maxSeats = rows * numberOfSeatsPerRow;
        for(int i = 0; i < seatingArrangement.length; ++i){
            for (int j = 0; j < seatingArrangement[0].length; j++){
                seatingArrangement[i][j] = new Seat();
            }
        }
    }

    /**
     * Reserve the seat in a particular level and in a column with respective to row.
     * @param row, reserving a seat in particular row with respective to level.
     * @param column, reserving seat in particular column with respective to row.
     * @return true if the seat was reserved, false otherwise.
     */
    public boolean reserveSeat(int row, int column){
        Seat seat = seatingArrangement[row][column];
        seat.reserveSeat();
        this.availableSeats -= 1;
        return true;
    }

    /**
     * Holding number of seat specified.
     * @param numSeats, number of seats to be hold in particular level.
     * @return set, with allocated seats.
     */
    public Set<SeatLevelWrapper> allocateSeats(int numSeats){
        Set<SeatLevelWrapper> setSeat = new HashSet<SeatLevelWrapper>();
        VenueLevel venueLevel = this;
        int seatsHeld = 0;

        for (int i = 0; i < this.getRows(); ++i){
            for (int j = 0; j < this.getNumberOfSeatsPerRow() && seatsHeld < numSeats; j++) {
                Seat[][] arrangement = (this.getSeatingArrangement());
                if(arrangement[i][j].getSeatState() == arrangement[i][j].getSeatAvailable()){
                    arrangement[i][j].setSeatState(arrangement[i][j].getSeatHeld());
                    SeatLevelWrapper seatLevelWrapper = new SeatLevelWrapper(venueLevel, arrangement[i][j]);
                    setSeat.add(seatLevelWrapper);
                    seatsHeld++;
                }

            }
        }
        this.setAvailableSeats(getAvailableSeats() - setSeat.size());
        return setSeat;
    }

    /**
     * Releases the seat which was put on hold.     
     * @param seat seat which is to be released
     */
    public void releaseSeat(Seat seat){
        seat.setSeatState(seat.getSeatAvailable());
        this.setAvailableSeats(this.getAvailableSeats() + 1);
        //availableSeats += 1;
    }


    /**
     * getter for level.
     * @return level number.
     */
    public int getLevel() {
        return level;
    }

    /**
     * getter for level name.
     * @return name of particular level.
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * getter for price for seats based on level.
     * @return price.
     */
    public double getPrice() {
        return price;
    }


    /**
     * getter for number of seats per row in particular level.
     * @return number.
     */
    public int getNumberOfSeatsPerRow() {
        return numberOfSeatsPerRow;
    }


    /**
     * getter for number of rows in particular level.
     * @return number.
     */
    public int getRows() {
        return rows;
    }

    /**
     * getter for number of available seat in particular level.
     * @return number.
     */
    public int getAvailableSeats() {
        return availableSeats;
    }

    /*
     * setter for number of available seat in particular level.
     * @param availableSeats, number of available seats.
     */
    public void setAvailableSeats(int availableSeats){
        this.availableSeats = availableSeats;
    }

    /*
     * getter for seating arrangements.
     * @return object.
     */
    public Seat[][] getSeatingArrangement() {
        return seatingArrangement;
    }

    /*
     * setter for seating arrangements.
     * @param object of seat class.
     */
    public void setSeatingArrangement(Seat[][] seatingArrangement){
        this.seatingArrangement = seatingArrangement;
    }

    /*
     * getter for remaining seats.
     * @return number.
     */
    public int getFirstUnoccupiedSeat() {
        return firstRemainingSeat;
    }

    /*
     * setter for remaining/unoccupied seats.
     * @param firstRemainingSeat, number of remaining seats.
     */
    public void setFirstUnoccupiedSeat(int firstRemainingSeat) {
        this.firstRemainingSeat = firstRemainingSeat;
    }

    /*
     * getter for maximum seats.
     * @return number.
     */
    public int getmaxSeats() {
        return maxSeats;
    }
}
