/**
 * 
 */
package dao;

import java.util.Map;

import models.Staff;

/**
 * 
 */
public class StaffDaoImpl implements StaffDao {
	
	private static Map<String, Staff> staffs;

	/**
	 * @param staffs
	 */
	public StaffDaoImpl(Map<String, Staff> staffs) {
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
