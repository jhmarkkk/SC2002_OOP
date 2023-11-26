package views;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import enums.SortType;
import enums.Visibility;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.views.CampViewable;

import models.Camp;
import models.Staff;

import utils.SortCampUtil;
import utils.DateUtil;
import utils.PrintUtil;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    public void sortView(SortType sortType) {

        int i = 1;
        ArrayList<GregorianCalendar> dateList;
        Staff currentUser = (Staff)currentUserDao.getCurrentUser();
        ArrayList<String> createdCampNameList = currentUser.getCreatedCamps();
        Map<String, Camp> campsData = campDao.getCamps();
        ArrayList<Camp> createdCampList = new ArrayList<Camp>();

        for (String campName : createdCampNameList) {
            createdCampList.add(campsData.get(campName));
        }

        createdCampList = SortCampUtil.sort(createdCampList, sortType);
        PrintUtil.header("List of All Camps");
        for (Camp camp : createdCampList) {
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
            System.out.printf("%-30s: %s\n","Visibility" , Visibility.toString(camp.getVisibility()));
            System.out.println();
        }
    }
}
