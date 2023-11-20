package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import interfaces.CreatedCampViewable;
import interfaces.ViewFilterable;
import interfaces.ViewFilterable.filterBy;
import interfaces.CampDaoInterface;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CreatedCampViewable {
    public void view() {
        System.out.println("===== Created Camps =====");
        System.out.printf("----- %s  -----");
    }

    /**
     * @param idlist
     * @return ArrayList<Object>
     */
    public ArrayList<Object> filter(ArrayList<String> idlist, filterBy filter) {
        // use the interface to get camps
        CampDaoInterface campDao = CampDaoImplementation();
        ArrayList<Object> camps = campDao.getCamps();
        for (String id : idlist) {
            for (Object camp : camps) {
                // if the id does not match the idlist, remove it from camps
                if (!Objects.equals(id, camp.getName())) {
                    camps.remove(camp);
                }
            }
        }
        if (filter == filterBy.ALPHA) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getName().compareTo.camp2.getName());
            return camps;
        }
        if (filter == filterBy.LOC) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getLocation().compareTo.camp2.getLocation());
            return camps;
        }
        if (filter == filterBy.DATE) {
            Collections.sort(camps, (camp1, camp2) -> camp1.getDate().compareTo.camp2.getName());
            return camps;
        }
    };
}
