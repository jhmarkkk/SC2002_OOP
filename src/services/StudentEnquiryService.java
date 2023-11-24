package services;

import java.util.Scanner;
import java.beans.Visibility;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;
import interfaces.services.EnquiryServiceable;
import models.Enquiry;
import models.Student;

import dao.CampDaoImpl;
import interfaces.dao.CampDao;
import models.Camp;


public class StudentEnquiryService implements EnquiryServiceable {
    Scanner sc = new Scanner(System.in);

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    private static final CampDao campDao = new CampDaoImpl();

    public void create(){
        Student student = currentUserDao.getCurrentUser().getRole();
        String facilitatingCamp = campDao.getCamps().get(student.getFacilitatingCamp());

        String faculty = currentUserDao.getCurrentUser().getFaculty();

        //Get list of camp name for the user
        ArrayList<String> studentCamps = new ArrayList<String>();
        for (Camp camp : campsMap.values()) {
            if (camp.getOpenTo.equals("NTU") || (faculty.equals(camp.getOpenTo())) && (camp.getVisibility() == Visibility.ON) && (!camp.getName().equals(facilitatingCamp))) {
                studentCamps.add(camp);
            }
        }

        //Print the list of camps
        System.out.println("----- List of Camps -----");
        int num = 0;
        for (int i = 0 ; i < studentCamps.size() ; i++){
            num = i + 1;
            System.out.println("(" + num + ") " + studentCamps.get(i) + "\n");
        }

        //Get camp name
        System.out.println("Camp to Enquiry: ");
        Integer campNameInput = sc.nextInt();
        String campName = studentCamps.get(campNameInput--);

        //Get enquiry
        System.out.println("Enter Enquiry: ");
        String enquiryString = sc.nextLine();

        //Get enquirer
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        String enquirer = currentUser.getUserID();

        //Create enquiry object
        Enquiry newEnquiry = new Enquiry(enquiryString, enquirer);

        // Get the user's existing enquiries map
        Map<String, ArrayList<Integer>> userEnquiries = currentUser.getEnquiries();

        // Check if the campName already exists in the user's enquiries
        if (userEnquiries.containsKey(campName)) {
            // Camp already exists, append the enquiryString into existingEnquiries Array
            ArrayList<Integer> existingEnquiries = userEnquiries.get(campName);
            existingEnquiries.add(newEnquiry.getEnquiryID());

        } else {
            // Camp does not exist, add the enquiryString with campName as key
            ArrayList<Integer> newEnquiries = new ArrayList<>();
            newEnquiries.add(newEnquiry.getEnquiryID());
            userEnquiries.put(campName, newEnquiries);
        }
        
        Camp enquiringCamp = campData.get(campName);
        Map<Integer, Enquiry> oldEnquiries = enquiringCamp.getEnquiries();
        
        // Updating Camp's Enquiry Map
        oldEnquiries.put(newEnquiry.getEnquiryID(), newEnquiry);
        enquiringCamp.setEnquiries(oldEnquiries);

    }

    public void delete(){
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        Camp facilitatingCamp = campDao.getCamps().get(currentUser.getFacilitatingCamp());

        Map<String, Camp> campData = campDao.getCamps();
        ArrayList<String> campNameList = new ArrayList<>(campData.keySet());
        ArrayList<String> enquiryList = campData.getEnquiries();

        // // Choose which camp to delete enquiry from
        // System.out.println("Delete from: ");
        // for (int i = 0 ; i < campNameList.size() ; i++){
        //     System.out.println(campNameList.get(i));
        // }
        // String campName = sc.nextLine();

        // If there are no enquiries
        if(enquiryList.size() == 0){
            System.out.println("No enquiries to delete!");
            return;
        }

        // Choose which enquiry to delete
        do {
            for (i = 0; i < enquiryList.size(); i++){
                System.out.printf("Choice %d : %s\n", i + 1, enquiryList.get(i));
                System.out.printf("Choice %d : Back", i + 2);
                System.out.printf("Select choice: ");
                int choice = sc.nextInt();
                System.out.println();
                if (choice == i + 2)
                    return;
                if (choice >= 1 || choice <= i + 1) {
                    String enquiryDelete = enquiryList.get(choice-1);
                    break;
                }
            }
        } while (true);

        // remove the enquiry from the user's knowledge
        enquiryList.remove(Integer.valueOf(enquiryDelete));
        // remove the enquiry from camp's knowledge
        Map<Integer, Enquiry> EnquiryMap = facilitatingCamp.getEnquiries();
        EnquiryMap.remove(enquiryDelete);
        facilitatingCamp.setEnquiries(EnquiryMap);

        System.out.printf("You have deleted the following enquiry: %s\n", enquiryDelete);
    }

    public void edit(){
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        Camp facilitatingCamp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        
        // Map<String, Camp> campData = campDao.getCamps();
        // ArrayList<String> campNameList = new ArrayList<>(campData.keySet());
        // ArrayList<String> enquiryList = campData.getEnquiries();

        Map<Integer, Enquiry> enquiryMap = facilitatingCamp.getEnquiries();
        ArrayList<Integer> enquiryIds = new ArrayList<>(enquiryMap.keySet());

        if (enquiryIds.size() == 0) {
            System.out.println("No enquiries to edit!");
            return;
        }

        int choice;
    do {
        System.out.println("Enquiries:");
        for (int i = 0; i < enquiryIds.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, enquiryMap.get(enquiryIds.get(i)).getEnquiry());
        }
        System.out.printf("%d. Back\n", enquiryIds.size() + 1);
        System.out.print("Select choice: ");
        choice = sc.nextInt();

        if (choice == enquiryIds.size() + 1) {
            return;
        }

        if (choice >= 1 && choice <= enquiryIds.size()) {
            int selectedEnquiryId = enquiryIds.get(choice - 1);
            Enquiry enquiryToEdit = enquiryMap.get(selectedEnquiryId);

            sc.nextLine(); // Consume the newline character left by nextInt()

            System.out.printf("Enter new enquiry for %s >>> ", facilitatingCamp.getName());
            String newEnquiryString = sc.nextLine();
            enquiryToEdit.setEnquiryString(newEnquiryString);

            System.out.printf("You have edited the following enquiry: %s\n", enquiryToEdit.getEnquiryString());
        } else {
            System.out.println("Invalid choice. Try again.");
        }
    } while (true);


        // // If there are no enquiries
        // if(enquiryList.size() == 0){
        //     System.out.println("No enquiries to edit!");
        //     return;
        // }
        // do {
        //     for (i = 0; i < enquiryList.size(); i++){
        //         System.out.printf("Choice %d : %s\n", i + 1, enquiryList.get(i));
        //         System.out.printf("Choice %d : Back", i + 2);
        //         System.out.printf("Select choice: ");
        //         int choice = sc.nextInt();
        //         System.out.println();
        //         if (choice == i + 2)
        //             return;
        //         if (choice >= 1 || choice <= i + 1) {
        //             String enquiryEdit = enquiryList.get(choice-1);
        //             break;
        //         }
        //     }
        // } while (true);

        // // change the enquiry from the camp's knowledge
        // Map<Integer, Enquiry> EnquiryMap = facilitatingCamp.getEnquiries();
        // Enquiry enquiryToEdit = EnquiryMap.get(enquiryEdit);
        // System.out.printf("Enter new Enquiry for %s >>> ", facilitatingCamp.getName());
        // String enquiryString = sc.nextLine();
        // enquiryToEdit.setEnquiries(enquiryString);

        // System.out.printf("You have edited the following enquiry: %s\n", enquiryEdit);
    }
}