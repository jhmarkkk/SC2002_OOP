package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;
import dao.StudentDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.dao.StudentDao;
import interfaces.services.ReplyEnquiryServiceable;

import models.Camp;
import models.Enquiry;
import models.Staff;

import utils.InputUtil;
import utils.PrintUtil;

public class StaffReplyEnquiryService implements ReplyEnquiryServiceable {

    public static final CampDao campDao = new CampDaoImpl();

    public static final StaffDao staffDao = new StaffDaoImpl();

    public static final StudentDao studentDao = new StudentDaoImpl();

    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void reply() {
        
        int i = 0, choice;
        Camp camp, selectedCamp;
        Enquiry selectedEnquiry;
        String replyField;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        ArrayList<Enquiry> validEnquiryList = new ArrayList<Enquiry>();
        Map<Integer, Camp> enquiryIDToCampMap = new HashMap<Integer, Camp>();
        
        for (String createdCampName : currentUser.getCreatedCamps()) {
            camp = campDao.getCamps().get(createdCampName);
            for (Enquiry enquiry : camp.getEnquiries().values()) {
                if (enquiry.getEnquirer() != null) continue;

                enquiryIDToCampMap.put(enquiry.getEnquiryID(), campDao.getCamps().get(createdCampName));
                validEnquiryList.add(enquiry);
            }
        }

        if (validEnquiryList.size() == 0) {
            System.out.println("\n> No enqury to reply");
            return;
        }

        do {
            PrintUtil.header("Reply Enquiry");
            for (i = 0; i < validEnquiryList.size(); i++)
                System.out.printf("%2d : Enquiry %d\n", i + 1, validEnquiryList.get(i).getEnquiryID());

            System.out.printf("%2d : Back\n", i + 1);
            choice = InputUtil.choice();
            if (choice == i + 1) return;
                
            if (choice >= 1 || choice <= i) {
                selectedEnquiry = validEnquiryList.get(choice - 1);
                break;
            }
        } while (true);

        selectedCamp = enquiryIDToCampMap.get(selectedEnquiry.getEnquiryID());
        System.out.printf("%-15s: %s\n","Camp" , selectedCamp.getName());
        System.out.printf("%-15s: %s\n","Enquired by" , studentDao.getStudents().get(selectedEnquiry.getEnquirer()).getName());
        System.out.printf("%-15s: %s\n","Enquiry" , selectedEnquiry.getEnquiry());
        do {
            replyField = InputUtil.nextString("Enter reply");
            if (!replyField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        selectedEnquiry.setReply(replyField);
        selectedEnquiry.setReplier(currentUser.getUserID());
        System.out.println("\n> Enquiry replied");
    }
}
