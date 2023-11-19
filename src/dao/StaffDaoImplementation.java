/**
 * 
 */
package dao;

import java.util.Map;

import models.Staff;

/**
 * 
 */
public class StaffDaoImplementation implements StaffDaoInterface {
	
	private Map<String, Staff> staffs;

	/**
	 * @param staffs
	 */
	public StaffDaoImplementation(Map<String, Staff> staffs) {
		this.staffs = staffs;
	}

	/**
	 * @return the staffs
	 */
	public Map<String, Staff> getStaffs() {
		return staffs;
	}

	/**
	 * @param staffs the staffs to set
	 */
	public void setStaffs(Map<String, Staff> staffs) {
		this.staffs = staffs;
	}
	
}
