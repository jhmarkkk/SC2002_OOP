package views;

import java.util.ArrayList;
import java.util.Map;

import interfaces.dao.CampDao;
import dao.CampDaoImpl;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;
import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;
import enums.SortType;
import enums.Visibility;
import interfaces.views.CampViewable;

import models.Camp;
import utils.SortCampUtil;
import utils.DateUtil;

public class StudentAllCampView implements CampViewable {
    public void sortView(SortType sortType) {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        StaffDao staffDao = new StaffDaoImpl();
        String faculty = currentUserDao.getCurrentUser().getFaculty();

        ArrayList<Camp> studentCamps = new ArrayList<Camp>();
        for (Camp camp : campsMap.values()) {
            // if the id does not match the idlist, remove it from camps
            if (camp.getVisibility() == Visibility.ON) {
                if (camp.getOpenTo().equals("NTU") || (faculty.equals(camp.getOpenTo()))) {
                    studentCamps.add(camp);
                }
            }
        }

        studentCamps = SortCampUtil.sort(studentCamps, sortType);
        int index = 1;
        System.out.println("===== List of Camps : Student =====\n");
        for (Camp studentCamp : studentCamps) {
            System.out.printf("\n----- %s Camp -----\n", index, studentCamp.getName());
            System.out.printf("Faculty : %s", studentCamp.getOpenTo());
            System.out.print("Duration: ");
            System.out.printf("From %s ", DateUtil.toString(studentCamp.getDates().get(0)));
            System.out.printf("to %s ",
                    DateUtil.toString(studentCamp.getDates().get(studentCamp.getDates().size() - 1)));

            System.out.printf("\nLocation: %s\n", studentCamp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", studentCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", studentCamp.getCommitteeSlots());
            System.out.printf("Description: %s\n", studentCamp.getDescription());
            System.out.printf("Staff in charge: %s",
                    staffDao.getStaffs().get(studentCamp.getStaffInCharge()).getName());
            index++;
        }
    }
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
