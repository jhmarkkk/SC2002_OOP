package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import interfaces.dao.CampDaoInterface;
import interfaces.views.CampViewable;
import interfaces.views.ViewFilterable;
import enums.FilterType;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    public void filterView(FilterType filterType) {

    }

    // CreatedCampView() {
    //     CampDaoInterface campDao = new CampDaoImpl();
    //     ArrayList<Camp> createdCamps = campDao.getCamps(); 
    //     CurrentUserDaoInterface currentUserDao;
    //     ArrayList<String> idlist = currentUserDao.getCurrentUser().getCreatedCamps();
    //     for (String id : idlist) {
    //         for (Camp camp : camps) {
    //             // if the id does not match the idlist, remove it from camps
    //             if (!Objects.equals(id, camp.getName())) {
    //                 camps.remove(camp);
    //             }
    //         }
    //     }

    // private ArrayList<Camp> createdCamps = createdCamps;
    // }

    // public void view() {
    //             CampDaoInterface campDao = CampDaoImplementation();
    //     ArrayList<Camp> camps = campDao.getCamps();
    //     System.out.println("===== Created Camps =====");
    //     System.out.printf(
    //             "Index | Camp Name | Dates | Registration closing date | Open to | Location | Total slots | Camp Committee slots | Description | Visibility");
    // }

    // /**
    //  * @param idlist
    //  * @return ArrayList<Object>
    //  */
    // public void filter(ArrayList<String> idlist, FilterType filterType = filterType.Name) {
    //     // use the interface to get camps
    //     CampDaoInterface campDao = CampDaoImplementation();
    //     ArrayList<Camp> camps = campDao.getCamps();
    //     for (String id : idlist) {
    //         for (Camp camp : camps) {
    //             // if the id does not match the idlist, remove it from camps
    //             if (!Objects.equals(id, camp.getName())) {
    //                 camps.remove(camp);
    //             }
    //         }
    //     }
    //     for (Camp camp : camps) {
    //     }
    //     if (filter == FilterType.NAME) {
    //         Collections.sort(camps, (camp1, camp2) -> camp1.getName().compareTo.camp2.getName());
    //         return camps;
    //     }
    //     if (filter == FilterType.DATES) {
    //         Collections.sort(camps, (camp1, camp2) -> camp1.getLocation().compareTo.camp2.getLocation());
    //         return camps;
    //     }
    //     if (filter == FilterType.DATE) {
    //         Collections.sort(camps, (camp1, camp2) -> camp1.getDate().compareTo.camp2.getName());
    //         return camps;
    //     }
    // };
}
