package controllers;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;
import interfaces.views.ProfileViewable;
import services.ChangePasswordService;
import utils.InputUtil;
import utils.PrintUtil;
import views.ProfileView;

/**
 * The {@code AbstractUserController} class serves as the base class for controllers managing user interactions.
 * 
 * <p>This abstract class defines the structure for user controllers, with abstract methods to be implemented by subclasses.</p>
 * 
 * <p>The class utilizes DAOs for accessing current user and camp information, services for password changes, and views for displaying user profiles.</p>
 * 
 * <p>Note: Subclasses are expected to implement the {@code start}, {@code viewAllCamps}, and {@code viewEnquiries} methods.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see dao.CampDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see services.ChangePasswordService
 * @see views.ProfileView
 * @see interfaces.dao.CampDao
 * @see interfaces.dao.CurrentUserDao
 * @see interfaces.services.ChangePasswordServiceable
 * @see interfaces.views.ProfileViewable
 * @see utils.InputUtil
 * @see utils.PrintUtil
 */
abstract class AbstractUserController {
	
	protected static CurrentUserDao currentuserDao = new CurrentUserDaoImpl();

	protected static CampDao campDao = new CampDaoImpl();
	
	protected static ProfileViewable profileView = new ProfileView();
	
	protected static ChangePasswordServiceable changePasswordService = new ChangePasswordService();
	
	/**
     * Abstract method to be implemented by subclasses to start the user interaction.
     */	
	public abstract void start();
	
    /**
     * Abstract method to be implemented by subclasses to view all camps.
     */
	protected abstract void viewAllCamps();
	
    /**
     * Abstract method to be implemented by subclasses to view user enquiries.
     */	
	protected abstract void viewEnquiries();

	/**
     * Displays the user profile and provides options to change the password or go back.
     */
	protected void viewProfile() {
			
		profileView.view();
		do {
			System.out.println("1. Change password");
			System.out.println("2. Back");
			
			switch (InputUtil.choice()) {
			case 1:
				if (changePassword()) return;
				break;
			case 2:
				return;
			default:
				PrintUtil.invalid("choice");
			}
			
		} while (true);
	}
	
    /**
     * Allows the user to change their password. Returns true if the password change is successful, false otherwise.
     * 
     * @return true if the password change is successful, false otherwise.
     */	
	protected boolean changePassword() {

		int choice;
		
		do {
			if(changePasswordService.changePassword()) return true;
			
			do {
				System.out.println("1. Try again");			
				System.out.println("2. Back");	

				choice = InputUtil.choice();		
				if (choice == 1) break;
				
				if (choice == 2) return false;
				
				PrintUtil.invalid("choice");
			} while (true);
		} while(true);
	}
}
