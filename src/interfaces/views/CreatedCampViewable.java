package interfaces.views;

import java.util.ArrayList;

public interface CreatedCampViewable extends Viewable, ViewFilterable {
    void view(ArrayList<Camp>);

    ArrayList<Object> filter(ArrayList<String> idList, filterBy filter);
}
