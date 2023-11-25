package controllers;

import java.util.Scanner;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;
import interfaces.views.ProfileViewable;

import services.ChangePasswordService;

import views.ProfileView;


abstract class AbstractUserController {
	
	protected static Scanner sc = new Scanner(System.in);
	
	protected static CurrentUserDao currentuserDao = new CurrentUserDaoImpl();
	
	protected static ProfileViewable profileView = new ProfileView();
	
	protected static ChangePasswordServiceable changePasswordService = new ChangePasswordService();
	
	public abstract void start();
	
	protected abstract void viewAllCamps();
	
	protected abstract void viewEnquiries();
	
	protected void viewProfile() {
		
		int choice;
		
		profileView.view();
		
		do {
			System.out.println("1. Change password");
			System.out.println("2. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				if (changePassword()) return;
				break;
			case 2:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while (true);
	}
	
	protected boolean changePassword() {

		int choice;
		
		do {
			if(changePasswordService.changePassword()) return true;
			
			do {
				System.out.println("1. Try again");			
				System.out.println("2. Back");
				System.out.print("\nChoice: ");
				
				choice = sc.nextInt();
				
				System.out.println();
				
				if (choice == 1) break;
				
				if (choice == 2) return false;
				
				System.out.println("Invalid choice. Please choose again.");
			} while (true);
		} while(true);
	}
}
