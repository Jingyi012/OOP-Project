import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static Admin admin;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        // Initialize the admin
        Address address = new Address("UTM", "Johor Bahru", 8990, "Johor");
        admin = new Admin("admin", "password", "OOP", "020816-080612", "2222", "Famale", address, "011-1235678",
                "ShortingStart@gmail.com");

        boolean exit = false;
        while (!exit) {

            displayMenu();

            int op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1:
                    // Code for handling Patient role
                    break;
                case 2:
                    // Code for handling Doctor role
                    break;
                case 3:
                    boolean loggedIn = false;
                    while (!loggedIn) {
                        loggedIn = adminLogin();
                        if (!loggedIn) {
                            System.out.println("Invalid username or password. Please try again.");
                        }
                    }
                    System.out.println("Login successful!");

                    // Perform admin operations
                    performAdminOperations();
                    break;

                case 4:
                    System.out.println("Exiting...");
                     exit = true;
                     break;
                default:
                    System.out.println("Invalid option. Please try again.\n");
                    // Show the menu again if the option is invalid
                    break;

            }
        }

    }

    public static void displayMenu() {
        System.out.println("============================= ");
        System.out.print("  Patient Appoinment System   ");
        System.out.println("\n============================= ");
        System.out.println("----- Your Role -----");
        System.out.println("[1] Patient");
        System.out.println("[2] Doctor");
        System.out.println("[3] Admin");
        System.out.print("[4] Exit");
        System.out.print("\nEnter Your Choice:");
    }

    public static boolean adminLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n------- Admin -------");
        System.out.print("\nEnter your username: ");
        String username = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        return username.equals(admin.getUsername()) && password.equals(admin.getPassword());
    }

    public static void performAdminOperations() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {

            System.out.println("\n------- Admin -------");
            System.out.print("1. Add a doctor");
            System.out.print("\n2. Edit a doctor");
            System.out.print("\n3. Delete a doctor");
            System.out.print("\n4. View doctor list");
            System.out.print("\n5. Exit");
            System.out.print("\nSelect an operation:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    admin.addDoctor();
                    break;
                case 2:
                    admin.editDoctor();

                    break;
                case 3:
                    admin.deleteDoctor();
                    break;
                case 4:
                    admin.displayDoctorList();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting...\n");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
    }
}
