package views;

import java.util.ArrayList;
import java.util.Map;

import interfaces.views.EnquiryViewable;
import models.Camp;
import models.Enquiry;

public class StaffEnquiryView implements EnquiryViewable {
    public void view() {
        CampDaoInterface campDao;
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        ArrayList<String> createdCampIDs = currentUserDao.getCurrentUser().getCreatedCamps();
        ArrayList<Camp> createdCamps;
        for (String createdCampID : createdCampIDs) {
            createdCamps.add(campsMap.get(createdCampID));
        }
        System.out.println("===== Created Camps Enquiries =====");
        int campIndex = 1;
        int enqIndex = 1;
        for (Camp createdCamp : createdCamps) {
            System.out.printf("----- Enquiry for %s (%d)-----\n", createdCamp.getName(), campIndex);
            for (Enquiry enq : createdCamp.getEnquiries()) {
                System.out.printf("***** Enquiry %d *****\n", enqIndex);
                System.out.printf("~~~~~ From: %s ~~~~~\n");
                System.out.printf("%s\n", enq.getEnquiry());
                if (enq.getReplier() != null) {
                    System.out.printf("~~~~~ Reply by: %s ~~~~~\n", enq.getReplier());
                    System.out.printf("%s\n", enq.getReply());
                }
                enqIndex++;
            }
            campIndex++;
        }

    }
}
