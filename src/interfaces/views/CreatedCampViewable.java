package interfaces.views;

import java.util.ArrayList;

public interface CreatedCampViewable extends Viewable, FilterViewable {
    void view(ArrayList<Camp>);

    void filter(ArrayList<String> idList, FilterType filter);
}
