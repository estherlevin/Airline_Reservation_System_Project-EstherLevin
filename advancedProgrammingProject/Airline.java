package advancedProgrammingProject;
import java.util.*;

/* class has ArrayList fields that aggregate Flight,
 *  Reservation, and Employee classes to hold data on each flight 
 */
public class Airline {
	private ArrayList<Flight> flights = new ArrayList<>();
	private ArrayList<Reservation> reservations = new ArrayList<>();
	private ArrayList<Employee> employees = new ArrayList<>();
	
	//no-arg constructor sets ArrayList to null
	public Airline() {
	}
	
	/**
	 * All field value constructor
	 * @param the values of all the fields 
	 */
	public Airline(ArrayList<Flight> flights, ArrayList<Reservation> reservations,
			ArrayList<Employee> employees) {
		
		/* making a deep copy of the passed in ArrayList
		for each field ArrayList. Uses copy constructors 
		for each class to create a copy of the object.*/
		for(int ctr = 0; ctr < flights.size(); ctr++) {
			this.flights.add(new Flight(flights.get(ctr)));
		}
		for(int ctr = 0; ctr < reservations.size(); ctr++) {
			this.reservations.add(new Reservation(reservations.get(ctr)));
		}		
		for(int ctr = 0; ctr < employees.size(); ctr++) {
			this.employees.add(new Employee(employees.get(ctr)));
		}
	}
	
	/**
	 * copy constructor
	 * @param object being copied
	 */
	public Airline(Airline copy) {
		this(copy.getFlightsArrayList(),copy.getReservationsArrayList(),
				copy.getEmployeeArrayList());
	}
	
	//Setters
	/**
	 * @param flight ArrayList 
	 * sets ArrayList by making deep copy
	 */
	public void setFlighsArrayList(ArrayList<Flight> flights) {
		for(int ctr = 0; ctr < flights.size(); ctr++) {
			this.flights.set(ctr,new Flight(flights.get(ctr)));
		}
	}
	/** @param reservation ArrayList  */
	public void setReservationsArrayList(ArrayList<Flight> flights) {
		for(int ctr = 0; ctr < reservations.size(); ctr++) {
			this.reservations.set(ctr,new Reservation(reservations.get(ctr)));
		}
	}
	/** @param employee ArrayList  */
	public void setEmployeesArrayList(ArrayList<Flight> flights) {
		for(int ctr = 0; ctr < employees.size(); ctr++) {
			this.employees.set(ctr,new Employee(employees.get(ctr)));
		}
	}
		
	//Getters
	/**@return ArrayList of flights */
	public ArrayList<Flight> getFlightsArrayList() {
		//making copy of ArrayList
		ArrayList<Flight> flt = new ArrayList<>();
		for(int i = 0; i < flights.size(); i++) {
			flt.add(new Flight(flights.get(i)));
		}
		//returning copy
		return flt;
	}
	/**@return ArrayList of flight reservations */
	public ArrayList<Reservation> getReservationsArrayList() {
		//making copy of ArrayList
		ArrayList<Reservation> rsv = new ArrayList<>();
		for(int i = 0; i < reservations.size(); i++) {
			rsv.add(new Reservation(reservations.get(i)));
		}
		//returning copy
		return rsv;
	}
	/**@return ArrayList of employees */
	public ArrayList<Employee> getEmployeeArrayList() {
		//making copy of ArrayList
		ArrayList<Employee> emp = new ArrayList<>();
		for(int i = 0; i < employees.size(); i++) {
			emp.add(new Employee(employees.get(i)));
		}
		//returning copy
		return emp;
	}
	
	/**@return String of fields and their values */
	//overrides default toString() method
	@Override 
	public String toString() {
		StringBuilder str = new StringBuilder("AirLine information\n");
		
		/* will loop through each element in 
		ArrayList and append to StringBuilder */
		if(!flights.isEmpty()) {
			str.append("\nAll flights: ");
			for(Flight vals : flights) {
				str.append(vals + "\n");
			}
		}
		else
			str.append("There are currently no flights listed.\n");
		if(!reservations.isEmpty()) {
			str.append("\nAll reservations: ");
			for(Reservation vals : reservations) {
				str.append(vals + "\n");
			}
		}
		else
			str.append("There are currently no reservations listed.\n");
		if(!employees.isEmpty()) {
			str.append("\nAll employees: ");
			for(Employee vals : employees) {
				str.append(vals + "\n");
			}
		}
		else
			str.append("There are currently no employees listed.\n");
				
		//returns StringBuilder object as a String
		return str.toString();
	}
	
