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
            System.out.printf("%-10s: %s\n", "Enquiry ID", enq.getEnquiryID());
            System.out.printf("%-10s: %s\n", "Enquirer", studentsMap.get(enq.getEnquirer()).getName());
            System.out.printf("%-10s: %s\n", "Camp", facilitatingCamp.getName());
            System.out.printf("%-10s: %s\n", "Enquiry", enq.getEnquiry());
            if (enq.getReplier() != null) {
                System.out.printf("%-10s: %s\n", "Replier", enq.getReplier());
                System.out.printf("%-10s: %s\n", "Reply", enq.getReply());
            }
            System.out.println();
        }

    }
}
