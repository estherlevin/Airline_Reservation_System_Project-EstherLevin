package advancedProgrammingProject;
import java.time.*;
import java.time.format.*;

/* class holds passengers flight information */
public class Flight {
	private int flightNum;
	private String departureCity;
	private String destinationCity;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	
	//no-arg constructor
	public Flight() {
		flightNum = 00;
		departureCity = "Unknown";
		destinationCity = "Unknown";
		//setting departure day to today's date with an unknown time (0)
		departureTime = LocalDateTime.of(departureTime.getYear(),departureTime.getMonth(),
				departureTime.getDayOfMonth(),00,00);
		
		//setting arrival day to today's date with an unknown time (0)
		arrivalTime = LocalDateTime.of(arrivalTime.getYear(),arrivalTime.getMonth(),
				arrivalTime.getDayOfMonth(),00,00); 
	}
	
	/**@param constructor receives all field values */
	public Flight(int flightNum,String departureCity,String destinationCity,
			LocalDateTime departureTime,LocalDateTime arrivalTime) {
		//validating that flightNum isn't less than 0
		if(flightNum < 0)
			throw new IllegalArgumentException("Invalid data entered.");
		
		this.flightNum = flightNum;
		this.departureCity = departureCity;
		this.destinationCity = destinationCity;
		
		//validating LocalTime objects - departure cannot be before arrival
		if(departureTime.isAfter(arrivalTime)) {
			throw new InvalidTimeException("Error: departure time cannot be after arrival time.");
		}
		
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
	
	/**@param all field values but departureTime and arrivalTime are Strings
	 * will parse String times into LocalDateTime 
	 */
	public Flight(int flightNum,String departureCity,String destinationCity,
			String departureTime,String arrivalTime) {
		this(flightNum,departureCity,destinationCity,
				LocalDateTime.parse(departureTime),LocalDateTime.parse(arrivalTime));
	}
	
	/**@param object to be copied in copy constructor */
	public Flight(Flight copy) {
		this(copy.getFlightNum(),copy.getDepartureCity(),copy.getDestinationCity(),
				copy.getDepartureTime(),copy.getArrivalTime());
	}
	
	//Setters
	/**
	 * @param flight number 
	 * sets flightNum field
	 */
	public void setFlightNum(int flightNum) {
		//validating that flightNum isn't less than 0
		if(flightNum < 0)
			throw new IllegalArgumentException("Invalid data entered.");
		this.flightNum = flightNum;
	}
	/**@param departure city to set field to*/
	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}
	/**@param destination city*/
	public void setDestinationCity(String destinationCity) {
		this.destinationCity = destinationCity;
	}
	/**
	 * @param departure time
	 * setter included validation 
	 */
	public void setDepartureTime(LocalDateTime departureTime) {
		//validating LocalTime objects - departure cannot be before arrival
		if(departureTime.isAfter(arrivalTime)) {
			throw new IllegalArgumentException("Error: departure time cannot be after arrival time.");
		}
		
		this.departureTime = departureTime;
	}
	/**
	 * @param arrival time
	 * setter included validation 
	 */
	public void setArrivalTime(LocalDateTime arrivalTime) {		
		//validating LocalTime objects - departure cannot be before arrival
		if(departureTime.isAfter(arrivalTime)) {
			throw new IllegalArgumentException("Error: departure time cannot be after arrival time.");
		}
				
		this.arrivalTime = arrivalTime;
	}
	
	//Getters
	/**@return flight number */
	public int getFlightNum() {
		return flightNum;
	}
	/**@return departure city */
	public String getDepartureCity() {
		return departureCity;
	}
	/**@return destination city */
	public String getDestinationCity() {
		return destinationCity;
	}
	/**@return departure time */
	public LocalDateTime getDepartureTime() {
		return departureTime;
	}
	/**@return arrival time*/
	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}
	
	/**@return String of fields and their values */
	//overrides default toString() method
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Flight information\nFlight number: " + flightNum+ ", ");
		str.append("Departure city: " + departureCity + ", Destination city: " + destinationCity);
		
		//formatting the LocalDateTime objects
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formatDeparture = departureTime.format(formatter);
        String formatArrival = arrivalTime.format(formatter);

        str.append("\nDeparture date and time: " + formatDeparture + "\nArrival date and time: " + formatArrival);
        
		//returns StringBuilder object as a String
		return str.toString();
	}
	
	/**@return boolean status of true or false if compared objects are the same or not */
	//overrides default equals() method 
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		
		//casting obj to type Person so it can be compared to Flight fields
		Flight other = (Flight)obj;
		
		//comparing flightNum - equals() method based on flight number
		if(flightNum != other.flightNum)
			return false;
				
		//if didn't return false already, will return true since objects are equal
		return true;
	}
}
