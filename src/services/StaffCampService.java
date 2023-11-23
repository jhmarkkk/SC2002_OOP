package services;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import dao.CurrentUserDaoImpl;

import java.util.HashMap;
import java.util.Map;

import enums.Visibility;
import models.Camp;
import models.CommitteeMember;
import models.Enquiry;
import models.Staff;
import models.Student;
import models.Suggestion;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.CampServiceable;
import dao.CurrentUserDaoImpl;

public class StaffCampService {
    public void create() {
        // TODO Auto-generated method stub
        
        //Initialize Camp variables
        ArrayList<Date> dates = new ArrayList<>();
        Date registrationClosingDate;
        //Staff staffInCharge;
        ArrayList<Student> attendees = new ArrayList<>();
        ArrayList<String> withdrawnAttendees = new ArrayList<>();
        ArrayList<CommitteeMember> committeeMembers = new ArrayList<>();
        Visibility visibility = Visibility.OFF;
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        ArrayList<Suggestion> suggestions = new ArrayList<>();

        Scanner sc = new Scanner(System.in);


        //Camp Name
        System.out.println("Enter camp name: ");
        String name = sc.nextLine();
        System.out.println("Camp name: " + name + "\n");


        // //Dates
        // ArrayList<Date> dates = new ArrayList<>();2;

        // System.out.println("How many days?");
        // int numOfDates = sc.nextInt();

        // for(int i=0; i<numOfDates; i++){
        //     System.out.println("Enter camp date " + i+1 + ": ");
        //     dates.add(sc.nextLine());
        // }
        // System.out.println("Camp dates: " + dates + "\n");


        // //Registration Closing Date
        // System.out.println("Enter registration closing date: ");
        // Date registrationClosingDate = sc.nextLine();
        // System.out.println("Registration Closing Date: " + registrationClosingDate + "\n");


        //Open To
        System.out.println("Is this camp open to School or NTU?");
        String openTo = sc.nextLine();
        System.out.println("Camp is only open to " + openTo + "\n");


        //Location
        System.out.println("Enter camp location: ");
        String location = sc.nextLine();
        System.out.println("Camp location: " + location + "\n");


        //Total Slots
        System.out.println("Enter toal number of slots: ");
        int totalSlots = sc.nextInt();
        System.out.println("Total number of slots: " + totalSlots + "\n");
        

        //Committee Slots
        System.out.println("Enter number of committee slots: ");
        int temp = sc.nextInt();
        while (temp > 10){
            System.out.println("Max number of committee slots is 10. Try again.");
            System.out.println("Enter number of committee slots: ");
            temp = sc.nextInt();
        }
        int committeeSlots = temp;
        System.out.println("Number of committee slots: " + committeeSlots + "\n");


        //Description
        System.out.println("Enter camp description: ");
        String description = sc.next();
        System.out.println("Camp description: " + description + "\n");


        //Staff in Charge
        //staff in charge = user
        private CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        Staff staffInCharge = (Staff)currentUserDao.getCurrentUser();


        // //Attendees
        // //attendees = students
        // private static final StudentDao studentDao = new StudentDaoImpl;
        // ArrayList<Student> attendees = 

        //Withdrawn Attendees


        // //Committee Members
        // private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDao();
        // ArrayList<CommitteeMember> committeeMembers = 
        
        //Visibility
        

        //Enquiries

        //Suggestions

        // Camp camp = new Camp(name, dates, registrationClosingDate, openTo, location,
        //         totalSlots, committeeSlots, description, staffInCharge, attendees,
        //         withdrawnAttendees, committeeMembers, visibility,
        //         enquiries, suggestions);

        Map<String, Camp> campMap = new HashMap<>();

        Camp camp = new Camp(name, openTo, location, totalSlots, committeeSlots, description);

        campMap.put(name, camp);

    }

    public void delete() {
        // TODO Auto-generated method stub
    }

    public void edit() {
        // TODO Auto-generated method stub
    }
}
