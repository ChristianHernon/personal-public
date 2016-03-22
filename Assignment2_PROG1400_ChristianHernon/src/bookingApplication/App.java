//******************************************
//	Author:		Christian Hernon, W0223388
//	Date:		Feb.02, 2015
//	Purpose:	PROG1100-701 Assignment #2
//				Airplane Booking App
//******************************************

package bookingApplication;

//Imports
import java.util.Scanner;
import java.util.ArrayList;
//import java.util.Collections;

public class App {
	
	ArrayList <Passenger> passengerList = new ArrayList<>();
	boolean seating[][] = new boolean[4][4];
	Scanner reader = new Scanner(System.in);
	int index = 0;
	
	public void start(){
		initializer();				//CALL the initializer
		bookNewPassenger();			//CALL to book a new passenger
		displayBookedPassengers(); 	//CALL to display the listing of all passengers booked
		reader.close();				//Close the scanner object
	}//end start  method
	
	//Loop through the array and set all values to false for empty seats
	public void initializer(){
		for(int i=0; i<seating.length; i++){
			for(int j=0; j<seating.length; j++){
				seating[i][j] = false;
			}
		}
	}//end initializer method
	
	//Method to generate a horizontal line
	public void lineDivider(){
		for(int i=0; i<39; i++){
			System.out.print("-");
		}
		System.out.println("");
	}//end lineDivider method
	
	//Output the seating array in a 4x4 grid with labeling
	public void displaySeatingPlan(){
		System.out.println("");
		lineDivider();
		System.out.println(" Seating Plan for Flight 477B");
		lineDivider();
		String header = "A B C D";
		System.out.println(String.format("%1$" + 22 + "s", header));
		for(int i=0; i<seating.length; i++){
			String lineOutput = "";
			lineOutput += (i+1) + " ";
			for(int j=0; j<4; j++){
				if(seating[i][j] == false){
					lineOutput += "O ";
				}
				else{
					lineOutput += "X ";
				}
			}
			System.out.println(String.format("%1$" + 23 + "s", lineOutput));
		}
		System.out.println("");
	}//end displaySeatingPlan method
	
	//Create booking for a new passenger
	public void bookNewPassenger(){
		boolean repeat = true;
		while (repeat){
			if (checkFirstClass() || checkEconomyClass()) {
				Passenger newPassenger = new Passenger();
				passengerList.add(newPassenger);
				passengerList.get(index).name = getPassengerName();
				String seatingClass = getSeatingClass();
				if (!seatingClass.equals("Next Flight")){
					String seatingType = getSeatingType();
					seatFinder(seatingClass, seatingType, passengerList.get(index).name);
					displaySeatingPlan();
				}
				index++;
				repeat = continueRunning();
			}
			else {
				System.out.println("We're sorry the plane is full. The next flight leaves in 3 hours.");
				repeat = false;
			}
		}	
	}//end bookNewPassenger method
	
	//Get a name for the new passenger
	public String getPassengerName(){

		String name = "";
		String temp = "";
		boolean nameEntered = false;
		while (!nameEntered){
			System.out.print("\nPlease enter the passenger's name: ");
			temp = reader.nextLine();
			if (!temp.isEmpty()){
				name = temp;
				nameEntered = true;
			}
			else {
				System.out.println("Invalid Entry");
			}
		}
		return name;
	}//end getPassengerName method
	
	//Get seating class for new passenger
	public String getSeatingClass(){
		String seatingClass = "";
		boolean classChosen = false;
		while (!classChosen){
			System.out.print("Choose your class; 1-First Class, 2-Economy, 3-Exit Program: ");
			String input = reader.nextLine();
			if (input.equals("1")){
				if (checkFirstClass()){
					seatingClass = "First Class";
					classChosen = true;
				}
				else if (checkEconomyClass()) {
					boolean decision = false;
					while (!decision){
						System.out.print("First Class is full, would you like to ride Economy Class? (Y/N): ");
						input = reader.nextLine();
						if (input.equalsIgnoreCase("y")){
							seatingClass = "Economy Class";
							classChosen = true;
							decision = true;
						}
						else if (input.equalsIgnoreCase("n")){
							System.out.println("The next flight leaves in 3 hours. Better luck next time.");
							seatingClass = "Next Flight";
							classChosen = true;
							decision = true;
						}
						else {
							System.out.println("Invalid Entry");
						}						
					}
				}
				else {
					System.out.print("The plane is full. The next flight leaves in 3 hours.");
				}
			}
			else if (input.equals("2")){
				if (checkEconomyClass()){
					seatingClass = "Economy Class";
					classChosen = true;
				}
				else if (checkFirstClass()) {
					boolean decision = false;
					while (!decision){
						System.out.print("First Class is full, would you like to ride First Class? (Y/N): ");
						input = reader.nextLine();
						if (input.equalsIgnoreCase("y")){
							seatingClass = "First Class";
							classChosen = true;
							decision = true;
						}
						else if (input.equalsIgnoreCase("n")){
							System.out.println("The next flight leaves in 3 hours. Better luck next time.");
							seatingClass = "Next Flight";
							classChosen = true;
							decision = true;
						}
						else {
							System.out.println("Invalid Entry");
						}						
					}
				}
				else {
					System.out.println("The plane is full. The next flight leaves in 3 hours.");
				}				
			}
			else if (input.equals("3")){
				System.exit(0);
			}
			else {
				System.out.println("Invalid Entry");
			}
		}
		return seatingClass;
	}
	
