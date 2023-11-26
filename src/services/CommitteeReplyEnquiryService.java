package services;

import java.util.ArrayList;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ReplyEnquiryServiceable;

import models.Camp;
import models.CommitteeMember;
import models.Enquiry;

import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code CommitteeReplyEnquiryService} class provides methods for committee members to reply to enquiries in the context of a specific camp.
 * It implements the {@code ReplyEnquiryServiceable} interface for handling the process of replying to enquiries.
 * 
 * <p>The class allows committee members to view and reply to enquiries related to the camp they are facilitating.</p>
 *  
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.ReplyEnquiryServiceable
 * @see dao.CampDaoImpl
 * @see dao.CommitteeMemberDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see models.CommitteeMember
 * @see models.Enquiry
 * @see utils.InputUtil
 * @see utils.PrintUtil
 * @see models.Camp
 */
public class CommitteeReplyEnquiryService implements ReplyEnquiryServiceable {

    /**
     * The data access object for managing camps.
     */
    public static final CampDao campDao = new CampDaoImpl();

    /**
     * The data access object for managing committee members.
     */    
    public static final CommitteeMemberDao staffDao = new CommitteeMemberDaoImpl();

    /**
     * The data access object for managing the current user.
     */    
    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    /**
     * Allows a committee member to reply to an enquiry.
     */    
    public void reply() {

        int i = 0, choice;
        Enquiry selectedEnquiry;
        String replyField;
        CommitteeMember currentUser = (CommitteeMember) currentUserDao.getCurrentUser();
        String campName = currentUser.getFacilitatingCamp();
        Camp camp = campDao.getCamps().get(campName);
        ArrayList<Enquiry> validEnquiryList = new ArrayList<Enquiry>();

        for (Enquiry enquiry : camp.getEnquiries().values()) {
            if (enquiry.getEnquirer() == null)
                continue;

            validEnquiryList.add(enquiry);
        }

        if (validEnquiryList.size() == 0) {
            System.out.println("\n> No enquiry to reply");
            return;
        }

        do {
            PrintUtil.header("Reply Enquiry");
            for (i = 0; i < validEnquiryList.size(); i++)
                System.out.printf("%2d : Enquiry %d\n", i + 1, validEnquiryList.get(i).getEnquiryID());

            System.out.printf("%2d : Back\n", i + 1);
            choice = InputUtil.choice();
            if (choice == i + 1)
                return;

            if (choice >= 1 || choice <= i) {
                selectedEnquiry = validEnquiryList.get(choice - 1);
                break;
            }
        } while (true);

        System.out.printf("%-10s: %s\n", "Camp", campName);
        System.out.printf("%-10s: %s\n", "Enquiry", selectedEnquiry.getEnquiry());
        do {
            replyField = InputUtil.nextString("Enter reply");
            if (!replyField.isBlank())
                break;

            PrintUtil.invalid("input");
        } while (true);

        selectedEnquiry.setReply(replyField);
        selectedEnquiry.setReplier(currentUser.getUserID());
        currentUser.setPoints(currentUser.getPoints() + 1);
        System.out.println("\n> Enquiry replied");
    }
}
