package interfaces.views;

import enums.SortType;

public interface CampViewable extends FilterViewable {
    // void view(ArrayList<Camp>);

    // void filter(ArrayList<String> idList, FilterType filter);
	
	public void sortView(SortType sortType);
}
