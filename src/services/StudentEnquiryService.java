package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enums.Role;
import enums.Visibility;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.EnquiryServiceable;

import models.Camp;
import models.CommitteeMember;
import models.Enquiry;
import models.Student;

import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code StudentEnquiryService} class provides methods to create, delete, and edit enquiries for a student.
 * It implements the {@link EnquiryServiceable} interface.
 *
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 *
 * @see interfaces.services.EnquiryServiceable
 */
public class StudentEnquiryService implements EnquiryServiceable {

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private static final CampDao campDao = new CampDaoImpl();

    /**
     * Creates a new enquiry for the current student.
     * The student selects a camp and provides an enquiry message.
     * The enquiry is associated with the selected camp and stored in the student's enquiries.
     */
    public void create() {

        int i = 0, choice;
        Camp selectedCamp;
        Enquiry newEnquiry;
        Map<Integer, Enquiry> enquiryData;
        ArrayList<Integer> studentCampEnquiryList;
        Student currentUser = (Student)currentUserDao.getCurrentUser();
        String faculty = currentUser.getFaculty(), selectedCampName, enquiryField;
        Map<String, ArrayList<Integer>> studentEnquiryData = currentUser.getEnquiries();
        Map<String, Camp> campData = campDao.getCamps();
        ArrayList<String> validCampList = new ArrayList<String>();
        String facilitatingCamp = null;

        if (currentUser.getRole() == Role.COMMITTEE) {
            facilitatingCamp = ((CommitteeMember)currentUser).getFacilitatingCamp();
        }

        for (Camp camp : campData.values()) {
            if (camp.getVisibility() == Visibility.OFF) continue;

            if (camp.getName().equals(facilitatingCamp)) continue;

            if (camp.getOpenTo().equals("NTU") || (camp.getOpenTo().equals(faculty)))
                validCampList.add(camp.getName());
        }       

        do {
            PrintUtil.header("Enquire About Camp");
            if (validCampList.isEmpty()) {
                System.out.println("\n> No available camps to enquire");
                return;
            }

			for (i = 0; i < validCampList.size(); i++)
				System.out.printf("%2d. %s\n", i + 1, validCampList.get(i));

			System.out.printf("%2d. Back\n", i + 1);
			choice = InputUtil.choice();
			if (choice == i + 1)
				return;

			if (choice >= 0 || choice <= i) {
                selectedCampName = validCampList.get(choice - 1);
				selectedCamp = campData.get(selectedCampName);
				break;
			}

			PrintUtil.invalid("choice");
		} while (true);

        do {
            enquiryField = InputUtil.nextString("Enter enquiry");
            if (!enquiryField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        newEnquiry = new Enquiry(enquiryField, currentUser.getUserID());
        enquiryData = selectedCamp.getEnquiries();
        enquiryData.put(newEnquiry.getEnquiryID(), newEnquiry);

        if (studentEnquiryData.containsKey(selectedCampName)) {
            studentCampEnquiryList = studentEnquiryData.get(selectedCampName);
            studentCampEnquiryList.add(newEnquiry.getEnquiryID());
        } else {
            studentCampEnquiryList = new ArrayList<>();
            studentCampEnquiryList.add(newEnquiry.getEnquiryID());
            studentEnquiryData.put(selectedCampName, studentCampEnquiryList);
        }

        System.out.println("\n> Enquiry created");
    }

    /**
     * Deletes a selected enquiry for the current student.
     * The student selects an enquiry to delete, and the associated camp is updated accordingly.
     */
    public void delete() {

        int i = 0, choice;
        Integer selectedEnquiryID;
        String campName;
        Camp camp, selectedCamp;
        ArrayList<Integer> enquiryIDList;
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        Map<String, Camp> campData = campDao.getCamps();
        Map<String, ArrayList<Integer>> studentEnquiryData = currentUser.getEnquiries();
        Map<Integer, Camp> enquiryIDToCampMap = new HashMap<Integer, Camp>();
        ArrayList<Integer> studentAllEnquiryList = new ArrayList<Integer>(), selectedCampStudentEnquiryList;

        for (Map.Entry<String, ArrayList<Integer>> entry : studentEnquiryData.entrySet()) {
            campName = entry.getKey();
            camp = campData.get(campName);
            enquiryIDList = entry.getValue();
            for (Integer enquiryID : enquiryIDList) {
                if (camp.getEnquiries().get(enquiryID).getReplier() != null) continue;
                enquiryIDToCampMap.put(enquiryID, camp);
                studentAllEnquiryList.add(enquiryID);
            }
        }

        if (studentAllEnquiryList.isEmpty()) {
            System.out.println("\n> No valid enquiry to delete");
            return;
        }
        
        do {
            PrintUtil.header("Delete Enquiry");
            for (i = 0; i < studentAllEnquiryList.size(); i++)
                System.out.printf("%2d. Enquiry ID %d\n", i + 1, studentAllEnquiryList.get(i));

            System.out.printf("%2d. Back\n", i + 1);
            choice = InputUtil.choice();
            if (choice == i + 1)
                return;

            if (choice >= 1 || choice <= i) {
                selectedEnquiryID = studentAllEnquiryList.get(choice - 1);
                break;
            }
        } while (true);
        
        selectedCamp = enquiryIDToCampMap.get(selectedEnquiryID);
        selectedCamp.getEnquiries().remove(selectedEnquiryID);

        selectedCampStudentEnquiryList = studentEnquiryData.get(selectedCamp.getName());
        selectedCampStudentEnquiryList.remove(selectedEnquiryID);
        if (selectedCampStudentEnquiryList.isEmpty()) studentEnquiryData.remove(selectedCamp.getName());
        
        System.out.println("\n> Enquiry deleted");
    }
    
    /**
     * Edits a selected enquiry for the current student.
     * The student selects an enquiry to edit and provides a new enquiry message.
     * The selected enquiry is updated with the new message.
     */
    public void edit() {
        
        int i = 0, choice;
        Integer selectedEnquiryID;
        String campName, newEnquiryField;
        Camp camp, selectedCamp;
        Enquiry selectedEnquiry;
        ArrayList<Integer> enquiryIDList;
        Student currentUser = (Student) currentUserDao.getCurrentUser();
        Map<String, Camp> campData = campDao.getCamps();
        Map<String, ArrayList<Integer>> studentEnquiryData = currentUser.getEnquiries();
        Map<Integer, Camp> enquiryIDToCampMap = new HashMap<Integer, Camp>();
        ArrayList<Integer> studentAllEnquiryList = new ArrayList<Integer>();

        for (Map.Entry<String, ArrayList<Integer>> entry : studentEnquiryData.entrySet()) {
            campName = entry.getKey();
            camp = campData.get(campName);
            enquiryIDList = entry.getValue();
            for (Integer enquiryID : enquiryIDList) {
                if (camp.getEnquiries().get(enquiryID).getReplier() != null) continue;
                enquiryIDToCampMap.put(enquiryID, camp);
                studentAllEnquiryList.add(enquiryID);
            }
        }

        if (studentAllEnquiryList.isEmpty()) {
            System.out.println("\n>  valid enquiry to edit");
            return;
        }
        
        do {
            PrintUtil.header("Edit Enquiry");
            for (i = 0; i < studentAllEnquiryList.size(); i++)
                System.out.printf("%2d. Enquiry ID %d\n", i + 1, studentAllEnquiryList.get(i));

            System.out.printf("%2d. Back\n", i + 1);
            choice = InputUtil.choice();
            if (choice == i + 1)
                return;

            if (choice >= 1 || choice <= i) {
                selectedEnquiryID = studentAllEnquiryList.get(choice - 1);
                break;
            }
        } while (true);

        selectedCamp = enquiryIDToCampMap.get(selectedEnquiryID);
        selectedEnquiry = selectedCamp.getEnquiries().get(selectedEnquiryID);
        do {
            newEnquiryField = InputUtil.nextString("Enter enquiry");
            if (!newEnquiryField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        selectedEnquiry.setEnquiry(newEnquiryField);
        System.out.println("\n> Enquiry updated");
    }
}