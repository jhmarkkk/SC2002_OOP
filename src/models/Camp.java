package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import enums.Visibility;

/**
 * The {@link Camp} class represents a camp in CAMs.
 * Each camp has a unique name, schedule, registration details, description, and staff in charge.
 * It also manages information about attendees, committee members, enquiries, suggestions, and visibility.
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 */
public class Camp {
	
	/**
	 * The name of the camp.
	 */
	private String name;

	/**
	 * The list of dates on which the camp is scheduled.
	 */
	private ArrayList<GregorianCalendar> dates;

	/**
	 * The closing date for camp registration.
	 */
	private GregorianCalendar registrationClosingDate;

	/**
	 * The faculty to which the camp is open.
	 */
	private String openTo;

	/**
	 * The location where the camp is held.
	 */
	private String location;

	/**
	 * The total number of available slots for the camp.
	 */
	private int totalSlots;

	/**
	 * The number of slots reserved for committee members.
	 */
	private int committeeSlots;

	/**
	 * A brief description of the camp.
	 */
	private String description;

	/**
	 * The name of the staff in charge of the camp.
	 */
	private String staffInCharge;

	/**
	 * A list of names of attendees registered for the camp.
	 */
	private ArrayList<String> attendees;

	/**
	 * A list of names of attendees who have withdrawn from the camp.
	 */
	private ArrayList<String> withdrawnAttendees;

	/**
	 * A list of names of committee members involved in the camp.
	 */
	private ArrayList<String> committeeMembers;

	/**
	 * A map associating enquiry IDs with their respective enquiries.
	 */
	private Map<Integer, Enquiry> enquiries;

	/**
	 * A map associating suggestion IDs with their respective suggestions.
	 */
	private Map<Integer, Suggestion> suggestions;

	/**
	 * The visibility status indicating whether the camp is open for registration.
	 */
	private Visibility visibility;
	
	/**
	 * Constructor used for importing {@link Camp} from CSV.
	 * 
	 * @param name 						The name of the camp.
	 * @param dates 					The list of dates on which the camp is scheduled.
	 * @param registrationClosingDate	The closing date for camp registration.
	 * @param openTo 					The faculty to which the camp is open.
	 * @param location 					The location where the camp is held.
	 * @param totalSlots 				The total number of available slots for the camp.
	 * @param committeeSlots 			The number of slots reserved for committee members.
	 * @param description 				A brief description of the camp.
	 * @param staffInCharge 			The name of the staff in charge of the camp.
	 * @param attendees 				A list of names of attendees registered for the camp.
	 * @param withdrawnAttendees 		A list of names of attendees who have withdrawn from the camp.
	 * @param committeeMembers			A list of names of committee members involved in the camp.
	 * @param enquiries 				A map associating enquiry IDs with their respective enquiries.
	 * @param suggestions 				A map associating suggestion IDs with their respective suggestions.
	 * @param visibility 				The visibility status indicating whether the camp is open for registration.
	 */
	public Camp(String name, ArrayList<GregorianCalendar> dates, GregorianCalendar registrationClosingDate, String openTo, String location, int totalSlots, int committeeSlots, String description, String staffInCharge, ArrayList<String> attendees, ArrayList<String> withdrawnAttendees, ArrayList<String> committeeMembers, Map<Integer, Enquiry> enquiries, Map<Integer, Suggestion> suggestions, Visibility visibility) {
		this.name = name;
		this.dates = dates;
		this.registrationClosingDate = registrationClosingDate;
		this.openTo = openTo;
		this.location = location;
		this.totalSlots = totalSlots;
		this.committeeSlots = committeeSlots;
		this.description = description;
		this.staffInCharge = staffInCharge;
		this.attendees = attendees;
		this.withdrawnAttendees = withdrawnAttendees;
		this.committeeMembers = committeeMembers;
		this.enquiries = enquiries;
		this.suggestions = suggestions;
		this.visibility = visibility;
	}

