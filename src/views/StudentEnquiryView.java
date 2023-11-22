package views;

import java.util.ArrayList;

import interfaces.views.EnquiryViewable;
import models.Camp;

public class StudentEnquiryView implements EnquiryViewable {
    public void view() {
        CampDaoInterface campDao;
        ArrayList<Enquiry> enquiries = campDao.getEnquiries();

    }
}
