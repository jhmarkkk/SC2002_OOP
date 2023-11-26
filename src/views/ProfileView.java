package views;

import enums.Role;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.views.ProfileViewable;

import models.CommitteeMember;
import models.User;

import utils.PrintUtil;

public class ProfileView implements ProfileViewable {
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
    public void view() {
    	
        User currentUser = currentUserDao.getCurrentUser();
        CommitteeMember committeeMember;
        
        PrintUtil.header("User Profile");
        System.out.printf("%-10s: %s\n","User ID" , currentUser.getUserID());
        System.out.printf("%-10s: %s\n", "Name", currentUser.getName());
        System.out.printf("%-10s: %s\n", "Format", currentUser.getFaculty());
        System.out.printf("%-10s: %s", "Role", Role.toString(currentUser.getRole()));
        
        if (currentUser.getRole() == Role.COMMITTEE) {
            committeeMember = (CommitteeMember) currentUser;
            System.out.printf(" of %s\n", committeeMember.getFacilitatingCamp());
            System.out.printf("%-10s: %d\n", "Points", committeeMember.getPoints());
        }
    }
}