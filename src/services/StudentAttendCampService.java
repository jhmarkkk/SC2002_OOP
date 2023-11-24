package services;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.Scanner;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;

import enums.Role;

import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import interfaces.services.AttendCampServiceable;

import models.Camp;
import models.CommitteeMember;
import models.Student;
import models.User;
import utils.DateUtil;


public class StudentAttendCampService implements AttendCampServiceable {
	
	private static final Scanner sc = new Scanner(System.in);

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

	private static final StudentDao studentDao = new StudentDaoImpl();

	private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();

	private static final CampDao campDao = new CampDaoImpl();
	
    public void register(){
    	
    	int i, choice;
    	Camp selectedCamp;
    	Student currentUser = (Student)currentUserDao.getCurrentUser();
    	ArrayList<Camp> validCamps = getValidCamps(currentUser);
    	
    	do {
    		System.out.println("Register for:");
    		for (i = 0; i < validCamps.size(); i++)
    			System.out.printf("%d. %s\n", i+1, validCamps.get(i).getName());
    		
    		System.out.printf("%d. Back\n", i+1);
    		System.out.print("Choice: ");
    		
    		choice = sc.nextInt();
    		
    		System.out.println();
    		
    		if (choice == i + 1) return;
    		
    		if (choice >= 0 || choice <= i) {
    			selectedCamp = validCamps.get(choice);
    			break;
    		}
    		
    		System.out.println("Invalid choice. Please choose again.");
		} while (true);
    	
    	do {
    		System.out.println("Register as:");
    		System.out.println("1. Attendee");
    		if (selectedCamp.getCommitteeSlots() > selectedCamp.getCommitteeMembers().size())
    			System.out.println("2. CommitteeMember");
    		System.out.print("Choice: ");
    		
    		choice = sc.nextInt();
    		
    		System.out.println();
    		
    		if (choice == 1) {
    			joinAsAttendee(currentUser, selectedCamp);
    			break;
    		}
    		
    		if (choice == 2 || selectedCamp.getCommitteeSlots() > selectedCamp.getCommitteeMembers().size()) {
    			joinAsCommittee(currentUser, selectedCamp);
    			break;
    		}
    		
    		System.out.println("Invalid choice. Please choose again.");
		} while (true);
    }
    
    public void withdraw(){
        
    	int i, choice;
    	String selectedCampName;
    	Camp selectedCamp;
    	Student currentUser = (Student)currentUserDao.getCurrentUser();
    	ArrayList<String> registeredCamps = currentUser.getRegisteredCamps();
    	
    	do {
    		System.out.println("Withdraw from:");
    		for (i = 0; i < registeredCamps.size(); i++)
    			System.out.printf("%d. %s\n", i+1, registeredCamps.get(i));
    		
    		System.out.printf("%d. Back\n", i+1);
    		System.out.print("Choice: ");
    		
    		choice = sc.nextInt();
    		
    		System.out.println();
    		
    		if (choice == i + 1) return;
    		
    		if (choice >= 0 || choice <= i) {
    			selectedCampName = registeredCamps.get(choice);
    			selectedCamp = campDao.getCamps().get(selectedCampName);
    			break;
    		}
    		
    		System.out.println("Invalid choice. Please choose again.");
		} while (true);
    	
    	
    	if(validateWithdrawingFromCommittee(currentUser, selectedCampName)) return;
    	
    	registeredCamps.remove(selectedCampName);
    	currentUser.setRegisteredCamps(registeredCamps);
    	
    	ArrayList<String> attendees = selectedCamp.getAttendees();
    	ArrayList<String> withdrawnAttendees = selectedCamp.getWithdrawnAttendees();
    	attendees.remove(currentUser.getUserID());
    	withdrawnAttendees.add(currentUser.getUserID());
    	selectedCamp.setAttendees(attendees);
    	selectedCamp.setWithdrawnAttendees(withdrawnAttendees);
    	
    	System.out.printf("You have withdrawn from %s\n", selectedCampName);
    }
    
