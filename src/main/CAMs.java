package main;

import services.StaffDataService;
import services.StudentDataService;
import services.CampDataService;

import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;

import services.StudentEnquiryService;

import models.User;


public class CAMs {

	private CAMs() {
	}

	public static void main(String[] args) {

		System.out.println("CAMs main() Testing");

		// trying exporter = new trying();
		// // Specify the path to your CSV file
		// String filePath = "testing.csv";
		// // Export the array to the CSV file
		// exporter.exporting(filePath);
		// exporter.importing(filePath);
		// System.out.println("Array exported to CSV successfully.");

		// StudentDataService studentDataService = new StudentDataService();
		// String testFilePath = "testing.csv";
		// studentDataService.exporting(testFilePath);
		
		//TESTING STUDENT SERVICES
		StudentDataService studentDataService = new StudentDataService();
		String inStudentFilePath = "data/StudentList.csv";
		studentDataService.importing(inStudentFilePath);
		String outStudentFilePath = "data/StudentListOut.csv";
		studentDataService.exporting(outStudentFilePath);

		//TESTING ENQUIRIES
		StudentEnquiryService enquiry = new StudentEnquiryService();
		enquiry.create();

		// TESTING STAFF SERVICES
		// StaffDataService staffDataService = new StaffDataService();
		// String inStaffFilePath = "data/StaffList.csv";
		// staffDataService.importing(inStaffFilePath);
		// String outStaffFilePath = "data/StaffListOut.csv";
		// staffDataService.exporting(outStaffFilePath);

	}
}
