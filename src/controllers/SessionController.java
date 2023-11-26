package controllers;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;
import interfaces.services.AuthServiceable;

import services.AuthService;

import utils.InputUtil;
import utils.PrintUtil;

public class SessionController {
	
	private static final AuthServiceable authService = new AuthService();
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

	private SessionController() {}
	
	public static void startSession() {
		
		do {
			PrintUtil.header("Start");
			System.out.println("1. Log in");
			System.out.println("2. Quit");
						
			switch (InputUtil.choice()) {
			case 1:
				authService.login();
				if (currentUserDao.getCurrentUser() != null) return;
				break;
			case 2:
				return;
			default:
				PrintUtil.invalid("choice");
			}
		} while (true);
	}
	
	public static void endSession() {
		
		authService.logout();
	}
}
