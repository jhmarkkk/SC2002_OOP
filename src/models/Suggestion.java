package models;

/**
 * 
 */
public class Suggestion {
	
	private int suggestionID;
	private String suggestion;
	private String suggester;
	private String approver = null;
	private static Integer suggestionCounter;
	
	/**
	 * Constructor used for importing Suggestion from csv.
	 * Before creating Suggestion instances, make sure you've set the suggestionCounter value!
	 * @param suggestion
	 * @param suggester
	 * @param approver
	 */
	public Suggestion(Integer suggestionID, String suggestion, String suggester, String approver) {
		
		this.suggestionID = suggestionID;
		this.suggestion = suggestion;
		this.suggester = suggester;
		this.approver = approver;
	}

	/**
	 * Constructor used for new Suggestion created by students.
	 * @param suggestion
	 * @param suggester
	 */
	public Suggestion(String suggestion, String suggester) {
		
		this.suggestionID = suggestionCounter++;
		this.suggestion = suggestion;
		this.suggester = suggester;
		this.approver = null;
	}

	/**
	 * @return the suggestion
	 */
	public String getSuggestion() {
		
		return suggestion;
	}

	/**
	 * @param suggestion the suggestion to set
	 */
	public void setSuggestion(String suggestion) {
		
		this.suggestion = suggestion;
	}

	/**
	 * @return the approver
	 */
	public String getApprover() {
		
		return approver;
	}

	/**
	 * @param approver the approver to set
	 */
	public void setApprover(String approver) {
		
		this.approver = approver;
	}

	/**
	 * @return the suggestionID
	 */
	public int getSuggestionID() {
		
		return suggestionID;
	}

	/**
	 * @return the suggester
	 */
	public String getSuggester() {
		
		return suggester;
	}

	/**
	 * @return the suggestionCounter
	 */
	public Integer getSuggestionCounter() {
		
		return suggestionCounter;
	}

	/**
	 * @param suggestionCounter the suggestionCounter to set
	 */
	public void setSuggestionCounter(Integer suggestionCounter) {
		
		Suggestion.suggestionCounter = suggestionCounter;
	}
	
	
}