	/**
	 * Constructor for Staff to create new {@link Camp}.
	 * 
	 * @param name 						The name of the camp.
	 * @param dates 					The list of dates on which the camp is scheduled.
	 * @param registrationClosingDate 	The closing date for camp registration.
	 * @param openTo 					The faculty to which the camp is open.
	 * @param location 					The location where the camp is held.
	 * @param totalSlots 				The total number of available slots for the camp.
	 * @param committeeSlots 			The number of slots reserved for committee members.
	 * @param description 				A brief description of the camp.
	 * @param staffInCharge 			The name of the staff in charge of the camp.
	 */
	public Camp(String name, ArrayList<GregorianCalendar> dates, GregorianCalendar registrationClosingDate, String openTo, String location, int totalSlots, int committeeSlots, String description, String staffInCharge) {
		this.name = name;
		this.dates = dates;
		this.registrationClosingDate = registrationClosingDate;
		this.openTo = openTo;
		this.location = location;
		this.totalSlots = totalSlots;
		this.committeeSlots = committeeSlots;
		this.description = description;
		this.staffInCharge = staffInCharge;
		this.attendees = new ArrayList<String>();
		this.withdrawnAttendees = new ArrayList<String>();
		this.committeeMembers = new ArrayList<String>();
		this.enquiries = new HashMap<Integer, Enquiry>();
		this.suggestions = new HashMap<Integer, Suggestion>();
		this.visibility = Visibility.OFF;
	}

	/**
	 * Returns the name of the camp.
	 * 
	 * @return The name of the camp.
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Sets the name of the camp.
	 * 
	 * @param name The new name for the camp to set.
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * Returns the dates of the camp.
	 * 
	 * @return An ArrayList of GregorianCalendar objects representing the dates of the camp.
	 */
	public ArrayList<GregorianCalendar> getDates() {
		
		return dates;
	}

	/**
	 * Sets the dates of the camp.
	 * 
	 * @param dates An ArrayList of GregorianCalendar objects representing the dates to set.
	 */
	public void setDates(ArrayList<GregorianCalendar> dates) {
		
		this.dates = dates;
	}

	/**
	 * Returns the registration closing date of the camp.	
	 *  
	 * @return The registration closing date of the camp as a GregorianCalendar object.
	 */
	public GregorianCalendar getRegistrationClosingDate() {
		
		return registrationClosingDate;
	}

	/**
	 * Sets the registration closing date of the camp.	
	 * 
	 * @param registrationClosingDate The GregorianCalendar object representing the new registration closing date of the camp to set.
	 */
	public void setRegistrationClosingDate(GregorianCalendar registrationClosingDate) {
		
		this.registrationClosingDate = registrationClosingDate;
	}

	/**
	 * Returns the faculty in which the camp is open.
	 * 
	 * @return The faculty in which the camp is open.
	 */
	public String getOpenTo() {
		
		return openTo;
	}

	/**
	 * Sets the faculty to which the camp is open.
	 * 
	 * @param openTo The new faculty to which the camp is open to.
	 */
	public void setOpenTo(String openTo) {
		
		this.openTo = openTo;
	}

	/**
	 * Returns the location where the camp is held.
	 * 
	 * @return The location where the camp is held.
	 */
	public String getLocation() {
		
		return location;
	}

	/**
	 * Sets the location where the cammp is held.
	 * 
	 * @param location The new location where the camp is held.
	 */
	public void setLocation(String location) {
		
		this.location = location;
	}

	/**
	 * Returns the total number of slots open for the camp.
	 * 
	 * @return the total number of slots open for the camp.
	 */
	public int getTotalSlots() {
		
		return totalSlots;
	}

	/**
	 * Sets the total number of slots open for the camp.
	 * 
	 * @param totalSlots the new total number of slots open for the camp.
	 */
	public void setTotalSlots(int totalSlots) {
		
		this.totalSlots = totalSlots;
	}

	/**
	 * Returns the number of slots available for the camp's committee members.
	 * 
	 * @return The number of slots available for the camp's committee members.
	 */
	public int getCommitteeSlots() {
		
		return committeeSlots;
	}

	/**
	 * Sets the number of slots available for the camp's committee members.
	 * 
	 * @param committeeSlots The new number of slots available for the camp's committee members.
	 */
	public void setCommitteeSlots(int committeeSlots) {
		
		this.committeeSlots = committeeSlots;
	}

