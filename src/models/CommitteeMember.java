package models;

import java.util.ArrayList;
import java.util.Map;

import enums.Role;

public class CommitteeMember extends Student {
	
	private String facilitatingCamp;
	private ArrayList<Integer> suggestions;
	private int points = 0;
	
	/**
	 * Constructor used for importing CommitteeMember from csv.
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
	 * Constructor used for when a Student becomes a CommitteeMember.
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
	 * @return the suggestions
	 */
	public ArrayList<Integer> getSuggestions() {
		
		return suggestions;
	}

	/**
	 * @param suggestions the suggestions to set
	 */
	public void setSuggestions(ArrayList<Integer> suggestions) {
		
		this.suggestions = suggestions;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		
		return points;
	}

	/**
	 * @param points the points to set
	 */
	public void setPoints(int points) {
		
		this.points = points;
	}

	/**
	 * @return the facilitatingCamp
	 */
	public String getFacilitatingCamp() {
		
		return facilitatingCamp;
	}
}
