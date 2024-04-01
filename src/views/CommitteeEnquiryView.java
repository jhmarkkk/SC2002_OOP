package views;

import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import interfaces.views.EnquiryViewable;

import models.Camp;
import models.CommitteeMember;
import models.Enquiry;
import models.Student;

import utils.PrintUtil;

/**
 * The {@code CommitteeEnquiryView} class provides functionality to view
 * enquiries related to a specific camp.
 * It implements the {@link EnquiryViewable} interface.
 *
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 *
 * @see interfaces.views.EnquiryViewable
 */
public class CommitteeEnquiryView implements EnquiryViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private static final StudentDao studentDao = new StudentDaoImpl();

    /**
     * Displays details of enquiries related to the camp facilitated by the current
     * committee member.
     */
    public void view() {
        CommitteeMember currentUser = (CommitteeMember) currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        Map<String, Student> studentData = studentDao.getStudents();

        PrintUtil.header(String.format("%s Enquiries", camp.getName()));
        for (Enquiry enquiry : camp.getEnquiries().values()) {
            System.out.println("-".repeat(50));
            System.out.printf("%-15s: %s\n", "Enquiry ID", enquiry.getEnquiryID());
            System.out.printf("%-15s: %s\n", "Camp", camp.getName());
            System.out.printf("%-15s: %s\n", "Enquired by", studentData.get(enquiry.getEnquirer()).getName());
            System.out.printf("%-15s: %s\n", "Enquiry", enquiry.getEnquiry());
            if (enquiry.getReplier() == null || enquiry.getReplier().equals("#NULL!")) {
                System.out.printf("%-15s: -\n", "Replied by");
                System.out.printf("%-15s: -\n", "Reply");
            } else {
                System.out.printf("%-15s: %s\n", "Replied by", enquiry.getReplier());
                System.out.printf("%-15s: %s\n", "Reply", enquiry.getReply());
            }

            System.out.println();
        }

    }
}
