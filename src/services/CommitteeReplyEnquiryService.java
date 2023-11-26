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
import utils.InputUtil;
import utils.PrintUtil;
import models.Camp;

public class CommitteeReplyEnquiryService implements ReplyEnquiryServiceable {

    public static final CampDao campDao = new CampDaoImpl();

    public static final CommitteeMemberDao staffDao = new CommitteeMemberDaoImpl();

    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void reply() {
        
        int i = 0, choice;
        Enquiry selectedEnquiry;
        String replyField;
        CommitteeMember currentUser = (CommitteeMember)currentUserDao.getCurrentUser();
        String campName = currentUser.getFacilitatingCamp();
        Camp camp = campDao.getCamps().get(campName);
        ArrayList<Enquiry> validEnquiryList = new ArrayList<Enquiry>();

        for (Enquiry enquiry : camp.getEnquiries().values()) {
            if (enquiry.getEnquirer() != null) continue;

            validEnquiryList.add(enquiry);
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


        System.out.printf("%-10s: %s\n","Camp" , campName);
        System.out.printf("%-10s: %s\n","Enquiry" , selectedEnquiry.getEnquiry());
        do {
            replyField = InputUtil.nextString("Enter reply");
            if (!replyField.isBlank()) break;

            PrintUtil.invalid("input");
        } while (true);

        selectedEnquiry.setReply(replyField);
        selectedEnquiry.setReplier(currentUser.getUserID());
        currentUser.setPoints(currentUser.getPoints() + 1);
        System.out.println("Enquiry replied");
    }
}
