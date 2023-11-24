package services;

import java.util.Map;
import java.util.ArrayList;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.SuggestionServiceable;
import models.Camp;
import models.CommitteeMember;
import models.Suggestion;

public class CommitteeSuggestionService implements SuggestionServiceable {
    Scanner sc = new Scanner(System.in);

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    public static final CampDao campDao = new CampDaoImpl();
    private static final CommitteeMember comMember = (CommitteeMember) currentUserDao.getCurrentUser();
    private static final Camp facilitatingCamp = campDao.getCamps().get(comMember.getFacilitatingCamp());

    public void create() {
        System.out.printf("Enter Suggestion for %s >>> ", facilitatingCamp.getName());
        String suggestionString = sc.nextLine();

        String suggester = comMember.getName();

        Suggestion newSuggestion = new Suggestion(suggestionString, suggester);

        Map<Integer, Suggestion> SuggestionMap = facilitatingCamp.getSuggestions();

        // add to camps knowledge of the suggestion
        SuggestionMap.put(newSuggestion.getSuggestionID(), newSuggestion);
        facilitatingCamp.setSuggestions(SuggestionMap);
        ArrayList<Integer> committeeSuggestionMap = comMember.getSuggestions();
        // add to comMember knowledge of suggestion
        committeeSuggestionMap.add(newSuggestion.getSuggestionID());

    }

    public void delete() {
        ArrayList<Integer> suggestionIDs = comMember.getSuggestions();
        if (suggestionIDs.size() == 0) {
            System.out.println("No suggestion to delete!");
            return;
        }
        for (Integer suggestionID : suggestionIDs) {
            Suggestion sug = facilitatingCamp.getSuggestions().get(suggestionID);
            System.out.printf("***** Suggestion %d *****\n", sug.getSuggestionID());
            System.out.printf("%s\n", sug.getSuggestion());
        }
        int suggestionDeleteID;
        System.out.println("Enter a suggestion number to delete: ");
        suggestionDeleteID = sc.nextInt();
        do {
            System.out.println("Invalid suggestion ID entered");
            System.out.println("Enter a suggestion number to delete: ");
            suggestionDeleteID = sc.nextInt();
        } while (!suggestionIDs.contains(suggestionDeleteID));

        // remove suggestion IDs from the user's knowledge
        suggestionIDs.remove(Integer.valueOf(suggestionDeleteID));
        // remove the suggestion from camp's knowledge
        Map<Integer, Suggestion> SuggestionMap = facilitatingCamp.getSuggestions();
        SuggestionMap.remove(suggestionDeleteID);
        facilitatingCamp.setSuggestions(SuggestionMap);

    }

    public void edit() {
        ArrayList<Integer> suggestionIDs = comMember.getSuggestions();
        if (suggestionIDs.size() == 0) {
            System.out.println("No suggestion to edit!");
            return;
        }
        for (Integer suggestionID : suggestionIDs) {
            Suggestion sug = facilitatingCamp.getSuggestions().get(suggestionID);
            System.out.printf("***** Suggestion %d *****\n", sug.getSuggestionID());
            System.out.printf("%s\n", sug.getSuggestion());
        }
        int suggestionEditID;
        System.out.println("Enter a suggestion number to edit: ");
        suggestionEditID = sc.nextInt();
        do {
            System.out.println("Invalid suggestion ID entered");
            System.out.println("Enter a suggestion number to edit: ");
            suggestionEditID = sc.nextInt();
        } while (!suggestionIDs.contains(suggestionEditID));
        // change the suggestion from the camp's knowledge
        Map<Integer, Suggestion> SuggestionMap = facilitatingCamp.getSuggestions();
        Suggestion suggestionToEdit = SuggestionMap.get(suggestionEditID);
        System.out.printf("Enter new Suggestion for %s >>> ", facilitatingCamp.getName());
        String suggestionString = sc.nextLine();
        suggestionToEdit.setSuggestion(suggestionString);
    }
}
