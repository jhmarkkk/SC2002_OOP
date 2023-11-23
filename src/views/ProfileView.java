package views;

import enums.Role;
import interfaces.views.ProfileViewable;
import models.User;
import models.CommitteeMember;
import interfaces.dao.CurrentUserDaoInterface;

public class ProfileView implements ProfileViewable {
    public void view() {
        CurrentUserDaoInterface currentUserDao;
        User currentUser = currentUserDao.getCurrentUser();
        System.out.println("===== User Profile =====");
        System.out.printf("User ID: %s\n", currentUser.getUserID());
        System.out.printf("Name: %s\n", currentUser.getName());
        System.out.printf("Faculty: %s\n", currentUser.getFaculty());
        if (currentUser.getRole() == Role.COMMITTEE) {
        	CommitteeMember currentCommitteeMember = (CommitteeMember) currentUser;
            System.out.printf("Role: Committee of %s", currentCommitteeMember.getFacilitatingCamp());
        } else {
            System.out.printf("Role: %s",
                    currentUser.getRole().toString());
        }
    }
}