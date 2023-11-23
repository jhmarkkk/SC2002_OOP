package controllers;

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
	
	private static DataServiceable staffDataService = staffDataService();
	
	private static DataServiceable studentDataService = StudentDataService();
	
	private static DataServiceable committeeMemberDataService = CommitteeDataService();
	
	private static DataServiceable campDataService = CampDataService();
	
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
