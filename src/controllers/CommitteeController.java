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

public class CommitteeController extends StudentController {
	
	private static final CampDetailViewable campDetailView = new CampDetailView();
	
	private static final SuggestionViewable suggestionView = new CommitteeSuggestionView();
	
	private static final ReplyEnquiryServiceable replyEnquiryService = new CommitteeReplyEnquiryService();
	
	private static final SuggestionServiceable suggestionService = new CommitteeSuggestionService();
	
	private static final GenerateReportServiceable generateReportService = new CommitteeGenerateReportService();

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
	
	protected void viewCampDetails() {
			
		campDetailView.view();
		
		do {
			System.out.println("1. View attendees' enquires");
			System.out.println("2. View my suggestions");
			System.out.println("3. Create suggestion");
			System.out.println("4. Generate attendee report");
			System.out.println("5. Back");
			System.out.print("\nChoice: ");
			
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
	
	protected void createSuggestion() {

		suggestionService.create();
	}
	
	protected void editSuggestion() {

		suggestionService.edit();
	}
	
	protected void deleteSuggestion() {

		suggestionService.delete();
	}
	
	protected void replyEnquiry() {
		
		replyEnquiryService.reply();
	}
	
	protected void generateReport() {
		
		generateReportService.exporting("report/CommitteeReport.txt");
	}
	
}
