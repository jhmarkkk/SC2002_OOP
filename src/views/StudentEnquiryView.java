package views;

import java.util.ArrayList;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.EnquiryViewable;
import models.Camp;
import models.Enquiry;
import models.Student;

public class StudentEnquiryView implements EnquiryViewable {
    public void view() {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        Student student = (Student) currentUserDao.getCurrentUser();
        for (Map.Entry<String, ArrayList<Integer>> entry : student.getEnquiries().entrySet()) {
            String campName = entry.getKey();
            ArrayList<Integer> enquiryIDs = entry.getValue();
            Map<Integer, Enquiry> enquiryListforCamp = campsMap.get(campName).getEnquiries();
            for (Integer enquiryID : enquiryIDs) {
                Enquiry selectedEnquiry = enquiryListforCamp.get(enquiryID);
                System.out.printf("***** Enquiry %d *****\n", selectedEnquiry.getEnquiryID());
                System.out.printf("%s\n", selectedEnquiry.getEnquiry());
                if (selectedEnquiry.getReplier() != null) {
                    System.out.printf("~~~~~ Replied by: %s ~~~~~\n", selectedEnquiry.getReplier());
                    System.out.printf("%s\n", selectedEnquiry.getReply());
                }
            }
        }
    }
}

// for (String registeredCampID : registeredCampIDs) {
// Camp registeredCamp = campsMap.get(registeredCampID);
// for (Enquiry enq : registeredCamp.getEnquiries().values()) {
// if (enq.getEnquirer().equals(student.getUserID()))
// System.out.printf("***** Enquiry %d *****\n", enq.getEnquiryID());
// System.out.printf("%s\n", enq.getEnquiry());
// if (enq.getReplier() != null) {
// System.out.printf("~~~~~ Replied by: %s ~~~~~\n", enq.getReplier());
// System.out.printf("%s\n", enq.getReply());
// }
// }

// }