package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StaffDaoImpl;

import enums.SortType;
import enums.Visibility;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampViewable;

import models.Camp;
import models.Staff;

import utils.SortCampUtil;
import utils.DateUtil;
import utils.PrintUtil;

public class StudentAllCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl(); 

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    private static final StaffDao staffDao = new StaffDaoImpl();

    public void sortView(SortType sortType) {

        int i = 1;
        Map<String, Camp> campsData = campDao.getCamps();
        Map<String, Staff> staffData = staffDao.getStaffs();
        String faculty = currentUserDao.getCurrentUser().getFaculty();
        ArrayList<Camp> campList = new ArrayList<Camp>();
        ArrayList<GregorianCalendar> dateList;

        for (Camp camp : campsData.values()) {

            if (camp.getVisibility() == Visibility.OFF) continue;

            if (camp.getOpenTo().equals("NTU") || (camp.getOpenTo().equals(faculty))) campList.add(camp);
        }

        campList = SortCampUtil.sort(campList, sortType);
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
            System.out.printf("%-30s: %s\n","Remaining attendee slots", camp.getAttendeeSlots() - camp.getAttendees().size());
            System.out.printf("%-30s: %s\n","Remaining camp committee slots",
                camp.getCommitteeSlots() - camp.getCommitteeMembers().size());
            System.out.printf("%-30s: %s\n","Description" , camp.getDescription());
            System.out.printf("%-30s: %s\n","Staff-in-charge" , staffData.get(camp.getStaffInCharge()).getName());
            System.out.println();
        }
    }
}
