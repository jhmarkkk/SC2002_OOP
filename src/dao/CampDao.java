/**
 * 
 */
package dao;

import java.util.Map;

import models.Camp;

/**
 * 
 */
public interface CampDao {
	public Map<String, Camp> getCamps();
	public void setCamps(Map<String, Camp> camps);
}
