/**
 * 
 */
package dao;

import models.User;

/**
 * 
 */
public class CurrentUserDaoImplementation implements CurrentUserDaoInterface {
	
	private User currentUser;

	/**
	 * @param currentUser
	 */
	public CurrentUserDaoImplementation(User currentUser) {
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
