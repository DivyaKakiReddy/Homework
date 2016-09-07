package com.java.ticket.module.theatre;

import java.util.*;
import java.util.UUID;

/**
 * @author Divya Kaki Reddy
 * Implements methods in the TicketService interface.
 */
public class TheatreTicketService implements TicketService{

    private Venue venue;
    private Map<Long, SeatHold> timeStampToSeatHoldMap;
    private Set<Integer> seatHoldIdSet;
    private Map<Integer, SeatHold> holdIdToSeatHoldMap;
    private Set<String> confirmationCodeString;

    /**
     * constructor.
     */
    public TheatreTicketService() {
        venue = new Venue();
        timeStampToSeatHoldMap = new TreeMap<>();   
        seatHoldIdSet = new HashSet<>();
        holdIdToSeatHoldMap = new HashMap<>();
        confirmationCodeString = new HashSet<>();
    }

    /**
     * @return number of seats available based on the level entered,
     *         if no level is entered, returns the total number of seats available in the theatre.
     *         if undefined level is passed, the method returns '-1' indicating invalid operation.
     * @param venueLevel a numeric venue level identifier to limit the search
     * @return
     */
    public synchronized int numberOfSeatsAvailable(Optional<Integer> venueLevel) {

        if (venueLevel.isPresent()) {
            if((venueLevel.get() < VenueLevel.values()[0].getLevel()) || (venueLevel.get() > VenueLevel.values()[VenueLevel.values().length - 1].getLevel())){
                return -1;                      //invalid operation
            }
            int venueLevelIndex = venueLevel.get()-1;
            VenueLevel venueLevel1 = VenueLevel.values()[venueLevelIndex];
            return venueLevel1.getAvailableSeats();
        }

        return this.getAvailableSeats(venueLevel);
    }

    /**
     * holding seats temporarily for a given threshold time before which they can be reserved.
     * @param numberOfSeats, number of seats remaining and can hold
     * @param minimumLevel, minimum venue level
     * @param maximumLevel, maximum venue level
     * @param customerEmail, unique identifier for the customer.
     * @return
     */
    public synchronized SeatHold findAndHoldSeats(int numberOfSeats, Optional<Integer> minimumLevel, Optional<Integer> maximumLevel, String customerEmail) {

        if(minimumLevel == null || maximumLevel == null || numberOfSeats <0 || customerEmail == ""){
            if(customerEmail == "")
                System.out.println("Please enter a valid email. No seats were reserved.");
            if (numberOfSeats < 0)
                System.out.println("Please enter a positive seat number. No seats were reserved.");
            return null;
        }

        removeOldHolds();

        Optional<Integer> valueOptional = Optional.ofNullable(null);
        if (numberOfSeats > this.numberOfSeatsAvailable(valueOptional)) {
            return null;
        }

        Integer venueLevelMin = minimumLevel.orElse(VenueLevel.values()[0].getLevel());
        Integer venueLevelMax = maximumLevel.orElse(VenueLevel.values()[VenueLevel.values().length - 1].getLevel());

        if(venueLevelMax < venueLevelMin){
            Integer temp = venueLevelMin;
            venueLevelMin = venueLevelMax;
            venueLevelMax = temp;
        }

        SeatHold seatHold = holdSeats(numberOfSeats, venueLevelMin, venueLevelMax, customerEmail);

        if(seatHold != null) {
            timeStampToSeatHoldMap.put(seatHold.getTimeOfHold(), seatHold);
            Set<SeatLevelWrapper> set = seatHold.getHoldSet();
        }

        return seatHold;
    }

    /**
     * Removes the existing holds based on the threshold value.
     */
    private void removeOldHolds() {

        for (Long t : timeStampToSeatHoldMap.keySet()) {
            if ((System.currentTimeMillis() - t) > 30000) {
                SeatHold releaseSeatHold = timeStampToSeatHoldMap.get(t);
                Set<SeatLevelWrapper> releaseSet = releaseSeatHold.getHoldSet();
                for (SeatLevelWrapper seatLevelWrapper : releaseSet) {
                    VenueLevel venueLevel = seatLevelWrapper.getVenueLevel();
                    Seat seat = seatLevelWrapper.getSeat();
                    venueLevel.releaseSeat(seat);
                }
                timeStampToSeatHoldMap.remove(t);
                holdIdToSeatHoldMap.remove(releaseSeatHold.getHoldId());
            }
            else
                break;
        }
    }

    /**
     * function to hold seats using findAndHoldSeats.
     * @param numberOfSeats, number of seats to be held.
     * @param minimumLevel, min level to reserve seats
     * @param maximumLevel, max level to reserve the seats
     * @param customerEmail, unique identifier for the customer
     * @return
     */
    private SeatHold holdSeats(int numberOfSeats, Integer minimumLevel, Integer maximumLevel, String customerEmail) {

        int totalPossibleSeats = venue.maxSeatsBetweenTwoLevels(VenueLevel.values()[minimumLevel-1], VenueLevel.values()[maximumLevel-1]);
        Set<SeatLevelWrapper> levelSeats = null;
        Set<SeatLevelWrapper> heldSeats = new HashSet<>();
        if (totalPossibleSeats < numberOfSeats) {
            System.out.println("Not enough seats in the levels");
        } else {

            Map<Integer, VenueLevel> levelToVenueMap = venue.getLevelToVenueLevelHashMap();
            for (Integer level : levelToVenueMap.keySet()) {
                if (numberOfSeats == 0) {
                    break;
                }
                if ((level >= minimumLevel) && level <= maximumLevel) {
                    VenueLevel currentLevel = levelToVenueMap.get(level);
                    if (currentLevel.getAvailableSeats() > 0) {
                        levelSeats = currentLevel.allocateSeats(numberOfSeats);
                        numberOfSeats = numberOfSeats - levelSeats.size();
                    }
                    if (levelSeats != null) {
                        for (SeatLevelWrapper seatLevelWrapper : levelSeats) {
                            heldSeats.add(seatLevelWrapper);
                        }
                    }
                }

            }
        }
        Long time = System.currentTimeMillis();

        int holdId = generateHoldId();
        seatHoldIdSet.add(holdId);

        if (heldSeats.size() != 0) {
            SeatHold seatHold = new SeatHold(heldSeats, holdId, time, customerEmail);
            holdIdToSeatHoldMap.put(holdId, seatHold);
            return seatHold;
        }
        return null;
    }

