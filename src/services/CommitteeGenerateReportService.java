package services;

import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
import models.Student;
import utils.InputUtil;

/**
 * The {@code CommitteeGenerateReportService} class provides methods for exporting reports related to committee members.
 * It implements the {@code GenerateReportServiceable} interface for report generation.
 *  
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.GenerateReportServiceable
 * @see dao.CampDaoImpl
 * @see dao.CommitteeMemberDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see dao.StudentDaoImpl
 * @see models.Camp
 * @see models.CommitteeMember
 * @see models.Student
 * @see enums.GenerateType
 * @see utils.InputUtil
 */
public class CommitteeGenerateReportService implements GenerateReportServiceable {
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final StudentDao studentDao = new StudentDaoImpl();
	
	private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
	
	private static final CampDao campDao = new CampDaoImpl();
	
	
    /**
     * Exports a report to the specified file path based on the chosen report type.
     *
     * @param filePath The path for the file to be exported to.
     */
	public void exporting(String filePath) {
    	
		int choice;
    	String report = "";
    	Path path = Paths.get(filePath);
    	CommitteeMember currentUser = (CommitteeMember)currentUserDao.getCurrentUser();
    	Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
    	
        do {
        	System.out.printf("Generating report for %s\n", currentUser.getFacilitatingCamp());
        	System.out.println("1. Generate all students");
			System.out.println("2. Generate attendees");
			System.out.println("3. Generate committee members");
			System.out.println("4. Back");
			
			choice = InputUtil.choice();
			switch (choice) {
			case 1:
				report = generate(camp, GenerateType.ALL);
				break;
			case 2:
				report = generate(camp, GenerateType.ATTENDEE);
				break;
			case 3:
				report = generate(camp, GenerateType.COMMITTEE);
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (choice < 1 || choice > 4);

        try {
			Files.writeString(path, report, StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("Invalid Path");
		}
    }
	
    /**
     * Generates a report based on the specified camp and report type.
     *
     * @param camp The camp for which the report is generated.
     * @param type The type of report to generate (ALL, ATTENDEE, COMMITTEE).
	 * 
     * @return The generated report as a string.
     */	
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
        	report = report.concat(String.format("%s\n", "=".repeat(10)));
        	report = report.concat(String.format("%-10s\n", "Name"));
        	report = report.concat(String.format("%s\n", "-".repeat(10)));      	
    		for (String userID : camp.getCommitteeMembers()) {
        		committeeMember = committeeMemberData.get(userID);
        		report = report.concat(String.format("%-10s\n", committeeMember.getName()));
        	}
    		
    		return report;
        }
        
        return report;
    }
}
