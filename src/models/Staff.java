package models;

import java.util.ArrayList;

import enums.Role;

/**
 * The {@link Staff} class represents a staff in CAMs.
 * Staff have a unique ID, password, name, and faculty.
 * Each staff also has a {@link Role} which determines their level of access.
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 */
public class Staff extends User{
	/**
	 * The list of camps created by the Staff member.
	 */
	private ArrayList<String> createdCamps;
	
	/**
	 * Constructor used for importing {@link Staff} from CSV.
	 * 
	 * @param userID		The user ID of the Staff member.
	 * @param password		The password of the Staff member.
	 * @param name			The name of the Staff member.
	 * @param faculty		The faculty to which the Staff member belongs.
	 * @param role			The role of the Staff member
	 * @param createdCamps	The list of camps created by the Staff member.
	 */
	public Staff(String userID, String password, String name, String faculty, ArrayList<String> createdCamps) {
		super(userID, password, name, faculty, Role.STAFF);
		this.createdCamps = createdCamps;
	}

	/**
	 * Returns the list of camps created by the Staff member
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
