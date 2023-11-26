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

    public static final CampDao campDao = new CampDaoImpl();

    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public static final StudentDao studentDao = new StudentDaoImpl();

    public void view() {

        Camp camp;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        Map<String, Student> studentData = studentDao.getStudents();

        PrintUtil.header("View Created Camp Enquiries");
        for (String createdCampID : currentUser.getCreatedCamps()) {
            camp = campDao.getCamps().get(createdCampID);
            for (Enquiry enquiry : camp.getEnquiries().values()) {
                System.out.println("-".repeat(29));
                System.out.printf("%-15s: %s\n","Enquiry ID" , enquiry.getEnquiryID());
                System.out.printf("%-15s: %s\n","Camp" , camp.getName());
                System.out.printf("%-15s: %s\n","Enquired by" , studentData.get(enquiry.getEnquirer()));
                System.out.printf("%-15s: %s\n","Enquiry" , enquiry.getEnquiry());
                System.out.printf("%-15s: %s\n","Replied by" , enquiry.getReplier());
                System.out.printf("%-15s: %s\n","Reply" , enquiry.getReply());
                System.out.println();
            }
        }
    }
}
