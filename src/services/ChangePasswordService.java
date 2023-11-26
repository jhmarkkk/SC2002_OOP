package services;

import controllers.SessionController;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;

import models.User;

import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code ChangePasswordService} class provides functionality to change the password for the currently logged-in user.
 * It ensures the security and validity of the password change process.
 * 
 * <p>The class utilizes the {@code SessionController} to manage the user's session and the {@code InputUtil} and {@code PrintUtil} utility classes for user input and output.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see controllers.SessionController
 * @see dao.CurrentUserDaoImpl
 * @see interfaces.dao.CurrentUserDao
 * @see interfaces.services.ChangePasswordServiceable
 * @see models.User
 * @see utils.InputUtil
 * @see utils.PrintUtil
 */
public class ChangePasswordService implements ChangePasswordServiceable{

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
    
    /**
     * Attempts to change the password for the currently logged-in user.
     *
     * @return {@code true} if the password change is successful; {@code false} otherwise.
     */
	public boolean changePassword(){

    	String oldPassword, newPassword;
    	User currentUser = currentUserDao.getCurrentUser();
    	
		PrintUtil.header("Changing Password");
    	oldPassword = InputUtil.nextString("Enter old password");
    	newPassword = InputUtil.nextString("Enter new password");
    	
    	if (!currentUser.getPassword().equals(oldPassword)) {
    		System.out.println("\n> Incorrect old password");
    		return false;
    	}
    	
    	if (currentUser.getPassword().equals(newPassword)) {
    		System.out.println("\n> Old and new passwords are the same");
    		return false;
    	}
    	
    	if (newPassword.isBlank()) {
    		System.out.println("\n> Invalid new password");
    		return false;
    	}
    	
    	if (newPassword.length() < 8) {
    		System.out.println("\n> Invalid new password");
    		return false;
    	}
    	
		currentUser.setPassword(newPassword);
    	System.out.println("\n> Password sucessfully changed");
    	SessionController.endSession();
    	return true;
    }
}
