package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import enums.FilterType;
import interfaces.views.CampViewable;
import models.Camp;
import utils.CampFilter;
import utils.CampFormatDate;

public class StaffAllCampView implements CampViewable {
    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        Map<String, Camp> campsMap = campDao.getCamps();
        ArrayList<Camp> staffAllCamps = new ArrayList<Camp>(campsMap.values());
        ArrayList<Camp> staffAllCamps = CampFilter.filter(staffAllCamps, filterType);
        int index = 1;
        SimpleDateFormat formatter = CampFormatDate.dateFormat();
        System.out.println("===== List of Camps : Staff =====");
        for (Camp staffCamp : staffAllCamps) {
            System.out.printf("----- (Camp %d) %s -----\n", index, staffCamp.getName());
            System.out.printf("Visibility : %s", staffCamp.getVisibility().toString());
            System.out.print("Dates: ");
            for (Date date : staffCamp.getDates()) {
                System.out.printf("%s ", formatter.format(date));
            }
            System.out.printf("\nLocation: %s\n", staffCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", staffCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", staffCamp.getCommitteeSlots());
            System.out.printf("Staff in charge: %s\n", staffCamp.getStaffInCharge().getName());
            index++;
        }
    }
}
