package views;

import enums.Role;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.views.ProfileViewable;

import models.CommitteeMember;
import models.User;

import utils.PrintUtil;

/**
 * The {@code ProfileView} class provides a view to display the user profile
 * information.
 * It implements the {@link ProfileViewable} interface.
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public class ProfileView implements ProfileViewable {

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    /**
     * Displays the user profile information.
     */
    public void view() {
        User currentUser = currentUserDao.getCurrentUser();
        CommitteeMember committeeMember;

        PrintUtil.header("User Profile");
        System.out.printf("%-10s: %s\n", "User ID", currentUser.getUserID());
        System.out.printf("%-10s: %s\n", "Name", currentUser.getName());
        System.out.printf("%-10s: %s\n", "Faculty", currentUser.getFaculty());
        System.out.printf("%-10s: %s", "Role", Role.toString(currentUser.getRole()));

        if (currentUser.getRole() == Role.COMMITTEE) {
            committeeMember = (CommitteeMember) currentUser;
            System.out.printf(" of %s\n", committeeMember.getFacilitatingCamp());
            System.out.printf("%-10s: %d\n", "Points", committeeMember.getPoints());
        }
        System.out.println();
    }
}