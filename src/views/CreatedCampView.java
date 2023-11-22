package views;

import java.lang.Comparable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Date;
import java.util.Map;

import interfaces.dao.CampDaoInterface;
import interfaces.dao.CurrentUserDaoInterface;
import interfaces.views.CampViewable;
import models.Camp;
import models.Student;
import enums.FilterType;

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
