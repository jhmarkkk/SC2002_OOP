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
	
	private Map<String, Camp> camps;

	/**
	 * @param camps
	 */
	public CampDaoImpl(Map<String, Camp> camps) {
		this.camps = camps;
	}

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
		this.camps = camps;
	}
	
	
}
