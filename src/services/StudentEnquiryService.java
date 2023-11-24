package services;

import java.util.Scanner;

import interfaces.services.EnquiryServiceable;
import models.Enquiry;
import models.Camp;

public class StudentEnquiryService implements EnquiryServiceable {

    Scanner sc = new Scanner(System.in);

    public void create(){
        System.out.println("create() in StudentEnquiryService class");
        
        // You can access the enquiryCounter like this
        Integer enquiryID = Camp.getEnquiryCounter();
        //need to setEn

        System.out.println("Enter Enquiry >>> ");
        String enquiryString = sc.nextLine();

        String enquirer = "enquirier";
        String replier = null;
        String reply = null;

        Enquiry enquiry = new Enquiry(enquiryID, enquiryString, enquirer, replier, reply);
        //Enquiry(enquiryID, enquiry, enquiryString, replier, reply);


        
    }
    public void delete(){
        // TODO Auto-generated method stub
    }
    public void edit(){
        // TODO Auto-generated method stub
    }
}
