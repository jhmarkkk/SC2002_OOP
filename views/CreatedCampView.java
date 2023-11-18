package views;

import java.util.ArrayList;

import interfaces.CreatedCampViewable;
import interfaces.Filterable;

public class CreatedCampView implements CreatedCampViewable {
    public void view() {
        System.out.println("Chainsaw Man");
    }

    public ArrayList<Object> filter(ArrayList<String> idlist) {
        
    };
}
