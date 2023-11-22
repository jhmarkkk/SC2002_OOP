package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import enums.FilterType;
import interfaces.views.CampDetailViewable;
import interfaces.dao.CampDaoImpl;
import models.Camp;
import models.CommitteeMember;
import models.Student;
import utils.CampFilter;

public class CampDetailView implements CampDetailViewable {
    public void filterView(FilterType filterType) {
        CurrentUserDaoInterface currentUserDao;
        String facilitatingCampID = currentUserDao.getCurrentUser().getFacilitatingCamp();
        CampDaoInterface campDao;
        Camp facilitatingCamp = campDao.getCamps().get(facilitatingCampID);

        System.out.printf("===== (Facilitating Camp Details) %s =====\n", facilitatingCamp.getName());
        System.out.printf("Visibility: ", facilitatingCamp.getVisibility().toString());
        System.out.print("Dates: ");
        for (Date date : facilitatingCamp.getDates()) {
            System.out.printf("%s ", date.toString());
        }
        System.out.printf("\nLocation: %s\n", facilitatingCamp.getLocation());
        System.out.print("Attendees: ");
        for (Student attendee : facilitatingCamp.getAttendees()) {
            System.out.printf(" %s", attendee.getName());
        }
        System.out.printf("Attendee Slots available: %d\n", facilitatingCamp.getAttendeeSlots());
        System.out.printf("Camp Committee Slots available: %d\n", facilitatingCamp.getCommitteeSlots());
        System.out.printf("Description: %s\n", facilitatingCamp.getDescription());
        System.out.printf("Staff in charge: %s\n", facilitatingCamp.getStaffInCharge().getName());

    }

}
