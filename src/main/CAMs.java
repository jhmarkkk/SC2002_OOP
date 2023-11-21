package main;

import services.StaffDataService;
import services.StudentDataService;
import services.StaffDataService;

public class CAMs {
	public static void main(String[] args) {

		//System.out.println("main testing");

		//TESTING SERVICES
		// StudentDataService studentDataService = new StudentDataService();
		// String studentFilePath = "StudentList.csv";
		// studentDataService.importing(studentFilePath);

		StaffDataService staffDataService = new StaffDataService();
		String staffFilePath = "StaffList.csv";
		staffDataService.importing(staffFilePath);
	}
}
