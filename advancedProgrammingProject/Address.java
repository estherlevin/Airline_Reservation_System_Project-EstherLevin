package advancedProgrammingProject;
/* Class holds fields and methods for person's address */

public class Address {
	private String street;
	private String city;
	private String state;
	private String zipcode;
	
	/** @param all field values and sets fields */
	public Address(String street,String city,String state,String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		zipcode = zip;
	}
	
	/**
	   @param object that should be copied -
	   copy constructor 
	*/
	public Address(Address copy) {
		this(copy.getStreet(),copy.getCity(),copy.getState(),copy.getZip());
	}
	
	/** @return field values */
	public String getStreet() {
		return street;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getZip() {
		return zipcode;
	}
	
	/* will override default toString() method */
	/** @return String of all fields and their values */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("Street: " + street + ", ");
		str.append("City: " + city + ", ");
		str.append("State: " + state + ", ");
		str.append("Zip: " + zipcode);
		
		//returning StringBuilder object in data type String
		return str.toString();
	}
}
