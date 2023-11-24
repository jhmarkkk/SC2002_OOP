package controllers;

import java.util.Scanner;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.services.AuthServiceable;

import services.AuthService;

public class SessionController {
	
	private static final Scanner sc = new Scanner(System.in);
	
	private static final AuthServiceable authService = new AuthService();
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

	private SessionController() {}
	
	public static void startSession() {
		
		int choice;
		
		do {
			System.out.println("Start");
			System.out.println("1. Log in");
			System.out.println("2. Quit");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				authService.login();
				if (currentUserDao.getCurrentUser() != null) return;
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (true);
	}
	
	public static void endSession() {
		
		authService.logout();
	}
}