    private ArrayList<Camp> getValidCamps(Student user){
    	
    	Map<String, Camp> campData = campDao.getCamps();
    	ArrayList<Camp> camps = new ArrayList<>(campData.values());
    	ArrayList<Camp> validCamps = new ArrayList<>();
    	ArrayList<String> registeredCampName = user.getRegisteredCamps();
    	ArrayList<GregorianCalendar> unavailableDates = new ArrayList<>();
    	GregorianCalendar today = new GregorianCalendar();
    	Boolean overlap = false;
    	
    	for (String name : registeredCampName)
    	    unavailableDates.addAll(campData.get(name).getDates());
    	
    	for (Camp camp : camps) {
			if (!camp.getOpenTo().equals(user.getFaculty()) && !camp.getOpenTo().equals("NTU")) continue;
			if (camp.getAttendeeSlots() >= camp.getAttendees().size() && camp.getCommitteeSlots() >= camp.getCommitteeMembers().size()) continue;
			if (DateUtil.toString(camp.getRegistrationClosingDate()).compareTo(DateUtil.toString(today)) < 0) continue;
			if (registeredCampName.contains(camp.getName())) continue;
			if (camp.getWithdrawnAttendees().contains(user.getUserID())) continue;
			if (user.getRole() == Role.COMMITTEE) {
				CommitteeMember committeeMember = (CommitteeMember) user;
				if (committeeMember.getFacilitatingCamp() == camp.getName()) continue;
			}
			ArrayList<GregorianCalendar> dates = camp.getDates();
			overlap = false;
			for (GregorianCalendar date : dates) {
				for (GregorianCalendar unavailableDate : unavailableDates) {
					if (DateUtil.toString(date).equals(DateUtil.toString(unavailableDate))) {
						overlap = true;
						break;
					}
				}
				if (overlap) break;
			}
			if (overlap) continue;
			
			validCamps.add(camp);
		}
    	return validCamps;
    }
    
    private void joinAsAttendee(Student user, Camp camp) {
    	
    	ArrayList<String> registeredCamps = user.getRegisteredCamps();
    	ArrayList<String> attendees = camp.getAttendees();
    	
    	registeredCamps.add(camp.getName());
    	user.setRegisteredCamps(registeredCamps);
    	
    	attendees.add(user.getUserID());
    	camp.setAttendees(attendees);
    	
    	System.out.printf("You have registered for %s as an attendee\n", camp.getName());
    }
    
    private void joinAsCommittee(Student user, Camp camp) {
    	
    	if (user.getRole() == Role.COMMITTEE) {
    		System.out.println("You are already a committee memner of another camp");
    		return;
    	}
    	
    	CommitteeMember committeeMember = new CommitteeMember(user.getUserID(), user.getPassword(),
    			user.getName(), user.getFaculty(), user.getRegisteredCamps(), user.getEnquiries(),
    			camp.getName(), new ArrayList<>(), 0);
    	
    	Map<String, CommitteeMember> committeeMembersStore = committeeMemberDao.getCommitteeMembers();
    	committeeMembersStore.put(committeeMember.getUserID(), committeeMember);
    	committeeMemberDao.setCommitteeMembers(committeeMembersStore);
    	
    	Map<String, Student> studentStore = studentDao.getStudents();
    	studentStore.remove(user.getUserID());
    	studentDao.setStudents(studentStore);
    	
    	ArrayList<String> committeeMembers = camp.getCommitteeMembers();
    	committeeMembers.add(committeeMember.getName());
    	camp.setCommitteeMembers(committeeMembers);
    	
    	System.out.printf("You have registered for %s as a committee member\n", camp.getName());
    }
    
    
    private boolean validateWithdrawingFromCommittee(User user, String campName) {
    	
    	if (user.getRole() != Role.COMMITTEE) return false;
    	
    	CommitteeMember committeeMember = (CommitteeMember) user;
    		
		if (!committeeMember.getFacilitatingCamp().equals(campName)) return false;
		
		System.out.printf("Committee member cannot withdraw from %s\n", campName);
		return true;
    }
}
