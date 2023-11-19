/**
 * 
 */
package dao;

import java.util.Map;

import models.Camp;

/**
 * 
 */
public class CampDaoImplementation implements CampDaoInterface {
	
	private Map<String, Camp> camps;

	/**
	 * @param camps
	 */
	public CampDaoImplementation(Map<String, Camp> camps) {
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
