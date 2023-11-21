package views;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import enums.FilterType;
import models.Camp;
import models.Student;
import models.CommitteeMember;
import interfaces.views.CampViewable;
import utils.CampFilter;

public class RegisteredCampView implements CampViewable {
    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        ArrayList<Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        Student student = currentUserDao.getCurrentUser();
        String role = student.getRole().toString();
        System.out.println("===== List of Registered Camps =====");
        if (role == "Committee") {
            CommitteeMember committeeMember = (CommitteeMember) student;
            for (Camp camp : camps) {
                // find the camp the Committee facilitates
                if (camp.getName().equals(committeeMember.getFacilitatingCamp())) {
                    System.out.printf("----- (Facilitating Camp) %s -----\n", camp.getName());
                    System.out.print("Dates: ");
                    for (Date date : camp.getDates()) {
                        System.out.printf("%s ", date.toString());
                    }
                    System.out.printf("\nLocation: %s\n", camp.getLocation());
                    System.out.printf("Attendee Slots available: %d\n", camp.getAttendeeSlots());
                    System.out.printf("Camp Committee Slots available: %d\n", camp.getCommitteeSlots());
                    System.out.printf("Description: %s\n", camp.getDescription());
                    System.out.printf("Staff in charge: %s\n", camp.getStaffInCharge().getName());
                }

            }
        }
        ArrayList<String> registeredCampIDs = student.getRegisteredCamps();
        for (String registeredCampID : registeredCampIDs) {
            for (Camp camp : camps) {
                // if the id does not match the idlist, remove it from camps
                if (!Objects.equals(registeredCampID, camp.getName())) {
                    camps.remove(camp);
                }
            }
        }
        camps = CampFilter.filter(camps, filterType);
        int index = 1;
        for (Camp camp : camps) {
            System.out.printf("----- (Attending Camp %d) %s -----\n", index, camp.getName());
            System.out.print("Dates: ");
            for (Date date : camp.getDates()) {
                System.out.printf("%s ", date.toString());
            }
            System.out.printf("\nLocation: %s\n", camp.getLocation());
            System.out.printf("Attendee Slots available: %d\n", camp.getAttendeeSlots());
            System.out.printf("Camp Committee Slots available: %d\n", camp.getCommitteeSlots());
            System.out.printf("Description: %s\n", camp.getDescription());
            System.out.printf("Staff in charge: %s\n", camp.getStaffInCharge().getName());
        }
    }
}
