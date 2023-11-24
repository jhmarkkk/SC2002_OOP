package models;

/**
 * 
 */
public class Enquiry {
	
	private Integer enquiryID;
	private String enquiry;
	private String enquirer;
	private String replier = null;
	private String reply = null;
	private static Integer enquiryCounter;
	
	/**
	 * Before creating Enquiry instances, make sure you've set the enquiryCounter value!
	 * @param enquiry
	 * @param enquirer
	 * @param replier
	 */
	public Enquiry(String enquiry, String enquirer, String replier, String reply) {
		
		this.enquiryID = enquiryCounter++;
		this.enquiry = enquiry;
		this.enquirer = enquirer;
		this.replier = replier;
		this.reply = reply;

	}

	/**
	 * @return the enquiry
	 */
	public String getEnquiry() {
		
		return enquiry;
	}

	/**
	 * @param enquiry the enquiry to set
	 */
	public void setEnquiry(String enquiry) {
		
		this.enquiry = enquiry;
	}

	/**
	 * @return the replier
	 */
	public String getReplier() {
		
		return replier;
	}

	/**
	 * @param replier the replier to set
	 */
	public void setReplier(String replier) {
		
		this.replier = replier;
	}

	/**
	 * @return the enquiryID
	 */
	public Integer getEnquiryID() {
		
		return enquiryID;
	}

	/**
	 * @return the enquirer
	 */
	public String getEnquirer() {
		
		return enquirer;
	}

	/**
	 * @return the reply
	 */
	public String getReply() {
		
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		
		this.reply = reply;
	}

	/**
	 * @return the enquiryCounter
	 */
	public Integer getEnquiryCounter() {
		
		return enquiryCounter;
	}

	/**
	 * @param enquiryCounter the enquiryCounter to set
	 */
	public void setEnquiryCounter(Integer enquiryCounter) {
		
		Enquiry.enquiryCounter = enquiryCounter;
	}
	
	
	
	
}
