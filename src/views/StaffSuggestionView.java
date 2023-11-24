package views;

import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.SuggestionViewable;
import models.Camp;
import models.Staff;
import models.Suggestion;

public class StaffSuggestionView implements SuggestionViewable {
    public void view() {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();

        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        Staff staff = (Staff) currentUserDao.getCurrentUser();
        for (String createdCampID : staff.getCreatedCamps()) {
            Camp createdCamp = campsMap.get(createdCampID);
            System.out.printf("===== %s Suggestions =====\n", createdCamp.getName());
            for (Suggestion sug : createdCamp.getSuggestions().values()) {
                System.out.printf("***** Suggestion %d from %s *****\n", sug.getSuggestionID(), sug.getSuggester());
                System.out.printf("%s\n", sug.getSuggestion());
                if (sug.getApproved() != null) {
                    System.out.printf("~~~~~ Approved by: %s ~~~~~\n", sug.getApprover());
                }
            }
        }
    }
}
