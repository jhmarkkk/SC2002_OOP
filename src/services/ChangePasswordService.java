package services;

import java.util.Scanner;

import controllers.SessionController;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;
import models.User;

public class ChangePasswordService implements ChangePasswordServiceable{

	private static final Scanner sc = new Scanner(System.in);
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
    public boolean changePassword(){

    	String oldPassword, newPassword;
    	User currentUser = currentUserDao.getCurrentUser();
    	
    	System.out.println("Changing password\n");
    	System.out.print("Enter old password: ");
    	
    	oldPassword = sc.nextLine();
    	
    	System.out.print("Enter new password: ");
    	
    	newPassword = sc.nextLine();
    	
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
    	
    	System.out.println("Password sucessfully changed");
    	SessionController.endSession();
    	return true;
    }
}
