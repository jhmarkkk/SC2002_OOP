package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import utils.InputUtil;
import utils.PrintUtil;

public class StaffApproveSuggestionService implements ApproveSuggestionServiceable {
   
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    private static final CampDao campDao = new CampDaoImpl();
    
    private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
    

    public void approve() {
    	
        int i = 0, choice;
        Integer suggestionID;
        Suggestion selectedSuggestion;
        Camp camp;
        CommitteeMember committeeMember;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        ArrayList<Integer> validSuggestionIDList = new ArrayList<>();
        Map<String, CommitteeMember> committeeMemberData = committeeMemberDao.getCommitteeMembers();
        Map<Integer, Camp> suggestionIDToCampMap = new HashMap<Integer, Camp>();
        
        for (String campName : currentUser.getCreatedCamps()) {
        	camp = campDao.getCamps().get(campName);
        	for (Suggestion suggestion : camp.getSuggestions().values()) {
        		suggestionIDToCampMap.put(suggestion.getSuggestionID(), camp);
        		if (suggestion.getApproved()) continue;

        		validSuggestionIDList.add(suggestion.getSuggestionID());
        	}
        }
        
		if (validSuggestionIDList.size() == 0) {
            System.out.println("No suggestion to approve");
            return;
        }

        do {
    		PrintUtil.header("Approve Suggestion");
    		for (i = 0; i < validSuggestionIDList.size(); i++)
    			System.out.printf("%2d. %s\n", i+1, validSuggestionIDList.get(i));
    		
    		System.out.printf("%2d. Back\n", i+1);
    		choice = InputUtil.choice();
    		if (choice == i + 1) return;
    		
    		if (choice >= 0 || choice <= i) {
    			suggestionID = validSuggestionIDList.get(choice - 1);
    			break;
    		}
    		
    		PrintUtil.invalid("choice");
		} while (true);
        
        camp = suggestionIDToCampMap.get(suggestionID);
        selectedSuggestion = camp.getSuggestions().get(suggestionID);
        committeeMember = committeeMemberData.get(selectedSuggestion.getSuggester());
        System.out.println("Camp: " + camp.getName());
		System.out.printf("%-15s: %s\n","Camp" , camp.getName());
		System.out.printf("%-15s: %s\n","Suggested by" , selectedSuggestion.getSuggester());
		System.out.printf("%-15s: %s\n","Suggestion" , selectedSuggestion.getSuggestion());
		
        do {
        	System.out.println("1. Approve");
        	System.out.println("2. Back");
			choice = InputUtil.choice();
    		switch (choice) {
			case 1:
				selectedSuggestion.setApproved(true);
				committeeMember.setPoints(committeeMember.getPoints() + 1);
				System.out.println("Suggestion approved");
				return;
			case 2:
				return;
			default:
				PrintUtil.invalid("choice");
			}
		} while (true);
    }
}
