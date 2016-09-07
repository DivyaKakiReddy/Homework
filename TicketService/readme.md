@author - Divya Kaki Reddy.

#Ticket Service Homework

##Aim
To build a ticket management system.

##Input/Output
Input and output using command line. The program interacts with the user taking inputs and displays output.

##Tested Environment
1. Windows 10 operating system, 64-bit OS, x64-based processor.
2. Java : JDK 1.8.0_31
3. Maven : 3.3.9
4. JUnit : 3.8.1 

##How to use the application.
* Extract complete application content into folder.
* Open "Command prompt" and locate pom.xml file in the folder.
* To build a project
	1. mvn clean
	2. mvn package
	3. jar file will be created "Homework-ticket-service.jar" in the target folder.
	4. change the directory to target folder.
	5. run the jar file using command "**java -jar Homework-Ticket-service.jar**"
	6. it start running the project.

##Algorithm
* First we need hold than reserve the seats.
* Reservation of seats are done in FIFO(First in first out).
* Sitting arrangement are not done manually.
* Only level preference is given but not the position in the level.

##Assumptions 
* We can not directly reserve seats, it goes through the processes of checking availability, holding required seats and then reserving.
* Hold won't be removed even if we reserve the held seats. Once it each to threshold time(30Sec for this application) automatically remove holds.
* Can not fill higher level until lower level are fill.

##Dependencies
* JUnit[3.8.1]



