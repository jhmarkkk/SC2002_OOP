package interfaces.dao;

import java.util.Map;

import models.Camp;


/**
 * The {@code CampDao} interface defines methods for accessing and manipulating camp-related data in the CAMs system.
 * Implementations of this interface provide the underlying data access logic for retrieving and updating camp information.
 * 
 * <p>It includes methods to get the map of camps and set the map of camps. 
 * The map associates camp names with corresponding {@link Camp} objects.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see Camp
 */
public interface CampDao {
	
    /**
     * Retrieves the map of camps associated with their names.
     * 
     * @return A map containing camp names as keys and corresponding {@link Camp} objects as values.
     */
	public Map<String, Camp> getCamps();
	
    /**
     * Sets the map of camps.
     * 
     * @param camps A new map containing camp names as keys and corresponding {@link Camp} objects as values.
     */	
	public void setCamps(Map<String, Camp> camps);
}
