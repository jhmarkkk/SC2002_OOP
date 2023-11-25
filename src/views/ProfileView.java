package views;

import enums.Role;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.views.ProfileViewable;

import models.User;
import models.CommitteeMember;

public class ProfileView implements ProfileViewable {
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
    public void view() {
    	
        User currentUser = currentUserDao.getCurrentUser();
        CommitteeMember committeeMember;
        
        System.out.println("===== User Profile =====");
        System.out.printf("User ID: %s\n", currentUser.getUserID());
        System.out.printf("Name: %s\n", currentUser.getName());
        System.out.printf("Faculty: %s\n", currentUser.getFaculty());
        System.out.printf("Role: %s",  Role.toString(currentUser.getRole()));
        
        if (currentUser.getRole() == Role.COMMITTEE) {
            committeeMember = (CommitteeMember) currentUser;
            System.out.printf(" of %s\n", committeeMember.getFacilitatingCamp());
            System.out.printf("Points: %d\n", committeeMember.getPoints());
        }
        
        System.out.println();
    }
}