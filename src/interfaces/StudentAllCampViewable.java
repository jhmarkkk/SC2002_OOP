package interfaces;

import java.util.ArrayList;

public interface StudentAllCampViewable extends Viewable, ViewFilterable {
    void view();

    ArrayList<Object> filter(ArrayList<String> idList, filterBy filter);
}
