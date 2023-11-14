package assignment_CAMsApp;

enum Visibility{
	OFF,
	ON
}

public class CampInformation {
	private String campName;
	private int[] date;
	private int registrationClosingDate;
	private String openTo;
	private String location;
	private int totalSlots;
	private int committeeSlots;
	private String description;
	private Staff staffInCharge;
	private Visibility visibility;
	
	
	public String getCampName() {
		return campName;
	}
	
	public void setCampName(String set_campName) {
		campName = set_campName;
	}
	
	public int[] getDate() {
		return date;
	}
	
	public void setDate(int[] set_date) {
		date = set_date;
	}
	
	public int getRegistrationClosingDate() {
		return registrationClosingDate;
	}
	
	public void setRegistrationClosingDate(int set_registrationClosingDate) {
		registrationClosingDate = set_registrationClosingDate;
	}
	
	public String getOpenTo() {
		return openTo;
	}
	
	public void setOpenTo(String set_openTo) {
		openTo = set_openTo;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String set_location) {
		location = set_location;
	}
	
	public int getTotalSlots() {
		return totalSlots;
	}
	
	public void setTotalSlots(int set_totalSlots) {
		totalSlots = set_totalSlots;
	}
	
	public int getCommitteeSlots() {
		return committeeSlots;
	}
	
	public void setCommitteeSlots(int set_committeeSlots) {
		committeeSlots = set_committeeSlots;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String set_description) {
		description = set_description;
	}
	
	public Staff getStaffInCharge() {
		return staffInCharge;
	}
	
	public void setStaffInCharge(Staff set_staffInCharge) {
		staffInCharge = set_staffInCharge;
	}
	
	public Visibility getVisibility() {
		return visibility;
	}
	
	public void setVisibility(Visibility set_visibility) {
		visibility = set_visibility;
	}
}
