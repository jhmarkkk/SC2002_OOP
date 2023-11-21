package views;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Date;

import interfaces.dao.CampDaoInterface;
import interfaces.dao.CurrentUserDaoInterface;
import interfaces.views.CampViewable;
import models.Camp;
import enums.FilterType;

import utils.CampFilter;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        ArrayList<Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        ArrayList<String> createdCampIDs = currentUserDao.getCurrentUser().getCreatedCamps();
        for (String createdCampID : createdCampIDs) {
            for (Camp camp : camps) {
                // if the id does not match the idlist, remove it from camps
                if (!Objects.equals(createdCampID, camp.getName())) {
                    camps.remove(camp);
                }
            }
        }
        camps = CampFilter.filter(camps, filterType);
        int index = 1;
        System.out.println("===== Created Camps =====");
        for (Camp camp : camps) {
            System.out.printf("----- (Created Camp %d) %s -----\n", index, camp.getName());
            System.out.print("Dates: ");
            for (Date date : camp.getDates()) {
                System.out.printf("%s ", date.toString());
            }
            System.out.printf("\nLocation: %s\n", camp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", camp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", camp.getCommitteeSlots());
            System.out.printf("Description: %s\n", camp.getDescription());
            System.out.printf("Staff in charge: %s\n", camp.getStaffInCharge().getName());
            index++;
        }
    }

};
