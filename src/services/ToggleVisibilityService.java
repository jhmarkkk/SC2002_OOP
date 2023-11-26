package services;

import java.util.ArrayList;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import enums.Visibility;

import models.Camp;
import models.Staff;
import utils.InputUtil;
import utils.PrintUtil;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ToggleVisibilityServiceable;

public class ToggleVisibilityService implements ToggleVisibilityServiceable{
		
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final CampDao campDao = new CampDaoImpl();
	
    public void toggle(){
    	
    	int i, choice;
    	String campName;
    	Camp selectedCamp;
    	Staff currentUser = (Staff)currentUserDao.getCurrentUser();
    	ArrayList<String> createdCampNames = currentUser.getCreatedCamps();
    	Map<String, Camp> campData = campDao.getCamps();
    	
    	do {
			PrintUtil.header("Toggle Visibility");
    		System.out.println("Toggle visibility for:");
			for (i = 0; i < createdCampNames.size(); i++) {
				campName = createdCampNames.get(i);
				System.out.printf("%2d. %-40s | %s\n", i+1, campName, campData.get(campName).getVisibility().toString());
			}
			
			System.out.printf("%2d. Back\n", i + 1);
    		choice = InputUtil.choice();
			if (choice == i + 1) return;
    		
    		if (choice < 1 || choice > i) {
    			PrintUtil.invalid("choice");;
    			continue;
    		}
    		
    		selectedCamp = campData.get(createdCampNames.get(choice - 1));
    	    if (selectedCamp.getVisibility() == Visibility.OFF)
    	    	selectedCamp.setVisibility(Visibility.ON);
    	    else {
    	    	if (validateToggle(selectedCamp))
    	    		System.out.println("Unable to toggle camp visibility off.");
    	    	else
    	    		selectedCamp.setVisibility(Visibility.OFF);
    	    }
		} while (true);
    }
    
    private boolean validateToggle(Camp camp) {
    	
    	if (!camp.getAttendees().isEmpty()) return false;
    	
    	if (!camp.getCommitteeMembers().isEmpty()) return false;
    	
    	if (!camp.getEnquiries().isEmpty()) return false;
    	
    	if (!camp.getSuggestions().isEmpty()) return false;
    	
    	return true;
    }
}
