package views;

import java.util.Date;

import enums.FilterType;
import interfaces.views.CampViewable;
import models.Camp;
import utils.CampFilter;

public class StaffAllCampView implements CampViewable {
    public void filterView(FilterType filterType) {
        ArrayList<Camp> camps = CampFilter.filter(camps, filterType);
        int index = 1;
        System.out.println("===== List of Camps : Staff =====");
        for (Camp camp : camps) {
            System.out.printf("----- (Camp %d) %s -----\n", index, camp.getName());
            System.out.printf("Visibility : %s", camp.getVisibility().toString());
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
