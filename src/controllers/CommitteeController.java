package controllers;

import interfaces.services.GenerateReportServiceable;
import interfaces.services.ReplyEnquiryServiceable;
import interfaces.services.SuggestionServiceable;
import interfaces.views.CampDetailViewable;
import interfaces.views.SuggestionViewable;
import models.CommitteeMember;
import services.CommitteeGenerateReportService;
import services.CommitteeReplyEnquiryService;
import services.CommitteeSuggestionService;

import utils.InputUtil;
import utils.PrintUtil;

import views.CampDetailView;
import views.CommitteeEnquiryView;
import views.CommitteeSuggestionView;

/**
 * The {@code CommitteeController} class extends the functionality of the {@code StudentController} class to provide specific features for Committee Members in the CAMs application.
 * 
 * <p>Committee Members have additional features such as viewing camp details, managing enquiries, suggestions, and generating reports.</p>
 * 
 * <p>This class implements the {@code start} method as required by the abstract base class, initiating the main menu for Committee Members.</p>
 * 
 * <p>
 * The class uses various service implementations for replying to enquiries, managing suggestions, and generating reports.
 * It also uses views to display camp details, enquiries, and suggestions to the Committee Member.
 * </p>
 * 
 * <p>Note: Subclasses are expected to implement specific methods for viewing attendees' enquiries, managing suggestions, and generating reports.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see StudentController
 * @see services.CommitteeGenerateReportService
 * @see services.CommitteeReplyEnquiryService
 * @see services.CommitteeSuggestionService
 * @see interfaces.services.GenerateReportServiceable
 * @see interfaces.services.ReplyEnquiryServiceable
 * @see interfaces.services.SuggestionServiceable
 * @see interfaces.views.CampDetailViewable
 * @see interfaces.views.SuggestionViewable
 * @see models.CommitteeMember
 * @see utils.InputUtil
 * @see utils.PrintUtil
 * @see views.CampDetailView
 * @see views.CommitteeEnquiryView
 * @see views.CommitteeSuggestionView
 */
public class CommitteeController extends StudentController {
	
	private static final CampDetailViewable campDetailView = new CampDetailView();
	
	private static final SuggestionViewable suggestionView = new CommitteeSuggestionView();
	
	private static final ReplyEnquiryServiceable replyEnquiryService = new CommitteeReplyEnquiryService();
	
	private static final SuggestionServiceable suggestionService = new CommitteeSuggestionService();
	
	private static final GenerateReportServiceable generateReportService = new CommitteeGenerateReportService();

	/**
     * Initiates the main menu for Committee Members, allowing them to access specific features.
     */
	@Override
	public void start() {
		
		CommitteeMember committeeMember = (CommitteeMember)currentuserDao.getCurrentUser();
		
		do {
			PrintUtil.header("Main Menu");
			System.out.println("1. View profile");
			System.out.println("2. View all camps");
			System.out.println("3. View registered camps");
			System.out.println("4. View my enquiries");
			System.out.println("5. Create enquiry");
			System.out.printf("6. View %s details\n", committeeMember.getFacilitatingCamp());
			System.out.println("7. Log out");
			
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
				viewCampDetails();
				break;
			case 7:
				return;
			default:
				PrintUtil.invalid("choice");
			}

			if (currentuserDao.getCurrentUser() == null) return;
			
		} while(true);
	}
	
	/**
     * Displays the specific features related to camp details, enquiries, suggestions, and reports for Committee Members.     
	 */
	protected void viewCampDetails() {
			
		
		do {
			campDetailView.view();
			System.out.println("1. View attendees' enquires");
			System.out.println("2. View my suggestions");
			System.out.println("3. Create suggestion");
			System.out.println("4. Generate attendee report");
			System.out.println("5. Back");
			
			switch (InputUtil.choice()) {
			case 1:
				viewAttendeesEnquiries();
				break;
			case 2:
				viewSuggestions();
				break;
			case 3:
				createSuggestion();
				break;
			case 4:
				generateReport();
				break;
			case 5:
				return;
			default:
				PrintUtil.invalid("choice");
			}

		} while(true);
	}
	
	/**
     * Displays attendees' enquiries and provides options to reply to them.
     */
	protected void viewAttendeesEnquiries() {
				
		enquiryView = new CommitteeEnquiryView();
		
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
				PrintUtil.invalid("choice");
			}
			
		} while (true);
	}
	
	/**
     * Displays suggestions and provides options to edit or delete them.
     */
	protected void viewSuggestions() {
		
		do {
			suggestionView.view();
			System.out.println("1. Edit suggestion");
			System.out.println("2. Delete suggestion");
			System.out.println("3. Back");
			
			switch (InputUtil.choice()) {
			case 1:
				editSuggestion();
				break;
			case 2:
				deleteSuggestion();
				return;
			case 3:
				return;
			default:
				PrintUtil.invalid("choice");
			}
		} while (true);
	}
	
	/**
     * Creates a new suggestion.
     */
	protected void createSuggestion() {

		suggestionService.create();
	}
	
    /**
     * Edits an existing suggestion.
     */	
	protected void editSuggestion() {

		suggestionService.edit();
	}
	
    /**
     * Deletes an existing suggestion.
     */	
	protected void deleteSuggestion() {

		suggestionService.delete();
	}
	
    /**
     * Replies to an enquiry.
     */	
	protected void replyEnquiry() {
		
		replyEnquiryService.reply();
	}

    /**
     * Generates a report and exports it to a specified file.
     */	
	protected void generateReport() {
		
		generateReportService.exporting("report/CommitteeReport.txt");
	}
	
}
