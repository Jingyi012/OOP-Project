import java.util.Scanner;

public class Main {
    // Initialize the admin
    private static Admin admin = new Admin("admin", "password");;

    public static void main(String[] args) {
        Scanner input = new Scanner (System.in);
        int role;
        //menu
        System.out.println("----------Clinic Appointment System----------");
        System.out.println("1. Admin\n2. Doctor\n3.Patient");
        System.out.print("Enter your choice: ");
        role = input.nextInt();

        switch(role){
            case 1: 
                if (adminLogin()) {
                    System.out.println("Login successful!");

                    // Perform admin operations
                    performAdminOperations();
                } else {
                    System.out.println("Invalid username or password. Access denied!");
                }
            break;
            case 2: 
            
            break;
            case 3: 
            
            break;
        }

        // Perform admin login
        
    }

    public static boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }

    public static void performAdminOperations() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("Select an operation:");
            System.out.println("1. Add a doctor");
            System.out.println("2. Edit a doctor");
            System.out.println("3. Delete a doctor");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    editDoctor();
                    break;
                case 3:
                    deleteDoctor();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }

    public static void addDoctor() {
        // Logic to add a doctor to the system
        System.out.println("Doctor added successfully!");
    }

    public static void editDoctor() {
        // Logic to edit the details of a doctor in the system
        System.out.println("Doctor details updated successfully!");
    }

    public static void deleteDoctor() {
        // Logic to delete a doctor from the system
        System.out.println("Doctor deleted successfully!");
    }

    public static void addAppointment(){
        
        Appointment app = new Appointment(null, null, null, null)
    }
}

