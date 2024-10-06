package advancedProgrammingProject;
import java.time.*;

/*class includes aggregation to hold all information 
 * for a person's flight reservation */
public class Reservation {
	private Flight flight;
	private Passenger passenger;
	private BookingStatus bookingStatus;
	private LocalDate reservationDate;
	
	//no-arg constructor
	public Reservation() {
	}
	/**@param receives all parameters for classes */
	public Reservation(Flight flight,Passenger passenger,
			BookingStatus bookingStatus,LocalDate reservationDate) {
		this.flight = flight;
		this.passenger = passenger;
		this.bookingStatus = bookingStatus;
		
		//validate LocalDate
		if(reservationDate.isBefore(LocalDate.now()))
			throw new IllegalArgumentException("Invalid date entered.");
		this.reservationDate = reservationDate;
	}
	/**
	 * copy constructor
	 * @param object being copied
	 */
	public Reservation(Reservation copy) {
		this(copy.getFlight(),copy.getPassenger(),copy.getBookingStatus(),copy.getReservationDate());
	}
	
	//Setters
	/**@param Flight object with already valid parameters*/
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	/**@param Passenger object with valid parameters */
	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	/**@param Booking status enum type */
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	/**@param reservation date to change */
	public void setReservationDate(LocalDate reservationDate) {
		if(reservationDate.isAfter(LocalDate.now()))
			throw new IllegalArgumentException("Invalid date entered.");
		this.reservationDate = reservationDate;
	}	
	
	//Getters
	/**@return flight information from Flight toString() */
	public Flight getFlight() {
		return flight;
	}
	/**@return passenger info from Passenger toString() */
	public Passenger getPassenger() {
		return passenger;
	}
	/**@return booking status from BookingStatus enum class */
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	/**@return reservation date */
	public LocalDate getReservationDate() {
		return reservationDate;
	}
	
	/**@return String of fields and their values */
	//overrides default toString() method
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Flight information - " + flight + "\n");
		str.append("Passenger information - " + passenger + "\nBooking status - " + bookingStatus);
		str.append("\nReservation date - " + reservationDate);
		
		//returns StringBuilder object as a String
		return str.toString();
	}
		
}
