package services;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;
import interfaces.services.ReplyEnquiryServiceable;

import models.Staff;
import models.Enquiry;

public class StaffReplyEnquiryService implements ReplyEnquiryServiceable {
    Scanner sc = new Scanner(System.in);
    public static final CampDao campDao = new CampDaoImpl();
    public static final StaffDao staffDao = new StaffDaoImpl();
    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void reply() {
        // downcast user into a staff object
        Staff staff = (Staff) currentUserDao.getCurrentUser();
        ArrayList<Integer> enquiriesID = new ArrayList<Integer>();
        // first, put get all the created camps and for createdCamp, put the enquiry
        // into a arraylist
        for (String createdCampID : staff.getCreatedCamps()) {
            for (Enquiry enq : campDao.getCamps().get(createdCampID).getEnquiries().values()) {
                enquiriesID.add(enq.getEnquiryID());
            }
        }
        int i = 0, choice, enquiryReplyID;
        if (enquiriesID.size() == 0) {
            System.out.println("No enquries to reply to!");
            return;
        }
        for (i = 0; i < enquiriesID.size(); i++) {
            System.out.printf("Choice %d : Enquiry ID %d", i + 1, enquiriesID.get(i));
            System.out.printf("Choice %d : Back", i + 2);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 2)
                return;
            if (choice >= 1 || choice <= i + 2) {
                enquiryReplyID = enquiriesID.get(i);
                break;
            }
        }

    }
}
