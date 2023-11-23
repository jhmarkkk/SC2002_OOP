package views;

import interfaces.views.SuggestionViewable;
import models.Camp;
import models.Suggestion;

public class CommitteeSuggestionView implements SuggestionViewable {
    public void view() {
        CurrentUserDaoInterface currentUserDao;
        String facilitatingCampID = currentUserDao.getCurrentUser().getFacilitatingCamp();
        CampDaoInterface campDao;
        Camp facilitatingCamp = campDao.getCamps().get(facilitatingCampID);
        int sugIndex = 1;
        System.out.printf("===== (Facilitating Camp Suggestions) %s =====\n", facilitatingCamp.getName());
        for (Suggestion sug : facilitatingCamp.getSuggestions()) {
            System.out.printf("***** Suggestion %d *****\n", sugIndex);
            System.out.printf("%s\n", sug.getEnquiry());
            if (sug.getApprover() != null) {
                System.out.printf("~~~~~ Approved by: %s ~~~~~\n", sug.getApprover());
            }
            sugIndex++;
        }
    }
}
