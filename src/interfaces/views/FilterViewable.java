package interfaces.views;

import java.util.ArrayList;

// enum FilterType {
//     NAME,
//     DATES,
//     CLOSING_DATE,
//     FACULTY,
//     STAFF
// }

import enums.SortType;

public interface FilterViewable {
    //void filter(ArrayList<String> idList, FilterType filterType);

    public void sortView(SortType sortType);
}
