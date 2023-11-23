package controllers;

import java.util.Map;
import java.util.HashMap;

import dao.StaffDaoImpl;
import dao.StudentDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.StaffDao;
import interfaces.StudentDao;
import interfaces.CommitteeMemberDao;
import interfaces.CampDao;
import interfaces.CurrentUserDao;
import interfaces.DataServiceable;

import services.StaffDataService;
import services.StudentDataService;
import services.CommitteeDataService;
import services.CampDataService;

public class DataTransferController {
	
	private static Map<String, String> filePath =  new HashMap<>();
	
	private static DataServiceable staffDataService = new StaffDataService();
	
	private static DataServiceable studentDataService = new StudentDataService();
	
	private static DataServiceable committeeMemberDataService = new CommitteeDataService();
	
	private static DataServiceable campDataService = new CampDataService();
	
	public static void importData() {
		staffDataService.importing();
		studentDataService.importing();
		committeeMemberDataService.importing();
		campDataService.importing();
	}
	
	public static void exportData() {
		
		staffDataService.exporting();
		studentDataService.exporting();
		committeeMemberDataService.exporting();
		campDataService.exporting();
	}
}
