package interfaces.views;

import java.util.ArrayList;

enum FilterType {
    NAME,
    DATES,
    CLOSING_DATE,
    FACULTY,
    STAFF
}

public interface FilterViewable {
    void filter(ArrayList<String> idList, FilterType filterType);
}
