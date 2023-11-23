package main;

<<<<<<< Updated upstream
=======
import services.StaffDataService;
import services.StudentDataService;
import services.StaffDataService;
import services.CampDataService;
>>>>>>> Stashed changes
import controllers.SessionController;
import controllers.DataTransferController;
import controllers.StaffController;
import controllers.StudentController;
import controllers.CommitteeController;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;

import models.User;

/**
 * The main class executing the Camp Application and Management System (CAMs).
 * This class handles the initialisation of data, log-in and starting the
 * appropriate session based on the role of logged-in user.
 * 
 * @author Chua Shan Hong
 *
 */
public class CAMs {
	
	
	private CAMs() {}
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
<<<<<<< Updated upstream
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
=======
//		do {
//			DataTransferController.importData();
//			SessionController.startSession();
//			CurrentUserDao currentUser = new CurrentUserDaoImpl();
//			User user = currentUser.getCurrentUser();
//			if (user == null) break;
//			
//			switch (user.getRole()) {
//			case STAFF:
//				new StudentController().start();
//				break;
//			case STUDENT:
//				new StudentController().start();
//				break;
//			case COMMITTEE:
//				new StudentController().start();
//				break;
//			}
//			
//			SessionController.endSession();
//			DataTransferController.exportData();
//		} while (true);

		System.out.println("main testing");

//		TESTING SERVICES
//		StudentDataService studentDataService = new StudentDataService();
//		String studentFilePath = "StudentList.csv";
//		studentDataService.importing(studentFilePath);
		
		CampDataService campDataService = new CampDataService();
		String campFilePath = "EnquiryList.csv";
		campDataService.importing(campFilePath);
		
>>>>>>> Stashed changes
	}
}
