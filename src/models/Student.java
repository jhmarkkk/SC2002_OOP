package models;

import java.util.ArrayList;
import java.util.Map;

import enums.Role;

public class Student extends User {
	
	private ArrayList<String> registeredCamps;
	private Map<String, ArrayList<Integer>> enquiries;
	
	/**
	 * @param userID
	 * @param password
	 * @param name
	 * @param faculty
	 * @param role
	 * @param registeredCamps
	 * @param enquiries
	 */
	
	 public Student(String userID, String password, String name, String faculty, ArrayList<String> registeredCamps, Map<String, ArrayList<Integer>> enquiries) {
		
		 super(userID, password, name, faculty, Role.STUDENT);
		this.registeredCamps = registeredCamps;
		this.enquiries = enquiries;
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

	/**
	 * @return the enquiries
	 */
	public Map<String, ArrayList<Integer>> getEnquiries() {
		
		return enquiries;
	}

	/**
	 * @param enquiries the enquiries to set
	 */
	public void setEnquiries(Map<String, ArrayList<Integer>> enquiries) {
		
		this.enquiries = enquiries;
	}
}
