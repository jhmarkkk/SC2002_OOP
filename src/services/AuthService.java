package services;

import java.util.Scanner;

import interfaces.services.AuthServiceable;  

public class AuthService implements AuthServiceable {
    
    private static final Scanner sc = new Scanner(System.in);

    public void login(){
        // TODO Auto-generated method stub

        // User Input Username & Password
        System.out.println("User ID >>> ");
		String userID = sc.nextLine();
		System.out.println("Password >>> ");
		String password = sc.nextLine();

		// System.out.println("User ID >>> " + userID);
		// System.out.println("Password >>> " + password);
        
        // Extract
    }

    public void logout(){
        // TODO Auto-generated method stub
    }

}
