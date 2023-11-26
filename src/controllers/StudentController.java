package controllers;

import enums.SortType;

import interfaces.services.AttendCampServiceable;
import interfaces.services.EnquiryServiceable;
import interfaces.views.CampViewable;
import interfaces.views.EnquiryViewable;

import models.Student;

import services.StudentAttendCampService;
import services.StudentEnquiryService;

import utils.InputUtil;
import utils.PrintUtil;

import views.StudentAllCampView;
import views.RegisteredCampView;
import views.StudentEnquiryView;

/**
 * The {@code StudentController} class handles the student-specific user interface and interactions in the CAMs application. 
 * It extends the {@code AbstractUserController} class and provides functionality for students to view their profile, change password, view all camps, view camps they are registered for, view their enquiries, create enquiries, register for camps, withdraw from camps, edit enquiries, and delete enquiries.
 * 
 * <p>This class uses various service implementations for handling specific operations related to student activities.</p>
 * 
 * <p>Note: Subclasses are expected to implement specific methods for sorting views, registering for camps, withdrawing from camps, creating, editing, and deleting enquiries.</p>
 * 
 * @author Chua Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see AbstractUserController
 * @see StudentAttendCampService
 * @see StudentEnquiryService
 * @see interfaces.views.CampViewable
 * @see interfaces.views.EnquiryViewable
 * @see interfaces.services.AttendCampServiceable
 * @see interfaces.services.EnquiryServiceable
 * @see models.Student
 * @see utils.InputUtil
 * @see utils.PrintUtil
 * @see views.StudentAllCampView
 * @see views.RegisteredCampView
 * @see views.StudentEnquiryView
 */
public class StudentController extends AbstractUserController {

	protected static CampViewable campView;

	protected static EnquiryViewable enquiryView;

	protected static AttendCampServiceable attendCampService = new StudentAttendCampService();

	protected static EnquiryServiceable enquiryService = new StudentEnquiryService();

    /**
     * Initiates the main menu for students, allowing them to access specific features.
     */	
	@Override
	public void start() {

		do {
			PrintUtil.header("Main Menu");
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View my registered camps");
			System.out.println("4. View my enquiries");
			System.out.println("5. Create enquiry");
			System.out.println("6. Log out");

			switch (InputUtil.choice()) {
				case 1:
					viewProfile();
					break;
				case 2:
					viewAllCamps();
					break;
				case 3:
					viewRegisteredCamps();
					break;
				case 4:
					viewEnquiries();
					break;
				case 5:
					createEnquiry();
					break;
				case 6:
					return;
				default:
					PrintUtil.invalid("choice");
			}

			if (currentuserDao.getCurrentUser() == null) return;

		} while (true);
	}

    /**
     * Displays all camps, allowing students to sort them based on various criteria.
     */	
	@Override
	protected void viewAllCamps() {

		SortType type = SortType.NAME;

		campView = new StudentAllCampView();

		do {
			campView.sortView(type);
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Register for camp");
			System.out.println("6. Back");

			switch (InputUtil.choice()) {
				case 1:
					type = SortType.DATES;
					break;
				case 2:
					type = SortType.CLOSING_DATE;
					break;
				case 3:
					type = SortType.LOCATION;
					break;
				case 4:
					type = SortType.FACULTY;
					break;
				case 5:
					registerForCamp();
					return;
				case 6:
					return;
				default:
					PrintUtil.invalid("choice");
			}

		} while (true);
	}

    /**
     * Displays enquiries created by the current student, allowing them to perform various actions.
     */	
	@Override
	protected void viewEnquiries() {

		Student currentUser = (Student)currentuserDao.getCurrentUser();
		
		if (currentUser.getEnquiries().isEmpty()) {
			System.out.println("No created equiry");
			return;
		}

		enquiryView = new StudentEnquiryView();
		do {
			enquiryView.view();
			System.out.println("1. Edit enquiry");
			System.out.println("2. Delete enquiry");
			System.out.println("3. Back");

			switch (InputUtil.choice()) {
				case 1:
					editEnquiry();
					break;
				case 2:
					deleteEnquiry();
					break;
				case 3:
					return;
				default:
					PrintUtil.invalid("choice");
			}
		} while (true);
	}

    /**
     * Displays camps registered by the current student, allowing them to withdraw from camps.
     */	
	protected void viewRegisteredCamps() {

		Student currentUser = (Student)currentuserDao.getCurrentUser();
		SortType type = SortType.NAME;
		
		if (currentUser.getRegisteredCamps().isEmpty()) {
			System.out.println("No registered camp");
			return;
		}

		campView = new RegisteredCampView();
		do {
			campView.sortView(type);
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Withdraw from camp");
			System.out.println("6. Back");

			switch (InputUtil.choice()) {
				case 1:
					type = SortType.DATES;
					break;
				case 2:
					type = SortType.CLOSING_DATE;
					break;
				case 3:
					type = SortType.LOCATION;
					break;
				case 4:
					type = SortType.FACULTY;
					break;
				case 5:
					withdrawFromCamp();
					break;
				case 6:
					return;
				default:
					PrintUtil.invalid("choice");
			}

		} while (true);
	}

    /**
     * Registers the student for a camp.
     */	
	protected void registerForCamp() {

		attendCampService.register();
	}

    /**
     * Withdraws the student from a registered camp.
     */
	protected void withdrawFromCamp() {

		attendCampService.withdraw();
	}

    /**
     * Creates a new enquiry.
     */	
	protected void createEnquiry() {

		enquiryService.create();
	}

    /**
     * Edits an existing enquiry.
     */	
	protected void editEnquiry() {

		enquiryService.edit();
	}

    /**
     * Deletes an existing enquiry.
     */	
	protected void deleteEnquiry() {

		enquiryService.delete();
	}

}
