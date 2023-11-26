package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import enums.SortType;
import enums.Role;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampDetailViewable;
import interfaces.views.CampViewable;

import models.Camp;
import models.Student;
import models.Staff;

import utils.DateUtil;
import utils.PrintUtil;
import utils.SortCampUtil;

/**
 * The {@code RegisteredCampView} class provides a view to display the list of camps that a student is registered for.
 * It implements the {@link CampViewable} interface.
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public class RegisteredCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final StaffDao staffDao = new StaffDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private static final CampDetailViewable campDetailView = new CampDetailView();
    
    /**
     * Displays the list of registered camps for the current student.
     * 
     * @param sortType The type of sorting to be applied to the camps.
     */    
    public void sortView(SortType sortType) {

        int i = 1;
        ArrayList<GregorianCalendar> dateList;
        Student student = (Student)currentUserDao.getCurrentUser();
        Map<String, Camp> campData = campDao.getCamps();
        Map<String, Staff> staffData = staffDao.getStaffs();
        ArrayList<String> registeredCampNameList = student.getRegisteredCamps();
        ArrayList<Camp> registeredCampList = new ArrayList<Camp>();

        for (String registeredCampID : registeredCampNameList) {
            registeredCampList.add(campData.get(registeredCampID));
        }
        
        registeredCampList = SortCampUtil.sort(registeredCampList, sortType);
        
        // If the student has a committee role, display the facilitating camp details
        PrintUtil.header("List of Registered Camp");
        if (student.getRole() == Role.COMMITTEE) campDetailView.view();

        // Display the details of each registered camp
        PrintUtil.header("Attending Camps");
        for (Camp camp : registeredCampList) {
            dateList = camp.getDates();
            PrintUtil.header(String.format("Camp %d", i++));
            System.out.printf("%-30s: %s\n","Name" , camp.getName());
            System.out.printf("%-30s: %s -> %s\n","Duration",
                DateUtil.toString(dateList.get(0)),
                DateUtil.toString(dateList.get(dateList.size() - 1)));
            System.out.printf("%-30s: %s\n","Registration Closing Date" ,DateUtil.toString(camp.getRegistrationClosingDate()));
            System.out.printf("%-30s: %s\n","User group" , camp.getOpenTo());
            System.out.printf("%-30s: %s\n","Location" , camp.getLocation());
            System.out.printf("%-30s: %s\n","Remaining attendee slots", camp.getAttendeeSlots() - camp.getAttendees().size());
            System.out.printf("%-30s: %s\n","Remaining camp committee slots",
                camp.getCommitteeSlots() - camp.getCommitteeMembers().size());
            System.out.printf("%-30s: %s\n","Description" , camp.getDescription());
            System.out.printf("%-30s: %s\n","Staff-in-charge" , staffData.get(camp.getStaffInCharge()).getName());
            System.out.println();
        }
    }
}
