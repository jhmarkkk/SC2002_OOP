package services;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Map;

import enums.Visibility;
import models.Camp;
import models.CommitteeMember;
import models.Enquiry;
import models.Staff;
import models.Student;
import models.Suggestion;
import interfaces.services.CampServiceable;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CampDao;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CampDaoImpl;


public class StaffCampService implements CampServiceable {
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    private static final CampDao campDao = new CampDaoImpl();

    Scanner sc = new Scanner(System.in);
    
    public void create() {
        Map<String, Camp> campMap = new HashMap<>();

        //CAMP NAME
        System.out.println("Enter camp name: ");
        String name = sc.nextLine();
        System.out.println("Camp name: " + name + "\n");


        //DATES
        // Accept user input for the starting camp date
        System.out.println("Enter starting camp date (YYYY-MM-DD): ");
        String startDateStr = sc.nextLine();
        GregorianCalendar startDate = toDate(startDateStr);

        // Accept user input for the ending camp date
        System.out.println("Enter number of days camp is held: ");
        int numOfDays = sc.nextInt();
        ArrayList<GregorianCalendar> dates = getDateRange(startDate, numOfDays);

        // Print the range of dates
        System.out.println("Camp Dates:");
        for (GregorianCalendar date : dates) {
            System.out.println(String.format("%04d-%02d-%02d", date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)));
        }
        System.out.println();


        //REGISTRATION CLOSING DATE
        System.out.println("Enter registration closing date: ");
        String closingDate = sc.next();
        GregorianCalendar registrationClosingDate = parseDate(closingDate);
        System.out.println("Registration Closing Date: ");
        System.out.println(String.format("%04d-%02d-%02d", registrationClosingDate.get(GregorianCalendar.YEAR), registrationClosingDate.get(GregorianCalendar.MONTH) + 1, registrationClosingDate.get(GregorianCalendar.DAY_OF_MONTH)) + "\n");


        //OPEN TO
        System.out.println("Enter user group (School Name or NTU):");
        String openTo = sc.next();
        
        System.out.println("Camp is only open to " + openTo + "\n");


        //LOCATION
        System.out.println("Enter camp location: ");
        String location = sc.next();
        System.out.println("Camp location: " + location + "\n");


        //TOTAL SLOTS
        System.out.println("Enter toal number of slots: ");
        int totalSlots = sc.nextInt();
        System.out.println("Total number of slots: " + totalSlots + "\n");
        

        //COMMITTEE SLOTS
        System.out.println("Enter number of committee slots: ");
        int temp = sc.nextInt();
        while (temp > 10 || temp > totalSlots){
            if(temp <= 10)
                System.out.printf("Max number of committee slots is %d. Try again.%n", totalSlots);
            else
                System.out.println("There can only be 0-10 committee slots. Try again.");
            System.out.println("Enter number of committee slots: ");
            temp = sc.nextInt();
        }
        int committeeSlots = temp;
        System.out.println("Number of committee slots: " + committeeSlots + "\n");


        //CAMP DESCRIPTION
        System.out.println("Enter camp description: ");
        String description = sc.next();
        System.out.println("Camp description: " + description + "\n");


        //STAFF IN CHARGE
        String staffInCharge = currentUserDao.getCurrentUser().getUserID();


        // Define rest of values as NULL
        ArrayList<String> attendees = null;
        ArrayList<String> withdrawnAttendees = null;
		ArrayList<String> committeeMembers = null;
        Map<Integer, Enquiry> enquiries = null;
		Map<Integer, Suggestion> suggestions = null;
        Visibility visibility = Visibility.OFF;

