package utils;

import java.util.Comparator;
import java.util.GregorianCalendar;

import models.Camp;

/**
 * The {@code CampComparators} class provides static nested classes that implement the {@link Comparator} interface for comparing instances of the {@link Camp} class based on different criteria.
 * These comparators are useful for sorting lists of camps according to specific attributes.
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 */
public class CampComparators {

    /**
     * The {@code NameComparator} class is a comparator for sorting camps based on their names in lexicographical order.
     */
    public static class NameComparator implements Comparator<Camp> {
    	
        /**
         * Compares two camps based on their names.
         * 
         * @param camp1 the first camp
         * @param camp2 the second camp
         * 
         * @return a negative integer, zero, or a positive integer as the first camp's name is less than, equal to, or greater than the second camp's name.
         */
        public int compare(Camp camp1, Camp camp2) {
            return camp1.getName().compareTo(camp2.getName());
        }
    }

    /**
     * The {@code StartComparator} class is a comparator for sorting camps based on their start dates.
     */    
    public static class StartComparator implements Comparator<Camp> {
        
        /**
         * Compares two camps based on their start dates.
         * 
         * @param camp1 the first camp
         * @param camp2 the second camp
         * 
         * @return a negative integer, zero, or a positive integer as the first camp's start date is less than, equal to, or greater than the second camp's start date.
         */
        public int compare(Camp camp1, Camp camp2) {
        	
            GregorianCalendar camp1Date = camp1.getDates().get(0);
            String camp1DateString = DateUtil.toString(camp1Date);
            GregorianCalendar camp2Date = camp2.getDates().get(0);
            String camp2DateString = DateUtil.toString(camp2Date);
            return camp1DateString.compareTo(camp2DateString);
        }
    }

    /**
     * The {@code ClosingComparator} class is a comparator for sorting camps based on their registration closing dates.
     */
    public static class ClosingComparator implements Comparator<Camp> {
    	
        /**
         * Compares two camps based on their registration closing dates.
         * 
         * @param camp1 the first camp
         * @param camp2 the second camp
         * 
         * @return a negative integer, zero, or a positive integer as the first camp's closing date is less than, equal to, or greater than the second camp's closing date.
         */
        public int compare(Camp camp1, Camp camp2) {
            GregorianCalendar camp1Date = camp1.getRegistrationClosingDate();
            String camp1DateString = DateUtil.toString(camp1Date);
            GregorianCalendar camp2Date = camp2.getRegistrationClosingDate();
            String camp2DateString = DateUtil.toString(camp2Date);
            return camp1DateString.compareTo(camp2DateString);
        }
    }

    /**
     * The {@code LocationComparator} class is a comparator for sorting camps based on their locations in lexicographical order.
     */
    public static class LocationComparator implements Comparator<Camp> {
    	
        /**
         * Compares two camps based on their locations.
         * 
         * @param camp1 the first camp
         * @param camp2 the second camp
         * 
         * @return a negative integer, zero, or a positive integer as the first camp's location is less than, equal to, or greater than the second camp's location.
         */        
        public int compare(Camp camp1, Camp camp2) {
        	
            return camp1.getLocation().compareTo(camp2.getLocation());
        }
    }

    /**
     * The {@code FacultyComparator} class is a comparator for sorting camps based on the faculty they are open to.
     */
    public static class FacultyComparator implements Comparator<Camp> {
    	
        /**
         * Compares two camps based on the faculty they are open to.
         * 
         * @param camp1 the first camp
         * @param camp2 the second camp
         * 
         * @return a negative integer, zero, or a positive integer as the first camp's faculty is less than, equal to, or greater than the second camp's faculty.
         */        
        public int compare(Camp camp1, Camp camp2) {
        	
            return camp1.getOpenTo().compareTo(camp2.getOpenTo());
        }
    }

    /**
     * The {@code StaffComparator} class is a comparator for sorting camps based on the staff member in charge.
     */
    public static class StaffComparator implements Comparator<Camp> {
    	
        /**
         * Compares two camps based on the staff member in charge.
         * 
         * @param camp1 the first camp
         * @param camp2 the second camp
         * 
         * @return a negative integer, zero, or a positive integer as the first camp's staff member in charge is less than, equal to, or greater than the second camp's staff member in charge.
         */
        public int compare(Camp camp1, Camp camp2) {
        	
            return camp1.getStaffInCharge().compareTo(camp2.getStaffInCharge());
        }
    }
}
