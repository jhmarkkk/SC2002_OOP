package utils;

import java.util.Scanner;

/**
 * The {@code InputUtil} class provides utility methods for handling user input.
 * It includes methods to read integers and strings from the console.
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 */
public class InputUtil {
    
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Reads and returns an integer from the console.
     * 
     * @return the integer entered by the user
     */    
    public static int choice() {
        System.out.print("\nChoice: ");
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Reads and returns an integer from the console with a specified prompt.
     * 
     * @param prompt the prompt to display to the user
     * 
     * @return the integer entered by the user
     */    
    public static int nextInt(String prompt) {
        System.out.print(String.format("\n%s: ", prompt));
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Reads and returns a string from the console with a specified prompt.
     * 
     * @param prompt the prompt to display to the user
     * 
     * @return the string entered by the user
     */    
    public static String nextString(String prompt) {
        System.out.print(String.format("\n%s: ", prompt));
        return sc.nextLine();
    }
}
