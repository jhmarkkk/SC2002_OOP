package models;

import java.util.ArrayList;
import java.util.Date;
import enums.Visibility;

public class Camp {
	private String name;
	private ArrayList<Date> dates;
	private Date registrationClosingDate;
	private String openTo;
	private String location;
	private int totalSlots;
	private int committeeSlots;
	private String description;
	private Staff staffInCharge;
	private ArrayList<Student> attendees;
	private ArrayList<String> withdrawnAttendees;
	private ArrayList<CommitteeMember> committeeMembers;
	private Visibility visibility = Visibility.OFF;
	private ArrayList<Enquiry> enquiries;
	private ArrayList<Suggestion> suggestions;
	
	/**
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
	 * @param visibility
	 * @param enquiries
	 * @param suggestions
	 */
	public Camp(String name, ArrayList<Date> dates, Date registrationClosingDate, String openTo, String location,
			int totalSlots, int committeeSlots, String description, Staff staffInCharge, ArrayList<Student> attendees,
			ArrayList<String> withdrawnAttendees, ArrayList<CommitteeMember> committeeMembers, Visibility visibility,
			ArrayList<Enquiry> enquiries, ArrayList<Suggestion> suggestions) {
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
		this.visibility = visibility;
		this.enquiries = enquiries;
		this.suggestions = suggestions;
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
	public ArrayList<Date> getDates() {
		return dates;
	}

	/**
	 * @param dates the dates to set
	 */
	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	/**
	 * @return the registrationClosingDate
	 */
	public Date getRegistrationClosingDate() {
		return registrationClosingDate;
	}

	/**
	 * @param registrationClosingDate the registrationClosingDate to set
	 */
	public void setRegistrationClosingDate(Date registrationClosingDate) {
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
	public ArrayList<Student> getAttendees() {
		return attendees;
	}

	/**
	 * @param attendees the attendees to set
	 */
	public void setAttendees(ArrayList<Student> attendees) {
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
	public ArrayList<CommitteeMember> getCommitteeMembers() {
		return committeeMembers;
	}

	/**
	 * @param committeeMembers the committeeMembers to set
	 */
	public void setCommitteeMembers(ArrayList<CommitteeMember> committeeMembers) {
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
	public ArrayList<Enquiry> getEnquiries() {
		return enquiries;
	}

	/**
	 * @param enquiries the enquiries to set
	 */
	public void setEnquiries(ArrayList<Enquiry> enquiries) {
		this.enquiries = enquiries;
	}

	/**
	 * @return the suggestions
	 */
	public ArrayList<Suggestion> getSuggestions() {
		return suggestions;
	}

	/**
	 * @param suggestions the suggestions to set
	 */
	public void setSuggestions(ArrayList<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	/**
	 * @return the staffInCharge
	 */
	public Staff getStaffInCharge() {
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
