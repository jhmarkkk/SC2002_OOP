package views;

import java.util.ArrayList;
import java.util.Map;
import java.util.Collections;
import java.util.Objects;

import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StaffDao;
import interfaces.views.CampViewable;

import dao.CurrentUserDaoImpl;
import dao.CampDaoImpl;
import dao.StaffDaoImpl;

import models.Camp;
import models.Staff;

import enums.SortType;

/**
 * This file is the implementation for staff to view all the camps that the
 * staff are currently in control of
 * 
 * @author Jiejun
 */

public class CreatedCampView implements CampViewable {

	public void sortView(SortType sortType) {
		CampDao campDao = new CampDaoImpl();
		Map<String, Camp> campMap = campDao.getCamps(); 
        
        CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
        String staffID = currentUserDao.getCurrentUser().getUserID();
        
        StaffDao staffDao = new StaffDaoImpl();
        Map<String, Staff> staffMap = staffDao.getStaffs();
        ArrayList<String> idlist = staffMap.get(staffID).getCreatedCamps();
        
        ArrayList<Camp> sortedCamps;
        System.out.println("===== Created Camps =====");
        System.out.printf("Index | Camp Name | Dates | Registration closing date | Open to | Location | Total slots | Camp Committee slots | Description | Visibility");
        
    }
	

	CreatedCampView() {
         CampDao campDao = new CampDaoImpl();
         ArrayList<Camp> createdCamps = 
         ArrayList<String> idlist = currentUserDao.getCurrentUser().getCreatedCamps();
         for (String id : idlist) {
             for (Camp camp : camps) {
                 // if the id does not match the idlist, remove it from camps
                 if (!Objects.equals(id, camp.getName())) {
                     camps.remove(camp);
                 }
             }
         }

     private ArrayList<Camp> createdCamps = createdCamps;
     }

     public void view() {
         

     /**
      * @param idlist
      * @return ArrayList<Object>
      */
     public void filter(ArrayList<String> idlist, FilterType filterType = filterType.Name) {
         // use the interface to get camps
         CampDaoInterface campDao = CampDaoImplementation();
         ArrayList<Camp> camps = campDao.getCamps();
         for (String id : idlist) {
             for (Camp camp : camps) {
                 // if the id does not match the idlist, remove it from camps
                 if (!Objects.equals(id, camp.getName())) {
                     camps.remove(camp);
                 }
             }
         }
         for (Camp camp : camps) {
         }
         if (filter == FilterType.NAME) {
             Collections.sort(camps, (camp1, camp2) -> camp1.getName().compareTo.camp2.getName());
             return camps;
         }
         if (filter == FilterType.DATES) {
             Collections.sort(camps, (camp1, camp2) -> camp1.getLocation().compareTo.camp2.getLocation());
             return camps;
         }
         if (filter == FilterType.DATE) {
             Collections.sort(camps, (camp1, camp2) -> camp1.getDate().compareTo.camp2.getName());
             return camps;
         }
     }
}
