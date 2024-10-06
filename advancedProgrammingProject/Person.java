package advancedProgrammingProject;
import java.time.*;
/* Class has fields of all person information including name, address, gender, and birthday */
public class Person {
	private String firstName;
	private String lastName;
	private Gender gender;
	private Address address;
	private LocalDate birthdate;

	/**@param receives all field values in constructor and birthdate as type String
	 * uses constructor call to pass fields to validated constructor */
	public Person(String firstName,String lastName,Gender gender
			,Address address,String birthdate) {
		
		//passes field values to validated constructor
		this(firstName,lastName,gender,address,LocalDate.parse(birthdate));
	}
	
	/**@param receives all field values and address field values */
	//creates Address object rather than receiving one 
	public Person(String firstName,String lastName, 
			Gender gender,String street, String city, String state, String zip, String birthdate) {
		
		//creating Address object to set address field to
		Address address = new Address(street,city,state,zip);
		
		//setting field values
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.birthdate = LocalDate.parse(birthdate);
		
		//validating that birthdate is not later than today's date
		if(this.birthdate.compareTo(LocalDate.now()) > 0)
				throw new IllegalArgumentException("Invalid date entered.");
	}
	
	/**@param constructor receives all field values and birthdate field as type LocalDate */
	public Person(String firstName, String lastName, Gender gender, Address address, LocalDate birthdate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.address = address;
		this.birthdate = birthdate;
		
		//validating that birthdate is not later than today's date
		if(this.birthdate.compareTo(LocalDate.now()) > 0)
				throw new IllegalArgumentException("Invalid date entered.");
	}
	
	/**@param oect to copy in copy constructor */
	public Person(Person p) {
		this(p.getFirstName(),p.getLastName(),p.getGender(),p.getAddress(),p.getBirthdate());
	}
	
	/**@return field values */
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public Gender getGender() {
		return gender;
	}
	public LocalDate getBirthdate() {
		return birthdate;
	}
	public Address getAddress() {
		return address;
	}	
	
	/**@param receives lastName field value and sets it */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**@param receives Address object as field value and sets it */
	public void setAddress(Address addr) {
		address = addr;
	}
	
	/**@return String of fields and their values */
	//overrides default toString() method
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("First name: " + firstName + ", ");
		str.append("Last name: " + lastName + ", Gender: " + gender + "\n");
		str.append("Address - " + address);
		str.append("\nBirthdate: " + birthdate);
		
		//returns StringBuilder object as a String
		return str.toString();
	}
	
	/**@return boolean status of true or false if compared objects are the same or not */
	//overrides default equals() method 
	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null)
			return false;
		if(getClass() != o.getClass())
			return false;
		
		//casting o to type Person so it can be compared to Person fields
		Person other = (Person)o;
		
		//comparing firstName Strings
		if(firstName == null) {
			if(other.firstName != null)
				return false;
		}
		else {
			if(!firstName.equals(other.firstName))
				return false;
		}
		
		//comparing lastName Strings
		if(lastName == null) {
			if(other.lastName != null)
				return false;
		}
		else {
			if(!lastName.equals(other.lastName))
				return false;
		}
		
		//comparing LocalDate objects (birthdate fields) using default LocalDate equals() method
		if(!birthdate.equals(other.birthdate))
			return false;
		
		//if didn't return false already, will return true since objects are equal
		return true;
	}
	
}
