package views;

import enums.SortType;
import enums.Role;
import interfaces.views.CampViewable;

import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;

import interfaces.dao.CampDao;
import dao.CampDaoImpl;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import models.Camp;
import models.Student;
import models.CommitteeMember;
import utils.CampFilter;
import utils.DateUtil;

public class RegisteredCampView implements CampViewable {
    public void sortView(SortType sortType) {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();
        StaffDao staffDao = new StaffDaoImpl();
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        Student student = (Student) currentUserDao.getCurrentUser();
        Role role = student.getRole();
        System.out.println("===== List of Registered Camps =====");
        if (role.equals(Role.COMMITTEE)) {
            CommitteeMember committeeMember = (CommitteeMember) student;
            // find the camp the Committee facilitates
            Camp facilitatingCamp = campsMap.get(committeeMember.getFacilitatingCamp());
            System.out.printf("----- (Facilitating Camp) %s -----\n", facilitatingCamp.getName());
            System.out.print("Duration: ");
            System.out.printf("From %s ", DateUtil.toString(facilitatingCamp.getDates().get(0)));
            System.out.printf("to %s ",
                    DateUtil.toString(facilitatingCamp.getDates().get(facilitatingCamp.getDates().size() - 1)));
            System.out.printf("\nLocation: %s\n", facilitatingCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", facilitatingCamp.getAttendeeSlots());
            System.out.printf("Staff in charge: %s\n", facilitatingCamp.getStaffInCharge());

        }

        ArrayList<String> registeredCampIDs = student.getRegisteredCamps();
        ArrayList<Camp> registeredCampList = new ArrayList<Camp>();
        for (String registeredCampID : registeredCampIDs) {
            registeredCampList.add(campsMap.get(registeredCampID));
        }
        registeredCampList = CampFilter.filter(registeredCampList, sortType);
        int index = 1;
        for (Camp registeredCamp : registeredCampList) {
            System.out.printf("----- (Attending Camp %d) %s -----\n", index, registeredCamp.getName());
            System.out.print("Dates: ");
            for (GregorianCalendar date : registeredCamp.getDates()) {
                System.out.printf("%s ", DateUtil.toString(date));
            }
            System.out.printf("\nLocation: %s\n", registeredCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", registeredCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", registeredCamp.getCommitteeSlots());
            System.out.printf("Staff in charge: %s\n",
                    staffDao.getStaffs().get(registeredCamp.getStaffInCharge()).getName());
            index++;
        }
    }
}
