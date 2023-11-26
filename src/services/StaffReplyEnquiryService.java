package services;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;
import interfaces.services.ReplyEnquiryServiceable;

import models.Staff;
import utils.InputUtil;
import utils.PrintUtil;
import models.Enquiry;
import models.Camp;

public class StaffReplyEnquiryService implements ReplyEnquiryServiceable {

    public static final CampDao campDao = new CampDaoImpl();

    public static final StaffDao staffDao = new StaffDaoImpl();

    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void reply() {
        
        int i = 0, choice;
        Camp createdCamp, selectedCamp;
        Integer selectedEnquiryID;
        Enquiry selectedEnquiry;
        String replyField;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        ArrayList<Enquiry> validEnquiryList = new ArrayList<Enquiry>();
        Map<Integer, Camp> enquiryIDToCampMap = new HashMap<Integer, Camp>();
        
        for (String createdCampName : currentUser.getCreatedCamps()) {
            createdCamp = campDao.getCamps().get(createdCampName);
            for (Enquiry enquiry : createdCamp.getEnquiries().values()) {
                if (enquiry.getEnquirer() != null) continue;

                enquiryIDToCampMap.put(enquiry.getEnquiryID(), campDao.getCamps().get(createdCampName));
                validEnquiryList.add(enquiry);
            }
        }

        if (validEnquiryList.size() == 0) {
            System.out.println("No enqury to reply");
            return;
        }

        do {
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
        System.out.printf("%-10s: %s\n","Camp" , selectedCamp.getName());
        System.out.printf("%-10s: %s\n","Enquiry" , selectedEnquiry.getEnquiry());
        do {
            replyField = InputUtil.nextString("Enter reply");
            if (!replyField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        selectedEnquiry.setReply(replyField);
        selectedEnquiry.setReplier(currentUser.getUserID());
        System.out.println("Enquiry replied");
    }
}
