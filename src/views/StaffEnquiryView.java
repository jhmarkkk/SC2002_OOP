package views;

import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.EnquiryViewable;
import models.Camp;
import models.Enquiry;
import models.Staff;

public class StaffEnquiryView implements EnquiryViewable {
    public void view() {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();

        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        Staff staff = (Staff) currentUserDao.getCurrentUser();
        for (String createdCampID : staff.getCreatedCamps()) {
            Camp createdCamp = campsMap.get(createdCampID);
            System.out.printf("===== %s Enquiries =====\n", createdCamp.getName());
            for (Enquiry enq : createdCamp.getEnquiries().values()) {
                System.out.printf("***** Enquiry %d from $s*****\n", enq.getEnquiryID(), enq.getEnquirer());
                System.out.printf("%s\n", enq.getEnquiry());
                if (enq.getReplier() != null) {
                    System.out.printf("~~~~~ Replied by: %s ~~~~~\n", enq.getReplier());
                    System.out.printf("%s\n", enq.getReply());
                }
            }
        }
    }
}
