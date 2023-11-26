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
 * The {@code CreatedCampView} class provides a view for staff to see all camps they have created.
 * It implements the {@link CampViewable} interface.
 * 
 * @author Chong Jiejun
 * @version 1.0
 * @since 1.0
 */
public class CreatedCampView implements CampViewable {

    private static final CampDao campDao = new CampDaoImpl();

    private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

    /**
     * Displays a sorted view of all camps created by the current staff member.
     *
     * @param sortType The type of sorting to be applied.
     */    
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