	/**
	 * Returns the description of the camp.
	 * 
	 * @return The description of the camp.
	 */
	public String getDescription() {
		
		return description;
	}

	/**
	 * Sets the description of the camp.
	 * 
	 * @param description The new description of the camp.
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}

	/**
	 * Returns the list of attendees for the camp.
	 * 
	 * @return An ArrayList containing the names of attendees for the camp.
	 */
	public ArrayList<String> getAttendees() {
		
		return attendees;
	}

	/**
	 * Sets the list of attendees for the camp.
	 * 
	 * @param attendees A new ArrayList containing the names of attendees for the camp.
	 */
	public void setAttendees(ArrayList<String> attendees) {
		
		this.attendees = attendees;
	}

	/**
	 * Returns the list of attendees who have withdrawn from the camp.
	 * 
	 * @return An ArrayList containing the names of attendees who have withdrawn from the camp.
	 */
	public ArrayList<String> getWithdrawnAttendees() {
		
		return withdrawnAttendees;
	}

	/**
	 * Sets the list of attendees who have withdrawn from the camp.
	 * 
	 * @param withdrawnAttendees A new ArrayList containing the names of attendees who have withdrawn from the camp.
	 */
	public void setWithdrawnAttendees(ArrayList<String> withdrawnAttendees) {
		
		this.withdrawnAttendees = withdrawnAttendees;
	}

	/**
	 * Returns the list of committee members of the camp.
	 * 
	 * @return An ArrayList containing the names of the committee members of the camp.
	 */
	public ArrayList<String> getCommitteeMembers() {
		
		return committeeMembers;
	}

	/**
	 * Sets the list of committee members of the camp.
	 * 
	 * @param committeeMembers A new ArrayList containing the names of the committee members of the camp.
	 */
	public void setCommitteeMembers(ArrayList<String> committeeMembers) {
		
		this.committeeMembers = committeeMembers;
	}

	/**
	 * Returns the visibility status indicating whether the camp is visible to students for registrations.
	 * 
	 * @return The visibility status of the camp.
	 */
	public Visibility getVisibility() {
		
		return visibility;
	}

	/**
	 * Sets the visibility status to determine whether the camp is visible to students for registrations.
	 * 
	 * @param visibility The new visibility status of the camp to set.
	 */
	public void setVisibility(Visibility visibility) {
		
		this.visibility = visibility;
	}

	/**
	 * Returns a map associating Enquiry IDs with their respective Enquiries.
	 * 
	 * @return A map of Enquiry IDs with their respective Enquiries.
	 */
	public Map<Integer, Enquiry> getEnquiries() {
		
		return enquiries;
	}

	/**
	 * Sets the map of Enquiry IDs with their respective Enquiries.
	 * 
	 * @param enquiries A new map of Enquiry IDs with their respective Enquiries to set.
	 */
	public void setEnquiries(Map<Integer, Enquiry> enquiries) {
		
		this.enquiries = enquiries;
	}

	/**
	 * Returns a map associating Suggestion IDs with their respective Suggestions.
	 * 
	 * @return A map of Suggestion IDs with their respective Suggestions.
	 */
	public Map<Integer, Suggestion> getSuggestions() {
		
		return suggestions;
	}

	/**
	 * Sets the map of Suggestion IDs with their respective Suggestions.
	 * 
	 * @param suggestions A new map of Suggestion IDs with their respective Suggestions to set.
	 */
	public void setSuggestions(Map<Integer, Suggestion> suggestions) {
		
		this.suggestions = suggestions;
	}

	/**
	 * Returns the name of the Staff-In-Charge of the camp.
	 * 
	 * @return The name of the Staff-In-Charge.
	 */
	public String getStaffInCharge() {
		
		return staffInCharge;
	}
	
	/**
	 * Returns the number of slots remaining for attendee registration.
	 * 
	 * @return The calculated number of slots available for attendee registration.
	 */
	public int getAttendeeSlots() {
		
		return this.totalSlots - this.committeeSlots;
	}
	
}
