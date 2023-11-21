package utils;

import java.util.ArrayList;
import java.util.Collections;

import enums.FilterType;
import models.Camp;

public class CampFilter {
    public static ArrayList<Camp> filter(ArrayList<Camp> camps, FilterType filterType) {
        if (filterType == FilterType.LOCATION) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getLocation().compareTo(camp2.getLocation()));
        }
        else if (filterType == FilterType.CLOSING_DATE) {
            Collections.sort(camps,
                    (camp1, camp2) -> camp1.getRegistrationClosingDate().compareTo(camp2.getRegistrationClosingDate()));
        }
        else if (filterType == FilterType.DATES) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getDates().get(0).compareTo(camp2.getDates().get(0)));

        }
        else if (filterType == FilterType.FACULTY) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getOpenTo().compareTo(camp2.getOpenTo()));
        }
        else if (filterType == FilterType.STAFF) {
            Collections.sort(camps,
            (camp1, camp2) -> camp1.getStaffInCharge().getName().compareTo(camp2.getStaffInCharge().getName()));
        }
        else (filterType == FilterType.NAME) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getName().compareTo(camp2.getName()));
        }
        return camps;
    }
}
