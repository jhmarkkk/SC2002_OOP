package services;

import java.util.Map;
import java.util.ArrayList;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.SuggestionServiceable;
import models.Camp;
import models.CommitteeMember;
import models.Suggestion;
import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code CommitteeSuggestionService} class provides methods for committee members to manage suggestions in the context of a specific camp.
 * It implements the {@code SuggestionServiceable} interface for handling the creation, deletion, and editing of suggestions.
 * 
 * <p>The class allows committee members to create, delete, and edit suggestions related to the camp they are facilitating.</p>
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.SuggestionServiceable
 * @see dao.CampDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see models.Camp
 * @see models.CommitteeMember
 * @see models.Suggestion
 * @see utils.InputUtil
 * @see utils.PrintUtil
 */
public class CommitteeSuggestionService implements SuggestionServiceable {

    /**
     * The data access object for managing camps.
     */
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    /**
     * The data access object for managing the current user.
     */    
    private static final CampDao campDao = new CampDaoImpl();

    /**
     * Creates a new suggestion for the camp facilitated by the current committee member.
     */
    public void create() {

        CommitteeMember currentUser = (CommitteeMember) currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        Map<Integer, Suggestion> SuggestionData = camp.getSuggestions();
        ArrayList<Integer> committeeSuggestionIDList = currentUser.getSuggestions();
        String suggestionField;
        Suggestion newSuggestion;

        PrintUtil.header("New Suggestion");
        do {
            suggestionField = InputUtil.nextString("Enter suggestion");
            if (!suggestionField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        newSuggestion = new Suggestion(suggestionField, currentUser.getUserID());
        SuggestionData.put(newSuggestion.getSuggestionID(), newSuggestion);
        committeeSuggestionIDList.add(newSuggestion.getSuggestionID());
        currentUser.setPoints(currentUser.getPoints() + 1);
        System.out.println("Suggestion created");
    }

    /**
     * Deletes a suggestion created by the current committee member for the camp facilitated by them.
     */
    public void delete() {

        int i, choice;
        Suggestion selectedSuggestion;
        CommitteeMember currentUser = (CommitteeMember) currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        Map<Integer, Suggestion> suggestionData = camp.getSuggestions();
        ArrayList<Suggestion> validSuggestionList = new ArrayList<>();

        for (Integer suggestionID : currentUser.getSuggestions()) {
            if (suggestionData.get(suggestionID).getSuggester() != null) continue;

            validSuggestionList.add(suggestionData.get(suggestionID));
        }

        if (validSuggestionList.size() == 0) {
            System.out.println("No valid suggestion to delete");
            return;
        }

        do {
            PrintUtil.header("Delete Suggestion");
            for (i = 0; i < validSuggestionList.size(); i++)
                System.out.printf("%2d. Suggestion %d\n", i, validSuggestionList.get(i).getSuggestionID());
            
            System.out.printf("%2d. Back", i + 1);
            choice = InputUtil.choice();
            if (choice == i + 1) return;

            if (choice >= 1 || choice <= i) {
                selectedSuggestion = validSuggestionList.get(choice - 1);
                break;
            }
        } while (true);

        validSuggestionList.remove(selectedSuggestion);
        suggestionData.remove(selectedSuggestion.getSuggestionID());
        currentUser.setPoints(currentUser.getPoints() - 1);
        System.out.println("Suggestion deleted");
    }

    /**
     * Edits the content of a suggestion created by the current committee member for the camp facilitated by them.
     */
    public void edit() {

        int i, choice;
        Suggestion selectedSuggestion;
        String newSuggestionField;
        CommitteeMember currentUser = (CommitteeMember) currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        Map<Integer, Suggestion> suggestionData = camp.getSuggestions();
        ArrayList<Suggestion> validSuggestionList = new ArrayList<>();

        for (Integer suggestionID : currentUser.getSuggestions()) {
            if (suggestionData.get(suggestionID).getSuggester() != null) continue;

            validSuggestionList.add(suggestionData.get(suggestionID));
        }

        if (validSuggestionList.size() == 0) {
            System.out.println("No valid suggestion to edit");
            return;
        }

        do {
            PrintUtil.header("Edit Suggestion");
            for (i = 0; i < validSuggestionList.size(); i++)
                System.out.printf("%2d. Suggestion %d\n", i, validSuggestionList.get(i).getSuggestionID());
            
            System.out.printf("%2d. Back", i + 1);
            choice = InputUtil.choice();
            if (choice == i + 1) return;

            if (choice >= 1 || choice <= i) {
                selectedSuggestion = validSuggestionList.get(choice - 1);
                break;
            }
        } while (true);

        do {
            newSuggestionField = InputUtil.nextString("Enter suggestion");
            if (!newSuggestionField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        selectedSuggestion.setSuggestion(newSuggestionField);
        System.out.println("Suggestion deleted");
    }
}
