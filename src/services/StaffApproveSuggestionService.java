package services;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ApproveSuggestionServiceable;

public class StaffApproveSuggestionService implements ApproveSuggestionServiceable {
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final CampDao campDao = new CampDaoImpl();
	
    public void approve(){
    	
    	
    }
}
