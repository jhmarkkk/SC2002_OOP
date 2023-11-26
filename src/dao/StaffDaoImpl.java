package dao;

import java.util.HashMap;
import java.util.Map;

import interfaces.dao.StaffDao;

import models.Staff;

/**
 * The {@code StaffDaoImpl} class is an implementation of the {@link StaffDao} interface.
 * It provides methods to manage and access staff members stored in a map.
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see StaffDao
 */
public class StaffDaoImpl implements StaffDao {
	
	private static Map<String, Staff> staffs = new HashMap<String, Staff>();

    /**
     * Retrieves the map of staff members stored in the DAO.
     * 
     * @return the map of staff members, where the keys are unique identifiers and the values are staff member objects.
     */
	public Map<String, Staff> getStaffs() {
		return staffs;
	}

    /**
     * Sets the map of staff members in the DAO, useful for initializing the DAO with an existing set of staff members or updating it.
     * 
     * @param staffs the map of staff members to set, where the keys are unique identifiers and the values are staff member objects.
     */
	public void setStaffs(Map<String, Staff> staffs) {
		StaffDaoImpl.staffs = staffs;
	}
	
}
