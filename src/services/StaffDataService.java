package services;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import enums.Role;
import interfaces.services.DataServiceable;
import models.User;

public class StaffDataService implements DataServiceable {
	
	public void exporting (String filePath) {
		// TODO Auto-generated method stub
	}
	
	public void importing (String filePath) {
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowNumber = 0;

			Map<String, User> staffDataMap = new HashMap<>();

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
						Role role = Role.STAFF;

						String username = email.substring(0, email.indexOf('@'));
						System.out.println("Username: " + username);
						

						User staffData = new User(username, password, name, faculty, role);

						// Put the data into the map with username as key
						staffDataMap.put(username, staffData);

						User exampleStaffData = staffDataMap.get(username);
						System.out.println("Username:" + exampleStaffData.getUserID());
						System.out.println("Password:" + exampleStaffData.getPassword());
						System.out.println("Name:" + exampleStaffData.getName());
						System.out.println("Faculty:" + exampleStaffData.getFaculty());
						System.out.println("Role:" + exampleStaffData.getRole() + "\n");						
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
