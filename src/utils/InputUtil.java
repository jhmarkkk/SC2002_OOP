package utils;

import java.util.Scanner;

public class InputUtil {
    
    private static final Scanner sc = new Scanner(System.in);

    public static int choice() {

        System.out.print("\nChoice: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int nextInt(String prompt) {

        System.out.print(String.format("\n%s: ", prompt));
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static String nextString(String prompt) {

        System.out.print(String.format("\n%s: ", prompt));
        return sc.nextLine();
    }
}
