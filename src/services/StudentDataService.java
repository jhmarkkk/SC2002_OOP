package services;

import java.lang.System;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Dictionary;
// import java.util.Enumeration;
// import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
// import java.util.Scanner;

import interfaces.services.DataServiceable;
import models.Student;

public class StudentDataService implements DataServiceable {

	private Map<String, Student> studentDataMap = new HashMap<>();

	public void exporting (String filePath) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write header line
            bw.write("Name,Email,Faculty,Password,RegisteredCamps,Enquiries");
            bw.newLine();

            // Iterate through the studentDataMap and write each student's data to the file
            for (Map.Entry<String, Student> entry : studentDataMap.entrySet()) {
                Student student = entry.getValue();

				String registeredCampsString;
				if (student.getRegisteredCamps().isEmpty()) {
					registeredCampsString = "#NULL!";
				} else {
					registeredCampsString = String.join("|", student.getRegisteredCamps());
				}

                // Write data fields separated by commas
                bw.write(student.getName() + ","
                        + student.getUserID() + "@e.ntu.edu.sg,"
                        + student.getFaculty() + ","
                        + student.getPassword() + ","
                        + registeredCampsString);
                bw.newLine();
            }

            System.out.println("Data exported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void importing(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowNumber = 0;

            while ((line = br.readLine()) != null) {
                rowNumber++;

                // Start only from 2nd row, neglecting the column header
                if (rowNumber >= 2) {
                    // Split the line into fields based on the comma separator
                    String[] fields = line.split(",");

                    // Ensure that there are enough fields to extract the email
                    if (fields.length > 1) {
						String name = fields[0];
                        String email = fields[1];
						String faculty = fields[2];
						String password = fields[3];

						String username = email.substring(0, email.indexOf('@'));

						//Extract Registered Camp(s)
						String registeredCampsString = fields[4];
						//Initialise Array to store Registered Camp(s)
						ArrayList<String> registeredCamps = new ArrayList<String>();
						//If there's no registered camp

						//There exsit a camp registered
						if (!registeredCampsString.equals("#NULL!")){
							if (registeredCampsString.contains("|")) {	//registered for > 1 camp
								registeredCamps.addAll(Arrays.asList(registeredCampsString.split("\\|")));
							} else {	//registered for 1 camp only
								registeredCamps.add(registeredCampsString);
							}
						}
	
						//Initialising student constructor
						Student studentData = new Student(username, password, name, faculty, registeredCamps);

						// Put the data into the map with username as key
						studentDataMap.put(username, studentData);
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
			System.out.println("Data imported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
