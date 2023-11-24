package services;

import interfaces.services.DataServiceable;
import interfaces.dao.CampDao;

import dao.CampDaoImpl;

import models.Camp;
import models.Enquiry;
import models.Suggestion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import utils.DateUtil;

import enums.Visibility;

public class CampDataService implements DataServiceable {

	public void exporting(String filePath) {
		// TODO Auto-generated method stub
	}

	public void importing(String filePath) {
		// TODO Auto-generated method stub

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			int enquiryCount, suggestionCount;
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
			Map<String, Camp> campDataMap = new HashMap<>();

			// Read the enquiryCounter and suggestionCounter
			line = br.readLine();
			String[] counts = line.split(",");
			enquiryCount = Integer.parseInt(counts[0]);
			suggestionCount = Integer.parseInt(counts[1]);
			Enquiry.setEnquiryCounter(enquiryCount);
			Suggestion.setSuggestionCounter(suggestionCount);

			// do 1 readLine() to skip headers
			line = br.readLine();

			while ((line = br.readLine()) != null) {
				System.out.println("CampDataService line: " + line);
				String[] fields = line.split(",");
				campName = fields[0];

				if (!fields[1].equals("#NULL!")) {
					if (fields[1].contains("\\|")) {
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
