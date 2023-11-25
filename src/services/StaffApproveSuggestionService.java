package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ApproveSuggestionServiceable;
import models.Camp;
import models.CommitteeMember;
import models.Staff;
import models.Suggestion;

public class StaffApproveSuggestionService implements ApproveSuggestionServiceable {

	private static final Scanner sc = new Scanner(System.in);
    
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    private static final CampDao campDao = new CampDaoImpl();
    
    private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
    

    public void approve() {
    	
        int i = 0, choice;
        Integer suggestionID;
        Suggestion selectedSuggestion;
        Camp camp;
        CommitteeMember committeeMember;
        Staff staff = (Staff) currentUserDao.getCurrentUser();
        ArrayList<Integer> suggestionIDList = new ArrayList<>();
        ArrayList<String> createdCampNames = staff.getCreatedCamps();
        Map<String, CommitteeMember> committeeMemberData = committeeMemberDao.getCommitteeMembers();
        Map<String, Camp> campData = campDao.getCamps();
        Map<Integer, Camp> suggestionCampPair = new HashMap<Integer, Camp>();
        
        for (String campName : createdCampNames) {
        	camp = campData.get(campName);
        	for (Suggestion suggestion : camp.getSuggestions().values()) {
        		suggestionCampPair.put(suggestion.getSuggestionID(), camp);
        		if (suggestion.getApproved()) continue;
        		suggestionIDList.add(suggestion.getSuggestionID());
        	}
        }
        
        do {
    		System.out.println("Approve suggestion:");
    		for (i = 0; i < suggestionIDList.size(); i++)
    			System.out.printf("%2d. %s\n", i+1, suggestionIDList.get(i));
    		
    		System.out.printf("%2d. Back\n", i+1);
    		System.out.print("Choice: ");
    		
    		choice = sc.nextInt();
    		
    		System.out.println();
    		
    		if (choice == i + 1) return;
    		
    		if (choice >= 0 || choice <= i) {
    			suggestionID = suggestionIDList.get(choice - 1);
    			break;
    		}
    		
    		System.out.println("Invalid choice. Please choose again.");
		} while (true);
        
        camp = suggestionCampPair.get(suggestionID);
        selectedSuggestion = camp.getSuggestions().get(suggestionID);
        committeeMember = committeeMemberData.get(selectedSuggestion.getSuggester());
        System.out.println("Suggestion: " + selectedSuggestion.getSuggestion());
        System.out.println("Suggested by: " + committeeMember.getName());
        System.out.println("Camp: " + camp.getName());
        
        do {
        	System.out.println("1. Approve");
        	System.out.println("2. Back");
    		System.out.print("Choice: ");
    		
    		choice = sc.nextInt();
    		
    		System.out.println();
    		
    		switch (choice) {
			case 1:
				selectedSuggestion.setApproved(true);
				committeeMember.setPoints(committeeMember.getPoints() + 1);
				System.out.println("Suggestion has been approved");
				return;
			case 2:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (true);
    }
}
