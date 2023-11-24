package views;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;
import models.Camp;
import enums.SortType;
import interfaces.views.CampViewable;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;

public class StudentAllCampView implements CampViewable {

    @Override
    public void sortView(SortType sortType) {
        // TODO Auto-generated method stub

        CampDao campDao = new CampDaoImpl();
        Map<String, Camp> campMap = campDao.getCamps();
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        String faculty = currentUserDao.getCurrentUser().getFaculty();
        
    }

    // public void view() {
    //     CampDaoInterface campDao = CampDaoImplementation();
    //     ArrayList<Camp> camps = campDao.getCamps();
    //     int index = 1;
    //     System.out.println("===== List of Camps =====");
    //     for (Camp camp : camps) {
    //         System.out.printf("----- (%d) %s -----\n", index, camp.getName());
    //         System.out.print("Dates: ");
    //         for (Date date : camp.getDates()) {
    //             System.out.printf("%s ", date.toString());
    //         }
    //         System.out.printf("\nLocation: %s\n", camp.getLocation());
    //         System.out.printf("Attendee Slots available: %d\n", camp.getAttendeeSlots());
    //         System.out.printf("Camp Committee Slots available: %d\n", camp.getCommitteeSlots());
    //         System.out.printf("Description: %s\n", camp.getDescription());
    //         System.out.printf("Staff in charge: %s\n", camp.getStaffInCharge().getName());
    //     }
    // }
}
