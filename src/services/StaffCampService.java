package services;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.CampServiceable;

import models.Camp;
import models.Staff;

import utils.DateUtil;
import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code StaffCampService} class provides methods for staff members to manage camps they are responsible for.
 * It implements the {@code CampServiceable} interface for creating, deleting, and editing camps.
 * 
 * <p>The class allows staff members to create new camps, delete existing camps, and edit the details of existing camps.</p>
 * 
 * @author Chuan Shan Hong
 * @author Joelle Chew Ningxi
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.CampServiceable
 * @see dao.CampDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see models.Camp
 * @see models.Staff
 * @see utils.DateUtil
 * @see utils.InputUtil
 * @see utils.PrintUtil
 */
public class StaffCampService implements CampServiceable {
	
    /**
     * The data access object for managing camps.
     */
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    /**
     * The data access object for managing the current user.
     */
	private static final CampDao campDao = new CampDaoImpl();

    /**
     * Creates a new camp based on user input and adds it to the list of camps managed by the current staff member.
     *
     * @see #enterName()
     * @see #enterDates()
     * @see #enterRegistrationClosingDate()
     * @see #enterOpenTo()
     * @see #enterLocation()
     * @see #enterTotalSlots()
     * @see #enterCommitteeSlots(int, int)
     * @see #enterDescription()
     */
	public void create() {
    	
		PrintUtil.header("Create camp");
		Staff currentUser = (Staff)currentUserDao.getCurrentUser();
		ArrayList<String> createdCampNameList = currentUser.getCreatedCamps();
        Map<String, Camp> campData = campDao.getCamps();
        String name = enterName();
        ArrayList<GregorianCalendar> dates = enterDates();
        GregorianCalendar registrationClosingDate = enterRegistrationClosingDate();
        String openTo = enterOpenTo();
        String location = enterLocation();
        int totalSlots = enterTotalSlots();
        int committeeSlots = enterCommitteeSlots(totalSlots, 0);
        String description = enterDescription();
        String staffInCharge = currentUserDao.getCurrentUser().getUserID();
        Camp camp = new Camp(name, dates, registrationClosingDate, openTo, location,
        		totalSlots, committeeSlots, description, staffInCharge);
        
        campData.put(name, camp);
		createdCampNameList.add(name);
		System.out.println("Camp created");
    }

    /**
     * Deletes an existing camp based on user input.
     * The staff member can only delete a camp if it has no attendees, committee members, or pending enquiries.
     *
     * @see #validateDelete(Camp)
     * @see utils.InputUtil#choice()
     */
    public void delete() {
    	
        int i = 0, choice;
        String selectedCampName;
        Camp selectedCamp;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        ArrayList<String> createdCampNames = currentUser.getCreatedCamps();
        Map<String, Camp> campData = campDao.getCamps();
      
        do {
			PrintUtil.header("Delete camp");
        	for (i = 0; i < createdCampNames.size(); i++)
        		System.out.printf("%2d. %s\n", i+1, createdCampNames.get(i));
        	
        	System.out.printf("%2d. Back\n", i+1);
        	choice = InputUtil.choice();
        	if (choice == i + 1) return;
        	
        	if (choice >= 1 || choice <= i) {
        		selectedCampName = createdCampNames.get(choice - 1);
        		selectedCamp = campData.get(selectedCampName);
        		break;
        	}
        	
			PrintUtil.invalid("choice");
		} while (true);
        
        if (validateDelete(selectedCamp)) {
        	campData.remove(selectedCampName);
			createdCampNames.remove(selectedCampName);
        	System.out.println(selectedCampName + " successfully deleted");
        	return;
        }
        
        System.out.println("Unable to delete " + selectedCampName);
    }

   /**
     * Edits the details of an existing camp based on user input.
     * The staff member can edit various aspects of the camp, such as dates, user group, location, total slots, and more.
     *
     * @see #enterRegistrationClosingDate()
     * @see #enterOpenTo()
     * @see #enterLocation()
     * @see #enterTotalSlots()
     * @see #enterCommitteeSlots(int, int)
     * @see #enterDescription()
     * @see utils.InputUtil#choice()
     */
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
			PrintUtil.header("Edit Camp");
        	for (i = 0; i < createdCampNames.size(); i++)
        		System.out.printf("%2d. %s\n", i+1, createdCampNames.get(i));
        	
