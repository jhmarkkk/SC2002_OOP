package models;

import java.util.ArrayList;
import java.util.Map;

import enums.Role;

/**
 * The {@link Student} class represents a student in CAMs.
 * Student have a unique ID, password, name, and faculty.
 * Each student also has a {@link Role} which determines their level of access.
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 */
public class Student extends User {
	
	/**
 	* The list of camps the Committee Member is registered for.
 	*/
	private ArrayList<String> registeredCamps;

	/**
 	* A map containing the Committee Member's enquiries, with camp names as keys and lists of enquiry IDs as values.
 	*/
	private Map<String, ArrayList<Integer>> enquiries;
	
	/**
	 * Constructor used for importing {@link Student} from CSV.
	 * 
	 * @param userID 			The user ID of the Student member.
	 * @param password 			The password of the Student member.
	 * @param name 				The name of the Student member.
	 * @param faculty 			The faculty to which the Student member belongs.
	 * @param registeredCamps	The list of camps the Committee Member is registered for.
	 * @param enquiries 		A map containing the Committee Member's enquiries, with camp names as keys and lists of enquiry IDs as values.
	 */
	 public Student(String userID, String password, String name, String faculty, ArrayList<String> registeredCamps, Map<String, ArrayList<Integer>> enquiries) {
		super(userID, password, name, faculty, Role.STUDENT);
		this.registeredCamps = registeredCamps;
		this.enquiries = enquiries;
	}
	
	/**
	 * Returns the list of camps the student is registered for.
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
	 * Returns a map associating camp names with lists of enquiry IDs made by the student for each camp.
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
