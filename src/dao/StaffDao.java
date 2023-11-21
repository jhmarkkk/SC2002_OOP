/**
 * 
 */
package dao;

import java.util.Map;

import models.Staff;

/**
 * 
 */
public interface StaffDao {
	public Map<String, Staff> getStaffs();
	public void setStaffs(Map<String, Staff> staffs);
}
