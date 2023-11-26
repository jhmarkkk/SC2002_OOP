package models;

/**
 * The {@code Suggestion} class represents a suggestion made by a {@link CommitteeMember} in CAMs.
 * A suggestion includes an ID, the suggestion message, the suggester's name, and an approval status indicating whether the suggestion has been approved.
 * 
 * <p>The class provides constructors for importing Suggestion instances from CSV and for creating new suggestions initiated by {@link CommitteeMember}. 
 * Additionally, it contains methods for retrieving and modifying the details of a suggestion.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 */
public class Suggestion {
	
	/**
     * The unique ID of the suggestion.
     */
	private Integer suggestionID;

    /**
     * The content of the suggestion message.
     */
	private String suggestion;

    /**
     * The name of the committee member making the suggestion.
     */
	private String suggester;

    /**
     * The approval status of the suggestion.
     */
	private boolean approved;


	private static Integer suggestionCounter;
	
	/**
	 * Constructor used for importing {@link Suggestion} from csv.
	 * 
	 * @param suggestionID	The unique ID of the suggestion.
	 * @param suggestion	The content of the suggestion message.
	 * @param suggester		The name of the committee member making the suggestion.
	 * @param approved		The approval status of the suggestion.
	 */
	public Suggestion(Integer suggestionID, String suggestion, String suggester, boolean approved) {
		this.suggestionID = suggestionID;
		this.suggestion = suggestion;
		this.suggester = suggester;
		this.approved = approved;
	}

	/**
	 * Constructor used for new {@link Suggestion} created by {@link CommitteeMember}.
	 * 
	 * @param suggestion	The content of the suggestion message.
	 * @param suggester		The name of the committee member making the suggestion.
	 */
	public Suggestion(String suggestion, String suggester) {
		this.suggestionID = suggestionCounter++;
		this.suggestion = suggestion;
		this.suggester = suggester;
		this.approved = false;
	}

	/**
	 * Returns the suggestion message.
	 * 
	 * @return The suggestion message.
	 */
	public String getSuggestion() {
		
		return suggestion;
	}

	/**
	 * Sets the suggestion message.
	 * 
	 * @param suggestion The new suggestion message to set.
	 */
	public void setSuggestion(String suggestion) {
		
		this.suggestion = suggestion;
	}

	/**
	 * Returns the approval status of the suggestion.
	 * 
	 * @return {@code true} if the suggestion is approved, {@code false} otherwise.
	 */
	public boolean getApproved() {
		
		return approved;
	}

	/**
	 * Sets the approval status of the suggestion.
	 * 
	 * @param approved The new approval status to set.
	 */
	public void setApproved(boolean approved) {
		
		this.approved = approved;
	}

	/**
	 * Returns the unique ID of the suggestion.
	 * 
	 * @return he unique ID of the suggestion.
	 */
	public Integer getSuggestionID() {
		
		return suggestionID;
	}

	/**
	 * Returns the name of the user who made the suggestion.
	 * 
	 * @return The name of the user who made the suggestion.
	 */
	public String getSuggester() {
		
		return suggester;
	}

	/**
	 * Returns the counter for tracking suggestion IDs.
	 * 
	 * @return The suggestion counter.
	 */
	public static Integer getSuggestionCounter() {
		
		return suggestionCounter;
	}

	/**
	 * Sets the suggestion counter.
	 * 
	 * @param suggestionCounter The new value for the suggestion counter to set.
	 */
	public static void setSuggestionCounter(Integer suggestionCounter) {
		
		Suggestion.suggestionCounter = suggestionCounter;
	}
}
