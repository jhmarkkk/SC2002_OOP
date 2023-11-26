package main;

import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;

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
 * @see controllers.SessionController
 * @see controllers.DataTransferController
 * @see controllers.StaffController
 * @see controllers.StudentController
 * @see controllers.CommitteeController
 * @see dao.CurrentUserDaoImpl
 * @see dao.StaffDaoImpl
 * @see dao.StudentDaoImpl
 * @see interfaces.dao.CurrentUserDao
 * @see interfaces.dao.StaffDao
 * @see interfaces.dao.StudentDao
 * @see models.Staff
 * @see models.User
 */
public class CAMs {

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private CAMs() {}

	
    /**
     * The main method of the CAMs application.
     *
     * @param args the command-line arguments.
     */
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
