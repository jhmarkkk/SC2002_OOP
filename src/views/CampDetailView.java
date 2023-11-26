package views;

import java.util.Map;

import enums.Visibility;;
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
import utils.DateUtil;

public class CampDetailView implements CampDetailViewable {
    public void view() {
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        CommitteeMember committeeMember = (CommitteeMember) currentUserDao.getCurrentUser();
        StaffDao staffDao = new StaffDaoImpl();
        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentsMap = studentDao.getStudents();
        CampDao campDao = new CampDaoImpl();
        Camp facilitatingCamp = campDao.getCamps().get(committeeMember.getFacilitatingCamp());

        System.out.printf("===== (Facilitating Camp Details) %s =====\n", facilitatingCamp.getName());
        System.out.print("Duration: ");
        System.out.printf("From %s ", DateUtil.toString(facilitatingCamp.getDates().get(0)));
        System.out.printf("to %s ",
                DateUtil.toString(facilitatingCamp.getDates().get(facilitatingCamp.getDates().size() - 1)));
        System.out.printf("\nLocation: %s\n", facilitatingCamp.getLocation());
        System.out.print("Attendees: ");
        for (String attendeeID : facilitatingCamp.getAttendees()) {
            System.out.printf(" %s", studentsMap.get(attendeeID).getName());
        }
        System.out.println();
        System.out.printf("Attendee Slots available: %d\n", facilitatingCamp.getAttendeeSlots());
        System.out.printf("Camp Committee Slots available: %d\n", facilitatingCamp.getCommitteeSlots());
        System.out.printf("Description: %s\n", facilitatingCamp.getDescription());
        System.out.printf("Staff in charge: %s\n",
                staffDao.getStaffs().get(facilitatingCamp.getStaffInCharge()).getName());

    }
}
