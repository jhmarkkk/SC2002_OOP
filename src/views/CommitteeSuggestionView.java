package views;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.SuggestionViewable;

import models.Camp;
import models.CommitteeMember;
import models.Suggestion;

import utils.PrintUtil;

public class CommitteeSuggestionView implements SuggestionViewable {
    
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    private static final CampDao campDao = new CampDaoImpl();
    
    public void view() {
        
        CommitteeMember currentUser = (CommitteeMember)currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());

        PrintUtil.header("My Suggestions");
        for (Suggestion suggestion : camp.getSuggestions().values()) {
            System.out.println("-".repeat(29));
            System.out.printf("%-15s: %s\n","Suggestion ID" , suggestion.getSuggestionID());
            System.out.printf("%-15s: %s\n","Camp" , camp.getName());
            System.out.printf("%-15s: %s\n","Suggested by" , suggestion.getSuggester());
            System.out.printf("%-15s: %s\n","Suggestion" , suggestion.getSuggestion());
            System.out.printf("%-15s: ","Status");
            if (suggestion.getApproved()) System.out.printf("%s\n", "Approved");
            else System.out.printf("%s\n", "Pending");
            
            System.out.println();
        }
    }
}
