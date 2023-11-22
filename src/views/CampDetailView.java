package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import enums.FilterType;
import interfaces.views.CampDetailViewable;
import models.Camp;
import models.CommitteeMember;
import models.Student;
import utils.CampFilter;

public class CampDetailView implements CampDetailViewable {
        public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        ArrayList<Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        String facilitatingCampID = currentUserDao.getCurrentUser().getFacilitatingCamp();
            for (Camp camp : camps) {
                // find the camp the Committee facilitates
                if (camp.getName().equals(committeeMember.getFacilitatingCamp())) {
                    System.out.printf("----- (Facilitating Camp) %s -----\n", camp.getName());
                    System.out.printf("Visibility: ", camp.getVisibility().toString());
                    System.out.print("Dates: ");
                    for (Date date : camp.getDates()) {
                        System.out.printf("%s ", date.toString());
                    }
                    System.out.printf("\nLocation: %s\n", camp.getLocation());
                    System.out.printf("Attendee Slots available: %d\n", camp.getAttendeeSlots());
                    System.out.printf("Camp Committee Slots available: %d\n", camp.getCommitteeSlots());
                    System.out.printf("Description: %s\n", camp.getDescription());
                    System.out.printf("Staff in charge: %s\n", camp.getStaffInCharge().getName());
                }

            }
        }


    }
}