    /**
     * Reserving seats which were in hold.
     * @param seatHoldId, Identifer of Seats held.
     * @param customerEmail, unique identifier for the customer.
     * @return
     */
    public synchronized String reserveSeats(int seatHoldId, String customerEmail) {

        Integer seatHoldIdInteger = new Integer(seatHoldId);

        if(seatHoldIdInteger == null)
            return null;

        SeatHold seatHold = holdIdToSeatHoldMap.get(seatHoldId);

        if(seatHold != null){
            Set<SeatLevelWrapper> heldSeatsWrapper = seatHold.getHoldSet();
            for (SeatLevelWrapper seatLevelWrapper: heldSeatsWrapper){
                VenueLevel venueLevel = seatLevelWrapper.getVenueLevel();
                Seat seat = seatLevelWrapper.getSeat();
                seat.reserveSeat();
            }
            timeStampToSeatHoldMap.remove(seatHold.getTimeOfHold());
            seatHoldIdSet.remove(seatHold.getHoldId());
        }
        else {
            return null;
        }

        //generate confirmation code.
        return generateConfirmationCode();

    }

    /**
     * Generate random holdId.
     * @return holdId for seat hold.
     */
    private int generateHoldId() {

        Random randomNum = new Random();
        Integer holdId = randomNum.nextInt((Integer.MAX_VALUE));
        while (seatHoldIdSet.contains(holdId)) {
            holdId = randomNum.nextInt((Integer.MAX_VALUE));
        }
        return holdId;
    }

    /**
     * Generates a reservation confirmation code for the customer booking.
     * @return String about the confirmation.
     */
    private String generateConfirmationCode() {

        String confirmationCode = "";
        while(confirmationCodeString.contains(confirmationCode) || confirmationCode.equals("")){
            confirmationCode = UUID.randomUUID().toString();
        }
        confirmationCodeString.add(confirmationCode);
        System.out.println("The confirmation code "+confirmationCode);
        return confirmationCode;
    }

    /**
     * Returns the number of available seats in a particular level.
     * @param level, to get number of seats available.
     * @return number of available seats in particular level
     */
    private int getAvailableSeats(Optional<Integer> level) {
        int totalAvailableSeats = 0;
        Map<Integer, VenueLevel> hashMap = venue.getLevelToVenueLevelHashMap();
        for (Integer levelNumber : hashMap.keySet()) {
            totalAvailableSeats = totalAvailableSeats + hashMap.get(levelNumber).getAvailableSeats();
        }
        return totalAvailableSeats;
    }

    /**
     * Display Number of available seats with respective to level in venue.
     * Expected output - "level ---> xxx (number of seats available)"
     */
    public void displaySeatsInAllLevels(){
        Map<Integer, VenueLevel> hashMap = venue.getLevelToVenueLevelHashMap();
        for (Integer level : hashMap.keySet()) {
            System.out.println(hashMap.get(level).getLevelName()+ " ----> "+hashMap.get(level).getAvailableSeats());
        }
    }

    /**
     * getter for venue.
     * @return venue.
     */
    public Venue getVenue() {
        return venue;
    }

    /**
     * sets the venue object.
     * @param venue.
     */
    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    /**
     * getter for timestamp of seat hold's.
     * @return Map.
     */
    public Map<Long, SeatHold> getTimeStampToSeatHoldMap() {
        return timeStampToSeatHoldMap;
    }

    /**
     * setter for timeStamp.
     * @param timeStampToSeatHoldMap.
     */
    public void setTimeStampToSeatHoldMap(Map<Long, SeatHold> timeStampToSeatHoldMap) {
        this.timeStampToSeatHoldMap = timeStampToSeatHoldMap;
    }

    /**
     * getter for HoldId's.
     * @return set of assigned HoldId's.
     */
    public Set<Integer> getSeatHoldIdSet() {
        return seatHoldIdSet;
    }

    /**
     * setter for Seat HoldId's.
     * @param seatHoldIdSet.
     */
    public void setSeatHoldIdSet(Set<Integer> seatHoldIdSet) {
        this.seatHoldIdSet = seatHoldIdSet;
    }

    /**
     * getter for holdId's with respective seat.
     * @return holdIdToSeatHoldMap.
     */
    public Map<Integer, SeatHold> getHoldIdToSeatHoldMap() {
        return holdIdToSeatHoldMap;
    }

    /**
     * setter for holdId's to SeatHold's.
     * @param holdIdToSeatHoldMap.
     */
    public void setHoldIdToSeatHoldMap(Map<Integer, SeatHold> holdIdToSeatHoldMap) {
        this.holdIdToSeatHoldMap = holdIdToSeatHoldMap;
    }

    /**
     * getter for confirmation String for seat booking activity.
     * @return confirmationCodeString.
     */
    public Set<String> getconfirmationCodeString() {
        return confirmationCodeString;
    }

    /**
     * setter for confirmation String for seat booking activity.
     * @param confirmationCodeString.
     */
    public void setconfirmationCodeString(Set<String> confirmationCodeString) {
        this.confirmationCodeString = confirmationCodeString;
    }
}