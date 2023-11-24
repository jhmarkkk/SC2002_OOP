package services;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;
import interfaces.services.ReplyEnquiryServiceable;

import models.Staff;
import models.Enquiry;
import models.Camp;

public class StaffReplyEnquiryService implements ReplyEnquiryServiceable {
    Scanner sc = new Scanner(System.in);
    public static final CampDao campDao = new CampDaoImpl();
    public static final StaffDao staffDao = new StaffDaoImpl();
    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void reply() {
        // downcast user into a staff object
        Staff staff = (Staff) currentUserDao.getCurrentUser();
        ArrayList<Integer> enquiriesID = new ArrayList<Integer>();
        Map<Integer, Camp> enqIDtoCamp = new HashMap<Integer, Camp>();
        // first, put get all the created camps and for createdCamp, put the enquiry
        // into a arraylist
        for (String createdCampID : staff.getCreatedCamps()) {
            Camp createdCamp = campDao.getCamps().get(createdCampID);
            for (Enquiry enq : createdCamp.getEnquiries().values()) {
                enqIDtoCamp.put(enq.getEnquiryID(), campDao.getCamps().get(createdCampID));
                enquiriesID.add(enq.getEnquiryID());
            }
        }
        int i = 0, choice, enquiryReplyID;
        if (enquiriesID.size() == 0) {
            System.out.println("No enquries to reply to!");
            return;
        }
        do {
            for (i = 0; i < enquiriesID.size(); i++)
                System.out.printf("Choice %d : Enquiry ID %d", i, enquiriesID.get(i));
            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 0 || choice <= i) {
                enquiryReplyID = enquiriesID.get(i);
                break;
            }
        } while (true);
        System.out.println("Enter your reply: ");
        String replyString = sc.nextLine();
        Camp selectedCamp = enqIDtoCamp.get(enquiryReplyID);
        Enquiry selectedEnquiry = selectedCamp.getEnquiries().get(enquiryReplyID);
        selectedEnquiry.setReply(replyString);
        selectedEnquiry.setReplier(staff.getUserID());
        System.out.printf("Reply sent! (Staff %s)\n", staff.getUserID());
    }
}
