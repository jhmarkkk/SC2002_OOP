package utils;

import java.util.ArrayList;
import java.util.Collections;

import enums.SortType;

import models.Camp;

import utils.CampComparators.ClosingComparator;
import utils.CampComparators.FacultyComparator;
import utils.CampComparators.LocationComparator;
import utils.CampComparators.NameComparator;
import utils.CampComparators.StaffComparator;
import utils.CampComparators.StartComparator;

public class SortCampUtil {

    public static ArrayList<Camp> sort(ArrayList<Camp> camps, SortType sortType) {

        switch (sortType) {
            case NAME:
                NameComparator nameComparator = new NameComparator();
                Collections.sort(camps, nameComparator);
                break;
            case DATES:
                StartComparator startComparator = new StartComparator();
                Collections.sort(camps, startComparator);
                break;
            case CLOSING_DATE:
                ClosingComparator closeComparator = new ClosingComparator();
                Collections.sort(camps, closeComparator);
                break;
            case LOCATION:
                LocationComparator locComparator = new LocationComparator();
                Collections.sort(camps, locComparator);
                break;
            case FACULTY:
                FacultyComparator facultyComparator = new FacultyComparator();
                Collections.sort(camps, facultyComparator);
                break;
            case STAFF:
                StaffComparator staffComparator = new StaffComparator();
                Collections.sort(camps, staffComparator);
                break;
        }
        return camps;
    }
}
