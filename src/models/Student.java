package models;

import java.util.ArrayList;
import enums.Role;

public class Student extends User {
	private ArrayList<String> registeredCamps;
	// private ArrayList<Integer> enquiries;
	
	/**
	 * @param userID
	 * @param password
	 * @param name
	 * @param faculty
	 * @param role
	 * @param registeredCamps
	 * @param enquiries
	 */
	//public Student(String userID, String password, String name, String faculty, Role role, ArrayList<String> registeredCamps, ArrayList<Integer> enquiries) {
	public Student(String userID, String password, String name, String faculty, Role role, ArrayList<String> registeredCamps) {
		super(userID, password, name, faculty, role);
		this.registeredCamps = registeredCamps;
		//this.enquiries = enquiries;
	}

	/**
	 * @return the registeredCamps
	 */
	public ArrayList<String> getRegisteredCamps() {
		return registeredCamps;
	}

	/**
	 * @param registeredCamps the registeredCamps to set
	 */
	public void setRegisteredCamps(ArrayList<String> registeredCamps) {
		this.registeredCamps = registeredCamps;
	}

	// /**
	//  * @return the enquiries
	//  */
	// public ArrayList<Integer> getEnquiries() {
	// 	return enquiries;
	// }

	// /**
	//  * @param enquiries the enquiries to set
	//  */
	// public void setEnquiries(ArrayList<Integer> enquiries) {
	// 	this.enquiries = enquiries;
	// }
	
	

}
