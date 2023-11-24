package services;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;
import interfaces.services.EnquiryServiceable;
import models.Enquiry;
import models.Student;


public class StudentEnquiryService implements EnquiryServiceable {

    Scanner sc = new Scanner(System.in);

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private Map<Integer, Enquiry> enquiryMap = new HashMap<>();

    public void create(){
        //System.out.println("create() in StudentEnquiryService class");
        
        System.out.println("Enter Enquiry >>> ");
        String enquiryString = sc.nextLine();

        Student currentUser = (Student) currentUserDao.getCurrentUser();
        String enquirer = currentUser.getName();

        Enquiry enquiry = new Enquiry(enquiryString, enquirer);

        enquiryMap.put(enquiry.getEnquiryID(), enquiry);

        // // ===== For testing purpose only, remove/comment out after testing =====
        // System.out.println("enquiryString: " + enquiry.getEnquiry());
        // System.out.println("enquirer: " + enquiry.getEnquirer());
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
