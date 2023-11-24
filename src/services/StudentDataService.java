package services;

import java.lang.System;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import interfaces.services.DataServiceable;
import interfaces.dao.StudentDao;
import dao.StudentDaoImpl;
import models.Student;

public class StudentDataService implements DataServiceable {

	public void exporting (String filePath) {

        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentDataMap = studentDao.getStudents();

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
                    // Construct the enquiries string
                    StringBuilder enquiryStringBuilder = new StringBuilder();
                    for (Map.Entry<String, ArrayList<Integer>> campEnquiryEntry : student.getEnquiries().entrySet()) {
                        String campName = campEnquiryEntry.getKey();
                        ArrayList<Integer> enquiryList = campEnquiryEntry.getValue();
                        String campEnquiryString = campName + "=" + String.join("|", enquiryList.stream().map(Object::toString).toArray(String[]::new));
                        enquiryStringBuilder.append(campEnquiryString).append("*");
                    }
                
                    // Remove the trailing asterisk if there are any enquiries
                    enquiryString = enquiryStringBuilder.length() > 0 ? enquiryStringBuilder.substring(0, enquiryStringBuilder.length() - 1) : "#NULL!";
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

        StudentDao studentDao = new StudentDaoImpl();
        Map<String, Student> studentDataMap = studentDao.getStudents();

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

                        Map<String, ArrayList<Integer>> enquiries = new HashMap<>();

                        if (!enquiryString.equals("#NULL!")){

                            String[] campEnquiries;
                            if (enquiryString.contains("*")){
                                // Split the string based on the asterisk "*"
                                campEnquiries = enquiryString.split("\\*");
                            } else {
                                campEnquiries = new String[]{enquiryString};
                            }

                            for (String campEnquiry : campEnquiries) {
                                // Split each campEnquiry based on the equal sign "="
                                String[] parts = campEnquiry.split("=");
                                    
                                // Ensure there are enough parts before accessing indices
                                if (parts.length == 2) {
                                    // The first part is the camp name, and the second part is the list of integers separated by "|"
                                    String campName = parts[0].trim();

                                    String[] enquiryValues;
                                    if (parts[1].contains("|")){
                                        enquiryValues = parts[1].split("\\|");
                                    } else {
                                        enquiryValues = new String[]{parts[1]};
                                    }

                                    // Convert the values to integers and add to the ArrayList
                                    ArrayList<Integer> enquiryList = new ArrayList<>();
                                    for (String value : enquiryValues) {
                                        enquiryList.add(Integer.parseInt(value));
                                    }

                                    // Put the campName and enquiryList into the Map
                                    enquiries.put(campName, enquiryList);
                                }
                            }                            
                        }                        	
						//Initialising student constructor
						Student studentData = new Student(username, password, name, faculty, registeredCamps, enquiries);

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