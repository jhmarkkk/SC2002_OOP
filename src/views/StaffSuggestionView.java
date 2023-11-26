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
import utils.PrintUtil;

/**
 * The {@code StaffSuggestionView} class provides functionality to view suggestions made for the camps created by the current staff member.
 * It implements the {@link SuggestionViewable} interface.
 *
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 *
 * @see interfaces.views.SuggestionViewable
 */
public class StaffSuggestionView implements SuggestionViewable {
    
    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    /**
     * Displays details of suggestions made for the camps created by the current staff member.
     */
    public void view() {
        
        Camp camp;
        Map<String, Camp> campData = campDao.getCamps();
        Staff currentUser = (Staff)currentUserDao.getCurrentUser();

        PrintUtil.header("View Committee Suggestions");
        for (String createdCampID : currentUser.getCreatedCamps()) {
            camp = campData.get(createdCampID);
            if (camp.getSuggestions().size() == 0) continue;

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
}
