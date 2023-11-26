package utils;


public class PrintUtil {
    
    private static final int SIZE = 100;

    public static void header(String title) {

        int padSize = SIZE - title.length();

        System.out.println();
        System.out.print("-".repeat(padSize / 2));
        System.out.print(title);
        System.out.println("-".repeat(Math.round(padSize / 2)));
    }

    public static void invalid(String type) {

        System.out.printf("Invalid %s. Try again.\n", type);
        System.out.println("-".repeat(SIZE - 1));
    }
}
