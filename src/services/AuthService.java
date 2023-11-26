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

public class AuthService implements AuthServiceable {
    
    private static final Scanner sc = new Scanner(System.in);
    
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    private static final StaffDao staffDao = new StaffDaoImpl();

    private static final StudentDao studentDao = new StudentDaoImpl();
    
    private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();

	private static final ChangePasswordServiceable changePasswordService = new ChangePasswordService();

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

    public void logout(){
        
    	currentUserDao.setCurrentUser(null);
    }
    
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
