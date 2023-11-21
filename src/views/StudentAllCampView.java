package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import enums.Visibility;
import enums.FilterType;
import interfaces.views.CampViewable;
import interfaces.views.FilterViewable;
import models.Camp;

public class StudentAllCampView implements CampViewable {
    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        ArrayList<Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        String faculty = currentUserDao.getCurrentUser().getFaculty();

        for (Camp camp : camps) {
            // if the id does not match the idlist, remove it from camps
            if (camp.getOpenTo() != "NTU" || (!Objects.equals(faculty, camp.getOpenTo()))
                    || camp.getVisibility().name() == "OFF") {
                camps.remove(camp);
            }

        }
        int index = 1;
        System.out.println("===== List of Camps =====");
        for (Camp camp : camps) {
            System.out.printf("----- (%d) %s -----\n", index, camp.getName());
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
