package advancedProgrammingProject;
import java.time.LocalDate;

/* Class contains fields of employee ID, type, and department along with other methods  */
public class Employee extends Person{
	private String employeeID;
	private String employeeType;
	private String department;
	
	/**
	 @param constructor receives field values for Person class 
	  in addition to all Employee field values
	*/
	//passes superclass field values to the Person class
	public Employee(String firstName, String lastName, Gender gender, 
			Address address, String birthdate, String employeeID,
			String employeeType, String department) {
		
		/* makes an implicit call to constructor that receives all field values
		and birthdate as type LocalDate */
		this(firstName,lastName,gender,address,LocalDate.parse(birthdate),
				employeeID,employeeType,department);
	}
	
	/**@param all field values including Person class's
	 * birthdate is received a LocalDate */
	public Employee(String firstName, String lastName, Gender gender, 
			Address address, LocalDate birthdate, String employeeID,
			String employeeType, String department) {
		
		//passes type Person field values to superclass
		super(firstName,lastName,gender,address,birthdate);
		
		//setting Employee fields
		this.employeeID = employeeID;
		this.employeeType = employeeType;
		this.department = department;
		
	}
	
	/**@param object to copy in copy constructor */
	public Employee(Employee e) {
		
		this(e.getFirstName(),e.getLastName(),e.getGender(),e.getAddress(),e.getBirthdate(),
				e.getEmployeeID(),e.getEmployeeType(),e.getDepartment()); 
	}
	
	/**@param field values to set fields to - setters */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	/**@return Employee class field values - getters*/
	public String getEmployeeID() {
		return employeeID;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public String getDepartment() {
		return department;
	}
	
	/**@return String of fields and their values */
	//overrides default toString() method
	@Override
	public String toString() {
		//appending parent toString method
		StringBuilder str = new StringBuilder(super.toString());
		str.append(", Employee ID: " + employeeID + ", ");
		str.append("Employee type: " + employeeType + ", Department: " + department);
		
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
		
		//casting o to type Employee so it can be compared to Employee fields
		Employee other = (Employee)o;
		
		//comparing employeeID Strings
		if(employeeID == null) {
			if(other.employeeID != null)
				return false;
		}
		else {
			if(!employeeID.equals(other.employeeID))
				return false;
		}
		
		//if didn't return false already, will return true since objects are equal
		return true;
	}
}
