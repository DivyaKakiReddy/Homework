package com.java.ticket.module.theatre;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Divya Kaki Reddy
 * decribe holding of seats.
 */

public class SeatHold {

    private Set<SeatLevelWrapper> holdSet = new HashSet<SeatLevelWrapper>();
    private int holdId;
    private Long timeOfHold;
    private String customerEmail;

    /*
     * Constructor for SeatHold with 4 parameters.
     * @param holdSet, held seat with respective to level in venue.
     * @param holdId, id for holding seats.
     * @param timeOfHold, time of hold.
     * @param customerEmail, unique identifier of customer.
     */
    public SeatHold(Set<SeatLevelWrapper> holdSet, int holdId, Long timeOfHold, String customerEmail){
        this.holdSet = holdSet;
        this.holdId = holdId;
        this.timeOfHold = timeOfHold;
        this.customerEmail = customerEmail;
    }


    /*
     * getter for hold set.
     * @return set of SeatLevelWrapper objects.
     */
    public Set<SeatLevelWrapper> getHoldSet() {
        return holdSet;
    }

    /*
     * setter for hold set.
     * @param set of holds with respective to levels in venue.
     */
    public void setHoldSet(Set<SeatLevelWrapper> holdSet) {
        this.holdSet = holdSet;
    }
    
    /*
     *getter for hold id.
     *@return holdId. 
     */
    public int getHoldId(){
        return holdId;
    }

    /*
     * setter for holdId.
     * @param holdId.
     */
    public void setHoldId(int holdId){
        this.holdId = holdId;
    }
    
    /*
     * getter for time of hold.
     * @return time.
     */
    public Long getTimeOfHold(){
        return timeOfHold;
    }

    /*
     * setter for time of Hold
     * @param time.
     */
    public void setTimeOfHold(Long timeOfHold) {
        this.timeOfHold = timeOfHold;
    }   

    /*
     * getter for  customer Email_id.
     * @return customer Email_id.
     */
    public String getCustomerEmail() {
        return customerEmail;
    }

    /*
     * setter for customer Email-Id.
     * @param Email_id of the customer.
     */
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}
