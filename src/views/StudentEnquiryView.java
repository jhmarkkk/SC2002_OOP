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

public class StudentEnquiryView implements EnquiryViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public static final StudentDao studentDao = new StudentDaoImpl();
    
    public void view() {

        String campName;
        Enquiry enquiry;
        ArrayList<Integer> studentCampEnquiryIDList;
        Map<Integer, Enquiry> campEnquiryData;
        Map<String, Camp> campsData = campDao.getCamps();
        Map<String, Student> studentData = studentDao.getStudents();
        Student currentUser = (Student)currentUserDao.getCurrentUser();
    
        PrintUtil.header("My Enquiries");
        for (Map.Entry<String, ArrayList<Integer>> entry : currentUser.getEnquiries().entrySet()) {
            campName = entry.getKey();
            studentCampEnquiryIDList = entry.getValue();
            campEnquiryData = campsData.get(campName).getEnquiries();
            for (Integer enquiryID : studentCampEnquiryIDList) {
                enquiry = campEnquiryData.get(enquiryID);
                System.out.println("-".repeat(50));
                System.out.printf("%-15s: %s\n","Enquiry ID" , enquiry.getEnquiryID());
                System.out.printf("%-15s: %s\n","Camp" , campName);
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
