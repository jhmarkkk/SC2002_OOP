package models;

import java.util.ArrayList;
import java.util.Map;

import enums.Role;

/**
 * The CommitteeMember class represents a committee member in the system, extending the Student class.
 * It includes information about the camps facilitated by the committee member, suggestion IDs, and the points earned by the committee member.
 */
public class CommitteeMember extends Student {
	
	/**
     * The name of the camp facilitated by the Committee Member.
     */
	private String facilitatingCamp;

	/**
     * A list of suggestion IDs representing suggestion provided by the Committee Member.
     */
	private ArrayList<Integer> suggestions;

	/**
     * The points earned by the Committee Member.
     */
	private int points = 0;
	
	/**
	 * Constructor used for importing Committee Member from CSV.
	 * 
	 * @param userID 			The user ID of the Committee Member.
	 * @param password 			The password of the Committee Member.
	 * @param name 				The name of the Committee Member.
	 * @param faculty			The faculty to which the Committee Member belongs.
	 * @param registeredCamps 	The list of camps the Committee Member is registered for.
	 * @param enquiries 		A map containing the Committee Member's enquiries, with camp names as keys and lists of enquiry IDs as values.
	 * @param facilitatingCamp	The name of the camp facilitated by the Committee Member.
	 * @param suggestions 		A list of suggestion IDs representing suggestion provided by the Committee Member.
	 * @param points 			The points earned by the Committee Member.
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
	 * 
	 * @param userID 			The user ID of the Committee Member.
	 * @param password 			The password of the Committee Member.
	 * @param name 				The name of the Committee Member.
	 * @param faculty 			The faculty to which the Committee Member belongs.
	 * @param registeredCamps 	The list of camps the Committee Member is registered for.
	 * @param enquiries 		A map containing the Committee Member's enquiries, with camp names as keys and lists of enquiry IDs as values.
	 * @param facilitatingCamp 	The name of the camp facilitated by the Committee Member.
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
