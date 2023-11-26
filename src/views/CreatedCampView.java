package views;

import java.util.ArrayList;
import java.util.Map;

import interfaces.dao.CampDao;
import dao.CampDaoImpl;

import interfaces.dao.StaffDao;
import dao.StaffDaoImpl;

import interfaces.dao.StudentDao;
import dao.StudentDaoImpl;

import interfaces.dao.CurrentUserDao;
import dao.CurrentUserDaoImpl;

import interfaces.views.CampViewable;

import models.Camp;
import models.Student;

import enums.SortType;

import utils.SortCampUtil;
import utils.DateUtil;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    public void sortView(SortType sortType) {
        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campMap = campDao.getCamps();

        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        String staffID = currentUserDao.getCurrentUser().getUserID();

        StaffDao staffDao = new StaffDaoImpl();

        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentsMap = studentDao.getStudents();

        // idlist is id of created Camps
        ArrayList<String> createdCampIDList = staffDao.getStaffs().get(staffID).getCreatedCamps();

        ArrayList<Camp> createdCampsList = new ArrayList<Camp>();

        for (String createdCampID : createdCampIDList) {
            createdCampsList.add(campMap.get(createdCampID));
        }
        createdCampsList = SortCampUtil.sort(createdCampsList, sortType);
        int index = 1;
        for (Camp createdCamp : createdCampsList) {
            System.out.printf("----- (Created Camp %d) %s -----\n", index, createdCamp.getName());
            System.out.printf("Visibility: ", createdCamp.getVisibility().toString());
            System.out.print("Duration: ");
            System.out.printf("From %s ", DateUtil.toString(createdCamp.getDates().get(0)));
            System.out.printf("to %s ",
                    DateUtil.toString(createdCamp.getDates().get(createdCamp.getDates().size() - 1)));
            System.out.printf("\nLocation: %s\n", createdCamp.getLocation());
            for (String attendeeID : createdCamp.getAttendees()) {
                System.out.printf(" %s", studentsMap.get(attendeeID).getName());
            }
            System.out.print("Committee Members in camp: ");
            for (String attendeeID : createdCamp.getCommitteeMembers()) {
                System.out.printf(" %s", studentsMap.get(attendeeID).getName());
            }
            System.out.printf("Attendee Slots available: %d\n", createdCamp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", createdCamp.getCommitteeSlots());
            System.out.printf("Staff in charge: %s\n",
                    staffDao.getStaffs().get(createdCamp.getStaffInCharge()).getName());
            index++;
        }
    }
}

// private ArrayList<Camp> createdCamps = createdCamps;
// }

// public void view() {
// CampDaoInterface campDao = CampDaoImplementation();
// ArrayList<Camp> camps = campDao.getCamps();
// System.out.println("===== Created Camps =====");
// System.out.printf(
// "Index | Camp Name | Dates | Registration closing date | Open to | Location |
// Total slots | Camp Committee slots | Description | Visibility");
// }

// /**
// * @param idlist
// * @return ArrayList<Object>
// */
// public void filter(ArrayList<String> idlist, FilterType filterType =
// filterType.Name) {
// // use the interface to get camps
// CampDaoInterface campDao = CampDaoImplementation();
// ArrayList<Camp> camps = campDao.getCamps();
// for (String id : idlist) {
// for (Camp camp : camps) {
// // if the id does not match the idlist, remove it from camps
// if (!Objects.equals(id, camp.getName())) {
// camps.remove(camp);
// }
// }
// }
// for (Camp camp : camps) {
// }
// if (filter == FilterType.NAME) {
// Collections.sort(camps, (camp1, camp2) ->
// camp1.getName().compareTo.camp2.getName());
// return camps;
// }
// if (filter == FilterType.DATES) {
// Collections.sort(camps, (camp1, camp2) ->
// camp1.getLocation().compareTo.camp2.getLocation());
// return camps;
// }
// if (filter == FilterType.DATE) {
// Collections.sort(camps, (camp1, camp2) ->
// camp1.getDate().compareTo.camp2.getName());
// return camps;
// }
// };
