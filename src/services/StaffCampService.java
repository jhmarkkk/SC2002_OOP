package services;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.CampServiceable;

import models.Camp;
import models.Staff;

import utils.DateUtil;

public class StaffCampService implements CampServiceable {
	
	private static final Scanner sc = new Scanner(System.in);

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
	private static final CampDao campDao = new CampDaoImpl();


	public void create() {
    	
        Map<String, Camp> campData = campDao.getCamps();
        String name = enterName();
        ArrayList<GregorianCalendar> dates = enterDates();
        GregorianCalendar registrationClosingDate = enterRegistrationClosingDate();
        String openTo = enterOpenTo();
        String location = enterLocation();
        int totalSlots = enterTotalSlots();
        int committeeSlots = enterCommitteeSlots(totalSlots);
        String description = enterDescription();
        String staffInCharge = currentUserDao.getCurrentUser().getUserID();
        Camp camp = new Camp(name, dates, registrationClosingDate, openTo, location,
        		totalSlots, committeeSlots, description, staffInCharge);
        
        campData.put(name, camp);
    }


    public void delete() {
    	
        int i = 0, choice;
        String selectedCampName;
        Camp selectedCamp;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        ArrayList<String> createdCampNames = currentUser.getCreatedCamps();
        Map<String, Camp> campData = campDao.getCamps();
      
        do {
        	System.out.println("Delete from:");
        	for (i = 0; i < createdCampNames.size(); i++)
        		System.out.printf("%d. %s\n", i+1, createdCampNames.get(i));
        	
        	System.out.printf("%d. Back\n", i+1);
        	System.out.print("Choice: ");
        	
        	choice = sc.nextInt();
        	
        	System.out.println();
        	
        	if (choice == i + 1) return;
        	
        	if (choice >= 1 || choice <= i) {
        		selectedCampName = createdCampNames.get(choice - 1);
        		selectedCamp = campData.get(selectedCampName);
        		break;
        	}
        	
        	System.out.println("Invalid choice. Try again.");
		} while (true);
        
        if (validateDelete(selectedCamp)) {
        	campData.remove(selectedCampName);
        	System.out.println(selectedCampName + " successfully deleted");
        	return;
        }
        
        System.out.println("Unable to delete " + selectedCampName);
    }


