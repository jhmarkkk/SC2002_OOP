package services;

import java.util.ArrayList;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import enums.Visibility;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ToggleVisibilityServiceable;

import models.Camp;
import models.Staff;

import utils.InputUtil;
import utils.PrintUtil;
/**
 * The {@code ToggleVisibilityService} class provides functionality to toggle the visibility of camps created by the current staff member. 
 * It implements the {@link ToggleVisibilityServiceable} interface.
 *
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 *
 * @see dao.CampDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see enums.Visibility
 * @see interfaces.dao.CampDao
 * @see interfaces.dao.CurrentUserDao
 * @see interfaces.services.ToggleVisibilityServiceable
 * @see models.Camp
 * @see models.Staff
 */

public class ToggleVisibilityService implements ToggleVisibilityServiceable{
		
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final CampDao campDao = new CampDaoImpl();

	/**
     * Toggles the visibility of camps created by the current staff member.
     * The staff member can choose a camp to toggle its visibility on or off.
     */
	
    public void toggle() {
    	
    	int i, choice;
    	String campName;
    	Camp selectedCamp;
    	Staff currentUser = (Staff)currentUserDao.getCurrentUser();
    	ArrayList<String> createdCampNames = currentUser.getCreatedCamps();
    	Map<String, Camp> campData = campDao.getCamps();
    	
    	do {
			PrintUtil.header("Toggle Visibility");
			for (i = 0; i < createdCampNames.size(); i++) {
				campName = createdCampNames.get(i);
				System.out.printf("%2d. %-30s | %s\n", i+1, campName, campData.get(campName).getVisibility().toString());
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
    	    	if (validateToggle(selectedCamp)) {
    	    		System.out.println("\n> Unable to toggle camp visibility off.");
					return;
				}
    	    	selectedCamp.setVisibility(Visibility.OFF);
			}
			System.out.println("\n> Visibility toggled");
		} while (true);
    }
    
    /**
     * Validates whether a camp can be toggled to the "OFF" visibility.
     * A camp cannot be toggled off if it has attendees, committee members, enquiries, or suggestions.
     *
     * @param camp The camp object to be validated.
     * @return {@code true} if the camp can be toggled off, {@code false} otherwise.
     */
	private boolean validateToggle(Camp camp) {
    	
    	if (!camp.getAttendees().isEmpty()) return false;
    	
    	if (!camp.getCommitteeMembers().isEmpty()) return false;
    	
    	if (!camp.getEnquiries().isEmpty()) return false;
    	
    	if (!camp.getSuggestions().isEmpty()) return false;
    	
    	return true;
    }
}
