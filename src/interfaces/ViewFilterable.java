package interfaces;

import java.util.ArrayList;

enum filterBy {
    ALPHA,
    DATE,
    LOC
}

public interface ViewFilterable {
    ArrayList<Object> filter(ArrayList<String> idList, filterBy filter);
}
