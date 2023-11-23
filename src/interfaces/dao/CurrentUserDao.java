/**
 * 
 */
package interfaces.dao;

import models.User;

/**
 * 
 */
public interface CurrentUserDao {
	public User getCurrentUser();
	public void setCurrentUser(User currentUser);
}
