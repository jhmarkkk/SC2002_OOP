package views;

import java.util.ArrayList;
import java.util.Map;

import interfaces.views.EnquiryViewable;
import models.Camp;
import models.Enquiry;

public class StudentEnquiryView implements EnquiryViewable {
    public void view() {
        CampDaoInterface campDao;
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        ArrayList<String> registeredCampIDs = currentUserDao.getCurrentUser().getRegisteredCamps();
        String userID = currentUserDao.getCurrentUser().getUserID();
        ArrayList<Camp> registeredCamps;
        for (String registeredCampID : registeredCampIDs) {
            registeredCamps.add(campsMap.get(registeredCampID));
        }
        System.out.println("===== Registered Camps Enquiries =====");
        int campIndex = 1;
        int enqIndex = 1;
        for (Camp registeredCamp : registeredCamps) {
            System.out.printf("----- Enquiry for %s (%d)-----\n", registeredCamp.getName(), campIndex);
            for (Enquiry enq : registeredCamp.getEnquiries()) {
                if (enq.getEnquirer().equals(userID)) {
                    System.out.printf("***** Enquiry %d *****\n", enqIndex);
                    System.out.printf("%s\n", enq.getEnquiry());
                    if (enq.getReplier() != null) {
                        System.out.printf("~~~~~ Reply by: %s ~~~~~\n", enq.getReplier());
                        System.out.printf("%s\n", enq.getReply());
                    }
                }
                enqIndex++;
            }
            campIndex++;
        }

    }
}
