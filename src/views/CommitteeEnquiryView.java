package views;

import interfaces.views.EnquiryViewable;
import models.Camp;
import models.CommitteeMember;
import models.Enquiry;
import models.Student;
import utils.PrintUtil;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;
import interfaces.dao.CampDao;

import java.util.Map;

import dao.CampDaoImpl;

public class CommitteeEnquiryView implements EnquiryViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private static final StudentDao studentDao = new StudentDaoImpl();

    public void view() {
        CommitteeMember currentUser = (CommitteeMember) currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        Map<String, Student> studentData = studentDao.getStudents();

        PrintUtil.header(String.format("View %s Enquiries", camp.getName()));
        for (Enquiry enquiry : camp.getEnquiries().values()) {
            System.out.println("-".repeat(29));
            System.out.printf("%-15s: %s\n","Enquiry ID" , enquiry.getEnquiryID());
            System.out.printf("%-15s: %s\n","Camp" , camp.getName());
            System.out.printf("%-15s: %s\n","Enquired by" , studentData.get(enquiry.getEnquirer()));
            System.out.printf("%-15s: %s\n","Enquiry" , enquiry.getEnquiry());
            System.out.printf("%-15s: %s\n","Replier" , enquiry.getReplier());
            System.out.printf("%-15s: %s\n","Reply" , enquiry.getReply());
            System.out.println();
        }

    }
}
