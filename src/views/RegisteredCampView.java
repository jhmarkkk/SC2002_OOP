package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import enums.SortType;
import enums.Role;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampViewable;

import models.Camp;
import models.Student;
import models.CommitteeMember;
import models.Staff;

import utils.SortCampUtil;
import utils.DateUtil;
import utils.PrintUtil;

public class RegisteredCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final StaffDao staffDao = new StaffDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void sortView(SortType sortType) {

        int i = 1;
        CommitteeMember committeeMember;
        Camp facilitatingCamp;
        ArrayList<GregorianCalendar> dateList;
        Student student = (Student)currentUserDao.getCurrentUser();
        Map<String, Camp> campData = campDao.getCamps();
        Map<String, Staff> staffData = staffDao.getStaffs();
        ArrayList<String> registeredCampNameList = student.getRegisteredCamps();
        ArrayList<Camp> registeredCampList = new ArrayList<Camp>();
        
        for (String registeredCampName : registeredCampNameList) registeredCampList.add(campData.get(registeredCampName));
        
        registeredCampList = SortCampUtil.sort(registeredCampList, sortType);
        
        PrintUtil.header("List of Registered Camp");
        if (student.getRole() == Role.COMMITTEE) {
            committeeMember = (CommitteeMember) student;
            facilitatingCamp = campData.get(committeeMember.getFacilitatingCamp());
            dateList = facilitatingCamp.getDates();
            PrintUtil.header("Facilitating Camp");
            System.out.printf("%-30s: %s\n","Name" , facilitatingCamp.getName());
            System.out.printf("%-30s: %s -> %s\n","Duration",
                DateUtil.toString(dateList.get(0)),
                DateUtil.toString(dateList.get(dateList.size() - 1)));
            System.out.printf("%-30s: %s\n","Registration Closing Date" ,DateUtil.toString(facilitatingCamp.getRegistrationClosingDate()));
            System.out.printf("%-30s: %s\n","User group" , facilitatingCamp.getOpenTo());
            System.out.printf("%-30s: %s\n","Location" , facilitatingCamp.getLocation());
            System.out.printf("%-30s: %s\n","Remaining attendee slots", facilitatingCamp.getAttendeeSlots() - facilitatingCamp.getAttendees().size());
            System.out.printf("%-30s: %s\n","Remaining camp committee slots",
                facilitatingCamp.getCommitteeSlots() - facilitatingCamp.getCommitteeMembers().size());
            System.out.printf("%-30s: %s\n","Description" , facilitatingCamp.getDescription());
            System.out.printf("%-30s: %s\n","Staff-in-charge" , staffData.get(facilitatingCamp.getStaffInCharge()).getName());
            System.out.println();
        }

        PrintUtil.header("Attending Camps");
        for (Camp camp : registeredCampList) {
            dateList = camp.getDates();
            PrintUtil.header(String.format("Camp %d", i++));
            System.out.printf("%-30s: %s\n","Name" , camp.getName());
            System.out.printf("%-30s: %s -> %s\n","Duration",
                DateUtil.toString(dateList.get(0)),
                DateUtil.toString(dateList.get(dateList.size() - 1)));
            System.out.printf("%-30s: %s\n","Registration Closing Date" ,DateUtil.toString(camp.getRegistrationClosingDate()));
            System.out.printf("%-30s: %s\n","User group" , camp.getOpenTo());
            System.out.printf("%-30s: %s\n","Location" , camp.getLocation());
            System.out.printf("%-30s: %s\n","Remaining attendee slots", camp.getAttendeeSlots() - camp.getAttendees().size());
            System.out.printf("%-30s: %s\n","Remaining camp committee slots",
                camp.getCommitteeSlots() - camp.getCommitteeMembers().size());
            System.out.printf("%-30s: %s\n","Description" , camp.getDescription());
            System.out.printf("%-30s: %s\n","Staff-in-charge" , staffData.get(camp.getStaffInCharge()).getName());
            System.out.println();
        }
    }
}
