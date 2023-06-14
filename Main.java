import java.util.Scanner;

public class Main {
    private static Admin admin;

    public static void main(String[] args) {
        // Initialize the admin
        admin = new Admin("admin", "password");

        // Perform admin login
        if (adminLogin()) {
            System.out.println("Login successful!");

            // Perform admin operations
            performAdminOperations();
        } else {
            System.out.println("Invalid username or password. Access denied!");
        }
    }

    private static boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username: ");
        String username = scanner.nextLine();
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }

    private static void performAdminOperations() {
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

    private static void addDoctor() {
        // Logic to add a doctor to the system
        System.out.println("Doctor added successfully!");
    }

    private static void editDoctor() {
        // Logic to edit the details of a doctor in the system
        System.out.println("Doctor details updated successfully!");
    }

    private static void deleteDoctor() {
        // Logic to delete a doctor from the system
        System.out.println("Doctor deleted successfully!");
    }
}

