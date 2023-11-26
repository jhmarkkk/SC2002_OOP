package controllers;

import interfaces.services.DataServiceable;
import services.CampDataService;
import services.CommitteeDataService;
import services.StaffDataService;
import services.StudentDataService;

/**
 * The {@code DataTransferController} class provides methods for importing and exporting data to and from the CAMs application.
 * 
 * <p>
 * It utilizes implementations of the {@code DataServiceable} interface for handling data operations for staff, students, committee members, and camps.
 * </p>
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see DataServiceable
 * @see StaffDataService
 * @see StudentDataService
 * @see CommitteeDataService
 * @see CampDataService
 */
public class DataTransferController {
	
	private static DataServiceable staffDataService = new StaffDataService();
	
	private static DataServiceable studentDataService = new StudentDataService();
	
	private static DataServiceable committeeMemberDataService = new CommitteeDataService();
	
	private static DataServiceable campDataService = new CampDataService();
	
    /**
     * Imports data from CSV files for staff, students, committee members, and camps.
     */	
	public static void importData() {
		
		staffDataService.importing("data/StaffList.csv");
		studentDataService.importing("data/StudentList.csv");
		committeeMemberDataService.importing("data/CommitteeList.csv");
		campDataService.importing("data/CampList.csv");
	}
	
    /**
     * Exports data to CSV files for staff, students, committee members, and camps.
     */	
	public static void exportData() {
		staffDataService.exporting("data/StaffList.csv");
		studentDataService.exporting("data/StudentList.csv");
		committeeMemberDataService.exporting("data/CommitteeList.csv");
		campDataService.exporting("data/CampList.csv");
	}
}
