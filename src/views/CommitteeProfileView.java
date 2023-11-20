package views;

public class CommitteeProfileView extends ProfileView {
    super.view();
    System.out.printf("Role: %s of %s\n", User.getRole().name(), User.getFacilitatingCamp());
}