    public void edit() {
    	
        int i = 0, choice;
        String selectedCampName;
        Camp selectedCamp;
        GregorianCalendar newRegistrationClosingDate;
        String newOpenTo;
        String newLocation;
        int newTotalSlots;
        int newCommitteeSlots;
        String newDescription;
        Staff currentUser = (Staff)currentUserDao.getCurrentUser();
        ArrayList<String> createdCampNames = currentUser.getCreatedCamps();
        Map<String, Camp> campData = campDao.getCamps();
        
        do {
        	System.out.println("Edit:");
        	for (i = 0; i < createdCampNames.size(); i++)
        		System.out.printf("%d. %s\n", i+1, createdCampNames.get(i));
        	
        	System.out.printf("%d. Back\n", i+1);
        	System.out.print("Choice: ");
        	
        	choice = sc.nextInt();
        	
        	System.out.println();
        	
        	if (choice == i + 1) return;
        	
        	if (choice >= 1 || choice <= i) {
        		selectedCampName = createdCampNames.get(choice - 1);
        		selectedCamp = campData.get(selectedCampName);
        		break;
        	}
        	
        	System.out.println("Invalid choice. Try again.");
		} while (true);

        do {
            System.out.printf("Editing %s\n", selectedCampName);
            System.out.println("Edit option:");
            System.out.println("1. Registration closing date");
            System.out.println("2. Open to");
            System.out.println("3. Location");
            System.out.println("4. Total slots");
            System.out.println("5. Camp committee slots");
            System.out.println("6. Description");
            System.out.println("7. Back");
        
            choice = sc.nextInt();
            
            switch(choice) {
                case 1:
                    System.out.println("Current registration closing date: " + selectedCamp.getRegistrationClosingDate());
                    newRegistrationClosingDate = enterRegistrationClosingDate();
                    selectedCamp.setRegistrationClosingDate(newRegistrationClosingDate);
                    System.out.println("Registration closing date updated");       
                    break;
                case 2:
                    System.out.println("Current user group: " + selectedCamp.getOpenTo());
                    newOpenTo = enterOpenTo();
                    selectedCamp.setOpenTo(newOpenTo);
                    System.out.println("User group updated");
                    break;
                case 3:
                    System.out.println("Current location: " + selectedCamp.getLocation());
                    newLocation = enterLocation();
                    selectedCamp.setLocation(newLocation);
                    System.out.println("Location Updated");
                    break;
                case 4:
                    System.out.println("Current total slots: " + selectedCamp.getTotalSlots());
                    newTotalSlots = enterTotalSlots();
                    selectedCamp.setTotalSlots(newTotalSlots);
                    System.out.println("Total slots updated");
                    break;
                case 5:
                    System.out.println("Current camp committee slots: " + selectedCamp.getCommitteeSlots());
                    newCommitteeSlots = enterCommitteeSlots(selectedCamp.getTotalSlots());
                    selectedCamp.setCommitteeSlots(newCommitteeSlots);
                    System.out.println("Camp committee slots updated");
                    break;
                case 6:
                    System.out.println("Current camp description: " + selectedCamp.getDescription());
                    newDescription = enterDescription();
                    selectedCamp.setDescription(newDescription);
                    System.out.println("Camp description updated");
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while(true);        
    }
    
    private static String enterName() {
    	
        String name;
        
        do {
        	System.out.print("Enter camp name: ");
        	name = sc.nextLine();
        	if (!name.isBlank()) return name;
        	
        	System.out.println("Invalid name. Try again.\n");
		} while (true);
    }
    
    private static ArrayList<GregorianCalendar> enterDates() {
    	
    	int numOfDays;
    	String startDateStr;
    	GregorianCalendar startDate;
    	GregorianCalendar today = new GregorianCalendar();
    	
    	do {
    		System.out.print("Enter starting camp date (YYYY-MM-DD): ");
    		startDateStr = sc.nextLine();
    		try {
    			startDate = DateUtil.toDate(startDateStr);
    		} catch (Exception e) {
    			System.out.println("Invalid input. Try again.\n");
    			continue;
			}
    		
    		if (DateUtil.toString(today).compareTo(startDateStr) < 0) {
    			System.out.println();
    			break;
    		}
    		
    		System.out.println("Invalid date. Try again.\n");
		} while (true);

        do {
        	System.out.print("Enter number of days camp is held: ");
        	try {
        		numOfDays = sc.nextInt();				
        		ArrayList<GregorianCalendar> dates = getDateRange(startDate, numOfDays);
        		System.out.println();
        		return dates;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.\n");
    			continue;
			}        	
		} while (true);
    }
    //Get Date Range method
    private static ArrayList<GregorianCalendar> getDateRange(GregorianCalendar startDate, int numOfDays) {
    	
        ArrayList<GregorianCalendar> dateRange = new ArrayList<>();
        GregorianCalendar currentDate = new GregorianCalendar(startDate.get(GregorianCalendar.YEAR),
                startDate.get(GregorianCalendar.MONTH), startDate.get(GregorianCalendar.DAY_OF_MONTH));

        for (int i = 0; i < numOfDays; i++) {
            dateRange.add(new GregorianCalendar(currentDate.get(GregorianCalendar.YEAR),
                    currentDate.get(GregorianCalendar.MONTH), currentDate.get(GregorianCalendar.DAY_OF_MONTH)));
            currentDate.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }
        
        return dateRange;
    }
    
    private static GregorianCalendar enterRegistrationClosingDate() {
		
    	String closingDateStr;
    	GregorianCalendar closingDate;
    	GregorianCalendar today = new GregorianCalendar();
    	
    	do {
    		System.out.print("Enter registration closing date (YYYY-MM-DD): ");
    		closingDateStr = sc.nextLine();
    		try {
    			closingDate = DateUtil.toDate(closingDateStr);
    		} catch (Exception e) {
    			System.out.println("Invalid input. Try again.\n");
    			continue;
			}
    		
    		if (DateUtil.toString(today).compareTo(closingDateStr) < 0) {
    			System.out.println();
    			return closingDate;
    		}
    		
    		System.out.println("Invalid date. Try again.\n");
		} while (true);
	}
    
    private static String enterOpenTo() {
    	
    	String openTo;
    	
    	do {
        	System.out.print("Enter user group (School Name or NTU): ");
        	openTo = sc.nextLine();
        	if (!openTo.isBlank()) return openTo;
        	
        	System.out.println("Invalid user group. Try again.\n");
		} while (true);
	}
    
    private static String enterLocation() {
    	
    	String location;
    	
    	do {
        	System.out.print("Enter user group (School Name or NTU): ");
        	location = sc.nextLine();
        	if (!location.isBlank()) return location;
        	
        	System.out.println("Invalid loaction. Try again.\n");
		} while (true);
	}
    
    private static int enterTotalSlots() {
		
    	int totalSlots;
    	
    	do {
        	System.out.print("Enter total slots: ");
        	try {
        		totalSlots = sc.nextInt();				
        		System.out.println();
        		return totalSlots;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.\n");
    			continue;
			}
		} while (true);
	}
    
    private static int enterCommitteeSlots(int totalSlots) {
		
    	int committeeSlots;
    	
    	do {
    		System.out.print("Enter number of committee slots: ");
    		try {
    			committeeSlots = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Try again.\n");
    			continue;
			}
    		
    		if (committeeSlots < 0 || committeeSlots > 10 || committeeSlots > totalSlots) {
    			System.out.println("Invalid number of slots. Try again.\n");
    			continue;
    		}
    		System.out.println();
    		return committeeSlots;
		} while (true);
	}
    
    private static String enterDescription() {
    	
    	String description;
    	
    	do {
        	System.out.print("Enter camp description: ");
        	description = sc.nextLine();
        	if (!description.isBlank()) return description;
        	
        	System.out.println("Invalid description. Try again.\n");
		} while (true);
	}

    private boolean validateDelete(Camp camp) {
    	
    	if (camp.getAttendees().size() > 0) return false;
    	
    	if (camp.getCommitteeMembers().size() > 0) return false;
    	
    	if (camp.getEnquiries().size() > 0) return false;
    	
    	return true;
    }
}

