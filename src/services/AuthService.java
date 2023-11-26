package services;

import java.util.Map;
import java.util.Scanner;

import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;
import dao.StudentDaoImpl;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.dao.StudentDao;
import interfaces.services.AuthServiceable;
import interfaces.services.ChangePasswordServiceable;
import models.CommitteeMember;
import models.Staff;
import models.Student;
import models.User;  

/**
 * The {@code AuthService} class provides authentication services for the CAMs application.
 * It allows users to log in by validating user credentials and log out.
 * 
 * <p>The class utilizes DAOs for accessing user information and provides password change functionality through a {@code ChangePasswordServiceable}.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.AuthServiceable
 * @see interfaces.services.ChangePasswordServiceable
 * @see dao.CommitteeMemberDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see dao.StaffDaoImpl
 * @see dao.StudentDaoImpl
 * @see models.CommitteeMember
 * @see models.Staff
 * @see models.Student
 * @see models.User
 */
 
public class AuthService implements AuthServiceable {
    
    private static final Scanner sc = new Scanner(System.in);
    
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    private static final StaffDao staffDao = new StaffDaoImpl();

    private static final StudentDao studentDao = new StudentDaoImpl();
    
    private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();

	private static final ChangePasswordServiceable changePasswordService = new ChangePasswordService();

	/**
     * Prompts the user to enter their credentials and validates the user.
     * If successful, sets the current user and allows password change if the password is the default.
     */
    public void login(){
        
    	User currentUser;
    	
        System.out.print("Enter user ID: ");
        
		String userID = sc.nextLine();
		
		System.out.print("Enter password: ");
		
		String password = sc.nextLine();
		
		currentUser = validateUser(userID, password);
		
		if (currentUser == null) {
			System.out.println("Invalid user ID or password");
			return;
		}

		currentUserDao.setCurrentUser(currentUser);
		while (currentUser.getPassword().equals("password"))
			changePasswordService.changePassword();
    }

    /**
     * Logs out the current user by setting it to null.
     */
    public void logout(){
    	currentUserDao.setCurrentUser(null);
    }
    
    
	/** 
	 * @param userID The username entered by the user
	 * @param password The password entered by the user
	 * 
	 * @return The user object if validation is successful, null otherwise.
	 */
	private User validateUser(String userID, String password) {
    	
    	User user = null;
    	Map<String, Staff> staffData = staffDao.getStaffs();
    	Map<String, Student> studentData = studentDao.getStudents();
    	Map<String, CommitteeMember> committeeMemberData = committeeMemberDao.getCommitteeMembers();
    	
    	if (staffData.containsKey(userID))
    		user = staffData.get(userID);
    	
    	else if (studentData.containsKey(userID))
    		user = studentData.get(userID);
    	
    	else if (committeeMemberData.containsKey(userID))
    		user = committeeMemberData.get(userID);
    	
    	if (user == null) return null;
    	
    	if (user.getPassword().equals(password)) return user;
    	
    	return null;
    }
}
