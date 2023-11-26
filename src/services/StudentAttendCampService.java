package services;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Map;

import controllers.SessionController;

import dao.CampDaoImpl;
import dao.CommitteeMemberDaoImpl;
import dao.CurrentUserDaoImpl;
import dao.StudentDaoImpl;

import enums.Role;
import enums.Visibility;

import interfaces.dao.CampDao;
import interfaces.dao.CommitteeMemberDao;
import interfaces.dao.CurrentUserDao;
import interfaces.dao.StudentDao;
import interfaces.services.AttendCampServiceable;

import models.Camp;
import models.CommitteeMember;
import models.Student;

import utils.DateUtil;
import utils.InputUtil;
import utils.PrintUtil;

/**
 * The {@code StudentAttendCampService} class provides methods for students to register for and withdraw from camps.
 * It implements the {@code AttendCampServiceable} interface for managing the camp registration process.
 * 
 * @author Chuan Shan Hong
 * @version 1.0
 * @since 1.0
 * 
 * @see interfaces.services.AttendCampServiceable
 * @see dao.CampDaoImpl
 * @see dao.CommitteeMemberDaoImpl
 * @see dao.CurrentUserDaoImpl
 * @see dao.StudentDaoImpl
 * @see models.Camp
 * @see models.CommitteeMember
 * @see models.Student
 * @see utils.DateUtil
 * @see utils.InputUtil
 * @see utils.PrintUtil
 */
public class StudentAttendCampService implements AttendCampServiceable {

	private static final CurrentUserDao currentUserDao = new CurrentUserDaoImpl();

	private static final StudentDao studentDao = new StudentDaoImpl();

	private static final CommitteeMemberDao committeeMemberDao = new CommitteeMemberDaoImpl();

	private static final CampDao campDao = new CampDaoImpl();

	/**
     * Allows a student to register for a camp by selecting from the list of valid camps.
     * The student can choose to register as an attendee or committee member based on available slots.
     * 
     * <p>If there are no valid camps to register for or if all slots are full, appropriate messages are displayed.</p>
     * 
     * @see utils.InputUtil
     * @see utils.PrintUtil
     * @see models.Camp
     * @see models.Student
     * @see enums.Role
     * @see enums.Visibility
     */
	public void register() {

		int i = 0, choice;
		Camp selectedCamp;
		Student currentUser = (Student) currentUserDao.getCurrentUser();
		ArrayList<Camp> validCamps = getValidCamps(currentUser);

		do {
			PrintUtil.header("Register for Camp");
			if (validCamps.size() == 0) {
				System.out.println("\n> No valid camp to register");
				return;
			}

			for (i = 0; i < validCamps.size(); i++)
				System.out.printf("%2d. %s\n", i + 1, validCamps.get(i).getName());

			System.out.printf("%2d. Back\n", i + 1);
			choice = InputUtil.choice();
			if (choice == i + 1)
				return;

			if (choice >= 0 || choice <= i) {
				selectedCamp = validCamps.get(choice - 1);
				break;
			}

			PrintUtil.invalid("choice");
		} while (true);

		do {
			System.out.println("\nRegister as:");
			System.out.println("1. Attendee");
			System.out.println("2. CommitteeMember");

			choice = InputUtil.choice();
			if (choice == 1) {
				if (selectedCamp.getAttendeeSlots() <= selectedCamp.getAttendees().size()) {
					System.out.println("\n> Attendee Slots are full");
					return;
				}

				joinAsAttendee(currentUser, selectedCamp);
				return;
			}

			if (choice == 2) {
				if (selectedCamp.getCommitteeSlots() <= selectedCamp.getCommitteeMembers().size()) {
					System.out.println("\n> Committee Slots are full");
					return;
				}

				joinAsCommittee(currentUser, selectedCamp);
				SessionController.endSession();
				return;
			}

			PrintUtil.invalid("choice");
		} while (true);
	}

