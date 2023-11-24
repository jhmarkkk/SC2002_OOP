package views;

import interfaces.views.EnquiryViewable;
import models.Camp;
import models.CommitteeMember;
import models.Enquiry;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;

import interfaces.dao.CampDao;
import dao.CampDaoImpl;

public class CommitteeEnquiryView implements EnquiryViewable {
    public void view() {
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        CommitteeMember committeeMember = (CommitteeMember) currentUserDao.getCurrentUser();
        CampDao campDao = new CampDaoImpl();
        Camp facilitatingCamp = campDao.getCamps().get(committeeMember.getFacilitatingCamp());
        System.out.printf("===== (Facilitating Camp Enquiries) %s =====\n", facilitatingCamp.getName());
        for (Enquiry enq : facilitatingCamp.getEnquiries().values()) {
            System.out.printf("***** Enquiry %d *****\n", enq.getEnquiryID());
            System.out.printf("%s\n", enq.getEnquiry());
            if (enq.getReplier() != null) {
                System.out.printf("~~~~~ Reply by: %s ~~~~~\n", enq.getReplier());
                System.out.printf("%s\n", enq.getReply());
            }
        }
    }
}
