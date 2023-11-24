package controllers;

import interfaces.services.DataServiceable;

import services.CampDataService;
import services.CommitteeDataService;
import services.StaffDataService;
import services.StudentDataService;

public class DataTransferController {
	
	private static DataServiceable staffDataService = new StaffDataService();
	
	private static DataServiceable studentDataService = new StudentDataService();
	
	private static DataServiceable committeeMemberDataService = new CommitteeDataService();
	
	private static DataServiceable campDataService = new CampDataService();
	
	public static void importData() {
		staffDataService.importing("data/StaffList.csv");
		studentDataService.importing("data/StudentList.csv");
		committeeMemberDataService.importing("data/CommitteeList.csv");
		campDataService.importing("data/CampList.csv");
	}
	
	public static void exportData() {
		
		staffDataService.exporting("data/StaffList.csv");
		studentDataService.exporting("data/StudentList.csv");
		committeeMemberDataService.exporting("data/CommitteeList.csv");
		campDataService.exporting("data/CampList.csv");
	}
}
