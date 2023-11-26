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

public class CAMs {

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	private static final StaffDao staffDao = new StaffDaoImpl();
	private static final StudentDao studentDao = new StudentDaoImpl();
	
	private CAMs() {}

	public static void main(String[] args) {

		do {
			DataTransferController.importData();

			currentUserDao.setCurrentUser(studentDao.getStudents().get("KOH1"));
			// SessionController.startSession();
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
			// DataTransferController.exportData();
			break;
		} while (true);
	}
}
