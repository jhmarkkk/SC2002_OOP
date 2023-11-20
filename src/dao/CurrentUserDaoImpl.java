/**
 * 
 */
package dao;

import models.User;

/**
 * 
 */
public class CurrentUserDaoImpl implements CurrentUserDao {
	
	private User currentUser;

	/**
	 * @param currentUser
	 */
	public CurrentUserDaoImpl(User currentUser) {
		this.currentUser = currentUser;
	}

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
		this.currentUser = currentUser;
	}
}
