package controllers;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ChangePasswordServiceable;
import interfaces.views.ProfileViewable;

import services.ChangePasswordService;

import utils.InputUtil;
import utils.PrintUtil;

import views.ProfileView;


abstract class AbstractUserController {
	
	protected static CurrentUserDao currentuserDao = new CurrentUserDaoImpl();

	protected static CampDao campDao = new CampDaoImpl();
	
	protected static ProfileViewable profileView = new ProfileView();
	
	protected static ChangePasswordServiceable changePasswordService = new ChangePasswordService();
	
	public abstract void start();
	
	protected abstract void viewAllCamps();
	
	protected abstract void viewEnquiries();
	
	protected void viewProfile() {
			
		profileView.view();
		do {
			System.out.println("1. Change password");
			System.out.println("2. Back");
			
			switch (InputUtil.choice()) {
			case 1:
				if (changePassword()) return;
				break;
			case 2:
				return;
			default:
				PrintUtil.invalid("choice");
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

				choice = InputUtil.choice();		
				if (choice == 1) break;
				
				if (choice == 2) return false;
				
				PrintUtil.invalid("choice");
			} while (true);
		} while(true);
	}
}
