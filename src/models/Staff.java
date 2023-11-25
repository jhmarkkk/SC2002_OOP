package models;

import java.util.ArrayList;

import enums.Role;

public class Staff extends User{
	/**
	 * 
	 */
	private ArrayList<String> createdCamps;
	
	/**
	 * @param userID
	 * @param password
	 * @param name
	 * @param faculty
	 * @param role
	 * @param createdCamps
	 */
	public Staff(String userID, String password, String name,
			String faculty, ArrayList<String> createdCamps) {
		super(userID, password, name, faculty, Role.STAFF);
		this.createdCamps = createdCamps;
	}

	/**
	 * @return the createdCamps
	 */
	public ArrayList<String> getCreatedCamps() {
		return createdCamps;
	}

	/**
	 * @param createdCamps the createdCamps to set
	 */
	public void setCreatedCamps(ArrayList<String> createdCamps) {
		this.createdCamps = createdCamps;
	}

}
