package views;

import enums.SortType;
import interfaces.views.CampViewable;

import java.util.ArrayList;
import java.util.Map;

import interfaces.dao.CampDao;
import dao.CampDaoImpl;

import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;

import models.Camp;
import utils.CampFilter;
import utils.DateUtil;

public class StaffAllCampView implements CampViewable {
    public void sortView(SortType sortType) {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();
        StaffDao staffDao = new StaffDaoImpl();
        ArrayList<Camp> staffAllCamps = new ArrayList<Camp>(campsMap.values());
        staffAllCamps = CampFilter.filter(staffAllCamps, sortType);
        int index = 1;
        System.out.println("===== List of Camps : Staff =====");
        for (Camp staffCamp : staffAllCamps) {
            System.out.printf("----- (Camp %d) %s -----\n", index, staffCamp.getName());
            System.out.printf("Visibility : %s", staffCamp.getVisibility().toString());
            System.out.print("Duration: ");
            System.out.printf("From %s ", DateUtil.toString(staffCamp.getDates().get(0)));
            System.out.printf("to %s ",
                    DateUtil.toString(staffCamp.getDates().get(staffCamp.getDates().size() - 1)));
            System.out.printf("\nLocation: %s\n", staffCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", staffCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", staffCamp.getCommitteeSlots());
            System.out.printf("Staff in charge: %s\n",
                    staffDao.getStaffs().get(staffCamp.getStaffInCharge()).getName());
            index++;
        }
    }
}
