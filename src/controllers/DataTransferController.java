package controllers;

import java.util.Map;
import java.util.HashMap;

import dao.StaffDaoImpl;
import dao.StudentDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CampDaoImpl;
import dao.CurrentUserDaoImpl;

import interfaces.dao.StaffDao;
import interfaces.dao.StudentDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CampDao;
import interfaces.dao.CurrentUserDao;
import interfaces.services.DataServiceable;

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
