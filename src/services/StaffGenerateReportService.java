package services;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;

import enums.GenerateType;

import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import interfaces.services.GenerateReportServiceable;

import models.Camp;
import models.CommitteeMember;
import models.Staff;
import models.Student;

import utils.InputUtil;
import utils.PrintUtil;

public class StaffGenerateReportService implements GenerateReportServiceable {

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final StudentDao studentDao = new StudentDaoImpl();
	
	private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
	
	private static final CampDao campDao = new CampDaoImpl();
	
	public void exporting(String filePath) {
    	
		int i = 0, choice;
    	String report = "", selectedCampName;
    	Camp selectedCamp;
    	Path path = Paths.get(filePath);
    	Staff currentUser = (Staff)currentUserDao.getCurrentUser();
    	ArrayList<String> createdCampNames = currentUser.getCreatedCamps();
    	
    	do {
			PrintUtil.header("Generate Report");
    		for (i = 0; i < createdCampNames.size(); i++)
    			System.out.printf("%2d. %s\n", i+1, createdCampNames.get(i));
    		
    		System.out.printf("%2d. Back\n", i+1);
    		choice = InputUtil.choice();
    		
    		if (choice == i + 1) return;
    		
    		if (choice >= 1 || choice <= i) {
    			selectedCampName = createdCampNames.get(choice - 1);
    			selectedCamp = campDao.getCamps().get(selectedCampName);
    			break;
    		}
    		
			PrintUtil.invalid("choice");
		} while (true);  	
    	
        do {
			PrintUtil.header(String.format("Generating report for %s\n", selectedCampName));
        	System.out.println("1. Generate all students");
			System.out.println("2. Generate attendees");
			System.out.println("3. Generate committee members");
			System.out.println("4. Back");

			switch (InputUtil.choice()) {
			case 1:
				report = generate(selectedCamp, GenerateType.ALL);
				break;
			case 2:
				report = generate(selectedCamp, GenerateType.ATTENDEE);
				break;
			case 3:
				report = generate(selectedCamp, GenerateType.COMMITTEE);
				break;
			case 4:
				return;
			default:
				PrintUtil.invalid("choice");
			}
		} while (choice < 1 || choice > 4);
        
        try {
			Files.writeString(path, report, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Invalid Path");
		}

		System.out.println("Report generated");
    }
	
	public String generate(Camp camp, GenerateType type){
    	
    	String report = "";
    	Student student;
    	CommitteeMember committeeMember;
    	Map<String, Student> studentData = studentDao.getStudents();
        Map<String, CommitteeMember> committeeMemberData = committeeMemberDao.getCommitteeMembers();
        
        if (type == GenerateType.ALL) {
        	report = String.format("Students' List for %s\n", camp.getName());
        	report = report.concat(String.format("%s\n", "=".repeat(28)));
        	report = report.concat(String.format("%-10s| %s\n", "Name", "Role"));
        	report = report.concat(String.format("%s\n", "-".repeat(28)));
        	for (String userID : camp.getAttendees()) {
        		student = studentData.get(userID);
        		report = report.concat(String.format("%-10s| %s\n", student.getName(), "Attenndee"));
        	}
        	
    		for (String userID : camp.getCommitteeMembers()) {
				System.out.println("Test: " + userID);
    			student = committeeMemberData.get(userID);
        		report = report.concat(String.format("%-10s| %s\n", student.getName(), "Committee member"));
        	}
    		
    		return report;
        }
        
        if (type == GenerateType.ATTENDEE) {
        	report = String.format("Attendees' List for %s\n", camp.getName());
        	report = report.concat(String.format("%s\n", "=".repeat(10)));
        	report = report.concat(String.format("%-10s\n", "Name"));
        	report = report.concat(String.format("%s\n", "-".repeat(10)));
        	for (String userID : camp.getAttendees()) {
        		student = studentData.get(userID);
        		report = report.concat(String.format("%-10s\n", student.getName()));
        	}

    		return report;
        }
        
        if (type == GenerateType.COMMITTEE) {
        	report = String.format("Committee Members' List for %s\n", camp.getName());
        	report = report.concat(String.format("%s\n", "=".repeat(18)));
        	report = report.concat(String.format("%-10s| %s\n", "Name", "Points"));
        	report = report.concat(String.format("%s\n", "-".repeat(18)));      	
    		for (String userID : camp.getCommitteeMembers()) {
        		committeeMember = committeeMemberData.get(userID);
        		report = report.concat(String.format("%-10s| %s\n", committeeMember.getName(), committeeMember.getPoints()));
        	}
    		
    		return report;
        }
        
        return report;
    }
}
