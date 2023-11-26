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

/**
 * The {@code StaffReplyEnquiryService} class provides methods for staff members to reply to enquiries related to camps they are responsible for.
 * It implements the {@code ReplyEnquiryServiceable} interface for replying to specific enquiries and managing the reply process.
 * 
 * <p>The class allows staff members to view a list of pending enquiries for camps they created, select an enquiry to reply to, and submit a reply.</p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.ReplyEnquiryServiceable
 * @see dao.CampDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see dao.StaffDaoImpl
 * @see models.Staff
 * @see utils.InputUtil
 * @see utils.PrintUtil
 * @see models.Enquiry
 * @see models.Camp
 */
public class StaffReplyEnquiryService implements ReplyEnquiryServiceable {

    /**
     * The data access object for managing camps.
     */
    public static final CampDao campDao = new CampDaoImpl();

    /**
     * The data access object for managing staff members.
     */
    public static final StaffDao staffDao = new StaffDaoImpl();

    /**
     * The data access object for managing the current user.
     */
    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
    
    /**
     * Allows a staff member to reply to a selected enquiry for a camp they created.
     * The staff member can view a list of pending enquiries, select one to reply to, and submit a reply.
     * 
     * <p>If there are no pending enquiries, a message is displayed indicating that there are no enquiries to reply to.</p>
     * 
     * @see utils.InputUtil
     * @see utils.PrintUtil
     * @see models.Enquiry
     * @see models.Camp
     */
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
            System.out.println("No enqury to reply");
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
