package services;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;
import enums.Role;
import enums.Visibility;
import interfaces.services.EnquiryServiceable;
import models.Enquiry;
import models.Student;

import dao.CampDaoImpl;
import interfaces.dao.CampDao;
import models.Camp;
import models.CommitteeMember;
import models.User;

public class StudentEnquiryService implements EnquiryServiceable {
    Scanner sc = new Scanner(System.in);

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    private static final CampDao campDao = new CampDaoImpl();

    public void create() {
        // Student student = currentUserDao.getCurrentUser().getRole();
        User user = currentUserDao.getCurrentUser();
        String facilitatingCamp = null;
        if (user.getRole().equals(Role.COMMITTEE)) {
            CommitteeMember committeeMember = (CommitteeMember) currentUserDao.getCurrentUser();
            facilitatingCamp = committeeMember.getFacilitatingCamp();
        }

        String faculty = currentUserDao.getCurrentUser().getFaculty();

        // Get list of camp name for the user
        Map<String, Camp> campsMap = campDao.getCamps();
        ArrayList<String> studentCamps = new ArrayList<String>();
        for (Camp camp : campsMap.values()) {
            if (camp.getOpenTo().equals("NTU") || (faculty.equals(camp.getOpenTo()))
                    && (camp.getVisibility() == Visibility.ON) && (!camp.getName().equals(facilitatingCamp))) {
                studentCamps.add(camp.getName());
            }
        }

        // Print the list of camps
        System.out.println("----- List of Camps -----");
        int num = 0;
        for (int i = 0; i < studentCamps.size(); i++) {
            num = i + 1;
            System.out.println("(" + num + ") " + studentCamps.get(i) + "\n");
        }

        // Get camp name
        System.out.println("Camp to Enquiry: ");
        Integer campNameInput = sc.nextInt();
        String campName = studentCamps.get(campNameInput--);

        // Get enquiry
        System.out.println("Enter Enquiry: ");
        String enquiryString = sc.nextLine();

        // Get enquirer
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        String enquirer = currentUser.getUserID();

        // Create enquiry object
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

        Camp enquiringCamp = campsMap.get(campName);
        Map<Integer, Enquiry> oldEnquiries = enquiringCamp.getEnquiries();

        // Updating Camp's Enquiry Map
        oldEnquiries.put(newEnquiry.getEnquiryID(), newEnquiry);
        enquiringCamp.setEnquiries(oldEnquiries);

    }

