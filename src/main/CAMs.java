package main;

import services.StaffDataService;
import services.StudentDataService;
import services.StaffDataService;
import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import enums.Role;

// import interfaces.CurrentUserDao;

import models.User;

public class CAMs {
	public static void main(String[] args) {
		
		do {
			DataTransferController.importData();
			SessionController.startSession();

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
