package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;
import interfaces.dao.StudentDao;
import dao.StudentDaoImpl;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampDetailViewable;
import models.Camp;
import models.Student;
import models.CommitteeMember;
import models.Staff;
import utils.DateUtil;
import utils.PrintUtil;

public class CampDetailView implements CampDetailViewable {
    public void view() {
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        CommitteeMember committeeMember = (CommitteeMember) currentUserDao.getCurrentUser();
        StaffDao staffDao = new StaffDaoImpl();
        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentsMap = studentDao.getStudents();
        CampDao campDao = new CampDaoImpl();
        Camp facilitatingCamp = campDao.getCamps().get(committeeMember.getFacilitatingCamp());
        Map<String, Staff> staffData = staffDao.getStaffs();
        ArrayList<GregorianCalendar> dateList;

        dateList = facilitatingCamp.getDates();
        PrintUtil.header(String.format("Facilitating Camp"));
        System.out.printf("%-30s: %s\n", "Name", facilitatingCamp.getName());
        System.out.printf("%-30s: %s -> %s\n", "Duration",
                DateUtil.toString(dateList.get(0)),
                DateUtil.toString(dateList.get(dateList.size() - 1)));
        System.out.printf("%-30s: %s\n", "Registration Closing Date",
                DateUtil.toString(facilitatingCamp.getRegistrationClosingDate()));
        System.out.printf("%-30s: %s\n", "User group", facilitatingCamp.getOpenTo());
        System.out.printf("%-30s: %s\n", "Location", facilitatingCamp.getLocation());
        System.out.printf("%-30s: %s\n", "Attendees: ");
        for (String attendeeID : facilitatingCamp.getAttendees()) {
            System.out.printf("%s ", studentsMap.get(attendeeID).getName());
        }
        System.out.println();
        System.out.printf("%-30s: %s\n", "Remaining attendee slots",
                facilitatingCamp.getAttendeeSlots() - facilitatingCamp.getAttendees().size());
        System.out.printf("%-30s: %s\n", "Remaining camp committee slots",
                facilitatingCamp.getCommitteeSlots() - facilitatingCamp.getCommitteeMembers().size());
        System.out.printf("%-30s: %s\n", "Description", facilitatingCamp.getDescription());
        System.out.printf("%-30s: %s\n", "Staff-in-charge",
                staffData.get(facilitatingCamp.getStaffInCharge()).getName());
        System.out.println();
    }

}
