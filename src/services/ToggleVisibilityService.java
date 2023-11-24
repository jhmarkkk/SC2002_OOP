package services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import enums.Visibility;

import models.Camp;
import models.Staff;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ToggleVisibilityServiceable;

public class ToggleVisibilityService implements ToggleVisibilityServiceable{
	
	private static final Scanner sc = new Scanner(System.in);
	
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
    		System.out.println("Toggle visibility for:");
			for (i = 0; i < createdCampNames.size(); i++) {
				campName = createdCampNames.get(i);
				System.out.printf("%2d. %-40s | %s\n", i+1, campName, campData.get(campName).getVisibility().toString());
			}
			
			System.out.printf("%2d. Back\n", i+1);
    		System.out.print("Choice: ");
    		
    		choice = sc.nextInt();
    		
    		System.out.println();
    		
			if (choice == i + 1) return;
    		
    		if (choice < 1 || choice > i + 1) {
    			System.out.println("Invalid choice. Please choose again.");
    			continue;
    		}
    		
    		selectedCamp = campData.get(createdCampNames.get(choice));
    	    if (selectedCamp.getVisibility() == Visibility.OFF)
    	    	selectedCamp.setVisibility(Visibility.ON);
    	    else
    	    	selectedCamp.setVisibility(Visibility.OFF);
		} while (true);
    }
}
