package services;

import java.util.Scanner;
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
        //System.out.println("create() in StudentEnquiryService class");
        
        //Get camp name for the enquiry
        Map<String, Camp> campData = campDao.getCamps();
        ArrayList<String> campNameList = new ArrayList<>(campData.keySet());

        System.out.println("===== List of Camps =====");
        for (int i = 0 ; i < campNameList.size() ; i++){
            System.out.println(campNameList.get(i));
        }
        System.out.println("Camp to Enquiry: ");
        String campName = sc.nextLine();

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
        // TODO Auto-generated method stub
        System.out.println("delete() in StudentEnquiryService class");



    }

    public void edit(){
        // TODO Auto-generated method stub
        System.out.println("edit() in StudentEnquiryService class");
    }
}
