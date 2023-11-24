package views;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.SuggestionViewable;

import models.Camp;
import models.Suggestion;
import models.CommitteeMember;

public class CommitteeSuggestionView implements SuggestionViewable {
    public void view() {
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        CommitteeMember committeeMember = (CommitteeMember) currentUserDao.getCurrentUser();
        CampDao campDao = new CampDaoImpl();
        Camp facilitatingCamp = campDao.getCamps().get(committeeMember.getFacilitatingCamp());
        int sugIndex = 1;
        System.out.printf("===== Facilitating %s Suggestions =====\n", facilitatingCamp.getName());
        for (Suggestion sug : facilitatingCamp.getSuggestions().values()) {
            if (!sug.getApproved()) {
                System.out.printf("***** Suggestion %d from %s *****\n", sug.getSuggestionID(), sug.getSuggester());
                System.out.printf("%s\n", sug.getSuggestion());
            }
        }
    }
}
