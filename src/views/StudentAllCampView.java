package views;

import java.util.ArrayList;
import interfaces.views.Viewable;
import interfaces.views.FilterViewable;

public class StudentAllCampView implements Viewable {
    public void view() {
        CampDaoInterface campDao = CampDaoImplementation();
        ArrayList<Camp> camps = campDao.getCamps();
        System.out.println("===== Student All Camps =====");
        System.out.printf(
                "Index | Camp Name | Dates | Registration closing date | Open to | Location | Total slots | Camp Committee slots | Description | Visibility");
    }
}
