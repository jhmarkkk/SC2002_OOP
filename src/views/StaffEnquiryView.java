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
import models.Enquiry;
import models.Staff;
import models.Student;
import utils.PrintUtil;

public class StaffEnquiryView implements EnquiryViewable {
    public void view() {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        Staff staff = (Staff) currentUserDao.getCurrentUser();
        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentsMap = studentDao.getStudents();
        PrintUtil.header("Created Camp Enquiries");
        for (String createdCampID : staff.getCreatedCamps()) {
            Camp createdCamp = campsMap.get(createdCampID);
            for (Enquiry enq : createdCamp.getEnquiries().values()) {
                System.out.println("-".repeat(29));
                System.out.printf("%-10s: %s\n", "Enquiry ID", enq.getEnquiryID());
                System.out.printf("%-10s: %s\n", "Enquirer", studentsMap.get(enq.getEnquirer()));
                System.out.printf("%-10s: %s\n", "Camp", createdCamp.getName());
                System.out.printf("%-10s: %s\n", "Enquiry", enq.getEnquiry());
                if (enq.getReplier() != null) {
                    System.out.printf("%-10s: %s\n", "Replier", enq.getReplier());
                    System.out.printf("%-10s: %s\n", "Reply", enq.getReply());
                }
                System.out.println();
            }
        }
    }
}