    /**
     * Allows a student to withdraw from a camp they have registered for.
     * The student can select from the list of registered camps and withdraw from the chosen camp.
     * 
     * <p>If the student is a committee member facilitating the camp, they cannot withdraw, and a message is displayed.</p>
     * 
     * @see utils.InputUtil
     * @see utils.PrintUtil
     * @see models.Camp
     * @see models.Student
     * @see enums.Role
     */
	public void withdraw() {

		int i = 0, choice;
		String selectedCampName;
		Camp selectedCamp;
		Student currentUser = (Student) currentUserDao.getCurrentUser();
		ArrayList<String> registeredCampNames = currentUser.getRegisteredCamps();

		do {
			PrintUtil.header("Withdraw from Camp");
			if (registeredCampNames.size() == 0) {
				System.out.println("\n> No camp to withdraw from");
				return;
			}
			
			for (i = 0; i < registeredCampNames.size(); i++)
				System.out.printf("%2d. %s\n", i + 1, registeredCampNames.get(i));

			System.out.printf("%2d. Back\n", i + 1);
			choice = InputUtil.choice();

			if (choice == i + 1)
				return;

			if (choice >= 1 || choice <= i) {
				selectedCampName = registeredCampNames.get(choice - 1);
				selectedCamp = campDao.getCamps().get(selectedCampName);
				break;
			}

			PrintUtil.invalid("choice");
		} while (true);

		if (validateWithdrawingFromCommittee(currentUser, selectedCampName))
			return;

		registeredCampNames.remove(selectedCampName);
		currentUser.setRegisteredCamps(registeredCampNames);

		ArrayList<String> attendees = selectedCamp.getAttendees();
		ArrayList<String> withdrawnAttendees = selectedCamp.getWithdrawnAttendees();
		attendees.remove(currentUser.getUserID());
		withdrawnAttendees.add(currentUser.getUserID());
		selectedCamp.setAttendees(attendees);
		selectedCamp.setWithdrawnAttendees(withdrawnAttendees);

		System.out.printf("\n> You have withdrawn from %s\n", selectedCampName);
	}

	
    /**
     * Retrieves a list of valid camps for a student based on various criteria.
     * The method considers factors such as camp visibility, faculty, available slots, registration deadlines, the student's already registered camps, withdrawn camps, facilitating camps (for committee members), and unavailable dates.
     * 
     * @param user The student object.
	 * 
     * @return An ArrayList of camps that are valid for the student to register.
     * 
     * @see utils.DateUtil
     * @see enums.Visibility
     */
	private ArrayList<Camp> getValidCamps(Student user) {

		Map<String, Camp> campData = campDao.getCamps();
		ArrayList<Camp> camps = new ArrayList<>(campData.values());
		ArrayList<Camp> validCamps = new ArrayList<>();
		ArrayList<String> registeredCampName = user.getRegisteredCamps();
		ArrayList<GregorianCalendar> unavailableDates = new ArrayList<>();

		for (String name : registeredCampName)
			unavailableDates.addAll(campData.get(name).getDates());

		for (Camp camp : camps) {
			if (camp.getVisibility() == Visibility.OFF)
			continue;
			
			if (compareFaculty(camp.getOpenTo(), user.getFaculty()))
			continue;
			
			if (compareSlots(camp))
			continue;
			
			if (compareDeadline(DateUtil.toString(camp.getRegistrationClosingDate())))
			continue;
			
			if (registeredCampName.contains(camp.getName()))
			continue;
			
			if (camp.getWithdrawnAttendees().contains(user.getUserID()))
			continue;
			
			if (compareFacilitatingCamp(user, camp.getName()))
			continue;
			
			if (compareDates(camp.getDates(), unavailableDates))
				continue;

			validCamps.add(camp);
		}
		return validCamps;
	}

	/**
	 * Validates whether the camp is open to the student's faculty.
	 * 
	 * @param campFaculty The faculty to which the camp is open.
	 * @param userFaculty The faculty of the student.
	 * @return {@code true} if the camp is not open to the student's faculty, {@code false} otherwise.
	 */
	private Boolean compareFaculty(String campFaculty, String userFaculty) {

		if (campFaculty.equals("NTU"))
			return false;

		if (campFaculty.equals(userFaculty))
			return false;

		return true;
	}

