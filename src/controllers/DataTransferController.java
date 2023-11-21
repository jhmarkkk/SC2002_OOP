package controllers;

import dao.StaffDaoImpl;
import dao.StudentDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CampDaoImpl;

import interfaces.StaffDao;
import interfaces.StudentDao;
import interfaces.CommitteeMemberDao;
import interfaces.CampDao;
import interfaces.DataServiceable;

import services.StaffDataService;
import services.StudentDataService;
import services.CommitteeDataService;
import services.CampDataService;

public class DataTransferController {
	
	private static DataServiceable staffDataService = staffDataService();
	
	private static DataServiceable studentDataService = StudentDataService();
	
	private static DataServiceable committeeMemberDataService = CommitteeDataService();
	
	private static DataServiceable campDataService = CampDataService();
	
	public static void importData() {
		
		StaffDao staffDao = new StaffDaoImpl();
		StudentDao studentDao = new StudentDaoImpl();
		CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();
		CampDao campDao = new CampDaoImpl();
	}
	
	public static void exportData() {
		
		staffDataService.export();
		studentDataService.export();
		committeeMemberDataService.export();
		campDataService.export();
	}
}
