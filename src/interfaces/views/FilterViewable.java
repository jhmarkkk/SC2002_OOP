package interfaces.views;

import java.util.ArrayList;

// enum FilterType {
//     NAME,
//     DATES,
//     CLOSING_DATE,
//     FACULTY,
//     STAFF
// }

import enums.FilterType;

public interface FilterViewable {
    //void filter(ArrayList<String> idList, FilterType filterType);

    public void filterView(FilterType filterType);
}
