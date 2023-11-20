package views;

import interfaces.ProfileViewable;

public class ProfileView implements ProfileViewable {
    public void view() {
        System.out.println("===== User Profile =====");
        System.out.printf("User ID: %s\n", User.getUserID());
        System.out.printf("Name: %s\n", User.getName());
        System.out.printf("Faculty: %s\n", User.getFaculty());
    }
}