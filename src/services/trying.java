package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class trying {
    public void exporting(String filePath){
       // Create arrays
       String[] registeredCamps = {"Camp1", "Camp2", "Camp3"};
    //    Integer[] enquiries = {10, 20, 30, 40, 50};

       // Create a string
       String exampleString = "Hello World!";

       try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Convert the arrays to pipe-separated strings
            String registeredCampsString = String.join("|", registeredCamps);

            // String enquiriesString = String.join("|", Arrays.toString(enquiries)
            // .replaceAll("\\s", "")
            // .replace("[", "")
            // .replace("]", "")
            // .split(","));

           // Write the arrays and string to the CSV file
        //    writer.write(String.format("%s,%s,%s", registeredCampsString, enquiriesString, exampleString));
           writer.write(String.format("%s,%s", registeredCampsString, exampleString));
        } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public void importing(String filePath){
        System.out.println("importing()");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();

            if (line != null && !line.isEmpty()) {
                // Split the line into fields based on the comma separator
                String[] fields = line.split(",");

                // Ensure that there are enough fields
                if (fields.length >= 2) {
                    // Extract arrays from fields[0] and fields[1]
                    String registeredCampsString = fields[0];
                    String exampleString = fields[1];

                    // Split the strings into arrays based on the "|" separator
                    //String[] registeredCampsArray = registeredCampsString.split("\\|");
                    ArrayList<String> registeredCamps = new ArrayList<String>(Arrays.asList(registeredCampsString.split("\\|")));

                    // Print each element of the registeredCampsArray
                    System.out.println("Registered Camps:");
                    int i = 0;
                    for (String element : registeredCamps) {
                        System.out.println("index[" + i + "] : " + element);
                    }

                    // Print the string
                    System.out.println("Imported String: " + exampleString);

                } else {
                    System.out.println("The CSV file does not have enough fields.");
                }
            } else {
                System.out.println("The CSV file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }   
    }
}
