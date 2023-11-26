package models;

import java.util.ArrayList;
import java.util.Map;

import enums.Role;

public class CommitteeMember extends Student {
	
	private String facilitatingCamp;
	private ArrayList<Integer> suggestions;
	private int points = 0;
	
	/**
	 * Constructor used for importing Committee Member from CSV.
	 * @param userID
	 * @param password
	 * @param name
	 * @param faculty
	 * @param registeredCamps
	 * @param enquiries
	 * @param facilitatingCamp
	 * @param suggestions
	 * @param points
	 */
	public CommitteeMember(String userID, String password, String name, String faculty,
			ArrayList<String> registeredCamps, Map<String, ArrayList<Integer>> enquiries,
			String facilitatingCamp, ArrayList<Integer> suggestions, int points) {
		
		super(userID, password, name, faculty, registeredCamps, enquiries);
		this.setRole(Role.COMMITTEE);
		this.facilitatingCamp = facilitatingCamp;
		this.suggestions = suggestions;
		this.points = points;
	}

	/**
	 * Constructor used to promote Student to Committee Member.
	 * @param userID
	 * @param password
	 * @param name
	 * @param faculty
	 * @param registeredCamps
	 * @param enquiries
	 * @param facilitatingCamp
	 */
	public CommitteeMember(String userID, String password, String name, String faculty,
			ArrayList<String> registeredCamps, Map<String, ArrayList<Integer>> enquiries,
			String facilitatingCamp) {
		
		super(userID, password, name, faculty, registeredCamps, enquiries);
		this.setRole(Role.COMMITTEE);
		this.facilitatingCamp = facilitatingCamp;
		this.suggestions = new ArrayList<Integer>();
		this.points = 0;
	}

	/**
	 * Retrieves a list of suggestion IDs.
	 * 
	 * @return An ArrayList containing suggestion IDs.
	 */
	public ArrayList<Integer> getSuggestions() {
		
		return suggestions;
	}

	/**
	 * Sets the list of suggestion IDs.
	 * 
	 * @param suggestions The new ArrayList of suggestion IDs to set.
	 */
	public void setSuggestions(ArrayList<Integer> suggestions) {
		
		this.suggestions = suggestions;
	}

	/**
	 * Retrieves the points earned by the committee member.
	 * 
	 * @return The points earned by the committee member.
	 */
	public int getPoints() {
		
		return points;
	}

	/**
	 * Sets the points earned for the committee member.
	 * 
	 * @param points The new value for the committee member's points.
	 */
	public void setPoints(int points) {
		
		this.points = points;
	}

	/**
	 * Retrieves the name of the camp in which the committee member is facilitating
	 * 
	 * @return The name of the camp being facilitated by the committee member.
	 */
	public String getFacilitatingCamp() {
		
		return facilitatingCamp;
	}
}
