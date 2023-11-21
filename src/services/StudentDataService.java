package services;

import java.lang.System;
import java.io.*;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import interfaces.services.DataServiceable;
import models.User;
import enums.Role;

public class StudentDataService implements DataServiceable {

	public void exporting (String filePath) {
		// TODO Auto-generated method stub
	}
	
	public void importing(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowNumber = 0;

			Map<String, User> studentDataMap = new HashMap<>();

            while ((line = br.readLine()) != null) {
                rowNumber++;

                // Check if this is the second row
                if (rowNumber >= 2) {
                    // Split the line into fields based on the comma separator
                    String[] fields = line.split(",");

                    // Ensure that there are enough fields to extract the email
                    if (fields.length > 1) {
                        // get email
						String name = fields[0];
                        String email = fields[1];
						String faculty = fields[2];
						String password = fields[3];
						Role role = Role.STUDENT;

						String username = email.substring(0, email.indexOf('@'));
						System.out.println("Username: " + username);
						

						//User studentData = new User(username, password, name, faculty, role);
						User studentData = new User(password, name, faculty, role);

						// Put the data into the map with username as key
						studentDataMap.put(username, studentData);

						User exampleStudentData = studentDataMap.get(username);
						//System.out.println("Username:" + exampleStudentData.getUserID());
						System.out.println("Password:" + exampleStudentData.getPassword());
						System.out.println("Name:" + exampleStudentData.getName());
						System.out.println("Faculty:" + exampleStudentData.getFaculty());
						System.out.println("Role:" + exampleStudentData.getRole() + "\n");						
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
