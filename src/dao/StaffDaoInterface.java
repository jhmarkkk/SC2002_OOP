/**
 * 
 */
package dao;

import java.util.Map;

import models.Staff;

/**
 * 
 */
public interface StaffDaoInterface {
	public Map<String, Staff> getStaffs();
	public void setStaffs(Map<String, Staff> staffs);
}
