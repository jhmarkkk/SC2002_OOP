package utils;

import java.util.Comparator;
import java.util.GregorianCalendar;

import models.Camp;

public class CampComparators {

    public static class NameComparator implements Comparator<Camp> {
        public int compare(Camp camp1, Camp camp2) {
            return camp1.getName().compareTo(camp2.getName());
        }
    }

    public static class StartComparator implements Comparator<Camp> {
        public int compare(Camp camp1, Camp camp2) {
            GregorianCalendar camp1Date = camp1.getDates().get(0);
            String camp1DateString = DateUtil.toString(camp1Date);
            GregorianCalendar camp2Date = camp2.getDates().get(0);
            String camp2DateString = DateUtil.toString(camp2Date);
            return camp1DateString.compareTo(camp2DateString);
        }
    }

    public static class ClosingComparator implements Comparator<Camp> {
        public int compare(Camp camp1, Camp camp2) {
            GregorianCalendar camp1Date = camp1.getRegistrationClosingDate();
            String camp1DateString = DateUtil.toString(camp1Date);
            GregorianCalendar camp2Date = camp2.getRegistrationClosingDate();
            String camp2DateString = DateUtil.toString(camp2Date);
            return camp1DateString.compareTo(camp2DateString);
        }
    }

    public static class LocationComparator implements Comparator<Camp> {
        public int compare(Camp camp1, Camp camp2) {
            return camp1.getLocation().compareTo(camp2.getLocation());
        }
    }

    public static class FacultyComparator implements Comparator<Camp> {
        public int compare(Camp camp1, Camp camp2) {
            return camp1.getOpenTo().compareTo(camp2.getOpenTo());
        }
    }

    public static class StaffComparator implements Comparator<Camp> {
        public int compare(Camp camp1, Camp camp2) {
            return camp1.getStaffInCharge().compareTo(camp2.getStaffInCharge());
        }
    }
}
