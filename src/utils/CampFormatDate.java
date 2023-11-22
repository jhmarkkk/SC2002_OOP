package utils;

import java.text.SimpleDateFormat;

public class CampFormatDate {
    public static SimpleDateFormat dateFormat() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy, HH:mm");
        return formatter;
    }
}
