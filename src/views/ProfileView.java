package views;

import interfaces.views.ProfileViewable;

public class ProfileView implements ProfileViewable {
    public void view() {
        CurrentUserDaoInterface currentUserDao;
        User currentUser = currentUserDao.getCurrentUser();
        System.out.println("===== User Profile =====");
        System.out.printf("User ID: %s\n", currentUser.getUserID());
        System.out.printf("Name: %s\n", currentUser.getName());
        System.out.printf("Faculty: %s\n", currentUser.getFaculty());
        if (currentUser.getRole().toString() == "Committee") {
            System.out.printf("Role: Committee of %s", currentUser.getFacilitatingCamp());
        } else {
            System.out.printf("Role: %s",
                    currentUser.getRole().toString());
        }
    }
}