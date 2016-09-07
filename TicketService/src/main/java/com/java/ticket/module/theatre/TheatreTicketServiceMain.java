package com.java.ticket.module.theatre;

import java.util.*;

/**
 * @author Divya Kaki Reddy
 * 
 * Class contain main function.
 */
public class TheatreTicketServiceMain {

    public static void main(String[] args) {

        int firstLevelNumber = VenueLevel.values()[0].getLevel();
        int lastIndexValues = VenueLevel.values().length - 1;
        int lastLevelNumber = VenueLevel.values()[lastIndexValues].getLevel();
        TheatreTicketService ticketService = new TheatreTicketService();

        System.out.println("Homework-Ticket Service");
        System.out.println("*****  Welcome to the ticket service.  *****");

        loop: while (true) {

            System.out.println("");
            System.out.println("");
            System.out.println("Please choose from the choices below.");
            System.out.println("1. Find the number of seats available.");
            System.out.println("2. Find and hold seats in the theater.");
            System.out.println("3. Reserve seats.");
            System.out.println("4. Wait for a while.");
            System.out.println("5. Display the number of seats available with every level.");
            System.out.println("6. Exit from the system.");
            System.out.println();

            Scanner scanner = new Scanner(System.in);
            int input;
            try{
                input = scanner.nextInt();
            }
            catch (InputMismatchException e){
                System.out.println("Please enter an integer value within the range.");
                break;
            }

            switch (input) {


                case (1): {

                    System.out.println("Please enter the level from "+ firstLevelNumber +" and "+ lastLevelNumber +", for which you like to check.");
                    Scanner scanner1 = new Scanner(System.in);
                    String venueLevelString = scanner1.nextLine();
                    Integer venueLevelInt;
                    try{
                        venueLevelInt = Integer.parseInt(venueLevelString);
                    }
                    catch (NumberFormatException e){
                        venueLevelInt = null;
                    }
                    if ((venueLevelInt == null) || (firstLevelNumber <= venueLevelInt && venueLevelInt <= lastLevelNumber)){
                        Optional<Integer> venueLevel = Optional.ofNullable(venueLevelInt);
                        int numSeatsAvailable = ticketService.numberOfSeatsAvailable(venueLevel);
                        if(venueLevel.isPresent()){
                            System.out.println(numSeatsAvailable+ " number of seats available in this level.");
                        }
                        else {
                            System.out.println(numSeatsAvailable+ " number of tickets available in the theatre.");
                        }
                    }
                    else {
                        System.out.println("Number not in range");
                    }

                    break;
                }

                case 2: {
                    System.out.println("Please enter the number of seats you like to reserve.");
                    Scanner scanner1 = new Scanner(System.in);
                    Integer numSeats;
                    try {
                        numSeats = scanner1.nextInt();
                    } catch (java.util.InputMismatchException e) {
                        System.out.println("Please enter an integer value.");
                        break;
                    }
                    System.out.println("Please enter the level from " +firstLevelNumber + " to " + lastLevelNumber + " in which you like to reserve seats in.");
                    String minVenueLevelString = scanner1.nextLine();
                    Integer minVenueLevelInt;
                    try {
                        minVenueLevelInt = Integer.parseInt(minVenueLevelString);
                    } catch (java.lang.NumberFormatException e) {
                        minVenueLevelInt = null;
                    }
                    Optional<Integer> minVenueLevel;
                    if ((minVenueLevelInt == null) || (firstLevelNumber <= minVenueLevelInt && minVenueLevelInt <= lastLevelNumber)) {
                        minVenueLevel = Optional.ofNullable(minVenueLevelInt);
                        if (minVenueLevel == null) {
                            minVenueLevel = Optional.of(firstLevelNumber);
                        }
                    } else {
                        System.out.println("Number not in range");
                        break;
                    }
                    String maxVenueLevelString = scanner1.nextLine();
                    Integer maxVenueLevelInt;
                    try {
                        maxVenueLevelInt = Integer.parseInt(maxVenueLevelString);
                    } catch (java.lang.NumberFormatException e) {
                        maxVenueLevelInt = null;
                    }
                    Optional<Integer> maxVenueLevel;
                    if ((firstLevelNumber <= maxVenueLevelInt && maxVenueLevelInt <= lastLevelNumber)) {
                        maxVenueLevel = Optional.ofNullable(maxVenueLevelInt);
                        if (maxVenueLevel == null) {
                            maxVenueLevel = Optional.of(lastLevelNumber);
                        }
                    } else if (maxVenueLevelInt == null) {
                        maxVenueLevel = Optional.ofNullable(maxVenueLevelInt);
                        if (maxVenueLevel == null) {
                            maxVenueLevel = Optional.of(lastLevelNumber);
                        }
                    } else {
                        System.out.println("Number not in range");
                        break;
                    }

                    System.out.println();
                    System.out.println("Please enter the email id of the customer");
                    String customerEmail = scanner1.nextLine();
                    System.out.println();
                    System.out.println("Total Avaliable seats are:");
                    SeatHold seatHold = ticketService.findAndHoldSeats(numSeats, minVenueLevel, maxVenueLevel, customerEmail);
                    ticketService.displaySeatsInAllLevels();

                    break;
                }
                
                case (3): {

                    System.out.println("This method cannot be run using command line because the seatHoldId's are randomly generated and therefore can't be entered.");
                    break ;
                }

                case (4): {
                	System.out.println("you are waiting for some time....");
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    break ;
                }

                case (5): {
                	System.out.println("Current Available seats are: ");
                    ticketService.displaySeatsInAllLevels();
                    break;
                }

                case (6): {
                	System.out.println("*** Thank you ***");
                    System.exit(0);
                }

                default: {
                    System.out.println("Please check the input and try again !");
                }
            }
        }
    }
}