	/**
	 * flight will be added to an airline if not already added
	 * otherwise will throw exception
	 * @param flight that will be added
	 */
	public void addFlight(Flight flight) {
		//making a copy of the Flight object passed in
		Flight flt = new Flight(flight);
		//if flight doesn't exist in ArrayList, will add it 
		if(!flights.contains(flt)) {
			flights.add(new Flight(flt));
		}
		//if flight already exist, will throw an error
		else {
			throw new FlightExistsException();
		}	
	}	
	/**
	 * Reservation will be added to reservation ArrayList
	 * @param flight that will be added
	 */
	public void addReservation(Reservation reservation) {
		//making a copy of the Reservation object passed in
		Reservation rsv = new Reservation(reservation);
		if(!reservations.contains(rsv))
			reservations.add(new Reservation(rsv));
		else
			throw new ReservationExistsException();
	}
	
	/** 
	 * will cancel reservation based on booking reference
	 * @param reservation to cancel
	 */
	public void cancelReservation(int bookingReference) {
		//will loop through each element in ArrayList and compare booking reference
		for(int x = 0; x < reservations.size(); x++) {
			if((reservations.get(x).getPassenger()).getBookingReference()
				== bookingReference) {
				/* if reservation exists already, will be
				 removed from reservations ArrayList */
				reservations.remove(reservations.get(x));
				//once reservation was removed, will return and not throw exception
				return;
			}
		}
		
		//if reservation is not found, exception will be thrown
		throw new ReservationNotFoundException();		
	}
	/**
	 * finds info of specific flight
	 * @param flight number
	 * @return Flight object that has specific flight number
	 */
	public Flight findFlightInfo(int flightNum) {
		//confirming that the number passed in isn't less than 0
		if(flightNum < 0)
			throw new IllegalArgumentException("Invalid data entered.");
		
		/* will loop through Flight ArrayList to 
		find flight that matches the flight number */
		for(int ctr = 0; ctr < flights.size(); ctr++) {
			if((flights.get(ctr).getFlightNum() == flightNum)) {
				//will return copy of Flight object that has same flight number
				Flight copy = new Flight(flights.get(ctr));
				return copy;
			}
		}
		
		//if the flight number wasn't found, will return null
		return null;
	}
	/**
	 * finds passenger info based on booking reference
	 * @param booking reference
	 * @return Passenger object that contains the parameter
	 */
	public Passenger findPassengerInfo(int bookingReference) {
		//confirming that the number passed in isn't less than 0
		if(bookingReference < 0)
			throw new IllegalArgumentException("Invalid data entered.");
		
		//will loop through Reservation ArrayList until booking reference matches
		for(int y = 0; y < reservations.size(); y++) {
			if((reservations.get(y).getPassenger()).getBookingReference()
				== bookingReference) {
				//if booking reference is found, will return copy of passenger object
				Passenger copy = new Passenger(reservations.get(y).getPassenger());
				return copy;
			}
		}
		
		//if booking reference isn't in the ArrayList, will return null
		return null;
	}
	/** 
	 * will add an employee to the Employee ArrayList
	 * @param Employee object to add
	 */
	public void addEmployee(Employee employee) {
		//making a copy of the Employee object for safety purposes
		Employee emp = new Employee(employee);
		/* if Employee ArrayList doesn't already have object passed in,
		will add to ArrayList */
		if(!employees.contains(emp)) {
			employees.add(new Employee(emp));
		}
		//if ArrayList already contains parameter, will throw exception
		else {
			throw new EmployeeExistsException();
		}
	}
	/**
	 * finds employee info 
	 * @param employee id
	 * @return Employee object that has specified employee id
	 */
	public Employee findEmployeeInfo(String id) {
		//will loop through Employee ArrayList until employee id matches
		for(int k = 0; k < employees.size(); k++) {
			if(employees.get(k).getEmployeeID().equals(id)) {
				//will return copy Employee object that has the same employee id
				Employee copy = new Employee(employees.get(k));
				return copy;
			}
		}
		
		//if employee id isn't in the ArrayList, will return null
		return null;
	}
}
