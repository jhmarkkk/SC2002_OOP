package models;

import java.util.ArrayList;
import java.util.Map;

import enums.Role;

/**
 * The Student class represents a student in the system, extending the User class.
 * It includes additional information about registered camps and enquiries made by the student.
 */
public class Student extends User {
	
	private ArrayList<String> registeredCamps;
	private Map<String, ArrayList<Integer>> enquiries;
	
	/**
	 * Constructor used for importing Student from CSV.
	 * 
	 * @param userID 			The user ID of the Student member.
	 * @param password 			The password of the Student member.
	 * @param name 				The name of the Student member.
	 * @param faculty 			The faculty to which the Student member belongs.
	 * @param role 				The role of the Student member
	 * @param registeredCamps	The list of camps the Committee Member is registered for.
	 * @param enquiries 		A map containing the Committee Member's enquiries, with camp names as keys and lists of enquiry IDs as values.
	 */
	 public Student(String userID, String password, String name, String faculty, ArrayList<String> registeredCamps, Map<String, ArrayList<Integer>> enquiries) {
		super(userID, password, name, faculty, Role.STUDENT);
		this.registeredCamps = registeredCamps;
		this.enquiries = enquiries;
	}
	
	/**
	 * Retrieves the list of camps the student is registered for.
	 * 
	 * @return An ArrayList containing the names of camps the student is registered for.
	 */
	public ArrayList<String> getRegisteredCamps() {

		return registeredCamps;
	}

	/**
	 * Sets the list of camps the student is registered for.
	 * 
	 * @param registeredCamps A new ArrayList containing the names of camps to set.
	 */
	public void setRegisteredCamps(ArrayList<String> registeredCamps) {
		
		this.registeredCamps = registeredCamps;
	}

	/**
	 * Retrieves a map associating camp names with lists of enquiry IDs made by the student for each camp.
	 * 
	 * @return A map of camp names with lists of enquiry IDs for each camp.
	 */
	public Map<String, ArrayList<Integer>> getEnquiries() {
		
		return enquiries;
	}

	/**
	 * Sets the map associating camp names with lists of enquiry IDs made by the student for each camp.
	 * 
	 * @param enquiries A new map of camp names with lists of enquiry IDs for each camp to set.
	 */
	public void setEnquiries(Map<String, ArrayList<Integer>> enquiries) {
		
		this.enquiries = enquiries;
	}
}
