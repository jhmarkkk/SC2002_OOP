package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import enums.SortType;
import interfaces.views.CampViewable;
// import interfaces.views.FilterViewable;

public class StudentAllCampView implements CampViewable {

    @Override
    public void sortView(SortType sortType) {
        // TODO Auto-generated method stub

        CampDao campDao = CampDaoImplementation();
        ArrayList<Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        String faculty = currentUserDao.getCurrentUser().getFaculty();
        
    }

    StudentAllCampView() {
        for (Camp camp : camps) {
            // if the id does not match the idlist, remove it from camps
            if (!Objects.equals(id, camp.getName())) {
                camps.remove(camp);
            }
        }
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
