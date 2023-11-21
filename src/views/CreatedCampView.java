package views;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import interfaces.dao.CampDaoInterface;
import interfaces.views.CampViewable;
import models.Camp;
import enums.FilterType;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

    public void filterView(FilterType filterType) {
        CampDaoInterface campDao;
        ArrayList<Camp> camps = campDao.getCamps();
        CurrentUserDaoInterface currentUserDao;
        ArrayList<String> campIDs = currentUserDao.getCurrentUser().getCreatedCamps();
        for (String campID : campIDs) {
            for (Camp camp : camps) {
                // if the id does not match the idlist, remove it from camps
                if (!Objects.equals(campID, camp.getName())) {
                    camps.remove(camp);
                }
            }
        }
        System.out.println("===== Created Camps =====");
        System.out.printf(
                "Index | Camp Name | Dates | Registration closing date | Open to | Location | Total slots | Camp Committee slots | Description | Visibility");
    }
};
