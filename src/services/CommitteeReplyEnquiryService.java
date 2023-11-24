package services;

import java.util.ArrayList;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.ReplyEnquiryServiceable;
import models.CommitteeMember;
import models.Enquiry;
import models.Camp;

public class CommitteeReplyEnquiryService implements ReplyEnquiryServiceable {
    Scanner sc = new Scanner(System.in);
    public static final CampDao campDao = new CampDaoImpl();
    public static final CommitteeMemberDao staffDao = new CommitteeMemberDaoImpl();

    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void reply() {
        // downcast user into a committee object
        CommitteeMember comMember = (CommitteeMember) currentUserDao.getCurrentUser();
        String facilitatingCampID = comMember.getFacilitatingCamp();
        Camp facilitatingCamp = campDao.getCamps().get(facilitatingCampID);
        ArrayList<Integer> facilitingEnqIDList = new ArrayList<Integer>(facilitatingCamp.getEnquiries().keySet());
        int i = 0, choice, enquiryToReply;
        do {
            for (i = 0; i < facilitingEnqIDList.size(); i++) {
                System.out.printf("Choice %d : Enquiry ID %d", i, facilitingEnqIDList.get(i));
            }
            System.out.printf("Choice %d : Back", i + 1);
            System.out.printf("Select choice: ");
            choice = sc.nextInt();
            System.out.println();
            if (choice == i + 1)
                return;
            if (choice >= 0 || choice <= i) {
                enquiryToReply = facilitingEnqIDList.get(choice);
                break;
            }
        } while (true);
        System.out.println("Enter your reply: ");
        String replyString = sc.nextLine();
        Enquiry selectedEnquiry = facilitatingCamp.getEnquiries().get(enquiryToReply);
        selectedEnquiry.setReply(replyString);
        selectedEnquiry.setReplier(comMember.getUserID());
        comMember.setPoints(comMember.getPoints() + 1);
        System.out.printf("Reply sent! (Committee Member %s)\n", comMember.getUserID());
    }
}
