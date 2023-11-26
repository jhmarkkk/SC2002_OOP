package services;

import controllers.SessionController;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;
import models.User;
import utils.InputUtil;
import utils.PrintUtil;

public class ChangePasswordService implements ChangePasswordServiceable{

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
    public boolean changePassword(){

    	String oldPassword, newPassword;
    	User currentUser = currentUserDao.getCurrentUser();
    	
		PrintUtil.header("Changing Password");
    	oldPassword = InputUtil.nextString("Enter old password");
    	newPassword = InputUtil.nextString("Enter new password");
    	
    	if (!currentUser.getPassword().equals(oldPassword)) {
    		System.out.println("Incorrect old password");
    		return false;
    	}
    	
    	if (currentUser.getPassword().equals(newPassword)) {
    		System.out.println("Old and new passwords are the same");
    		return false;
    	}
    	
    	if (newPassword.isBlank()) {
    		System.out.println("Invalid new password");
    		return false;
    	}
    	
    	if (newPassword.length() < 8) {
    		System.out.println("Invalid new password");
    		return false;
    	}
    	
		currentUser.setPassword(newPassword);
    	System.out.println("Password sucessfully changed");
    	SessionController.endSession();
    	return true;
    }
}
