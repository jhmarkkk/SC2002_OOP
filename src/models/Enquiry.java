package models;

/**
 * 
 */
public class Enquiry {
	private int enquiryID;
	private String enquiry;
	private String enquirer;
	private String replier = null;
	
	/**
	 * @param enquiryID
	 * @param enquiry
	 * @param enquirer
	 * @param replier
	 */
	public Enquiry(int enquiryID, String enquiry, String enquirer, String replier) {
		this.enquiryID = enquiryID;
		this.enquiry = enquiry;
		this.enquirer = enquirer;
		this.replier = replier;
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
	public int getEnquiryID() {
		return enquiryID;
	}

	/**
	 * @return the enquirer
	 */
	public String getEnquirer() {
		return enquirer;
	}
	
	
	
	
}
