package interfaces.dao;

import models.User;

/**
 * The {@code CurrentUserDao} interface defines methods for accessing and manipulating the current user information in the CAMs system. 
 * Implementations of this interface provide the underlying data access logic for retrieving and updating the current user.
 * 
 * <p>It includes methods to get the current user and set the current user. 
 * The current user is represented by a {@link User} object, containing user-specific information such as ID, name, and role.</p>
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see User
 */
public interface CurrentUserDao {

    /**
     * Retrieves the current user.
     * 
     * @return The {@link User} object representing the current user.
     */	
	public User getCurrentUser();
	
	/**
     * Sets the current user.
     * 
     * @param currentUser The new {@link User} object representing the new current user.
     */
	public void setCurrentUser(User currentUser);
}
