package models;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import enums.Visibility;

public class Camp {
	
	private String name;
	private ArrayList<GregorianCalendar> dates;
	private GregorianCalendar registrationClosingDate;
	private String openTo;
	private String location;
	private int totalSlots;
	private int committeeSlots;
	private String description;
	private String staffInCharge;
	private ArrayList<String> attendees;
	private ArrayList<String> withdrawnAttendees;
	private ArrayList<String> committeeMembers;
	private Map<Integer, Enquiry> enquiries;
	private Map<Integer, Suggestion> suggestions;
	private Visibility visibility;
	
	/**
	 * Constructor used for importing Camp from CSV
	 * 
	 * @param name
	 * @param dates
	 * @param registrationClosingDate
	 * @param openTo
	 * @param location
	 * @param totalSlots
	 * @param committeeSlots
	 * @param description
	 * @param staffInCharge
	 * @param attendees
	 * @param withdrawnAttendees
	 * @param committeeMembers
	 * @param enquiries
	 * @param suggestions
	 * @param visibility
	 */
	public Camp(String name, ArrayList<GregorianCalendar> dates, GregorianCalendar registrationClosingDate,
			String openTo, String location, int totalSlots, int committeeSlots, String description,
			String staffInCharge, ArrayList<String> attendees, ArrayList<String> withdrawnAttendees,
			ArrayList<String> committeeMembers, Map<Integer, Enquiry> enquiries,
			Map<Integer, Suggestion> suggestions, Visibility visibility) {
		
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
	 * Constructor for Staff to create new Camp.
	 * 
	 * @param name
	 * @param dates
	 * @param registrationClosingDate
	 * @param openTo
	 * @param location
	 * @param totalSlots
	 * @param committeeSlots
	 * @param description
	 * @param staffInCharge
	 */
	public Camp(String name, ArrayList<GregorianCalendar> dates, GregorianCalendar registrationClosingDate,
			String openTo, String location, int totalSlots, int committeeSlots, String description, String staffInCharge) {
		
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
	 * Retrieves the name of the camp.
	 * 
	 * @return The name of the camp.
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * Sets the name of the camp.
	 * 
	 * @param name The new name for the camp.
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * Retrieves the dates of the camp.
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
	 * Retrieves the registration closing date of the camp.	
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
	 * Retrieves the faculty in which the camp is open.
	 * 
	 * @return The faculty in which the camp is open.
	 */
	public String getOpenTo() {
		
		return openTo;
	}

	/**
	 * Sets the faculty to which the camp is open.
	 * 
	 * @param openTo The faculty to which the camp is open to.
	 */
	public void setOpenTo(String openTo) {
		
		this.openTo = openTo;
	}

	/**
	 * Retrieves the location where the camp is held.
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
	 * Retrieves the total number of slots open for the camp.
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
	 * Retrieves the number of slots available for the camp's committee members.
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
	 * Retrieves the description of the camp.
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
	 * Retrieves the list of attendees for the camp.
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
	 * Retrieves the list of attendees who have withdrawn from the camp.
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
	 * Retrieves the list of committee members of the camp.
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
	 * Retrieves the visibility status indicating whether the camp is visible to students for registrations.
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
	 * Retrieves a map associating Enquiry IDs with their respective Enquiries.
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
	 * Retrieves a map associating Suggestion IDs with their respective Suggestions.
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
	 * Retrieves the name of the Staff-In-Charge of the camp.
	 * 
	 * @return The name of the Staff-In-Charge.
	 */
	public String getStaffInCharge() {
		
		return staffInCharge;
	}
	
	/**
	 * Retrieves the number of slots remaining for attendee registration.
	 * 
	 * @return The calculated number of slots available for attendee registration.
	 */
	public int getAttendeeSlots() {
		
		return this.totalSlots - this.committeeSlots;
	}
	
}
