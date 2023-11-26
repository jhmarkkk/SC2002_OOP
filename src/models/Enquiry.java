package models;

/**
 * The {@code Enquiry} class represents an inquiry made by a {@link User} in CAMs.
 * An enquiry consists of an ID, the actual inquiry message, the enquirer's name, the name of the replier (if any), and a reply message (if available).
 * 
 * <p>The class provides constructors for importing Enquiry instances from CSV and for creating new enquiries initiated by an {@link User}. 
 * Additionally, it contains methods for retrieving and modifying the details of an enquiry.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 */
public class Enquiry {
	
    /**
     * The unique ID of the enquiry.
     */
	private Integer enquiryID;

    /**
     * The content of the enquiry message.
     */
	private String enquiry;

    /**
     * The name of the user making the enquiry.
     */
	private String enquirer;

    /**
     * The name of the user replying to the enquiry.
     */
	private String replier = null;

    /**
     * The reply message provided in response to the enquiry.
     */
	private String reply = null;

    /**
     * A counter to keep track of the number of enquiries created.
     */
	private static Integer enquiryCounter;
	
	/**
	 * Constructor used for importing {@link Enquiry} from csv.
	 * 
	 * @param enquiryID	The unique ID of the enquiry.
	 * @param enquiry	The content of the enquiry message.
	 * @param enquirer	The name of the user making the enquiry.
	 * @param replier	The name of the user replying to the enquiry.
	 * @param reply		The reply message provided in response to the enquiry.
	 */
	public Enquiry(Integer enquiryID, String enquiry, String enquirer, String replier, String reply) {
		
		this.enquiryID = enquiryID;
		this.enquiry = enquiry;
		this.enquirer = enquirer;
		this.replier = replier;
		this.reply = reply;
	}

	/**
	 * Constructor used for new {@link Enquiry} created by {@link Student}.
	 * 
	 * @param enquiry	The content of the enquiry message.
	 * @param enquirer	The name of the user making the enquiry.
	 */
	public Enquiry(String enquiry, String enquirer) {
		
		this.enquiryID = enquiryCounter++;
		this.enquiry = enquiry;
		this.enquirer = enquirer;
		this.replier = null;
		this.reply = null;
	}

	/**
	 * Returns the content of the enquiry message.
	 * 
	 * @return The content of the enquiry.
	 */
	public String getEnquiry() {
		
		return enquiry;
	}

	/**
	 * Sets the content of the enquiry message.
	 * 
	 *  @param enquiry The new content of the enquiry to set.
	 */
	public void setEnquiry(String enquiry) {
		
		this.enquiry = enquiry;
	}

	/**
	 * Returns the name of the user replying to the enquiry.
	 * 
	 * @return The name of the replier.
	 */
	public String getReplier() {
		
		return replier;
	}

	/**
	 * Sets the name of the user replying to the enquiry.
	 * 
	 * @param replier The new name of the replier to set.
	 */
	public void setReplier(String replier) {
		
		this.replier = replier;
	}

	/**
	 * Returns the unique ID of the enquiry.
	 * 
	 * @return The enquiry ID.
	 */
	public Integer getEnquiryID() {
		
		return enquiryID;
	}

	/**
	 * Returns the name of the user making the enquiry.
	 * 
	 * @return The name of the enquirer.
	 */
	public String getEnquirer() {
		
		return enquirer;
	}

	/**
	 * Returns the reply message provided in response to the enquiry.
	 * 
	 * @return The content of the reply.
	 */
	public String getReply() {
		
		return reply;
	}

	/**
	 * Sets the reply message provided in response to the enquiry.
	 * 
	 * @param reply The new content of the reply to set.
	 */
	public void setReply(String reply) {
		
		this.reply = reply;
	}

	/**
	 * Returns the current value of the enquiry counter.
	 * 
	 * @return The current value of the enquiry counter.
	 */
	public static Integer getEnquiryCounter() {
		
		return enquiryCounter;
	}

	/**
	 * Sets the value of the enquiry counter.
	 * 
	 * @param enquiryCounter The new value of the enquiry counter to set.
	 */
	public static void setEnquiryCounter(Integer enquiryCounter) {
		
		Enquiry.enquiryCounter = enquiryCounter;
	}
	
}
