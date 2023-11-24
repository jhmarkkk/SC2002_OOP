package views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

import enums.Visibility;
import enums.FilterType;
import interfaces.views.CampViewable;
import interfaces.dao.CampDaoInterface;
import interfaces.dao.CurrentUserDaoInterface;
import models.Camp;
import utils.CampFilter;
import utils.CampFormatDate;

import java.text.SimpleDateFormat;

public class StudentAllCampView implements CampViewable {
    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        Map<String, Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        String faculty = currentUserDao.getCurrentUser().getFaculty();
        ArrayList<Camp> studentCamps;
        for (Camp camp : camps.values()) {
            // if the id does not match the idlist, remove it from camps
            if (camp.getOpenTo().equals("NTU") || (Objects.equals(faculty, camp.getOpenTo()))
                    || camp.getVisibility().name() == "ON") {
                studentCamps.add(camp);
            }

        }
        studentCamps = CampFilter.filter(studentCamps, filterType);
        int index = 1;
        SimpleDateFormat formatter = CampFormatDate.dateFormat();
        System.out.println("===== List of Camps : Student =====");
        for (Camp studentCamp : studentCamps) {
            System.out.printf("----- (Camp %d) %s -----\n", index, studentCamp.getName());
            System.out.print("Dates: ");
            for (Date date : studentCamp.getDates()) {
                System.out.printf("%s ", formatter.format(date));
            }
            System.out.printf("\nLocation: %s\n", studentCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", studentCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", studentCamp.getCommitteeSlots());
            System.out.printf("Description: %s\n", studentCamp.getDescription());
            System.out.printf("Staff in charge: %s\n", studentCamp.getStaffInCharge().getName());
            index++;
        }
    }
import java.util.Map;
import java.util.Objects;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import models.Camp;
import enums.SortType;
import interfaces.views.CampViewable;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;

public class StudentAllCampView implements CampViewable {

    public void filterView(FilterType filterType) {

    }

    // StudentAllCampView() {
    // CampDaoInterface campDao = CampDaoImplementation();
    // ArrayList<Camp> camps = campDao.getCamps();
    // CurrentUserDaoInterface currentUserDao;
    // String faculty = currentUserDao.getCurrentUser().getFaculty();
    // for (Camp camp : camps) {
    // // if the id does not match the idlist, remove it from camps
    // if (!Objects.equals(id, camp.getName())) {
    // camps.remove(camp);
    // }
    // }
    // }

    // public void view() {
    // CampDaoInterface campDao = CampDaoImplementation();
    // ArrayList<Camp> camps = campDao.getCamps();
    // int index = 1;
    // System.out.println("===== List of Camps =====");
    // for (Camp camp : camps) {
    // System.out.printf("----- (%d) %s -----\n", index, camp.getName());
    // System.out.print("Dates: ");
    // for (Date date : camp.getDates()) {
    // System.out.printf("%s ", date.toString());
    // }
    // System.out.printf("\nLocation: %s\n", camp.getLocation());
    // System.out.printf("Attendee Slots available: %d\n", camp.getAttendeeSlots());
    // System.out.printf("Camp Committee Slots available: %d\n",
    // camp.getCommitteeSlots());
    // System.out.printf("Description: %s\n", camp.getDescription());
    // System.out.printf("Staff in charge: %s\n",
    // camp.getStaffInCharge().getName());
    // }
    // }
}
