package main;

// import controllers.SessionController;
// import controllers.DataTransferController;
// import controllers.StaffController;
// import controllers.StudentController;
// import controllers.CommitteeController;

// import interfaces.dao.CurrentUserDao;
// import dao.CurrentUserDaoImpl;

// import models.User;

public class CAMs {

	private CAMs() {
	}

	public static void main(String[] args) {

		System.out.println("CAMs main() Testing");
		
		// do {
		// 	DataTransferController.importData();
		// 	SessionController.startSession();
		// 	CurrentUserDao currentUser;
		// 	User user = currentUser.getCurrentUser();
		// 	if (user == null) break;
			
		// 	switch (user.getRole()) {
		// 	case STAFF:
		// 		new StudentController().start();
		// 		break;
		// 	case STUDENT:
		// 		new StudentController().start();
		// 		break;
		// 	case COMMITTEE:
		// 		new StudentController().start();
		// 		break;
		// 	}
			
		// 	SessionController.endSession();
		// 	DataTransferController.exportData();
		// } while (true);
	}
}
