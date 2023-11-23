package controllers;

import java.util.Scanner;

import enums.Role;

import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;
import services.ChangePasswordService;
import views.ProfileView;
import interfaces.views.ProfileViewable;

import models.User;

import services.ChangePasswordService;

abstract class AbstractUserController {
	
	protected static Scanner sc = new Scanner(System.in);
	
	protected CurrentUserDao currentUserDao;
	
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
				changePassword();
				break;
			case 2:
				break;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while (choice != 2);
	}
	
	protected void changePassword() {

		int choice;
		
		do {
			if(changePasswordService.changePassword()) break;
			
			do {
				System.out.println("1. Try again");			
				System.out.println("2. Back");
				
				choice = sc.nextInt();
				if (choice == 1 || choice == 2) break;
				
				System.out.println("Invalid choice. Please choose again.");
			} while (true);
		} while(choice != 2);
	}
}
