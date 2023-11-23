package controllers;

import java.util.Scanner;

import interfaces.AuthServiceable;

import services.AuthService;

public class SessionController {
	
	private static final Scanner sc = new Scanner(System.in);
	
	private static final AuthServiceable authService = new AuthService();
	
	
	private SessionController() {}
	
	public static void startSession() {
		
		int choice;
		
		do {
			System.out.println("1. Log in");
			System.out.println("2. Quit");
			System.out.print("\nChoice :");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				authService.login();
				break;
			case 2:
				break;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (choice != 2);
	}
	
	public static void endSession() {
		
		authService.logout();
	}
}
