package services;

import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;

import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import interfaces.services.GenerateReportServiceable;
import models.Camp;
import models.CommitteeMember;
import models.Student;



public class CommitteeGenerateReportService implements GenerateReportServiceable {
	
	private static final Scanner sc = new Scanner(System.in);
	
	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();
	
	private static final StudentDao studentDao = new StudentDaoImpl();
	
	private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
	
	private static final CampDao campDao = new CampDaoImpl();
	
	public void exporting(String path) {
    	
		int choice;
    	String report;
        CommitteeMember currentUser = (CommitteeMember)currentUserDao.getCurrentUser();
        Camp camp = campDao.getCamps().get(currentUser.getFacilitatingCamp());
        
        
        do {
        	System.out.printf("Generating report for %s\n", currentUser.getFacilitatingCamp());
        	System.out.println("1. Generate all students");
			System.out.println("2. Generate attendees");
			System.out.println("3. Generate committee members");
			System.out.println("4. Back");
			System.out.print("\nChoice: ");
			
			choice = sc.nextInt();
			
			System.out.println();
			
			switch (choice) {
			case 1:
				generate
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				return;
			default:
				System.out.println("Invalid choice. Please choose again.");
			}
		} while (choice < 1 || choice > 4);
        
    }
	
    public String generate(){
    	
    	String report = "Student List";
    	Map<String, Student> studentData = studentDao.getStudents();
        Map<String, CommitteeMember> committeeMemberData = committeeMemberDao.getCommitteeMembers();
        
        
        return report;
    }
    
    
}
