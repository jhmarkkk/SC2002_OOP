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
}
