/**
 * 
 */
package dao;

import interfaces.dao.CurrentUserDao;
import models.User;

/**
 * 
 */
public class CurrentUserDaoImpl implements CurrentUserDao {
	
	private static User currentUser;

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		CurrentUserDaoImpl.currentUser = currentUser;
	}
}
