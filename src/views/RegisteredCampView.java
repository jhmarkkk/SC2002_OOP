package views;

import enums.FilterType;
import interfaces.views.CampViewable;

public class RegisteredCampView implements CampViewable {
    public void filterView(FilterType filterType) {

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Map;

import enums.FilterType;
import models.Camp;
import models.Student;
import models.CommitteeMember;
import interfaces.views.CampViewable;
import utils.CampFilter;
import utils.CampFormatDate;

public class RegisteredCampView implements CampViewable {
    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        Student student = currentUserDao.getCurrentUser();
        String role = student.getRole().toString();
        SimpleDateFormat formatter = CampFormatDate.dateFormat();
        System.out.println("===== List of Registered Camps =====");
        if (role == "Committee") {
            CommitteeMember committeeMember = (CommitteeMember) student;
            // find the camp the Committee facilitates
            Camp facilitatingCamp = campsMap.get(committeeMember.getFacilitatingCamp());
            System.out.printf("----- (Facilitating Camp) %s -----\n", facilitatingCamp.getName());
            System.out.print("Dates: ");
            for (Date date : facilitatingCamp.getDates()) {
                System.out.printf("%s ", formatter.format(date));
            }
            System.out.printf("\nLocation: %s\n", facilitatingCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", facilitatingCamp.getAttendeeSlots());
            System.out.printf("Staff in charge: %s\n", facilitatingCamp.getStaffInCharge().getName());

        }

        ArrayList<String> registeredCampIDs = student.getRegisteredCamps();
        ArrayList<Camp> registeredCamps;
        for (String registeredCampID : registeredCampIDs) {
            registeredCamps.add(campsMap.get(registeredCampID));
        }
        registeredCamps = CampFilter.filter(registeredCamps, filterType);
        int index = 1;
        for (Camp registeredCamp : registeredCamps) {
            System.out.printf("----- (Attending Camp %d) %s -----\n", index, registeredCamp.getName());
            System.out.print("Dates: ");
            for (Date date : registeredCamp.getDates()) {
                System.out.printf("%s ", formatter.format(date));
            }
            System.out.printf("\nLocation: %s\n", registeredCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", registeredCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", registeredCamp.getCommitteeSlots());
            System.out.printf("Staff in charge: %s\n", registeredCamp.getStaffInCharge().getName());
            index++;
        }
    }
}
