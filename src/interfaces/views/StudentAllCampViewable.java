package interfaces.views;

import java.util.ArrayList;

public interface StudentAllCampViewable extends Viewable, FilterViewable {
    void view(ArrayList<Camp> camps);

    ArrayList<Object> filter(ArrayList<String> idList, filterBy filter);
}
