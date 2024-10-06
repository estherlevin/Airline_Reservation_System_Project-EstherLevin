package advancedProgrammingProject;
import java.util.*;
import java.time.*;
/* client code will allow use several classes add
and display flights, passengers, and employees to the
airline. Provides a menu-driven interface for
users to interact with the airline reservation system. */
public class AdvancedProgrammingProject7 {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
			
		Airline air = null;
		//instantiating objects
		try {
			air = new Airline();
		}
		catch(IllegalArgumentException e) {
			System.out.println("Invalid data.");
		}
		catch(NullPointerException e) {
			System.out.println("Objects are null.");
		}
		
		int choice = 0;
		do {
			//displaying menu 
			displayMenu();
			
			//if there's an exception, will continue looping until none is thrown
			boolean status = true;	
			do {
				//allowing user to enter a choice
				try {
					choice = keyboard.nextInt();
					while(choice < 0 || choice > 8) {
						System.out.print("Invalid choice. Please re-enter: ");
						choice = keyboard.nextInt();
					}
					
					keyboard.nextLine();
					//if no exception was thrown, status will become false
					status = false;
				}
				catch(InputMismatchException e) {
					System.out.println("Invalid data. Please re-enter:");
					keyboard.nextLine();
				}
			}
			while(status);
		
			//will call method with switch statement to invoke method of users choice
			chooseSwitch(keyboard,choice,air);
		}
		while(choice != 8);
		
	}
	
	//method will display menu
	public static void displayMenu() {
		System.out.println("Enter:\n1) To create a new flight");
		System.out.println("2) To create a flight reservation\n" +
			"3) To cancel a reservation\n4) To view flight details"
			+ "\n5) To view passenger details\n6) To add an employee to the airline system"
			+ "\n7) View employee details\n8) To exit program");
	}
	
	//method has switch statement to invoke method based on users choice
	public static void chooseSwitch(Scanner keyboard,int choice,Airline air) {
		switch(choice) {
			case 1: 
				createFlight(keyboard,air);
				break;
			case 2: 
				createReservation(keyboard,air);
				break;
			case 3: 
				cancelReservation(keyboard,air);
				break;
			case 4: 
				displayFlight(keyboard,air);
				break;
			case 5: 
				displayPassenger(keyboard,air);
				break;
			case 6: 
				createEmployee(keyboard,air);
				break;
			case 7:
				displayEmployee(keyboard,air);
				break;
			default: 
				System.out.println("Goodbye!");
		}
	}
	//will allow user to create a new Flight object in the Flight ArrayList
	public static void createFlight(Scanner keyboard,Airline air) {
		//finding field values for Flight class to create new Flight object
		try { 
			
			/* adding a new Flight object to Flight ArrayList
			by first calling method to create the object */
			Flight flt = createFlightObject(keyboard);
			
			//adding the new flight object to the Airline ArrayList
			air.addFlight(flt);
			
			System.out.println("Flight added successfully.\n");
		}
		catch(InvalidTimeException e) {
			System.out.println(e.getMessage());
		}
		catch(IllegalArgumentException e) {
			System.out.println("Illegal data entered.");
		}
		catch(FlightExistsException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//user will input flight number
	public static int findFlightNum(Scanner keyboard) {
		boolean status = true;
		
		System.out.print("Enter the flight number: ");
		int flightNum = 0;
		
		//will loop if exception is thrown
		do {
			try {
				flightNum = keyboard.nextInt();
				//validating that flight number isn't less than 0
				while(flightNum < 0) {
					System.out.println("Invalid data. Please re-enter:");
					flightNum = keyboard.nextInt();
				}
				//if no exception thrown, status will be false
				status = false;
				keyboard.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid data. Please re-enter:");
				keyboard.nextLine();
			}	
		}
		while(status);
		//returning the flight number
		return flightNum;
	}
	
	//user will input departure city
	public static String findDepartureCity(Scanner keyboard) {
		System.out.print("Enter departure city: ");
		String departure = keyboard.nextLine();
		while(departure.isEmpty()) {
			System.out.println("departure city must be entered. Please re-enter: ");
			departure = keyboard.nextLine();
		}
		return departure;
	}
	//user will input arrival city
	public static String findArrivalCity(Scanner keyboard) {
		System.out.print("Enter arrival city: ");
		String arrival = keyboard.nextLine();
		while(arrival.isEmpty()) {
			System.out.println("Arrival city must be entered. Please re-enter: ");
			arrival = keyboard.nextLine();
		}
		return arrival;
	}
	//user will enter departure time and date
	public static LocalDateTime findDepartDateTime(Scanner keyboard) {
		boolean status = true;
		LocalDateTime departDateTime = null;

		//will loop if exception is thrown
		do {
			System.out.println("Enter date and time of departure.");
			try { 
				//asking user for year
				System.out.print("Year in yyyy format (must be today's year or later): ");
				int year = keyboard.nextInt();
				//validating year isn't before today's year (in the past)
				while(year < LocalDate.now().getYear()) {
					System.out.print("Invalid year. Please re-enter: ");
					year = keyboard.nextInt();
				}
				
				//asking user for month
				System.out.print("Month in mm format: ");
				int month = keyboard.nextInt();
				
				//asking user for day 
				System.out.print("Day in dd format: ");
				int day = keyboard.nextInt();
			
				//asking user for hour of departure
				System.out.print("Hour of departure: ");
				int hour = keyboard.nextInt();
				//validating hour isn't less than 0 or greater than 24
				while(hour < 1 || hour > 24) {
					System.out.print("Invalid hour. Please re-enter: ");
					hour = keyboard.nextInt();
				}	
				
				//asking user for minute of departure
				System.out.print("minute of departure: ");
				int min = keyboard.nextInt();
				//validating hour isn't less than 0 or greater than 59
				while(min < 0 || min > 59) {
					System.out.print("Invalid hour. Please re-enter: ");
					min = keyboard.nextInt();
				}	
			
				//adding all data into LocalDateTime object constructor
				departDateTime = LocalDateTime.of(year,month,day,hour,min);
				
				status = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid data. Please re-enter. ");
				keyboard.nextLine();
			}
			catch(NullPointerException e) {
				System.out.println("LocalDateTime cannot receive null.");
				keyboard.nextLine();
			}
			catch(DateTimeException e) {
				System.out.println("Invalid date entered.");
			}
		}
		while(status);
		
		return departDateTime;
	}
	
	//user will enter arrival time and date
	public static LocalDateTime findArrivalDateTime(Scanner keyboard,LocalDateTime departDateTime) {
		boolean status = true;
		LocalDateTime arriveDateTime = null;
		//will loop if exception is thrown
		do {
			System.out.println("Enter date and time of arrival.");
			
			try { 
				//asking user for year
				System.out.print("Year in yyyy format (must be today's year or later): ");
				int year = keyboard.nextInt();
				//validating year isn't before today's year (in the past)
				while(year < LocalDate.now().getYear()) {
					System.out.print("Invalid year. Please re-enter: ");
					year = keyboard.nextInt();
				}
					
				//asking user for month
				System.out.print("Month in mm format: ");
				int month = keyboard.nextInt();
				
				//asking user for day 
				System.out.print("Day in dd format: ");
				int day = keyboard.nextInt();
					
				//asking user for hour of departure
				System.out.print("Hour of arrival: ");
				int hour = keyboard.nextInt();
				//validating hour isn't less than 0 or greater than 24
				while(hour < 1 || hour > 24) {
					System.out.print("Invalid hour. Please re-enter: ");
					hour = keyboard.nextInt();
				}	
					
				//asking user for minute of departure
				System.out.print("minute of arrival: ");
				int min = keyboard.nextInt();
				//validating hour isn't less than 0 or greater than 59
				while(min < 0 || min > 59) {
					System.out.print("Invalid hour. Please re-enter: ");
					min = keyboard.nextInt();
				}	
				
				//adding all data into LocalDateTime object constructor
				arriveDateTime = LocalDateTime.of(year,month,day,hour,min);					
				status = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid data. Please re-enter. ");
				keyboard.nextLine();
			}
			catch(NullPointerException e) {
				System.out.println("LocalDateTime cannot receive null.");
				keyboard.nextLine();
			}
			catch(DateTimeException e) {
				System.out.println("Invalid date entered.");
			}
		}
		while(status == true);
			
		return arriveDateTime;
	}
	
	//method will display all flights and they're information
	public static void displayFlight(Scanner keyboard,Airline air) {
		//getting flight number
		int flightNum = findFlightNum(keyboard);
		
		//calling Airline method to find flight object with matching flight number
		Flight flt = air.findFlightInfo(flightNum);
		
		//if method returned null, Flight object doesn't exist
		if(flt == null)
			System.out.println("Flight " + flightNum +" does not exist.");
		//if object exist, will display flight info using toString()
		else
			System.out.println(flt);		
	}
	
	//method will display all passenger information based on booking reference
	public static void displayPassenger(Scanner keyboard,Airline air) {
		int bookingRef = findBookingRef(keyboard);
		
		try {
			//method will return passenger object with the same booking reference
			Passenger psg = air.findPassengerInfo(bookingRef);
			
			//if passenger object doesn't exist, will show message
			if(psg == null)
				System.out.println("Passenger not in system.");
			//if passenger object exists, will use toString to display
			else
				System.out.println(psg);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//method will create a flight reservation
	public static void createReservation(Scanner keyboard,Airline air) {
		try {
			int flightNum = findFlightNum(keyboard);
			Flight flt = air.findFlightInfo(flightNum);
			
			//if Flight object equals null, flight doesn't exist so will return
			if(flt == null){
				System.out.println("Flight does not exists. Reservation cannot be created.");
				return;
			}
			
			Passenger psng = createPassengerObj(keyboard);
			LocalDate reservationDate = findReservationDate(keyboard);
			
			//creating a Reservation object
			Reservation rsrv = new Reservation(flt,psng,BookingStatus.CONFIRMED,reservationDate);
					
			//adding reservation object to Reservation ArrayList
			air.addReservation(rsrv);
			
			//if no exception was thrown, will confirm reservation completion
			System.out.println("Reservation confirmed successfully.");
		}
		catch(ReservationExistsException e) {
			System.out.println(e.getMessage());
		}
		catch(IllegalArgumentException e) {
			System.out.println("Invalid data entered. Cannot create reservation.");
		}
	}
	
	//will cancel a reservation
	public static void cancelReservation(Scanner keyboard,Airline air) {
		int bookingRef = findBookingRef(keyboard);
		
		try {
			//will remove reservation object with same booking reference
			air.cancelReservation(bookingRef);
			
			//if no exception is thrown, will display message
			System.out.println("Reservation cancelled successfully.");
		}
		catch(ReservationNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//finding reservation date 
	public static LocalDate findReservationDate(Scanner keyboard) {
		System.out.println("Enter reservation date");
		//making LocalDate object null until info is found in try-catch
		LocalDate rsrvDate = null;
		
		boolean status = true;
		//will loop if exception is thrown
		do {
			try { 				
				//asking user for year
				System.out.print("Year in yyyy format (must be today's year or later): ");
				int year = keyboard.nextInt();
				//validating year isn't before today's year (in the past)
				while(year < LocalDate.now().getYear()) {
					System.out.print("Invalid year. Please re-enter: ");
					year = keyboard.nextInt();
				}
				
				//asking user for month
				System.out.print("Month in mm format: ");
				int month = keyboard.nextInt();
				
				//asking user for day 
				System.out.print("Day in dd format: ");
				int day = keyboard.nextInt();	
				
				//assigning reservation date to values entered
				rsrvDate = LocalDate.of(year,month,day);
				
				//if no exception was thrown, status will become false and leave loop
				status = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid data. Please re-enter: ");
				keyboard.nextLine();
			}
			catch(NullPointerException e) {
				System.out.println("LocalDateTime cannot receive null.");
				keyboard.nextLine();
			}
			catch(DateTimeException e) {
				System.out.println("Invalid date entered.");
			}
		}
		while(status);
		
		//if no exception was thrown, will return reservation date
		return rsrvDate;
	}
	
	//will create a Flight object for reservation
	public static Flight createFlightObject(Scanner keyboard) {
		//creating Flight object and setting to null until field values are found
		Flight flt = null;
		boolean status = true;
		//finding field values for Flight class to create new Flight object
		try { 
			int flightNum = findFlightNum(keyboard);
			String departureCity = findDepartureCity(keyboard);
			String arrivalCity = findArrivalCity(keyboard);
			do {
				try { 
					LocalDateTime departDateTime = findDepartDateTime(keyboard);
					LocalDateTime arrivalDateTime = findArrivalDateTime(keyboard,departDateTime);
				
					//creating new Flight object
					flt = new Flight(flightNum,departureCity,arrivalCity,
						departDateTime,arrivalDateTime);
					status = false;
				}
				catch(DateTimeException e) {
					System.out.println("Invalid date entered.");
				}
				catch(InvalidTimeException e) {
					System.out.println(e.getMessage());
				}
			}
			while(status);
		}
		catch(IllegalArgumentException e) {
			System.out.println("Illegal data entered.");
		}
		catch(FlightExistsException e) {
			System.out.println(e.getMessage());
		}		
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		//once no exception was thrown, will return the flight object
		return flt;
	}
	
	//will create a Passenger object
	public static Passenger createPassengerObj(Scanner keyboard) {
		//assigning Passenger object to null until field values are found
		Passenger psg = null;
		boolean status = true; 
		try {
				String fName = getPsgFirstName(keyboard);
				String lName = getPsgLastName(keyboard);
				Gender gend = findGender(keyboard);

				Address addr = findPassAddress(keyboard);
				int bookingRef = findBookingRef(keyboard);
				String passNum = findPassportNum(keyboard);
				String nationality = findNationality(keyboard);
			do {
				try { 
					LocalDate birthdate = findBDate(keyboard);
		
					psg = new Passenger(fName,lName,gend,
					addr,birthdate,bookingRef,passNum,nationality);
					//if no exception was thrown, status will be false and will leave loop
					status = false;
				}	
				catch(NullPointerException e) {
					System.out.println(e.getMessage());
				}
				catch(DateTimeException e) {
					System.out.println("Invalid date entered.");
				}
			}
			while(status);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		
		//returning Passenger object
		return psg;
	}
	
	//finding passengers first name
	public static String getPsgFirstName(Scanner keyboard) {
		System.out.print("First Name: ");
		String fName = keyboard.nextLine();
		while(fName.isEmpty()) {
			System.out.println("First name must be entered. Please re-enter: ");
			fName = keyboard.nextLine();
		}
		return fName;
	}
	//finding passengers gender
	public static String getPsgLastName(Scanner keyboard) {
		System.out.print("Last Name: ");
		String lName = keyboard.nextLine();
		while(lName.isEmpty()) {
			System.out.println("Last name must be entered. Please re-enter: ");
			lName = keyboard.nextLine();
		}
		return lName;
	}
	//finding passengers gender
	public static Gender findGender(Scanner keyboard) {
		System.out.print("Gender (Enter M for male or F for female): ");
		char gender = keyboard.nextLine().toUpperCase().charAt(0);
		
		while(gender != 'M' && gender != 'F') {
			System.out.print("Invalid gender. Please re-enter: ");
			gender = keyboard.nextLine().toUpperCase().charAt(0);
		}
		
		if(gender == 'M') {
			return Gender.M;
		}
		else
			return Gender.F;
	}
	//finding passenger address 
	public static Address findPassAddress(Scanner keyboard) {
		System.out.print("Street: ");
		String street = keyboard.nextLine();
		while(street.isEmpty()) {
			System.out.println("Street address must be entered. Please re-enter: ");
			street = keyboard.nextLine();
		}
		
		System.out.print("City: ");
		String city = keyboard.nextLine();
		while(city.isEmpty()) {
			System.out.println("City must be entered. Please re-enter: ");
			city = keyboard.nextLine();
		}
		
		System.out.print("State: ");
		String state = keyboard.nextLine();
		while(state.isEmpty()) {
			System.out.println("State must be entered. Please re-enter: ");
			state = keyboard.nextLine();
		}
		
		System.out.print("Zip: ");
		String zip = keyboard.nextLine();
		while(zip.isEmpty()) {
			System.out.println("Zipcode must be entered. Please re-enter: ");
			zip = keyboard.nextLine();
		}
		
		//making Address object null so it can be returned
		Address addr = null;
		try {
			addr = new Address(street,city,state,zip);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch(NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
		return addr;
	}
	
	//getting birth date LocalDate object
	public static LocalDate findBDate(Scanner keyboard) {
		//making LocalDate object null until info is found in try-catch
		LocalDate bday = null;
		
		boolean status = true;
		//will loop if exception is thrown
		do {
			System.out.println("Enter birthdate");
			try { 				
				//asking user for year
				System.out.print("Year in yyyy format: ");
				int year = keyboard.nextInt();
				//validating year isn't after today's year or before 1900
				while(year > LocalDate.now().getYear() || year < 1900) {
					System.out.print("Invalid year. Please re-enter: ");
					year = keyboard.nextInt();
				}
				
				//asking user for month
				System.out.print("Month in mm format: ");
				int month = keyboard.nextInt();
				
				/*validation is already included in DateTimeException */
				
				//asking user for day 
				System.out.print("Day in dd format: ");
				int day = keyboard.nextInt();

				//assigning birth date to values entered
				bday = LocalDate.of(year,month,day);
				
				//if no exception was thrown, status will become false and leave loop
				status = false;
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid data. Please re-enter: ");
				keyboard.nextLine();
			}
			catch(NullPointerException e) {
				System.out.println("LocalDateTime cannot receive null.");
				keyboard.nextLine();
			}
			catch(DateTimeException e) {
				System.out.println("Invalid date entered.");
			}
		}
		while(status);
		keyboard.nextLine();
		//if no exception was thrown, will return birth date
		return bday;		
	}
	
	//will find booking reference
	public static int findBookingRef(Scanner keyboard) {
		boolean status = true;
		
		System.out.print("Enter booking reference (numeric formatting only): ");
		int booking = 0;
		
		//will loop if exception is thrown
		do {
			try {
				booking = keyboard.nextInt();
				//validating that booking reference isn't less than 0
				while(booking < 0) {
					System.out.println("Invalid data. Please re-enter:");
					booking = keyboard.nextInt();
				}
				//if no exception thrown, status will be false
				status = false;
				keyboard.nextLine();
			}
			catch(InputMismatchException e) {
				System.out.println("Invalid data. Please re-enter:");
				keyboard.nextLine();
			}	
		}
		while(status);
		//returning the booking reference
		return booking;
	}
	//finding passenger's passport number
	public static String findPassportNum(Scanner keyboard) {
		System.out.print("Passport number: ");
		String passNum = keyboard.nextLine();
		while(passNum.isEmpty()) {
			System.out.println("Passport number cannot be empty. Please re-enter: ");
			passNum = keyboard.nextLine();
		}
		return passNum;
	}
	//finding passenger's nationality
	public static String findNationality(Scanner keyboard) {
		System.out.print("Nationality: ");
		String nationality = keyboard.nextLine();
		while(nationality.isEmpty()) {
			System.out.println("Nationality cannot be empty. Please re-enter: ");
			nationality = keyboard.nextLine();
		}
		return nationality;
	}
	
	/* will create an  Employee object and use
	Airline class method to add to ArrayList */
	public static void createEmployee(Scanner keyboard,Airline air) {
		try {
			String fName = getPsgFirstName(keyboard);
			String lName = getPsgLastName(keyboard);
			Gender gend = findGender(keyboard);
			Address addr = findPassAddress(keyboard);
			LocalDate bday = findBDate(keyboard);
			
			String empID = findEmployeeID(keyboard);
			String empType = findEmployeeType(keyboard);
			String department = findDepartment(keyboard);
			
			//creating an Employee object 
			Employee emp = new Employee(fName,lName,
					gend,addr,bday,empID,empType,department);
			
			//adding employee object to ArrayList using Airline class method
			air.addEmployee(emp);
			
			//if no exception was thrown, will display completion message
			System.out.println("Employee added successfully.");
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
		catch(EmployeeExistsException e) {
			System.out.println(e.getMessage());
		}
	}
		
	/**@return employee ID */
	public static String findEmployeeID(Scanner keyboard) {
		System.out.print("Employee ID: ");
		String id = keyboard.nextLine();
		while(id.isEmpty()) {
			System.out.println("Employee ID must be entered. Please re-enter: ");
			id = keyboard.nextLine();
		}
		return id;		
	}
	/**@return employee type */
	public static String findEmployeeType(Scanner keyboard) {
		System.out.print("Employee type: ");
		String type = keyboard.nextLine();
		while(type.isEmpty()) {
			System.out.println("Employee type must be entered. Please re-enter: ");
			type = keyboard.nextLine();
		}
		return type;		
	}
	/**@return employee department */
	public static String findDepartment(Scanner keyboard) {
		System.out.print("Department: ");
		String department = keyboard.nextLine();
		while(department.isEmpty()) {
			System.out.println("Department must be entered. Please re-enter: ");
			department = keyboard.nextLine();
		}
		return department;		
	}
	
	//will display employee information based on employee ID
	public static void displayEmployee(Scanner keyboard,Airline air) {
		String id = findEmployeeID(keyboard);
		
		try {
			//method will return employee object with the same id
			Employee emp = air.findEmployeeInfo(id);
			
			//if employee object doesn't exist, will show message
			if(emp == null)
				System.out.println("Employee not in system.");
			//if employee object exists, will use toString to display
			else
				System.out.println(emp);
		}
		catch(IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}		
}
