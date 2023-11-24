/**
 * 
 */
package dao;

import java.util.Map;

import interfaces.dao.StaffDao;
import models.Staff;

/**
 * 
 */
public class StaffDaoImpl implements StaffDao {
	
	private static Map<String, Staff> staffs;

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
		StaffDaoImpl.staffs = staffs;
	}
	
}
