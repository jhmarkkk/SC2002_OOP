/**
 * 
 */
package dao;

import models.User;

/**
 * 
 */
public interface CurrentUserDaoInterface {
	public User getCurrentUser();
	public void setCurrentUser(User currentUser);
}
