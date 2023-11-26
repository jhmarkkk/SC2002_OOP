package utils;

/**
 * The {@code PrintUtil} class provides utility methods for printing headers and error messages.
 * It includes methods to print headers with a specified title and display invalid input messages.
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 */
public class PrintUtil {
    
    private static final int SIZE = 100;

    /**
     * Prints a header with the specified title.
     * 
     * @param title the title of the header
     */
    public static void header(String title) {

        int padSize = SIZE - title.length();

        System.out.println();
        System.out.print("-".repeat(padSize / 2));
        System.out.print(title);
        System.out.println("-".repeat(Math.round(padSize / 2)));
    }

    /**
     * Prints an invalid input message for a specified type.
     * 
     * @param type the type of input that was invalid
     */    
    public static void invalid(String type) {
        System.out.printf("Invalid %s. Try again.\n", type);
        System.out.println("-".repeat(SIZE - 1));
    }
}
