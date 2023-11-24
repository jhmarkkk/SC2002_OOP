package services;

import java.lang.System;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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

                String enquiryString;
                if (student.getEnquiries().isEmpty()) {
                    enquiryString = "#NULL!";
                } else {
                    enquiryString = String.join("|", student.getEnquiries().stream().map(Object::toString).toArray(String[]::new));
                }

                // Write data fields separated by commas
                bw.write(student.getName() + ","
                        + student.getUserID() + "@e.ntu.edu.sg,"
                        + student.getFaculty() + ","
                        + student.getPassword() + ","
                        + registeredCampsString + ","
                        + enquiryString);
                bw.newLine();
            }

            System.out.println("Student data exported successfully!");
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

						//There exist camp(s) registered
						if (!registeredCampsString.equals("#NULL!")){
							if (registeredCampsString.contains("|")) {	//registered for > 1 camp
								registeredCamps.addAll(Arrays.asList(registeredCampsString.split("\\|")));
							} else {	//registered for 1 camp only
								registeredCamps.add(registeredCampsString);
							}
						}

                        //Extract enquiries
                        String enquiryString = fields[5];
                        //Initialise Array to store enquiry IDs
                        ArrayList<Integer> enquiry = new ArrayList<Integer>();

                        //There exist enquiry
                        if (!enquiryString.equals("#NULL!")){
                            if (enquiryString.contains("|")){
                                //enquiry.addAll(Arrays.asList(enquiryString.split("\\|")));
                                Arrays.asList(enquiryString.split("\\|")).forEach(s -> enquiry.add(Integer.parseInt(s)));
                            } else {
                                //enquiry.add(enquiryString);
                                enquiry.add(Integer.parseInt(enquiryString));
                            }
                        }
	
						//Initialising student constructor
						Student studentData = new Student(username, password, name, faculty, registeredCamps, enquiry);

						// Put the data into the map with username as key
						studentDataMap.put(username, studentData);
                    }
                }
            }
			System.out.println("Student data imported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}