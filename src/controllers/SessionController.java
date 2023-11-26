package controllers;

import dao.CurrentUserDaoImpl;
import interfaces.dao.CurrentUserDao;
import interfaces.services.AuthServiceable;
import services.AuthService;
import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code SessionController} class manages user sessions in the CAMs application.
 * 
 * <p>It provides methods for starting and ending user sessions, allowing users to log in and log out.</p>
 * 
 * <p>
 * The class uses an implementation of the {@code AuthServiceable} interface for handling authentication operations,
 * and a {@code CurrentUserDao} for managing the current user in the session.
 * </p>
 * 
 * <p>Note: The class assumes a loop where users can choose to log in or quit.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see AuthServiceable
 * @see CurrentUserDao
 * @see CurrentUserDaoImpl
 * @see services.AuthService
 * @see utils.InputUtil
 * @see utils.PrintUtil
 */
public class SessionController {
	
	private static final AuthServiceable authService = new AuthService();
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

	private SessionController() {}
	
    /**
     * Starts a user session, allowing users to log in or quit.
     * 
     * <p>
     * Users can log in using the {@code AuthService} and the {@code login} method.
     * If the login is successful, the current user is stored using the {@code CurrentUserDao}.
     * </p>
     * 
     * <p>
     * The method assumes a loop where users can choose to log in or quit until a valid choice is made.
     * </p>
     */	
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
	
    /**
     * Ends the user session by logging out.
     * 
     * <p>The {@code logout} method from the {@code AuthService} is used to perform the logout operation.</p>
     */	
	public static void endSession() {
		
		authService.logout();
	}
}
