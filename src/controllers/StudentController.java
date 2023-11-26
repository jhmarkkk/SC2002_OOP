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

public class StudentController extends AbstractUserController {

	protected static CampViewable campView;

	protected static EnquiryViewable enquiryView;

	protected static AttendCampServiceable attendCampService = new StudentAttendCampService();

	protected static EnquiryServiceable enquiryService = new StudentEnquiryService();

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

	protected void registerForCamp() {

		attendCampService.register();
	}

	protected void withdrawFromCamp() {

		attendCampService.withdraw();
	}

	protected void createEnquiry() {

		enquiryService.create();
	}

	protected void editEnquiry() {

		enquiryService.edit();
	}

	protected void deleteEnquiry() {

		enquiryService.delete();
	}

}
