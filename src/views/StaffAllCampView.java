package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import dao.CampDaoImpl;
import dao.StaffDaoImpl;

import enums.SortType;
import enums.Visibility;


import interfaces.dao.CampDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampViewable;

import models.Camp;
import models.Staff;

import utils.SortCampUtil;
import utils.DateUtil;
import utils.PrintUtil;

/**
 * The {@code StaffAllCampView} class provides a view for staff to see a list of all camps.
 * It implements the {@link CampViewable} interface.
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public class StaffAllCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final StaffDao staffDao = new StaffDaoImpl();

    /**
     * Displays a sorted view of all camps for staff.
     * 
     * @param sortType The type of sorting to be applied to the camps.
     */    
    public void sortView(SortType sortType) {

        int i = 1;
        ArrayList<GregorianCalendar> dateList;
        Map<String, Staff> staffData = staffDao.getStaffs();
        Map<String, Camp> campsData = campDao.getCamps();
        ArrayList<Camp> campList = new ArrayList<Camp>(campsData.values());
        campList = SortCampUtil.sort(campList, sortType);

        // Display details of each camp
        PrintUtil.header("List of All Camps");
        for (Camp camp : campList) {
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
            System.out.printf("%-30s: %s\n","Staff-in-charge" , staffData.get(camp.getStaffInCharge()).getName());
            System.out.printf("%-30s: %s\n","Visibility" , Visibility.toString(camp.getVisibility()));
            System.out.println();
        }
    }
}
