package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import interfaces.services.DataServiceable;
import models.Staff;

public class StaffDataService implements DataServiceable {
	
	public void exporting (String filePath) {

        Map<String, Staff> staffDataMap = new HashMap<>();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write header line
            bw.write("Name,Email,Faculty,Password,createdCamps");
            bw.newLine();

            // Iterate through the studentDataMap and write each student's data to the file
            for (Map.Entry<String, Staff> entry : staffDataMap.entrySet()) {
                Staff staff = entry.getValue();

				String createdCampsString;
				if (staff.getCreatedCamps().isEmpty()) {
					createdCampsString = "#NULL!";
				} else {
					createdCampsString = String.join("|", staff.getCreatedCamps());
				}

                // Write data fields separated by commas
                bw.write(staff.getName() + ","
                        + staff.getUserID() + "@ntu.edu.sg,"
                        + staff.getFaculty() + ","
                        + staff.getPassword() + ","
                        + createdCampsString);
                bw.newLine();
            }

            System.out.println("Staff data exported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public void importing (String filePath) {

        Map<String, Staff> staffDataMap = new HashMap<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int rowNumber = 0;

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
						
						String username = email.substring(0, email.indexOf('@'));
						System.out.println("Username: " + username);
						
						String createdCampsString = fields[4];
						ArrayList<String> createdCamps = new ArrayList<String>();

						if (!createdCampsString.equals("#NULL!")){
							if (createdCampsString.contains("|")){
								createdCamps.addAll(Arrays.asList(createdCampsString.split("\\|")));
							} else {
								createdCamps.add(createdCampsString);
							}
						}

						Staff staffData = new Staff(username, password, name, faculty, createdCamps);
			
						// Put the data into the map with username as key
						staffDataMap.put(username, staffData);
                    }
                }
            }
			System.out.println("Staff data imported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
