package interfaces.views;

import java.util.ArrayList;

public interface RegisteredCampViewable extends Viewable, FilterViewable {
    void view(ArrayList<Camp> camps);

    ArrayList<Camp> filter(ArrayList<String> idList, filterBy filter);
}
