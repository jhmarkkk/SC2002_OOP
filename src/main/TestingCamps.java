package main;

// import controllers.SessionController;
// import controllers.DataTransferController;
// import controllers.StaffController;
// import controllers.StudentController;
// import controllers.CommitteeController;

import models.User;
import services.CommitteeDataService;
import services.StaffDataService;
import services.StudentDataService;

public class TestingCamps {

	private TestingCamps() {
	}

	public static void main(String[] args) {

		System.out.println("TestingCamps main() Testing");
		
		//TESTING COMMITTEE DATA SERVICES
		CommitteeDataService committeeDataService = new CommitteeDataService();
		String inCommitteeFilePath = "data/CommitteeList.csv";
		committeeDataService.importing(inCommitteeFilePath);
		String outCommitteeFilePath = "data/CommitteeListOut.csv";
		committeeDataService.exporting(outCommitteeFilePath);
		
		//TESTING STUDENT DATA SERVICES
		StudentDataService studentDataService = new StudentDataService();
		String inStudentFilePath = "data/StudentList.csv";
		studentDataService.importing(inStudentFilePath);
		String outStudentFilePath = "data/StudentListOut.csv";
		studentDataService.exporting(outStudentFilePath);
		
		//TESTING STAFF DATA SERVICES
		StaffDataService staffDataService = new StaffDataService();
		String inStaffFilePath = "data/StaffList.csv";
		staffDataService.importing(inStaffFilePath);
		String outStaffFilePath = "data/StaffListOut.csv";
		staffDataService.exporting(outStaffFilePath);
	}
}
