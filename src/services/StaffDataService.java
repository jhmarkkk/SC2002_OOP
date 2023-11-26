package services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import dao.StaffDaoImpl;

import interfaces.services.DataServiceable;
import interfaces.dao.StaffDao;

import models.Staff;

/**
 * The {@code StaffDataService} class provides methods for exporting staff data to a file and importing staff data from a file.
 * It implements the {@code DataServiceable} interface to support data import and export operations.
 * 
 * @author Huang Caihong
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.DataServiceable
 * @see interfaces.dao.StaffDao
 * @see dao.StaffDaoImpl
 * @see models.Staff
 */
public class StaffDataService implements DataServiceable {

	private static final StaffDao staffDao = new StaffDaoImpl();
	
    /**
     * Exports staff data to a specified file path.
     * The exported file contains a header line and data lines with staff details.
     *
     * @param filePath The path for the file to be exported to.
     * @see java.io.BufferedWriter
     * @see java.io.FileWriter
     * @see java.io.IOException
     * @see models.Staff
     * @see interfaces.dao.StaffDao
     * @see dao.StaffDaoImpl
     */

	public void exporting (String filePath) {

        Map<String, Staff> staffDataMap = staffDao.getStaffs();

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

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    /**
     * Imports staff data from a specified file path.
     * The imported file is expected to have a header line and data lines with staff details.
     * The data is processed and added to the staff data map.
     *
     * @param filePath The path for the file to be imported from.
     * @see java.io.BufferedReader
     * @see java.io.FileReader
     * @see java.io.IOException
     * @see models.Staff
     * @see interfaces.dao.StaffDao
     * @see dao.StaffDaoImpl
     */
	public void importing (String filePath) {

        StaffDao staffDao = new StaffDaoImpl();
        Map<String, Staff> staffDataMap = staffDao.getStaffs();


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
        } catch (IOException e) {
            e.printStackTrace();
        }

	}
}