        	System.out.printf("%2d. Back\n", i+1);
        	choice = InputUtil.choice();
        	
        	if (choice == i + 1) return;
        	
        	if (choice >= 1 || choice <= i) {
        		selectedCampName = createdCampNames.get(choice - 1);
        		selectedCamp = campData.get(selectedCampName);
        		break;
        	}
        	
			PrintUtil.invalid("choice");
		} while (true);

        do {
			PrintUtil.header(String.format("Editing %s", selectedCampName));
            System.out.println("Edit option:");
            System.out.println("1. Edit registration closing date");
            System.out.println("2. Edit user group");
            System.out.println("3. Edit location");
            System.out.println("4. Edit total slots");
            System.out.println("5. Edit camp committee slots");
            System.out.println("6. Edit description");
            System.out.println("7. Back");

            switch(InputUtil.choice()) {
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
                    newCommitteeSlots = enterCommitteeSlots(selectedCamp.getTotalSlots(), selectedCamp.getCommitteeMembers().size());
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
					PrintUtil.invalid("choice");
                    break;
            }
        } while(true);        
    }
    
    
    /**
     * Prompts the user to enter the name of the camp.
     *
     * @return The name of the camp entered by the user.
     */
	private static String enterName() {
    	
        String name;
        
        do {
        	name = InputUtil.nextString("Enter camp name");
        	if (!name.isBlank()) return name;
        	
			PrintUtil.invalid("name");
		} while (true);
    }
    
    /**
     * Prompts the user to enter the starting date and the number of days the camp is held.
     *
     * @return A list of Gregorian calendar dates representing the range of camp dates.
     * @see #getDateRange(GregorianCalendar, int)
     * @see utils.InputUtil#nextInt(String)
     */
    private static ArrayList<GregorianCalendar> enterDates() {
    	
    	int numOfDays;
    	String startDateStr;
    	GregorianCalendar startDate;
    	GregorianCalendar today = new GregorianCalendar();
    	
    	do {
    		startDateStr = InputUtil.nextString("Enter starting camp date (YYYY-MM-DD)");
    		try {
    			startDate = DateUtil.toDate(startDateStr);
    		} catch (Exception e) {
				PrintUtil.invalid("input");
    			continue;
			}
    		
    		if (DateUtil.toString(today).compareTo(startDateStr) < 0) {
    			break;
    		}

			PrintUtil.invalid("date");
		} while (true);

        do {
			numOfDays = InputUtil.nextInt("Enter number of days camp is held");
			if (numOfDays <= 0) {
				PrintUtil.invalid("input");
				continue;
			}

			ArrayList<GregorianCalendar> dates = getDateRange(startDate, numOfDays);
			System.out.println();
			return dates;    	
		} while (true);
    }
    
    /**
     * Retrieves a date range based on the starting date and the number of days the camp is held.
     *
     * @param startDate The starting date of the camp.
     * @param numOfDays The number of days the camp is held.
     *
     * @return A list of Gregorian calendar dates representing the range of camp dates.
     */
    private static ArrayList<GregorianCalendar> getDateRange(GregorianCalendar startDate, int numOfDays) {
    	
        ArrayList<GregorianCalendar> dateRange = new ArrayList<GregorianCalendar>();
        GregorianCalendar currentDate = new GregorianCalendar(startDate.get(GregorianCalendar.YEAR),
                startDate.get(GregorianCalendar.MONTH), startDate.get(GregorianCalendar.DAY_OF_MONTH));

        for (int i = 0; i < numOfDays; i++) {
            dateRange.add(new GregorianCalendar(currentDate.get(GregorianCalendar.YEAR),
                    currentDate.get(GregorianCalendar.MONTH), currentDate.get(GregorianCalendar.DAY_OF_MONTH)));
            currentDate.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }
        
        return dateRange;
    }

    /**
     * Prompts the user to enter the registration closing date for the camp.
     *
     * @return The registration closing date entered by the user.
     * @see utils.InputUtil#nextString(String)
     */
    private static GregorianCalendar enterRegistrationClosingDate() {
		
    	String closingDateStr;
    	GregorianCalendar closingDate;
    	GregorianCalendar today = new GregorianCalendar();
    	
    	do {
    		closingDateStr = InputUtil.nextString("Enter registration closing date (YYYY-MM-DD)");
    		try {
    			closingDate = DateUtil.toDate(closingDateStr);
    		} catch (Exception e) {
				PrintUtil.invalid("input");
    			continue;
			}
    		
    		if (DateUtil.toString(today).compareTo(closingDateStr) < 0) {
    			System.out.println();
    			return closingDate;
    		}
    		
			PrintUtil.invalid("date");
		} while (true);
	}

    /**
     * Prompts the user to enter the user group for the camp (e.g., School Name or NTU).
     *
     * @return The user group entered by the user.
     * @see utils.InputUtil#nextString(String)
     */
    private static String enterOpenTo() {
    	
    	String openTo;
    	
    	do {
        	openTo = InputUtil.nextString("Enter user group (School Name or NTU)");
        	if (!openTo.isBlank()) return openTo;
        	
			PrintUtil.invalid("input");
		} while (true);
	}
    
	/**
     * Prompts the user to enter the location of the camp.
     *
     * @return The location entered by the user.
     * @see utils.InputUtil#nextString(String)
     */
    private static String enterLocation() {
    	
    	String location;
    	
    	do {
        	location = InputUtil.nextString("Enter location");
        	if (!location.isBlank()) return location;
        	
        	PrintUtil.invalid("input");
		} while (true);
	}
    
	/**
	 * Prompts the user to enter the total number of slots available for the camp.
	 *
	 * @return The total number of slots entered by the user.
	 * @see utils.InputUtil#nextInt(String)
	 */
    private static int enterTotalSlots() {
		
    	int totalSlots;
    	
    	do {
			totalSlots = InputUtil.nextInt("Enter total slots");				
			if (totalSlots <= 0) {
				PrintUtil.invalid("input");
				continue;
			}

			return totalSlots;
		} while (true);
	}
    
	/**
	 * Prompts the user to enter the number of committee slots for the camp.
	 * Validates the input to ensure it is within a valid range.
	 *
	 * @param totalSlots      The total number of slots available for the camp.
	 * @param occupiedSlots   The number of slots already occupied by committee members.
	 *
	 * @return The number of committee slots entered by the user.
	 * @see utils.InputUtil#nextInt(String)
	 */
    private static int enterCommitteeSlots(int totalSlots, int occupiedSlots) {
		
    	int committeeSlots;
    	
    	do {
    		committeeSlots = InputUtil.nextInt("Enter number of committee slots");   		
    		if (committeeSlots < 0 || committeeSlots > 10 ||
				committeeSlots > totalSlots || committeeSlots < occupiedSlots) {
				PrintUtil.invalid("input");
    			continue;
    		}

    		return committeeSlots;
		} while (true);
	}
    
	/**
	 * Prompts the user to enter the description of the camp.
	 *
	 * @return The description entered by the user.
	 * @see utils.InputUtil#nextString(String)
	 */	
    private static String enterDescription() {
    	
    	String description;
    	
    	do {
        	description = InputUtil.nextString("Enter camp description");
        	if (!description.isBlank()) return description;
        	
			PrintUtil.invalid("input");
		} while (true);
	}

    /**
     * Validates whether a camp can be deleted.
     * A camp can be deleted only if it has no attendees, committee members, or pending enquiries.
     *
     * @param camp The camp to be validated for deletion.
     *
     * @return {@code true} if the camp can be deleted, {@code false} otherwise.
     */
    private static boolean validateDelete(Camp camp) {
    	
    	if (camp.getAttendees().size() > 0) return false;
    	
    	if (camp.getCommitteeMembers().size() > 0) return false;
    	
    	if (camp.getEnquiries().size() > 0) return false;
    	
    	return true;
    }
}

