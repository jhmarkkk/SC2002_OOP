package main;

import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;
import dao.StudentDaoImpl;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.dao.StudentDao;
import models.Staff;
import models.User;

/**
 * The {@code CAMs} class serves as the entry point for the Course Administration Management System (CAMs) application.
 * It initializes the necessary DAOs and controllers, imports data, and starts user sessions based on their roles.
 * 
 * <p>The application supports user roles such as Staff, Student, and Committee, each with its respective controller.</p>
 * 
 * <p>The main method controls the flow of the application by importing data, setting the current user, determining the user's role, and initiating the corresponding controller.</p>
 * 
 * <p>Note: The application assumes a loop where users can interact with the system until they choose to exit.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see SessionController
 * @see DataTransferController
 * @see StaffController
 * @see StudentController
 * @see CommitteeController
 * @see CurrentUserDaoImpl
 * @see StaffDaoImpl
 * @see StudentDaoImpl
 * @see interfaces.dao.CurrentUserDao
 * @see interfaces.dao.StaffDao
 * @see interfaces.dao.StudentDao
 * @see models.Staff
 * @see models.User
 */
public class CAMs {

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	private static final StaffDao staffDao = new StaffDaoImpl();
	private static final StudentDao studentDao = new StudentDaoImpl();
	
	private CAMs() {}

	public static void main(String[] args) {

		do {
			DataTransferController.importData();

			SessionController.startSession();
			User user = currentUserDao.getCurrentUser();
			if (user == null)
				break;

			switch (user.getRole()) {
			case STAFF:
				new StaffController().start();
				break;
			case STUDENT:
				new StudentController().start();
				break;
			case COMMITTEE:
				new CommitteeController().start();
				break;
			}

			SessionController.endSession();
			DataTransferController.exportData();
		} while (true);
	}
}
