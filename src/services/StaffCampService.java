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
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;
import dao.CommitteeMemberDaoImpl;


public class StaffCampService {
    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    Map<String, Camp> campMap = new HashMap<>();

    Scanner sc = new Scanner(System.in);

    
    public void create() {
        //CAMP NAME
        System.out.println("Enter camp name: ");
        String name = sc.nextLine();
        System.out.println("Camp name: " + name + "\n");


        //DATES
        // Accept user input for the starting camp date
        System.out.println("Enter starting camp date (YYYY-MM-DD): ");
        String startDateStr = sc.nextLine();
        GregorianCalendar startDate = parseDate(startDateStr);

        System.out.println("Enter number of days camp is held: ");
        int numOfDays = sc.nextInt();
        ArrayList<GregorianCalendar> dates = getDateRange(startDate, numOfDays);

        // // Accept user input for the ending camp date
        // System.out.println("Enter ending camp date (YYYY-MM-DD): ");
        // String endDateStr = sc.nextLine();
        // GregorianCalendar endDate = parseDate(endDateStr);

        // Print the range of dates
        //ArrayList<GregorianCalendar> dates = getDateRange(startDate, endDate);
        System.out.println("Camp Dates:");
        for (GregorianCalendar date : dates) {
            System.out.println(String.format("%04d-%02d-%02d", date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)));
        }
        System.out.println();


        //Registration Closing Date
        System.out.println("Enter registration closing date: ");
        String closingDate = sc.nextLine();
        GregorianCalendar registrationClosingDate = parseDate(closingDate);
        System.out.println("Registration Closing Date: ");
        System.out.println(String.format("%04d-%02d-%02d", registrationClosingDate.get(GregorianCalendar.YEAR), registrationClosingDate.get(GregorianCalendar.MONTH) + 1, registrationClosingDate.get(GregorianCalendar.DAY_OF_MONTH)) + "\n");


        //OPEN TO
        System.out.println("Is this camp open to School or NTU?");
        String openTo = sc.nextLine();
        System.out.println("Camp is only open to " + openTo + "\n");


        //LOCATION
        System.out.println("Enter camp location: ");
        String location = sc.nextLine();
        System.out.println("Camp location: " + location + "\n");


        //TOTAL SLOTS
        System.out.println("Enter toal number of slots: ");
        int totalSlots = sc.nextInt();
        System.out.println("Total number of slots: " + totalSlots + "\n");
        

        //COMMITTEE SLOTS
        System.out.println("Enter number of committee slots: ");
        int temp = sc.nextInt();
        while (temp > 10){
            System.out.println("Max number of committee slots is 10. Try again.");
            System.out.println("Enter number of committee slots: ");
            temp = sc.nextInt();
        }
        int committeeSlots = temp;
        System.out.println("Number of committee slots: " + committeeSlots + "\n");


        //CAMP DESCRIPTION
        System.out.println("Enter camp description: ");
        String description = sc.next();
        System.out.println("Camp description: " + description + "\n");


        // //STAFF IN CHARGE
        // String staffInCharge = currentUserDao.getCurrentUser().getUserID();

        Camp camp = new Camp(name, dates, registrationClosingDate, openTo, location, totalSlots, committeeSlots, description);
        campMap.put(name, camp);
    }


    public void delete() {
        // TODO Auto-generated method stub
    }


    public void edit() {
        System.out.println("---------------Edit Camps---------------");
        System.out.println("Edit:");
        System.out.println("1. Names");
        System.out.println("2. Dates");
        System.out.println("3. Registration closing date");
        System.out.println("4. Open to");
        System.out.println("5. Location");
        System.out.println("6. Total slots");
        System.out.println("7. Camp committee slots");
        System.out.println("8. Description");
        System.out.println("9. Back");
        
        // int choice = sc.nextInt();
        
        // switch(choice){
        //     case 1:
        //         //1. NAMES (not done)
        //         System.out.println("Current name: " + camp.getName(name));
        //         System.out.println("Enter new name: ");
        //         String newName = sc.nextLine();
        //         System.out.println("Updated new name: " + newName);

        //         Camp camp = campMap.get(name);
        //         campMap.remove(name);
        //         camp.setName(newName);
        //         campMap.put(newName, camp);
        //         break;
        //     case 2:
        //         //2. DATES
        //         System.out.println("Current camp dates:");
        //         for (GregorianCalendar date : camp.getDates(dates)) {
        //             System.out.println(String.format("%04d-%02d-%02d", date.get(GregorianCalendar.YEAR), date.get(GregorianCalendar.MONTH) + 1, date.get(GregorianCalendar.DAY_OF_MONTH)));
        //         }

        //         camp.setDates


        //         break;
        //     case 3:
        //         //text
        //         break;
        //     case 4:
        //         //text
        //         break;
        //     case 5:
        //         //text
        //         break;
        //     case 6:
        //         //text
        //         break;
        //     case 7:
        //         //text
        //         break;
        //     case 8:
        //         //text
        //         break;
        //     case 9:
        //         //text
        //         break;
        //     default:
        //         System.out.println("Invalid choice. Try again.");
        // }        

    }


    //Parse Date method
    private static GregorianCalendar parseDate(String dateStr) {
        String[] parts = dateStr.split("-");
        int year = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]) - 1; // Months are 0-based in Java
        int day = Integer.parseInt(parts[2]);
        return new GregorianCalendar(year, month, day);
    }

    // //Get Date Range method 1
    // private static ArrayList<GregorianCalendar> getDateRange(GregorianCalendar startDate, GregorianCalendar endDate) {
    //     ArrayList<GregorianCalendar> dateRange = new ArrayList<>();
    //     GregorianCalendar currentDate = new GregorianCalendar(startDate.get(GregorianCalendar.YEAR),
    //             startDate.get(GregorianCalendar.MONTH), startDate.get(GregorianCalendar.DAY_OF_MONTH));

    //     while (currentDate.before(endDate) || currentDate.equals(endDate)) {
    //         dateRange.add(new GregorianCalendar(currentDate.get(GregorianCalendar.YEAR),
    //                 currentDate.get(GregorianCalendar.MONTH), currentDate.get(GregorianCalendar.DAY_OF_MONTH)));
    //         currentDate.add(GregorianCalendar.DAY_OF_MONTH, 1);
    //     }

    //     return dateRange;
    // }

    //Get Date Range method 2
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
