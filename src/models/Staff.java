package models;

import java.util.ArrayList;

import enums.Role;

/**
 * The Staff class represents a staff member in the system.
 * It extends the User class and includes additional information about the camps created by the staff member.
 */
public class Staff extends User{
	/**
	 * The list of camps created by the Staff member.
	 */
	private ArrayList<String> createdCamps;
	
	/**
	 * Constructor used for importing Staff from CSV.
	 * 
	 * @param userID		The user ID of the Staff member.
	 * @param password		The password of the Staff member.
	 * @param name			The name of the Staff member.
	 * @param faculty		The faculty to which the Staff member belongs.
	 * @param role			The role of the Staff member
	 * @param createdCamps	The list of camps created by the Staff member.
	 */
	public Staff(String userID, String password, String name,
			String faculty, ArrayList<String> createdCamps) {
		super(userID, password, name, faculty, Role.STAFF);
		this.createdCamps = createdCamps;
	}

	/**
	 * Retrieves the list of camps created by the Staff member
	 * 
	 * @return An ArrayList containing the names of camps created by the Staff member.
	 */
	public ArrayList<String> getCreatedCamps() {
		return createdCamps;
	}

	/**
	 * Sets the list of camps created by the Staff member.
	 * 
	 * @param createdCamps A new ArrayList containing the names of camps to set.
	 */
	public void setCreatedCamps(ArrayList<String> createdCamps) {
		this.createdCamps = createdCamps;
	}

}
