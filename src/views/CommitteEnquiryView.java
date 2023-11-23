package views;

import interfaces.views.EnquiryViewable;
import models.Camp;
import models.Enquiry;

public class CommitteEnquiryView implements EnquiryViewable {
    public void view() {
        CurrentUserDaoInterface currentUserDao;
        String facilitatingCampID = currentUserDao.getCurrentUser().getFacilitatingCamp();
        CampDaoInterface campDao;
        Camp facilitatingCamp = campDao.getCamps().get(facilitatingCampID);
        int enqIndex = 1;
        System.out.printf("===== (Facilitating Camp Enquiries) %s =====\n", facilitatingCamp.getName());
        for (Enquiry enq : facilitatingCamp.getEnquiries()) {
            System.out.printf("***** Enquiry %d *****\n", enqIndex);
            System.out.printf("%s\n", enq.getEnquiry());
            if (enq.getReplier() != null) {
                System.out.printf("~~~~~ Reply by: %s ~~~~~\n", enq.getReplier());
                System.out.printf("%s\n", enq.getReply());
            }
            enqIndex++;
        }
    }
}