    public void delete() {
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        // String facilitatingCamp =
        // campDao.getCamps().get(currentUser.getFacilitatingCamp());

        Map<String, Camp> campData = campDao.getCamps();
        Map<String, ArrayList<Integer>> campIDToEnquiryList = currentUser.getEnquiries();
        Map<Integer, Camp> enquiryIDToCamp = new HashMap<Integer, Camp>();
        ArrayList<Integer> allStudentEnquiriesList = new ArrayList<Integer>();
        for (Map.Entry<String, ArrayList<Integer>> entry : campIDToEnquiryList.entrySet()) {
            String campName = entry.getKey();
            Camp currentCamp = campData.get(campName);
            ArrayList<Integer> enquiryIDs = entry.getValue();
            for (Integer enquiryID : enquiryIDs) {
                enquiryIDToCamp.put(enquiryID, currentCamp);
                allStudentEnquiriesList.add(enquiryID);
            }
        }
        int i = 0, choice, selectedEnquiryID;
        do {
            for (i = 0; i < allStudentEnquiriesList.size(); i++)
                System.out.printf("Choice %d : Suggestion ID %d\n", i + 1, allStudentEnquiriesList.get(i));
            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 1 || choice <= i) {
                selectedEnquiryID = allStudentEnquiriesList.get(choice - 1);
                break;
            }
        } while (true);
        // get the camp that the enquiry to delete belongs to
        Camp selectedCamp = enquiryIDToCamp.get(selectedEnquiryID);
        // get the array list of enquiries from the selected Camp
        ArrayList<Integer> selectedCampEnquiries = campIDToEnquiryList.get(selectedCamp.getName());
        // remove student's knowledge of enquiry
        selectedCampEnquiries.remove(Integer.valueOf(selectedEnquiryID));
        // remove camp's knowledge of enquiry
        selectedCamp.getEnquiries().remove(selectedEnquiryID);

    }

    public void edit() {
        // if they are in student enquiry service, the current User object can be safely
        // downcasted to a Student since CommitteeMember is a subclass of Student
        Student currentStudent = (Student) currentUserDao.getCurrentUser();
        Map<String, Camp> campData = campDao.getCamps();
        Map<String, ArrayList<Integer>> campIDToEnquiryListForUser = new HashMap<String, ArrayList<Integer>>(
                currentStudent.getEnquiries());
        Map<Integer, Camp> enquiryIDToCamp = new HashMap<Integer, Camp>();
        ArrayList<Integer> allStudentEnquiriesList = new ArrayList<Integer>();
        for (Map.Entry<String, ArrayList<Integer>> entry : campIDToEnquiryListForUser.entrySet()) {
            String campName = entry.getKey();
            Camp currentCamp = campData.get(campName);
            ArrayList<Integer> enquiryIDs = entry.getValue();
            for (Integer enquiryID : enquiryIDs) {
                enquiryIDToCamp.put(enquiryID, currentCamp);
                allStudentEnquiriesList.add(enquiryID);
            }
        }

        if (allStudentEnquiriesList.size() == 0) {
            System.out.println("No enquiries to edit!");
            return;
        }
        int i = 0, choice, selectedEnquiryID;
        do {
            for (i = 0; i < allStudentEnquiriesList.size(); i++)
                System.out.printf("Choice %d : Suggestion ID %d for Camp %s\n", i + 1, allStudentEnquiriesList.get(i),
                        enquiryIDToCamp.get(allStudentEnquiriesList.get(i)).getName());
            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 1 || choice <= i) {
                selectedEnquiryID = allStudentEnquiriesList.get(choice - 1);
                break;
            }
        } while (true);
        // get the camp that the enquiry to delete belongs to
        Camp selectedCamp = enquiryIDToCamp.get(selectedEnquiryID);
        // get the Enquiry that the student wants to edit
        Enquiry selectedEnquiry = selectedCamp.getEnquiries().get(selectedEnquiryID);
        System.out.printf("Enter amended enquiry for %s >>> ", selectedCamp.getName());
        String newEnquiryString = sc.nextLine();
        selectedEnquiry.setEnquiry(newEnquiryString);
        System.out.printf("Enquiry for %s with id $d successfully edited!\n", selectedCamp.getName(),
                selectedEnquiryID);
    }
}
// int choice;
// do {
// System.out.println("Enquiries:");
// for (int i = 0; i < enquiryIds.size(); i++) {
// System.out.printf("%d. %s\n", i + 1,
// enquiryMap.get(enquiryIds.get(i)).getEnquiry());
// }
// System.out.printf("%d. Back\n", enquiryIds.size() + 1);
// System.out.print("Select choice: ");
// choice = sc.nextInt();

// if (choice == enquiryIds.size() + 1) {
// return;
// }

// if (choice >= 1 && choice <= enquiryIds.size()) {
// int selectedEnquiryId = enquiryIds.get(choice - 1);
// Enquiry enquiryToEdit = enquiryMap.get(selectedEnquiryId);

// sc.nextLine(); // Consume the newline character left by nextInt()

// System.out.printf("Enter new enquiry for %s >>> ",
// facilitatingCamp.getName());
// String newEnquiryString = sc.nextLine();
// enquiryToEdit.setEnquiryString(newEnquiryString);

// System.out.printf("You have edited the following enquiry: %s\n",
// enquiryToEdit.getEnquiryString());
// } else {
// System.out.println("Invalid choice. Try again.");
// }
// } while (true);
// // If there are no enquiries
// if(enquiryList.size() == 0){
// System.out.println("No enquiries to edit!");
// return;
// }
// do {
// for (i = 0; i < enquiryList.size(); i++){
// System.out.printf("Choice %d : %s\n", i + 1, enquiryList.get(i));
// System.out.printf("Choice %d : Back", i + 2);
// System.out.printf("Select choice: ");
// int choice = sc.nextInt();
// System.out.println();
// if (choice == i + 2)
// return;
// if (choice >= 1 || choice <= i + 1) {
// String enquiryEdit = enquiryList.get(choice-1);
// break;
// }
// }
// } while (true);

// // change the enquiry from the camp's knowledge
// Map<Integer, Enquiry> EnquiryMap = facilitatingCamp.getEnquiries();
// Enquiry enquiryToEdit = EnquiryMap.get(enquiryEdit);
// System.out.printf("Enter new Enquiry for %s >>> ",
// facilitatingCamp.getName());
// String enquiryString = sc.nextLine();
// enquiryToEdit.setEnquiries(enquiryString);

// System.out.printf("You have edited the following enquiry: %s\n",
// enquiryEdit);