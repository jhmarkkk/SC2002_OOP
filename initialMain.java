import java.util.Scanner;

enum Role{
  STUDENT,
  STAFF,
  COMMITTEE
}

public class initialMain  {

    public static void main(String[] args) {
        setRole();
    }
  
    //Functions//
    public static void setRole() {
        Scanner sc = new Scanner(System.in);

        Role role;
        
        System.out.println("Role:");
        System.out.println("(1) Student");
        System.out.println("(2) Staff");
        System.out.println("(3) Committee");
        int roleInput = sc.nextInt();

        switch (roleInput) {
            case 1: 
                role = Role.STUDENT;  
                break;
            case 2:
                role = Role.STAFF;  
                break;  
            case 3: 
                role = Role.COMMITTEE;  
                break;
        
            default:
                System.out.println("default");
                break;
        }

        System.out.println("Role: " + role);

        
    }

    public static void getRole(){
        return roleInput;
    }
    
  
}