package controllers;

import java.util.Scanner;

import interfaces.*;
import services.*;
import views.*;

/**
 * This {@link StaffController} class is responsible for handling the
 * staff specific user interface and interactions. It extends the
 * {@link UserController} class and provides functionality for staff to
 * view his/her profile, change password, view all camps, view camps
 * created by him/herself, view attendees within aforementioned camps,
 * create camps, edit camps, delete camps, view suggestions, approve
 * suggestions, view enquiry, reply enquiry and generate report
 * @author Chua Shan Hong
 *
 */
public class StaffController extends AbstractUserController {
	
	protected static final AllCampViewable allCampView = new StaffAllCampView;

	@Override
	public void start() {
		int choice;
		
		do {
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View created camps");
			System.out.println("4. Create new camp");
			System.out.println("5. View suggestions");
			System.out.println("6. View enquiries");
			System.out.println("7. Generate Report");
			System.out.println("8. Log out");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				viewProfile();
				break;
			case 2:
				viewAllCamps();
				break;
			case 3:
				viewCreatedCamps();
				break;
			case 4:
				createCamp();
				break;
			case 5:
				viewSuggestions();
				break;
			case 6:
				viewEnquiries();
				break;
			case 7:
				generateReport();
				break;
			case 8:
				break;

			default:
				System.out.println("Invalid choice. Please choose again.");
			}

		} while(choice != 8);

	}

	@Override
	protected void viewAllCamps() {
		
		int choice;
		
		allCampView.view();
		
		do {
			System.out.println("1. Sort by camp name");
			System.out.println("2. Sort by camp dates");
			System.out.println("3. Sort by camp registration closing date");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Sort by camp staff-in-charge");
			System.out.println("6. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				changePassword();
				break;
			case 2:
				break;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
			
		} while (choice == 2);

	}
	
	@Override
	protected void viewEnquiries() {
		// TODO Auto-generated method stub
		
	}
	
	protected void viewCreatedCamps() {
		// TODO Auto-generated method stub

	}
	
	protected void viewAttendees() {
		// TODO Auto-generated method stub

	}
	
	protected void createCamp() {
		// TODO Auto-generated method stub

	}
	
	protected void editCamp() {
		// TODO Auto-generated method stub

	}
	
	protected void deleteCamp() {
		// TODO Auto-generated method stub

	}
	
	protected void viewSuggestions() {
		// TODO Auto-generated method stub

	}
	
	protected void approveSuggestion() {
		// TODO Auto-generated method stub

	}
	
	protected void replyEnquiry() {
		// TODO Auto-generated method stub

	}
	
	protected void generateReport() {
		// TODO Auto-generated method stub

	}
	
}
