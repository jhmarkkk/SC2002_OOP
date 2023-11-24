package views;

import java.lang.Comparable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampViewable;

import dao.CurrentUserDaoImpl;
import dao.CampDaoImpl;
import dao.StaffDaoImpl;

import models.Camp;
import models.Staff;

import utils.CampComparators;

import enums.SortType;

import utils.CampFilter;
import utils.CampFormatDate;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        Map<String, Camp> campsMap = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        ArrayList<String> createdCampIDs = currentUserDao.getCurrentUser().getCreatedCamps();
        ArrayList<Camp> createdCamps;
        for (String createdCampID : createdCampIDs) {
            createdCamps.add(campsMap.get(createdCampID));
        }
        createdCamps = CampFilter.filter(createdCamps, filterType);
        int index = 1;
        SimpleDateFormat formatter = CampFormatDate.dateFormat();
        System.out.println("===== Created Camps =====");
        for (Camp createdCamp : createdCamps) {
            System.out.printf("----- (Created Camp %d) %s -----\n", index, createdCamp.getName());
            System.out.printf("Visibility : %s", camp.getVisibility().toString());
            System.out.print("Dates: ");
            for (Date date : createdCamp.getDates()) {
                System.out.printf("%s ", formatter.format(date));
            }
            System.out.printf("\nLocation: %s\n", createdCamp.getLocation());
            System.out.print("Attendees: ");
            for (Student attendee : createdCamp.getAttendees()) {
                System.out.printf(" %s", attendee.getName());
            }
            System.out.printf("Attendee Slots available: %d\n", createdCamp.getAttendeeSlots());
            System.out.printf("\nCamp Committee Slots available: %d\n", createdCamp.getCommitteeSlots());
            System.out.printf("Description: %s\n", createdCamp.getDescription());
            System.out.printf("Staff in charge: %s\n", createdCamp.getStaffInCharge().getName());
            index++;
        }
    }

};
    public void sortView(SortType sortType) {
		CampDao campDao = new CampDaoImpl();
		Map<String, Camp> campMap = campDao.getCamps(); 
        
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        String staffID = currentUserDao.getCurrentUser().getUserID();
        
        StaffDao staffDao = new StaffDaoImpl();
        Map<String, Staff> staffMap = staffDao.getStaffs();
        
        //idlist is id of created Camps
        ArrayList<String> idList = staffMap.get(staffID).getCreatedCamps();
        
        ArrayList<Camp> campsList = new ArrayList<Camp>();

        
        for (int idIndex = 0; idIndex < idList.size(); idIndex++){
            Camp indexCamp = campMap.get(idList.get(idIndex));
            campsList.add(indexCamp);
        }

        switch(sortType){
            case NAME:
                Collections.sort(campsList, new CampComparators.NameComparator());
                break;

            case DATES:
                Collections.sort(campsList, new CampComparators.StartComparator());
                break;

            case CLOSING_DATE:
                Collections.sort(campsList, new CampComparators.ClosingComparator());
                break;

            case LOCATION:
                Collections.sort(campsList, new CampComparators.LocationComparator());
                break;

            case FACULTY:
                Collections.sort(campsList, new CampComparators.FacultyComparator());
                break;

            case STAFF:
                Collections.sort(campsList, new CampComparators.StaffComparator());
                break;

            default:
                Collections.sort(campsList, new CampComparators.NameComparator());
                break;

        }
        
        System.out.println("===== Created Camps =====");
        System.out.printf("Index | Camp Name | Dates | Registration closing date | Open to | Location | Total slots | Camp Committee slots | Description | Visibility");
        
    }
}
