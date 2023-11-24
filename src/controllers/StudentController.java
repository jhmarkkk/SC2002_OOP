package controllers;

import enums.SortType;
import interfaces.views.CampViewable;
import interfaces.views.SortViewable;
import interfaces.services.AttendCampServiceable;
import interfaces.services.EnquiryServiceable;

import services.StudentAttendCampService;
import services.StudentEnquiryService;

import views.StudentAllCampView;
import views.RegisteredCampView;
import views.StudentEnquiryView;

public class StudentController extends AbstractUserController {

	protected static CampViewable campView;

	protected static SortViewable enquiryView;

	protected static AttendCampServiceable attendCampService = new StudentAttendCampService();

	protected static EnquiryServiceable enquiryService = new StudentEnquiryService();

	@Override
	public void start() {

		int choice;

		do {
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View registered camps");
			System.out.println("4. View enquiries");
			System.out.println("5. Create enquiry");
			System.out.println("6. Log out");
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
					System.out.println("Invalid choice. Please choose again.");
			}
		} while (true);
	}

	@Override
	protected void viewAllCamps() {

		int choice;

		campView = new StudentAllCampView();
		campView.sortView(SortType.NAME);

		do {
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Register for camp");
			System.out.println("6. Back");
			System.out.print("\nChoice: ");

			choice = sc.nextInt();

			System.out.println();

			switch (choice) {
				case 1:
					campView.sortView(SortType.DATES);
					break;
				case 2:
					campView.sortView(SortType.CLOSING_DATE);
					break;
				case 3:
					campView.sortView(SortType.LOCATION);
					break;
				case 4:
					campView.sortView(SortType.FACULTY);
					break;
				case 5:
					registerForCamp();
					break;
				case 6:
					return;
				default:
					System.out.println("Invalid choice. Please choose again.");
			}

		} while (true);
	}

	@Override
	protected void viewEnquiries() {

		int choice;

		enquiryView = new StudentEnquiryView();
		enquiryView.view();

		do {
			System.out.println("1. Create enquiry");
			System.out.println("2. Back");
			System.out.print("\nChoice: ");

			choice = sc.nextInt();

			System.out.println();

			switch (choice) {
				case 1:
					createEnquiry();
					break;
				case 2:
					return;
				default:
					System.out.println("Invalid choice. Please choose again.");
			}
		} while (true);
	}

	protected void viewRegisteredCamps() {

		int choice;

		campView = new RegisteredCampView();
		campView.sortView(SortType.NAME);

		do {
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Withdraw from camp");
			System.out.println("6. Back");
			System.out.print("\nChoice: ");

			choice = sc.nextInt();

			System.out.println();

			switch (choice) {
				case 1:
					campView.sortView(SortType.DATES);
					break;
				case 2:
					campView.sortView(SortType.CLOSING_DATE);
					break;
				case 3:
					campView.sortView(SortType.LOCATION);
					break;
				case 4:
					campView.sortView(SortType.FACULTY);
					break;
				case 5:
					withdrawFromCamp();
					break;
				case 6:
					return;
				default:
					System.out.println("Invalid choice. Please choose again.");
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
