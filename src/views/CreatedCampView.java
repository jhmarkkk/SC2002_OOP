package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import models.Staff;
import models.Student;

import enums.SortType;
import enums.Visibility;
import utils.SortCampUtil;
import utils.DateUtil;
import utils.PrintUtil;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();


    public void sortView(SortType sortType) {

        int i = 1;
        ArrayList<GregorianCalendar> dateList;
        Staff currentUser = (Staff)currentUserDao.getCurrentUser();
        ArrayList<String> createdCampNameList = currentUser.getCreatedCamps();
        Map<String, Camp> campsData = campDao.getCamps();
        ArrayList<Camp> createdCampList = new ArrayList<Camp>();

        for (String campName : createdCampNameList) {
            createdCampList.add(campsData.get(campName));
        }

        createdCampList = SortCampUtil.sort(createdCampList, sortType);
        PrintUtil.header("List of All Camps");
        for (Camp camp : createdCampList) {
            dateList = camp.getDates();
            PrintUtil.header(String.format("Camp %d", i++));
            System.out.printf("%-30s: %s\n","Name" , camp.getName());
            System.out.printf("%-30s: %s -> %s\n","Duration",
                DateUtil.toString(dateList.get(0)),
                DateUtil.toString(dateList.get(dateList.size() - 1)));
            System.out.printf("%-30s: %s\n","Registration Closing Date" ,DateUtil.toString(camp.getRegistrationClosingDate()));
            System.out.printf("%-30s: %s\n","User group" , camp.getOpenTo());
            System.out.printf("%-30s: %s\n","Location" , camp.getLocation());
            System.out.printf("%-30s: %s\n","Total slots" , camp.getTotalSlots());
            System.out.printf("%-30s: %s\n","Camp committee slots" , camp.getCommitteeSlots());
            System.out.printf("%-30s: %s\n","Description" , camp.getDescription());
            System.out.printf("%-30s: %s\n","Visibility" , Visibility.toString(camp.getVisibility()));
            System.out.println();
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
