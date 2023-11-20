package views;

import java.util.ArrayList;
import java.util.Date;

import interfaces.views.Viewable;
import interfaces.views.FilterViewable;

public class StudentAllCampView implements Viewable {
    public void view() {
        CampDaoInterface campDao = CampDaoImplementation();
        ArrayList<Camp> camps = campDao.getCamps();
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