	//Method to get the seating type preference
	public String getSeatingType(){
		String seatingType = "";
		boolean typeChosen = false;
		while (!typeChosen){
			System.out.print("Choose your seating type; 1-Window, 2-Aisle, 3-Exit Program: ");
			String input = reader.nextLine();
			if (input.equals("1")){
				seatingType = "Window";
				typeChosen = true;
			}
			else if (input.equals("2")){
				seatingType = "Aisle";
				typeChosen = true;
			}
			else if (input.equals("3")){
				System.exit(0);
			}
			else {
				System.out.println("Invalid Entry");
			}
		}		
		return seatingType;
	}
	
	//Check to see if more passengers will be booked
	public boolean continueRunning(){
		boolean repeat = true;
		System.out.print("Would you like to book another passenger? (Y/N): ");
		if(reader.nextLine().equalsIgnoreCase("n")){
			repeat = false;
		}
		return repeat;
	}//end continueRunning method
	
	//Use the information from the chooseSeating method to find an available seat
	public boolean seatFinder(String classType, String seatType, String name){
		boolean seatBooked = false;
		int row = 0;
		int col = 0;
		int iMax = 0;
		int jMax = 0;
		int countBy = 0;
		if(classType.equals("First Class")){
			row = 0;
			iMax = 2;
		}
		else if(classType.equals("Economy Class")){
			row = 2;
			iMax = 4;
		}
		if(seatType.equals("Window")){
			col = 0;
			jMax = 4;
			countBy = 3;
		}
		else if(seatType.equals("Aisle")){
			col = 1;
			jMax = 3;
			countBy = 1;
		}
		for(int i=row; i<iMax; i++){
			for(int j=col; j<jMax; j+=countBy){
				if(seating[i][j] == false && seatBooked == false){
					seating[i][j] = true;
					seatBooked = true;
					displayBordingPass(name, i, j, classType);
				}
			}
		}
		if(!seatBooked){
			System.out.println("Sorry, there are no more " + seatType + " seats available. "
					+ "You will be booked into the first available seat in " + classType);
			for(int i=row; i<iMax; i++){
				for(int j=0; j<4; j++){
					if(seating[i][j] == false && seatBooked == false){
						seating[i][j] = true;
						seatBooked = true;
						displayBordingPass(name, i, j, classType);
					}
				}
			}
		}		
		return seatBooked;
	}//end seatFinder method
	
	//This method checks to see that there is at least one open seat in first class
	public boolean checkFirstClass(){
		boolean seatAvailable = false;
		int firstClass = 2;
		for(int i=0; i<firstClass; i++){
			for(int j=0; j<seating.length; j++){
				if(seating[i][j] == false){
					seatAvailable = true;
				}
			}
		}			
		return seatAvailable;
	}//end checkFirstClass method
	
	//This method checks to see that there is at least one open seat in economy class
	public boolean checkEconomyClass(){
		boolean seatAvailable = false;
		int economyClass = 4;
		for(int i=2; i<economyClass; i++){
			for(int j=0; j<seating.length; j++){
				if(seating[i][j] == false){
					seatAvailable = true;
				}
			}
		}			
		return seatAvailable;
	}//end checkEconomyClass method
	
	//Takes the values for passenger name, seat, and class to output a formatted boarding pass
	public void displayBordingPass(String name, int row, int col, String ticketType){
		System.out.println("");
		lineDivider();
		String seatLetter = "";
		switch (col){
			case 0: seatLetter = "A";
					break;
			case 1: seatLetter = "B";
					break;
			case 2: seatLetter = "C";
					break;
			case 3: seatLetter = "D";
					break;		
		}
		System.out.println("Bording Pass for Tiny Planes Airlines\nFlight: 477B");
		System.out.println("Passenger Name: " + name);
		System.out.println("Ticket Type: " + ticketType);
		System.out.println("Seat: " + (row + 1) + seatLetter);	
		lineDivider();	
		passengerList.get(index).seat = (row + 1) + seatLetter;
	}//end displayBoardingPass method	

	//Show a listing of all the passengers booked
	public void displayBookedPassengers(){
		System.out.println("\nPassenger Manifest for flight 477B\n" + String.format("%1$-" + 20 + "s", "Name") + "Seat");
		lineDivider();
		for (int i=0; i<passengerList.size(); i++){
			String name = passengerList.get(i).name;
			String seat = passengerList.get(i).seat;
			System.out.println(String.format("%1$-" + 20 + "s", name) + seat);
		}
	}	
}//end App class






