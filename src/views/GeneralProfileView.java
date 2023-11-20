package views;

public class GeneralProfileView extends ProfileView {
    super.view();
    System.out.printf("Role: %s\n", User.getRole().name);
}
