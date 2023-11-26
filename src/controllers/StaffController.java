package controllers;

import java.util.Map;

import enums.SortType;

import interfaces.views.CampViewable;
import interfaces.views.EnquiryViewable;
import interfaces.views.SuggestionViewable;
import interfaces.services.ApproveSuggestionServiceable;
import interfaces.services.CampServiceable;
import interfaces.services.GenerateReportServiceable;
import interfaces.services.ReplyEnquiryServiceable;
import interfaces.services.ToggleVisibilityServiceable;

import models.Camp;
import models.Staff;

import services.StaffApproveSuggestionService;
import services.StaffCampService;
import services.StaffGenerateReportService;
import services.StaffReplyEnquiryService;
import services.ToggleVisibilityService;

import utils.InputUtil;
import utils.PrintUtil;

import views.CreatedCampView;
import views.StaffAllCampView;
import views.StaffEnquiryView;
import views.StaffSuggestionView;

 /**
 * The {@code StaffController} class handles the staff-specific user interface and interactions in the CAMs application. 
 * It extends the {@code UserController} class and provides functionality for staff to view their profile, change password, view all camps, view camps created by themselves, view attendees within camps, create camps, edit camps, delete camps, view suggestions, approve suggestions, view enquiries, reply to enquiries, and generate reports.
 * 
 * <p>Note: Subclasses are expected to implement specific methods for sorting views, toggling visibility, creating, editing, and deleting camps, approving suggestions, replying to enquiries, and generating reports.</p>
 * 
 * @author Chua Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see UserController
 * @see StaffApproveSuggestionService
 * @see StaffCampService
 * @see StaffGenerateReportService
 * @see StaffReplyEnquiryService
 * @see ToggleVisibilityService
 * @see interfaces.views.CampViewable
 * @see interfaces.views.EnquiryViewable
 * @see interfaces.views.SuggestionViewable
 * @see interfaces.services.ApproveSuggestionServiceable
 * @see interfaces.services.CampServiceable
 * @see interfaces.services.GenerateReportServiceable
 * @see interfaces.services.ReplyEnquiryServiceable
 * @see interfaces.services.ToggleVisibilityServiceable
 * @see models.Camp
 * @see models.Staff
 * @see utils.InputUtil
 * @see utils.PrintUtil
 * @see views.CreatedCampView
 * @see views.StaffAllCampView
 * @see views.StaffEnquiryView
 * @see views.StaffSuggestionView
 */
public class StaffController extends AbstractUserController {

	private static CampViewable campView;

	private static final EnquiryViewable enquiryView = new StaffEnquiryView();

	private static final SuggestionViewable suggestionView = new StaffSuggestionView();

	private static final ToggleVisibilityServiceable toggleVisibilityService = new ToggleVisibilityService();

	private static final CampServiceable campService = new StaffCampService();

	private static final ApproveSuggestionServiceable approveSuggestionService = new StaffApproveSuggestionService();

	private static final ReplyEnquiryServiceable replyEnquiryService = new StaffReplyEnquiryService();

	private static final GenerateReportServiceable generateReportService = new StaffGenerateReportService();
	
    /**
     * Initiates the main menu for staff, allowing them to access specific features.
     */	
	@Override
	public void start() {

		do {
			PrintUtil.header("Main Menu");
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View my created camps");
			System.out.println("4. Create new camp");
			System.out.println("5. View committee members' suggestions");
			System.out.println("6. View attendees' enquiries");
			System.out.println("7. Generate Report");
			System.out.println("8. Log out");

			switch (InputUtil.choice()) {
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
					return;
				default:
					PrintUtil.invalid("choice");;
			}
			
			if (currentuserDao.getCurrentUser() == null) return;
			
		} while (true);
	}

    /**
     * Displays all camps, allowing staff to sort them based on various criteria.
     */	
	@Override
	protected void viewAllCamps() {

		Map<String, Camp> campData = campDao.getCamps();
		SortType type = SortType.NAME;

		if (campData.isEmpty()) {
			System.out.println("No camp");
			return;
		}

		campView = new StaffAllCampView();
		do {
			campView.sortView(type);
			PrintUtil.header("Sort Option");
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Sort by camp staff-in-charge");
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
					type = SortType.STAFF;
					break;
				case 6:
					return;
				default:
					PrintUtil.invalid("choice");;
			}

		} while (true);
	}

    /**
     * Displays enquiries, allowing staff to reply to them.
     */
	@Override
	protected void viewEnquiries() {

		do {
			enquiryView.view();
			System.out.println("1. Reply enquiry");
			System.out.println("2. Back");

			switch (InputUtil.choice()) {
				case 1:
					replyEnquiry();
					break;
				case 2:
					return;
				default:
					PrintUtil.invalid("choice");;
			}

		} while (true);
	}

    /**
     * Displays camps created by the current staff, allowing them to perform various actions.
     */	
	protected void viewCreatedCamps() {

		Staff currentUser = (Staff)currentuserDao.getCurrentUser();
		SortType type = SortType.NAME;

		if (currentUser.getCreatedCamps().isEmpty()) {
			System.out.println("No created camp");
			return;
		}

		campView = new CreatedCampView();
		do {
			campView.sortView(type);
			System.out.println("1. Sort by camp dates");
			System.out.println("2. Sort by camp registration closing date");
			System.out.println("3. Sort by camp location");
			System.out.println("4. Sort by camp faculty");
			System.out.println("5. Sort by camp staff-in-charge");
			System.out.println("6. Toggle visibility");
			System.out.println("7. Edit camp");
			System.out.println("8. Delete camp");
			System.out.println("9. Back");

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
					type = SortType.STAFF;
					break;
				case 6:
					toggleVisibility();
					break;
				case 7:
					editCamp();
					break;
				case 8:
					deleteCamp();
					return;
				case 9:
					return;
				default:
					PrintUtil.invalid("choice");;
			}
		} while (true);
	}

    /**
     * Toggles the visibility of a camp.
     */	
	protected void toggleVisibility() {

		toggleVisibilityService.toggle();
	}

    /**
     * Creates a new camp.
     */	
	protected void createCamp() {

		campService.create();
	}

    /**
     * Edits an existing camp.
     */	
	protected void editCamp() {

		campService.edit();
	}

    /**
     * Deletes an existing camp.
     */	
	protected void deleteCamp() {

		campService.delete();
	}

    /**
     * Displays suggestions and allows staff to approve them.
     */	
	protected void viewSuggestions() {

		do {
			suggestionView.view();
			System.out.println("1. Approve suggestion");
			System.out.println("2. Back");

			switch (InputUtil.choice()) {
				case 1:
					approveSuggestion();
					break;
				case 2:
					return;
				default:
					PrintUtil.invalid("choice");;
			}

		} while (true);
	}

    /**
     * Approves a suggestion.
     */	
	protected void approveSuggestion() {

		approveSuggestionService.approve();
	}

    /**
     * Replies to an enquiry.
     */	
	protected void replyEnquiry() {

		replyEnquiryService.reply();
	}

    /**
     * Generates a report and exports it.
     */	
	protected void generateReport() {

		generateReportService.exporting("report/StaffReport.txt");
	}

}
