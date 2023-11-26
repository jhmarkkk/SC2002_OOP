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

public class CommitteeSuggestionService implements SuggestionServiceable {

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private static final CampDao campDao = new CampDaoImpl();

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
        System.out.println("\n> Suggestion created");
    }

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
            System.out.println("\n> No valid suggestion to delete");
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
        System.out.println("\n> Suggestion deleted");
    }

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
            System.out.println("\n> No valid suggestion to edit");
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
        System.out.println("\n> Suggestion edited");
    }
}
