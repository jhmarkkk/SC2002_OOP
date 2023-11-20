package interfaces.views;

import java.util.ArrayList;

public interface StaffAllCampViewable extends Viewable, FilterViewable {
    void view();

    ArrayList<Object> filter(ArrayList<String> idList, filterBy filter);
}
