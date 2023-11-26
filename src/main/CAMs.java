package main;

import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import dao.CurrentUserDaoImpl;

import interfaces.dao.CurrentUserDao;

import models.User;

public class CAMs {

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
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
