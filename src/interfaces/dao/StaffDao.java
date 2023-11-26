package interfaces.dao;

import java.util.Map;

import models.Staff;

/**
 * The {@code StaffDao} interface defines methods for accessing and manipulating staff information in the CAMs system. 
 * Implementations of this interface provide the underlying data access logic for retrieving and updating staff data.
 * 
 * <p>It includes methods to get the staffs and set the staffs.
 * Staffs are represented by a {@link Staff} object, containing details such as ID, name, and other relevant information.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see Staff
 */
public interface StaffDao {
	
	/**
     * Retrieves the staffs from the data source.
     * 
     * @return A Map containing staff ID as keys and corresponding {@link Staff} objects as values.
     */
	public Map<String, Staff> getStaffs();
	
    /**
     * Sets the staffs in the data source.
     * 
     * @param staffs A new Map containing staff ID as keys and corresponding {@link Staff} objects as values.
     */	public void setStaffs(Map<String, Staff> staffs);
}