        Camp camp = new Camp(name, dates, registrationClosingDate, openTo, location, totalSlots, committeeSlots, description,
        staffInCharge, attendees, withdrawnAttendees, committeeMembers, enquiries, suggestions, visibility);
        campMap.put(name, camp);
    }


    public void delete() {
        int i, choice;
        String selectedCampName;
        
        Map<String, Camp> campData = campDao.getCamps();
        Student currentUser = (Student)currentUserDao.getCurrentUser();
        CommitteeMember currentCommitteeMember = (CommitteeMember)currentUserDao.getCurrentUser();

    	ArrayList<String> registeredCamps = currentUser.getRegisteredCamps();
        
        //Choose which camp to delete
        System.out.println("Delete from:");
        for (i = 0; i < registeredCamps.size(); i++)
            System.out.printf("%d. %s\n", i+1, registeredCamps.get(i));
        
        System.out.printf("%d. Back\n", i+1);
        System.out.print("Choice: ");
        
        choice = sc.nextInt();
        
        System.out.println();
        
        if (choice == i + 1) return;

        selectedCampName = registeredCamps.get(choice - 1);

        // Deleting specified camp name and its values
        campData.remove(selectedCampName);
        System.out.println(selectedCampName + " successfully deleted.");
        System.out.println("Remaining number of camps created by you: " + registeredCamps.size());

        // Deleting each enquiry in the Enquiries Map
        Map<String, ArrayList<Integer>> userEnquiries = currentUser.getEnquiries();
        if(userEnquiries.containsKey(selectedCampName)){
            userEnquiries.remove(selectedCampName);
        }

        // Deleting each suggestion in the Suggestion Map
        ArrayList<Integer> userSuggestions = currentCommitteeMember.getSuggestions();
        for (int index : userSuggestions){
            userSuggestions.remove(index);
        }
    }


    public void edit() {
        int i, choice, edit_choice;
        String selectedCampName;
        Camp selectedCamp;
        
        //Map<String, Camp> campData = campDao.getCamps();
        Student currentUser = (Student)currentUserDao.getCurrentUser();
    	ArrayList<String> registeredCamps = currentUser.getRegisteredCamps();
        
        //Choose which camp to edit
        System.out.println("Edit from:");
        for (i = 0; i < registeredCamps.size(); i++)
            System.out.printf("%d. %s\n", i+1, registeredCamps.get(i));
        
        System.out.printf("%d. Back\n", i+1);
        System.out.print("Choice: ");
        
        choice = sc.nextInt();
        
        System.out.println();
        
        if (choice == i + 1) return;

        
        // if (choice >= 0 || choice <= i) {
        //     selectedCampName = registeredCamps.get(choice);
        //     selectedCamp = campDao.getCamps().get(selectedCampName);
        // }
        selectedCampName = registeredCamps.get(choice - 1);
        selectedCamp = campDao.getCamps().get(selectedCampName);
        
        //
        do {
            System.out.println("---------------Edit Camps---------------");
            System.out.println("Edit:");
            System.out.println("1. Dates");
            System.out.println("2. Registration closing date");
            System.out.println("3. Open to");
            System.out.println("4. Location");
            System.out.println("5. Total slots");
            System.out.println("6. Camp committee slots");
            System.out.println("7. Description");
            System.out.println("8. Back");
        
            edit_choice = sc.nextInt();
            
            switch(edit_choice){
                case 1:
                    // 1. DATES
                    System.out.println("Current camp dates:");
                    for (GregorianCalendar date : selectedCamp.getDates()) {
                        System.out.println(String.format("%04d-%02d-%02d", date.get(GregorianCalendar.YEAR),
                        date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)));
                    }

                    System.out.println("Enter new starting camp date (YYYY-MM-DD): ");
                    String startDateStr = sc.nextLine();
                    GregorianCalendar startDate = toDate(startDateStr);

                    // Accept user input for the number of days camp is held
                    System.out.println("Enter number of days camp is held: ");
                    int numOfDays = sc.nextInt();
                    ArrayList<GregorianCalendar> newDates = getDateRange(startDate, numOfDays);

                    // Print the range of dates
                    System.out.println("Camp Dates:");
                    for (GregorianCalendar date : newDates) {
                        System.out.println(String.format("%04d-%02d-%02d", date.get(GregorianCalendar.YEAR),
                        date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)));
                    }
                    selectedCamp.setDates(newDates);
                    break;
                case 2:
                    // 2. REGISTRATION CLOSING DATE
                    System.out.println("Current registration closing date: " + selectedCamp.getRegistrationClosingDate());
                    System.out.println("Enter new registration closing date: ");
                    String newClosingDate = sc.nextLine();
                    
                    // Change from String to GregorianCalendar
                    GregorianCalendar newRegistrationClosingDate = parseDate(newClosingDate);

                    System.out.println("Updated registration closing date: " + newRegistrationClosingDate);
                    System.out.println(String.format("%04d-%02d-%02d", newRegistrationClosingDate.get(GregorianCalendar.YEAR),
                    newRegistrationClosingDate.get(GregorianCalendar.MONTH) + 1, newRegistrationClosingDate.get(GregorianCalendar.DAY_OF_MONTH)) + "\n");
                    
                    selectedCamp.setRegistrationClosingDate(newRegistrationClosingDate);
                    break;
                case 3:
                    // 3. OPEN TO 
                    System.out.println("Current user group: " + selectedCamp.getOpenTo());
                    System.out.println("Enter new user group: ");
                    String newOpenTo = sc.nextLine();
                    System.out.println("Updated user group: " + newOpenTo);
                    selectedCamp.setOpenTo(newOpenTo);
                    break;
                case 4:
                    // 4. LOCATION
                    System.out.println("Current location: " + selectedCamp.getLocation());
                    System.out.println("Enter new location: ");
                    String newLocation = sc.nextLine();
                    System.out.println("Updated location: " + newLocation);
                    selectedCamp.setLocation(newLocation);
                    break;
                case 5:
                    // 5. TOTAL SLOTS
                    System.out.println("Current total slots: " + selectedCamp.getTotalSlots());
                    System.out.println("Enter new total slots: ");
                    int newTotalSlots = sc.nextInt();
                    System.out.println("Updated total slots: " + newTotalSlots);
                    selectedCamp.setTotalSlots(newTotalSlots);
                    break;
                case 6:
                    // 6. CAMP COMMITTEE SLOTS
                    System.out.println("Current camp committee slots: " + selectedCamp.getCommitteeSlots());
                    System.out.println("Enter new camp committee slots: ");
                    int newCommitteeSlots = sc.nextInt();
                    System.out.println("Updated camp committee slots: " + newCommitteeSlots);
                    selectedCamp.setCommitteeSlots(newCommitteeSlots);
                    break;
                case 7:
                    // 7. DESCRIPTION
                    System.out.println("Current camp description: " + selectedCamp.getDescription());
                    System.out.println("Enter new camp description: ");
                    String newDescription = sc.nextLine();
                    System.out.println("Updated camp description: " + newDescription);
                    selectedCamp.setDescription(newDescription);
                    break;
                case 8:
                    // 8. BACK
                    // Go back to main menu
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        } while(edit_choice < 1 || edit_choice > 8);        
    }


    //Get Date Range method
    private static ArrayList<GregorianCalendar> getDateRange(GregorianCalendar startDate, int numOfDays) {
        ArrayList<GregorianCalendar> dateRange = new ArrayList<>();
        GregorianCalendar currentDate = new GregorianCalendar(startDate.get(GregorianCalendar.YEAR),
                startDate.get(GregorianCalendar.MONTH), startDate.get(GregorianCalendar.DAY_OF_MONTH));

        for (int i=0; i<numOfDays; i++){
            dateRange.add(new GregorianCalendar(currentDate.get(GregorianCalendar.YEAR),
                    currentDate.get(GregorianCalendar.MONTH), currentDate.get(GregorianCalendar.DAY_OF_MONTH)));
            currentDate.add(GregorianCalendar.DAY_OF_MONTH, 1);
        }
        return dateRange;
    }
}
