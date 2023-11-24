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

    public void create() {
        CommitteeMember comMember = (CommitteeMember) currentUserDao.getCurrentUser();
        Camp facilitatingCamp = campDao.getCamps().get(comMember.getFacilitatingCamp());
        
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

        System.out.printf("You have created suggestion with id %d\n", newSuggestion.getSuggestionID());
    }

    public void delete() {
        int i, choice, suggestionDeleteID;
        ArrayList<Integer> suggestionIDs = comMember.getSuggestions();
        if (suggestionIDs.size() == 0) {
            System.out.println("No suggestion to delete!");
            return;
        }
        do {
            for (i = 0; i < suggestionIDs.size(); i++)
                System.out.printf("Choice %d : Suggestion ID %d\n", i, suggestionIDs.get(i));
            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 0 || choice <= i) {
                suggestionDeleteID = suggestionIDs.get(choice-1);
                break;
            }
        } while (true);

        // remove suggestion IDs from the user's knowledge
        suggestionIDs.remove(Integer.valueOf(suggestionDeleteID));
        // remove the suggestion from camp's knowledge
        Map<Integer, Suggestion> SuggestionMap = facilitatingCamp.getSuggestions();
        SuggestionMap.remove(suggestionDeleteID);
        facilitatingCamp.setSuggestions(SuggestionMap);

        System.out.printf("You have deleted suggestion with id %d\n", suggestionDeleteID);
    }

    public void edit() {
        int i, choice, suggestionEditID;
        ArrayList<Integer> suggestionIDs = comMember.getSuggestions();
        if (suggestionIDs.size() == 0) {
            System.out.println("No suggestion to edit!");
            return;
        }
        do {
            for (i = 0; i < suggestionIDs.size(); i++)
                System.out.printf("Choice %d : Suggestion ID %d\n", i, suggestionIDs.get(i));
            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 0 || choice <= i) {
                suggestionEditID = suggestionIDs.get(choice);
                break;
            }
        } while (true);

        // change the suggestion from the camp's knowledge
        Map<Integer, Suggestion> SuggestionMap = facilitatingCamp.getSuggestions();
        Suggestion suggestionToEdit = SuggestionMap.get(suggestionEditID);
        System.out.printf("Enter new Suggestion for %s >>> ", facilitatingCamp.getName());
        String suggestionString = sc.nextLine();
        suggestionToEdit.setSuggestion(suggestionString);

        System.out.printf("You have edited suggestion with id %d\n", suggestionEditID);
    }
}
