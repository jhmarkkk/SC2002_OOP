package services;

import interfaces.services.DataServiceable;
import interfaces.dao.CampDao;

import dao.CampDaoImpl;

import models.Camp;
import models.Enquiry;
import models.Suggestion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import utils.DateUtil;

import enums.Visibility;

public class CampDataService implements DataServiceable {

	
	/** 
	 * @param filePath The path for the file to be exported to
	 */
	public void exporting(String filePath) {
		CampDao campDao = new CampDaoImpl();
		Map<String, Camp> campDataMap = campDao.getCamps();

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
			// Write enquiryCounter and suggestionCounter
			bw.write("Enquiry Counter,Suggestion Counter");
			bw.newLine();
			String enquiryCounterString = Integer.toString(Enquiry.getEnquiryCounter() - 1);
			String suggestionCounterString = Integer.toString(Suggestion.getSuggestionCounter() - 1);
			bw.write(enquiryCounterString + "," + suggestionCounterString);
			bw.newLine();
			// Write header line
			bw.write(
					"campName,dates,registrationClosingDate,openTo,location,totalSlots,committeeSlots,description,staffInCharge,attendees,withdrawnAttendees,committeeMembers,visibility,enquiries,suggestions");
			bw.newLine();

			// Iterate through the studentDataMap and write each student's data to the file
			for (Map.Entry<String, Camp> entry : campDataMap.entrySet()) {
				Camp exportingCamp = entry.getValue();

				String campName = exportingCamp.getName();

				String dates;
				ArrayList<String> dateStringArrayList = new ArrayList<String>();
				for (GregorianCalendar cal : exportingCamp.getDates()) {
					dateStringArrayList.add(DateUtil.toString(cal));
				}
				dates = String.join("|", dateStringArrayList);

				String registeredClosingDate = DateUtil.toString(exportingCamp.getRegistrationClosingDate());

				String openTo = exportingCamp.getOpenTo();

				String location = exportingCamp.getLocation();

				String totalSlots = Integer.toString(exportingCamp.getTotalSlots());

				String committeeSlots = Integer.toString(exportingCamp.getCommitteeSlots());

				String description = exportingCamp.getDescription();

				String staff = exportingCamp.getStaffInCharge();

				String attendees;
				if (exportingCamp.getAttendees().isEmpty()) {
					attendees = "#NULL!";
				} else {
					attendees = String.join("|", exportingCamp.getAttendees());
				}

				String withdrawnAttendees;
				if (exportingCamp.getWithdrawnAttendees().isEmpty()) {
					withdrawnAttendees = "#NULL!";
				} else {
					withdrawnAttendees = String.join("|", exportingCamp.getWithdrawnAttendees());
				}

				String committeeMembers;
				if (exportingCamp.getCommitteeMembers().isEmpty()) {
					committeeMembers = "#NULL!";
				} else {
					committeeMembers = String.join("|", exportingCamp.getCommitteeMembers());
				}

				String visibility = exportingCamp.getVisibility().toString();
				visibility = visibility.toUpperCase();

				String enquiryStrings;
				Map<Integer, Enquiry> enquiryMap = exportingCamp.getEnquiries();
				if (enquiryMap.values().isEmpty()) {
					enquiryStrings = "#NULL!";
				} else if (enquiryMap.values().size() == 1) {
					// there is only 1 enquiry
					ArrayList<String> enquiryFields = new ArrayList<String>();
					for (Enquiry enquiry : exportingCamp.getEnquiries().values()) {
						enquiryFields.add(Integer.toString(enquiry.getEnquiryID()));
						enquiryFields.add(enquiry.getEnquiry());
						enquiryFields.add(enquiry.getEnquirer());
						// if reply is null, there must be no replier either
						if (enquiry.getReply() == null) {
							enquiryFields.add("#NULL!");
							enquiryFields.add("#NULL!");
						} else {
							enquiryFields.add(enquiry.getReply());
							enquiryFields.add(enquiry.getReplier());
						}
					}
					enquiryStrings = String.join("|", enquiryFields);

				} else {
					// there are multiple enquiries
					ArrayList<String> enquiryStringList = new ArrayList<String>();
					for (Enquiry enquiry : exportingCamp.getEnquiries().values()) {
						ArrayList<String> enquiryFields = new ArrayList<String>();

						enquiryFields.add(Integer.toString(enquiry.getEnquiryID()));
						enquiryFields.add(enquiry.getEnquiry());
						enquiryFields.add(enquiry.getEnquirer());
						// if reply is null, there must be no replier either
						if (enquiry.getReply() == null) {
							enquiryFields.add("#NULL!");
							enquiryFields.add("#NULL!");
						} else {
							enquiryFields.add(enquiry.getReply());
							enquiryFields.add(enquiry.getReplier());
						}
						String enquiryString = String.join("|", enquiryFields);
						enquiryStringList.add(enquiryString);
					}
					enquiryStrings = String.join("*", enquiryStringList);
				}

				String suggestionStrings;
				Map<Integer, Suggestion> suggestionMap = exportingCamp.getSuggestions();
				if (suggestionMap.values().isEmpty()) {
					suggestionStrings = "#NULL!";
				} else if (suggestionMap.values().size() == 1) {
					// there is only 1 suggestion
					ArrayList<String> suggestionFields = new ArrayList<String>();
					for (Suggestion suggestion : suggestionMap.values()) {
						suggestionFields.add(Integer.toString(suggestion.getSuggestionID()));
						suggestionFields.add(suggestion.getSuggestion());
						suggestionFields.add(suggestion.getSuggester());
						suggestionFields.add(Boolean.toString(suggestion.getApproved()));
					}

					suggestionStrings = String.join("|", suggestionFields);
				} else {
					// there are multiple suggestions
					ArrayList<String> suggestionStringList = new ArrayList<String>();
					for (Suggestion suggestion : suggestionMap.values()) {
						ArrayList<String> suggestionFields = new ArrayList<String>();
						suggestionFields.add(Integer.toString(suggestion.getSuggestionID()));
						suggestionFields.add(suggestion.getSuggestion());
						suggestionFields.add(suggestion.getSuggester());
						suggestionFields.add(Boolean.toString(suggestion.getApproved()));

						String suggestionString = String.join("|", suggestionFields);
						suggestionStringList.add(suggestionString);
					}
					suggestionStrings = String.join("*", suggestionStringList);
				}

				ArrayList<String> rowArrayList = new ArrayList<String>();
				rowArrayList.add(campName);
				rowArrayList.add(dates);
				rowArrayList.add(registeredClosingDate);
				rowArrayList.add(openTo);
				rowArrayList.add(location);
				rowArrayList.add(totalSlots);
				rowArrayList.add(committeeSlots);
				rowArrayList.add(description);
				rowArrayList.add(staff);
				rowArrayList.add(attendees);
				rowArrayList.add(withdrawnAttendees);
				rowArrayList.add(committeeMembers);
				rowArrayList.add(visibility);
				rowArrayList.add(enquiryStrings);
				rowArrayList.add(suggestionStrings);

				// Write data fields separated by commas
				bw.write(String.join(",", rowArrayList));
				bw.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void importing(String filePath) {
		CampDao campDao = new CampDaoImpl();
		Map<String, Camp> campDataMap = campDao.getCamps();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;

			int enquiryCount, suggestionCount;

			// Read the header of enquiryCounter and suggestionCounter
			line = br.readLine();
			// Read the enquiryCounter and suggestionCounter
			line = br.readLine();
			String[] counts = line.split(",");
			enquiryCount = Integer.parseInt(counts[0]) + 1;
			suggestionCount = Integer.parseInt(counts[1]) + 1;
			Enquiry.setEnquiryCounter(enquiryCount);
			Suggestion.setSuggestionCounter(suggestionCount);

			// do 1 readLine() to skip headers
			line = br.readLine();

			while ((line = br.readLine()) != null) {

				String campName, openTo, location, description, staff;
				String[] enquiryStrings = new String[1];
				String[] suggestionStrings = new String[1];
				ArrayList<GregorianCalendar> dates = new ArrayList<GregorianCalendar>();
				GregorianCalendar registrationClosingDate;
				int totalSlots, committeeSlots;
				Visibility visibility;
				ArrayList<String> attendees = new ArrayList<String>();
				ArrayList<String> withdrawnAttendees = new ArrayList<String>();
				ArrayList<String> committeeMembers = new ArrayList<String>();

				Map<Integer, Enquiry> enquiryMap = new HashMap<Integer, Enquiry>();
				Map<Integer, Suggestion> suggestionMap = new HashMap<Integer, Suggestion>();
				
				String[] fields = line.split(",");
				campName = fields[0];

				if (!fields[1].equals("#NULL!")) {
					if (fields[1].contains("|")) {
						String[] dateStrings = fields[1].split("\\|");
						for (String dateString : dateStrings) {
							dates.add(DateUtil.toDate(dateString));
						}
					} else {
						dates.add(DateUtil.toDate(fields[1]));
					}
				}

				registrationClosingDate = DateUtil.toDate(fields[2]);
				openTo = fields[3];
				location = fields[4];
				totalSlots = Integer.parseInt(fields[5]);
				committeeSlots = Integer.parseInt(fields[6]);
				description = fields[7];
				staff = fields[8];

				if (!fields[9].equals("#NULL!")) {
					if (fields[9].contains("|")) {
						String[] attendeeStrings = fields[9].split("\\|");
						for (String attendeeString : attendeeStrings) {
							attendees.add(attendeeString);
						}
					} else {
						attendees.add(fields[9]);
					}
				}

				if (!fields[10].equals("#NULL!")) {
					if (fields[10].contains("|")) {
						String[] withdrawnAttendeeStrings = fields[10].split("\\|");
						for (String withdrawnAttendeeString : withdrawnAttendeeStrings) {
							withdrawnAttendees.add(withdrawnAttendeeString);
						}
					} else {
						withdrawnAttendees.add(fields[10]);
					}
				}

				if (!fields[11].equals("#NULL!")) {
					if (fields[11].contains("|")) {
						String[] committeeStrings = fields[11].split("\\|");
						for (String committeeString : committeeStrings) {
							committeeMembers.add(committeeString);
						}
					} else {
						committeeMembers.add(fields[11]);
					}
				}

				if (fields[12].equals("ON")) {
					visibility = Visibility.ON;
				} else {
					visibility = Visibility.OFF;
				}

				if (!fields[13].equals("#NULL!")) {
					if (fields[13].contains("*")) {
						enquiryStrings = fields[13].split("\\*");
					} else {
						enquiryStrings[0] = fields[13];
					}
					for (String enquiryString : enquiryStrings) {
						String[] enquiryFields = enquiryString.split("\\|");
						Enquiry enquiry = new Enquiry(Integer.valueOf(enquiryFields[0]), enquiryFields[1],
								enquiryFields[2],
								enquiryFields[3], enquiryFields[4]);
						enquiryMap.put(enquiry.getEnquiryID(), enquiry);
					}
				}

				if (!fields[14].equals("#NULL!")) {
					if (fields[14].contains("*")) {
						suggestionStrings = fields[14].split("\\*");
					} else {
						suggestionStrings[0] = fields[14];
					}
					for (String suggestionString : suggestionStrings) {
						String[] suggestionFields = suggestionString.split("\\|");
						Suggestion suggestion = new Suggestion(Integer.valueOf(suggestionFields[0]),
								suggestionFields[1], suggestionFields[2], Boolean.parseBoolean(suggestionFields[3]));
						suggestionMap.put(suggestion.getSuggestionID(), suggestion);
					}
				}

				Camp importedCamp = new Camp(campName, dates, registrationClosingDate, openTo, location,
						totalSlots, committeeSlots, description, staff, attendees, withdrawnAttendees,
						committeeMembers, enquiryMap, suggestionMap, visibility);

				campDataMap.put(importedCamp.getName(), importedCamp);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
