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

/**
 * The {@code StaffEnquiryView} class provides functionality to view enquiries made for the camps created by the current staff member.
 * It implements the {@link EnquiryViewable} interface.
 *
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 *
 * @see interfaces.views.EnquiryViewable
 */
public class StaffEnquiryView implements EnquiryViewable {
    /**
     * Represents the data access object for camp-related operations.
     */
    public static final CampDao campDao = new CampDaoImpl();
    
    /**
     * Represents the data access object for the current user.
     */
    public static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    /**
     * Represents the data access object for student-related operations.
     */
    public static final StudentDao studentDao = new StudentDaoImpl();
    
    /**
     * Displays details of enquiries made for the camps created by the current staff member.
     */
    public void view() {

        Camp camp;
        Staff currentUser = (Staff) currentUserDao.getCurrentUser();
        Map<String, Student> studentData = studentDao.getStudents();

        PrintUtil.header("Created Camp Enquiries");
        for (String createdCampID : currentUser.getCreatedCamps()) {
            camp = campDao.getCamps().get(createdCampID);
            for (Enquiry enquiry : camp.getEnquiries().values()) {
                System.out.println("-".repeat(50));
                System.out.printf("%-15s: %s\n","Enquiry ID" , enquiry.getEnquiryID());
                System.out.printf("%-15s: %s\n","Camp" , camp.getName());
                System.out.printf("%-15s: %s\n","Enquired by" , studentData.get(enquiry.getEnquirer()));
                System.out.printf("%-15s: %s\n","Enquiry" , enquiry.getEnquiry());
                if (enquiry.getEnquirer() == null) {
                    System.out.printf("%-15s: -\n","Replied by");
                    System.out.printf("%-15s: -\n","Reply");
                } else {
                    System.out.printf("%-15s: %s\n","Replied by" , enquiry.getReplier());
                    System.out.printf("%-15s: %s\n","Reply" , enquiry.getReply());
                }
                
                System.out.println();
            }
        }
    }
}
