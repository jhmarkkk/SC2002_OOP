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
import interfaces.dao.CommitteeMemberDao;
import dao.CommitteeMemberDaoImpl;
import models.CommitteeMember;

public class CommitteeDataService implements DataServiceable {
    public void exporting (String filePath) {
        
        CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
        Map<String, CommitteeMember> committeeDataMap = committeeMemberDao.getCommitteeMembers();
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            // Write header line
            bw.write("Name,Email,Faculty,Password,RegisteredCamps,Enquiries,FacilitatingCamp,Suggestions,Points");
            bw.newLine();

            for (Map.Entry<String, CommitteeMember> entry : committeeDataMap.entrySet()) {
                CommitteeMember committeeMember = entry.getValue();
            
                String registeredCampsString;
				if (committeeMember.getRegisteredCamps().isEmpty()) {
					registeredCampsString = "#NULL!";
				} else {
					registeredCampsString = String.join("\\|", committeeMember.getRegisteredCamps());
				}

                String enquiryString;
                if (committeeMember.getEnquiries().isEmpty()) {
                    enquiryString = "#NULL!";
                } else {
                    // Construct the enquiries string
                    StringBuilder enquiryStringBuilder = new StringBuilder();
                    for (Map.Entry<String, ArrayList<Integer>> campEnquiryEntry : committeeMember.getEnquiries().entrySet()) {
                        String campName = campEnquiryEntry.getKey();
                        ArrayList<Integer> enquiryList = campEnquiryEntry.getValue();
                        String campEnquiryString = campName + "=" + String.join("\\|", enquiryList.stream().map(Object::toString).toArray(String[]::new));
                        enquiryStringBuilder.append(campEnquiryString).append("*");
                    }
                
                    // Remove the trailing asterisk if there are any enquiries
                    enquiryString = enquiryStringBuilder.length() > 0 ? enquiryStringBuilder.substring(0, enquiryStringBuilder.length() - 1) : "#NULL!";
                }

                String suggestionsString;
                if (committeeMember.getSuggestions().isEmpty()) {
                    suggestionsString = "#NULL!";
                } else {
                    suggestionsString = String.join("\\|", committeeMember.getSuggestions().toString());
                }

                // Write data fields separated by commas
                bw.write(committeeMember.getName() + ","
                        + committeeMember.getUserID() + "@e.ntu.edu.sg,"
                        + committeeMember.getFaculty() + ","
                        + committeeMember.getPassword() + ","
                        + registeredCampsString + ","
                        + enquiryString + ","
                        + committeeMember.getFacilitatingCamp() + ","
                        + suggestionsString + ","
                        + committeeMember.getPoints());
                bw.newLine();
            }

            System.out.println("Committee data exported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

    
    
    }
        
    public void importing (String filePath) {
        CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
        Map<String, CommitteeMember> committeeDataMap = committeeMemberDao.getCommitteeMembers();

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
                            
                        String facilitatingCamp = fields[6];
                            
                        String suggestionString = fields[7];
                        ArrayList<Integer> suggestions = new ArrayList<Integer>();

                        if (!suggestionString.equals("#NULL!")){
                            if (suggestionString.contains("|")) {
                                String[] suggestionArray = suggestionString.split("\\|");
                                for (String value : suggestionArray) {
                                    suggestions.add(Integer.parseInt(value));
                                }
                            } else {
                                suggestions.add(Integer.parseInt(suggestionString));
                            }
                        }

                        int points = Integer.parseInt(fields[8]);

                        CommitteeMember committeeData = new CommitteeMember(username, password, name, faculty, registeredCamps, enquiries, facilitatingCamp, suggestions, points);
                
                        committeeDataMap.put(username, committeeData);
                    }
                }
            }
        	System.out.println("Committee data imported successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
