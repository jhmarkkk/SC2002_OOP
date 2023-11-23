package main;

import services.StaffDataService;
import services.StudentDataService;
import services.StaffDataService;
import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import interfaces.CurrentUserDao;

import models.User;

public class CAMs {
	
	
	private CAMs() {}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		do {
			DataTransferController.importData();
			SessionController.startSession();
			CurrentUserDao currentUser;
			User user = currentUser.getCurrentUser();
			if (user == null) break;
			
			switch (user.getRole()) {
			case STAFF:
				new StudentController().start();
				break;
			case STUDENT:
				new StudentController().start();
				break;
			case COMMITTEE:
				new StudentController().start();
				break;
			}
			
			SessionController.endSession();
			DataTransferController.exportData();
		} while (true);

		//System.out.println("main testing");

		//TESTING SERVICES
		// StudentDataService studentDataService = new StudentDataService();
		// String studentFilePath = "StudentList.csv";
		// studentDataService.importing(studentFilePath);

		// StaffDataService staffDataService = new StaffDataService();
		// String staffFilePath = "StaffList.csv";
		// staffDataService.importing(staffFilePath);
	}
}
