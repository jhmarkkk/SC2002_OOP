package views;

import java.util.ArrayList;
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
import models.Student;

import utils.PrintUtil;

/**
 * The {@code StudentEnquiryView} class provides a view for students to see
 * their enquiries.
 * It implements the {@link EnquiryViewable} interface.
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public class StudentEnquiryView implements EnquiryViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    /**
     * Represents the data access object for student-related operations.
     */
    public static final StudentDao studentDao = new StudentDaoImpl();

    /**
     * Displays the enquiries made by the student.
     */
    public void view() {

        String campName;
        Enquiry enquiry;
        ArrayList<Integer> studentCampEnquiryIDList;
        Map<Integer, Enquiry> campEnquiryData;
        Map<String, Camp> campsData = campDao.getCamps();
        Map<String, Student> studentData = studentDao.getStudents();
        Student currentUser = (Student) currentUserDao.getCurrentUser();

        PrintUtil.header("My Enquiries");
        for (Map.Entry<String, ArrayList<Integer>> entry : currentUser.getEnquiries().entrySet()) {
            campName = entry.getKey();
            studentCampEnquiryIDList = entry.getValue();
            campEnquiryData = campsData.get(campName).getEnquiries();
            for (Integer enquiryID : studentCampEnquiryIDList) {
                enquiry = campEnquiryData.get(enquiryID);
                System.out.println("-".repeat(50));
                System.out.printf("%-15s: %s\n", "Enquiry ID", enquiry.getEnquiryID());
                System.out.printf("%-15s: %s\n", "Camp", campName);
                System.out.printf("%-15s: %s\n", "Enquired by", studentData.get(enquiry.getEnquirer()));
                System.out.printf("%-15s: %s\n", "Enquiry", enquiry.getEnquiry());
                if (enquiry.getReplier() == null) {
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
}