	/**
	 * Validates whether the camp has available slots for attendees and committee members.
	 * 
	 * @param camp The camp for which to check available slots.
	 * @return {@code true} if either attendee or committee member slots are full, {@code false} otherwise.
	 */
	private Boolean compareSlots(Camp camp) {

		if (camp.getAttendees().size() < camp.getAttendeeSlots())
			return false;

		if (camp.getCommitteeMembers().size() < camp.getCommitteeSlots())
			return false;

		return true;
	}

	/**
	 * Validates whether the camp registration deadline has passed.
	 * 
	 * @param deadline The registration closing date of the camp.
	 * @return {@code true} if the registration deadline has passed, {@code false} otherwise.
	 * 
	 * @see utils.DateUtil
	 */
	private Boolean compareDeadline(String deadline) {

		GregorianCalendar today = new GregorianCalendar();

		if (DateUtil.toString(today).compareTo(deadline) < 0)
			return false;

		return true;
	}

	/**
	 * Validates whether the student is already facilitating another camp as a committee member.
	 * 
	 * @param user The student object.
	 * @param campName The name of the camp for which to check facilitation.
	 * @return {@code true} if the student is facilitating another camp, {@code false} otherwise.
	 */
	private Boolean compareFacilitatingCamp(Student user, String campName) {

		CommitteeMember committeeMember;

		if (user.getRole() != Role.COMMITTEE)
			return false;

		committeeMember = (CommitteeMember) user;
		if (committeeMember.getFacilitatingCamp().equals(campName))
			return true;

		return false;
	}

	/**
	 * Validates whether the camp dates overlap with any dates the student is unavailable.
	 * 
	 * @param dates The dates of the camp.
	 * @param unavailableDates The dates on which the student is unavailable.
	 * @return {@code true} if there is an overlap, {@code false} otherwise.
	 * 
	 * @see utils.DateUtil
	 */
	private Boolean compareDates(ArrayList<GregorianCalendar> dates, ArrayList<GregorianCalendar> unavailableDates) {

		for (GregorianCalendar date : dates) {
			for (GregorianCalendar unavailableDate : unavailableDates) {
				if (DateUtil.toString(date).equals(DateUtil.toString(unavailableDate))) {
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Handles the process of a student joining a camp as an attendee.
	 * 
	 * @param user The student object.
	 * @param camp The camp for which the student is registering.
	 */
	private void joinAsAttendee(Student user, Camp camp) {

		ArrayList<String> registeredCamps = user.getRegisteredCamps();
		ArrayList<String> attendees = camp.getAttendees();

		registeredCamps.add(camp.getName());
		user.setRegisteredCamps(registeredCamps);

		attendees.add(user.getUserID());
		camp.setAttendees(attendees);

		System.out.printf("\n> You have registered for %s as an attendee\n", camp.getName());
	}

	/**
	 * Handles the process of a student joining a camp as a committee member.
	 * 
	 * @param user The student object.
	 * @param camp The camp for which the student is registering as a committee member.
	 */
	private void joinAsCommittee(Student user, Camp camp) {

		if (user.getRole() == Role.COMMITTEE) {
			System.out.println("\n> You are already a committee member of another camp");
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
		committeeMembers.add(committeeMember.getUserID());
		camp.setCommitteeMembers(committeeMembers);

		System.out.printf("\n> You have registered for %s as a committee member\n", camp.getName());
	}

	/**
	 * Validates whether a committee member can withdraw from a camp.
	 * 
	 * @param user The committee member object.
	 * @param campName The name of the camp from which to withdraw.
	 * 
	 * @return {@code true} if the committee member cannot withdraw, {@code false} otherwise.
	 */
	private boolean validateWithdrawingFromCommittee(Student user, String campName) {

		if (user.getRole() != Role.COMMITTEE)
			return false;

		CommitteeMember committeeMember = (CommitteeMember) user;

		if (!committeeMember.getFacilitatingCamp().equals(campName))
			return false;

		System.out.printf("\n> Cannot withdraw from %s as a committee member\n", campName);
		return true;
	}
}
