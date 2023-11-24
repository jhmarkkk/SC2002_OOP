package models;

import java.util.ArrayList;
import java.util.Map;
import java.util.GregorianCalendar;
import java.util.HashMap;

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
	 * Constructor used for importing Camp from csv.
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
	 * Constructor used for Staff to create new Camp.
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
			String openTo, String location, int totalSlots, int committeeSlots, String description,
			String staffInCharge) {
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
	 * @return the name
	 */
	public String getName() {
		
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		
		this.name = name;
	}

	/**
	 * @return the dates
	 */
	public ArrayList<GregorianCalendar> getDates() {
		
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(ArrayList<GregorianCalendar> dates) {
		
		this.dates = dates;
	}

	/**
	 * @return the registrationClosingDate
	 */
	public GregorianCalendar getRegistrationClosingDate() {
		
		return registrationClosingDate;
	}

	/**
	 * @param registrationClosingDate the registrationClosingDate to set
	 */
	public void setRegistrationClosingDate(GregorianCalendar registrationClosingDate) {
		
		this.registrationClosingDate = registrationClosingDate;
	}

	/**
	 * @return the openTo
	 */
	public String getOpenTo() {
		
		return openTo;
	}

	/**
	 * @param openTo the openTo to set
	 */
	public void setOpenTo(String openTo) {
		
		this.openTo = openTo;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		
		this.location = location;
	}

	/**
	 * @return the totalSlots
	 */
	public int getTotalSlots() {
		
		return totalSlots;
	}

	/**
	 * @param totalSlots the totalSlots to set
	 */
	public void setTotalSlots(int totalSlots) {
		
		this.totalSlots = totalSlots;
	}

	/**
	 * @return the committeeSlots
	 */
	public int getCommitteeSlots() {
		
		return committeeSlots;
	}

	/**
	 * @param committeeSlots the committeeSlots to set
	 */
	public void setCommitteeSlots(int committeeSlots) {
		
		this.committeeSlots = committeeSlots;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		
		this.description = description;
	}

	/**
	 * @return the attendees
	 */
	public ArrayList<String> getAttendees() {
		
		return attendees;
	}

	/**
	 * @param attendees the attendees to set
	 */
	public void setAttendees(ArrayList<String> attendees) {
		
		this.attendees = attendees;
	}

	/**
	 * @return the withdrawnAttendees
	 */
	public ArrayList<String> getWithdrawnAttendees() {
		
		return withdrawnAttendees;
	}

	/**
	 * @param withdrawnAttendees the withdrawnAttendees to set
	 */
	public void setWithdrawnAttendees(ArrayList<String> withdrawnAttendees) {
		
		this.withdrawnAttendees = withdrawnAttendees;
	}

	/**
	 * @return the committeeMembers
	 */
	public ArrayList<String> getCommitteeMembers() {
		
		return committeeMembers;
	}

	/**
	 * @param committeeMembers the committeeMembers to set
	 */
	public void setCommitteeMembers(ArrayList<String> committeeMembers) {
		
		this.committeeMembers = committeeMembers;
	}

	/**
	 * @return the visibility
	 */
	public Visibility getVisibility() {
		
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(Visibility visibility) {
		
		this.visibility = visibility;
	}

	/**
	 * @return the enquiries
	 */
	public Map<Integer, Enquiry> getEnquiries() {
		
		return enquiries;
	}

	/**
	 * @param enquiries the enquiries to set
	 */
	public void setEnquiries(Map<Integer, Enquiry> enquiries) {
		
		this.enquiries = enquiries;
	}

	/**
	 * @return the suggestions
	 */
	public Map<Integer, Suggestion> getSuggestions() {
		
		return suggestions;
	}

	/**
	 * @param suggestions the suggestions to set
	 */
	public void setSuggestions(Map<Integer, Suggestion> suggestions) {
		
		this.suggestions = suggestions;
	}

	/**
	 * @return the staffInCharge
	 */
	public String getStaffInCharge() {
		
		return staffInCharge;
	}
	
	/**
	 * 
	 * @return The number of slots remaining for attendees.
	 */
	public int getAttendeeSlots() {
		
		return this.totalSlots - this.committeeSlots;
	}
	

}
