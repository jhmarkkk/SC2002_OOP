package services;

import java.lang.System;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.services.DataServiceable;
import models.User;
import models.Student;
import enums.Role;

public class StudentDataService implements DataServiceable {

	// //Export into CSV
	// public void exporting (String filePath) {
	// 	// TODO Auto-generated method stub
	// }
	
	// //Import into system
	// public void importing(String filePath) {
    //     try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
    //         String line;
    //         int rowNumber = 0;

	// 		Map<String, User> studentDataMap = new HashMap<>();

    //         while ((line = br.readLine()) != null) {
    //             rowNumber++;

    //             // Check if this is the second row
    //             if (rowNumber >= 2) {
    //                 // Split the line into fields based on the comma separator
    //                 String[] fields = line.split(",");

    //                 // Ensure that there are enough fields to extract the email
    //                 if (fields.length > 1) {
    //                     // get email
	// 					String name = fields[0];
    //                     String email = fields[1];
	// 					String faculty = fields[2];
	// 					String password = fields[3];
	// 					Role role = Role.STUDENT;
	// 					// ArrayList<String> registeredCamps = fields[4];
	// 					// ArrayList<String> enquiries = fields[5];

	// 					// Extract registered camps and enquiries as ArrayList<String>
    //         			String[] registeredCampsArray = fields[4].split(";"); // Assuming camps are separated by semicolon
    //         			ArrayList<String> registeredCamps = new ArrayList<>(Arrays.asList(registeredCampsArray));

    //         			String[] enquiriesArray = fields[5].split(";"); // Assuming enquiries are separated by semicolon
    //         			ArrayList<Integer> enquiries = new ArrayList<>(Arrays.asList(enquiriesArray));


	// 					String username = email.substring(0, email.indexOf('@'));
	// 					System.out.println("Username: " + username);
						

	// 					Student studentData = new Student(username, password, name, faculty, role, registeredCamps, enquiries);
	// 					//User studentData = new User(password, name, faculty, role);

	// 					// Put the data into the map with username as key
	// 					studentDataMap.put(username, studentData);

	// 					User exampleStudentData = studentDataMap.get(username);
	// 					System.out.println("Username:" + exampleStudentData.getUserID());
	// 					System.out.println("Password:" + exampleStudentData.getPassword());
	// 					System.out.println("Name:" + exampleStudentData.getName());
	// 					System.out.println("Faculty:" + exampleStudentData.getFaculty());
	// 					System.out.println("Role:" + exampleStudentData.getRole() + "\n");						
    //                 }
    //             }
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }

	//TRYING
	//public class trying {
    public void exportingTest(String filePath){
		// Create an array
        int[] dataArray = {1, 2, 3, 4, 5};

        // Create a string
        String exampleString = "Hello World!";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Convert the array to a comma-separated string
            String arrayString = Arrays.toString(dataArray)
					.replace(",", "|");

            // Write the array and string to the CSV file
            writer.write(String.format("%s,%s", arrayString, exampleString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public void exporting(String filePath){
        ArrayList<String> registeredCamps;
		ArrayList<String> enquiries;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Convert the array to a comma-separated string
			String commaSeparatedString_camps = String.join("|", registeredCamps);
			String commaSeparatedString_enquiries = String.join("|", enquiries);

            // Write the array and string to the CSV file
            writer.write(String.format("%s,%s", commaSeparatedString_camps));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void importingTest(String filePath){
        System.out.println("importing()");

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();

            if (line != null && !line.isEmpty()) {
                // Use regex to find the array enclosed in square brackets
                Pattern pattern = Pattern.compile("\\[([^\\]]+)\\]");
                Matcher matcher = pattern.matcher(line);

                ArrayList<Integer> importedArray = new ArrayList<>();

                // If a match is found, extract the array elements
                if (matcher.find()) {
                    String arrayContent = matcher.group(1);
                    String[] elements = arrayContent.split("\\s*,\\s*");

                    // Convert the elements to integers and add them to the ArrayList
                    for (String element : elements) {
                        importedArray.add(Integer.parseInt(element));
                    }
                }

                // Print each element of the array
                System.out.println("Imported Array:");
                int i = 0;
                for (int element : importedArray) {
                    System.out.println("index[" + i + "] : " + element);
                    i++;
                }

                // Print the string
                System.out.println("Imported String: " + line.substring(line.lastIndexOf(',') + 1));
            } else {
                System.out.println("The CSV file is empty.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }    
    //}
}
}

