package dao;

import interfaces.dao.CurrentUserDao;

import models.User;

/**
 * The {@code CurrentUserDaoImpl} class is an implementation of the {@link CurrentUserDao} interface.
 * It provides methods to manage and access the current user stored in the DAO.
 * 
 * @author Chin Jun Hao, Mark
 * @version 1.0
 * @since 1.0
 * 
 * @see CurrentUserDao
 */
public class CurrentUserDaoImpl implements CurrentUserDao {
	
	private static User currentUser;

    /**
     * Retrieves the current user stored in the DAO.
     * 
     * @return the current user.
     */
	public User getCurrentUser() {
		return currentUser;
	}

    /**
     * Sets the current user in the DAO.
     * 
	 * @param currentUser the current user to set.
     */	
	public void setCurrentUser(User currentUser) {
		CurrentUserDaoImpl.currentUser = currentUser;
	}
}
