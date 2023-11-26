package dao;

import java.util.HashMap;
import java.util.Map;

import interfaces.dao.CampDao;

import models.Camp;

/**
 * The {@code CampDaoImpl} class is an implementation of the {@link CampDao} interface.
 * It provides methods to manage and access camps stored in a map.
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 */
public class CampDaoImpl implements CampDao {
	
	private static Map<String, Camp> camps = new HashMap<String, Camp>();

    /**
     * Retrieves the map of camps.
     *
     * @return the map of camps, where the keys are unique identifiers and the values are camp objects.
     */
	public Map<String, Camp> getCamps() {
		return camps;
	}

    /**
     * Sets the map of camps.
     * 
     * @param camps the new map of camps to set, where the keys are unique identifiers and the values are camp objects.
     */
	public void setCamps(Map<String, Camp> camps) {
		CampDaoImpl.camps = camps;
	}
	
	
}
