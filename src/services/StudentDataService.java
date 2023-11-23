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

import interfaces.services.DataServiceable;
import models.Student;
import enums.Role;

public class StudentDataService implements DataServiceable {

	public void exporting (String filePath) {
		// TODO Auto-generated method stub
	}
	
	public void importing(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowNumber = 1;

			Map<String, Student> studentDataMap = new HashMap<>();

            while ((line = br.readLine()) != null) {
                rowNumber++;

                // Check if this is the second row
                if (rowNumber >= 2) {
                    // Split the line into fields based on the comma separator
                    String[] fields = line.split(",");

                    // Ensure that there are enough fields to extract the email
                    if (fields.length > 1) {
						String name = fields[0];
                        String email = fields[1];
						String faculty = fields[2];
						String password = fields[3];
						Role role = Role.STUDENT;

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
	

						Student studentData = new Student(username, password, name, faculty, role, registeredCamps);

						// Put the data into the map with username as key
						studentDataMap.put(username, studentData);

						//PRINTING student map
						Student exampleStudentData = studentDataMap.get(username);
						System.out.println("Username:" + exampleStudentData.getUserID());
						System.out.println("Password:" + exampleStudentData.getPassword());
						System.out.println("Name:" + exampleStudentData.getName());
						System.out.println("Faculty:" + exampleStudentData.getFaculty());
						System.out.println("Role:" + exampleStudentData.getRole());
						System.out.println("Registered Camps:");
						int i = 0;
						for (String element : exampleStudentData.getRegisteredCamps()) {
							System.out.println("registeredCamps[" + i + "] : " + element);
							i++;
						}

						// Line Space
						System.out.println("");
						
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}