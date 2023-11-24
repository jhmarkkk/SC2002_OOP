/**
 * 
 */
package dao;

import java.util.Map;

import interfaces.dao.CampDao;
import models.Camp;

/**
 * 
 */
public class CampDaoImpl implements CampDao {
	
	private static Map<String, Camp> camps;

	/**
	 * @return the camps
	 */
	public Map<String, Camp> getCamps() {
		return camps;
	}

	/**
	 * @param camps the camps to set
	 */
	public void setCamps(Map<String, Camp> camps) {
		CampDaoImpl.camps = camps;
	}
	
	
}
