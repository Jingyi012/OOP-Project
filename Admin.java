import java.io.*;
import java.util.Scanner;

public class Admin extends User {
    private String username;
    private String password;

    public Admin(String username, String password, String name, String icNum, String DOB, String gender, Address add,
            String phone, String email) {
        super(name, icNum, DOB, gender, add, phone, email);
        this.username = username;
        this.password = password;
    }

    // Getters and setters

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addDoctor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n-------Add Doctor Details-------");
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("\nEnter doctor specialization: ");
        String specialization = scanner.nextLine();
        System.out.print("\nEnter doctor phone number: ");
        String phoneNumber = scanner.nextLine();

        String doctorDetails = name + ", " + specialization + ", " + phoneNumber;

        try {
            // Open the text file in append mode
            FileWriter fileWriter = new FileWriter("doctors.txt", true);
            fileWriter.write(doctorDetails + "\n"); // Write the doctor details to the file
            fileWriter.close(); // Close the file

            System.out.println("\nDoctor added successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the doctor details: " + e.getMessage());
        }
    }

    public void editDoctor() {
        openDoctorFile();
        displayDoctorList();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the doctor you want to edit: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.println("\nChoose the field you want to edit:");
        System.out.println("1. Name");
        System.out.println("2. Specialization");
        System.out.println("3. Phone Number");
        System.out.println("4. Cancel");
        System.out.print("Enter your choice: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        updateDoctor(doctorChoice, fieldChoice);

        System.out.println("Doctor details updated successfully!");
        closeDoctorFile();
    }

    public void deleteDoctor() {
        System.out.println("\n-------Delete Doctor -------");
        openDoctorFile();
        displayDoctorList();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the doctor you want to delete: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        deleteDoctor(doctorChoice);
        closeDoctorFile();

        System.out.println("Doctor deleted successfully!");
    }

    public void openDoctorFile() {
        // Open the doctors.txt file for reading
        // Implement the logic to open the file here
        try {
            File file = new File("doctors.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while opening the doctor file: " + e.getMessage());
        }
    }

    public void closeDoctorFile() {
        // Close the doctors.txt file
        // Implement the logic to close the file here
    }

    public void displayDoctorList() {
        try {
            System.out.println("\nList of Doctors: ");
            System.out.println("================\n");
            System.out.printf("%-6S %-25S %-20S %-20S%n", "No.", "Doctor Name", "Specialization", "Contact Number");
            System.out.printf("%-6S %-25S %-20S %-20S%n", "---", "-------------", "---------------", "--------------");
            File file = new File("doctors.txt");
            Scanner inFile = new Scanner(file);
            int count = 1;
            while (inFile.hasNextLine()) {
                String line = inFile.nextLine();
                String[] doctorDetails = line.split(", ");
                if (doctorDetails.length >= 3) {
                    String name = doctorDetails[0];
                    String specialization = doctorDetails[1];
                    String phoneNumber = doctorDetails[2];
                    System.out.printf("%-6d %-25s %-20s %-20s%n", count, name, specialization, phoneNumber);
                    count++;

                }
            }
            inFile.close();
        } catch (

        IOException e) {
            System.out.println("An error occurred while reading the doctor file: " + e.getMessage());
        }
    }

    public void updateDoctor(int doctorChoice, int fieldChoice) {
        try {
            File file = new File("doctors.txt");
            File tempFile = new File("temp.txt");

            Scanner scanner = new Scanner(file);
            FileWriter fileWriter = new FileWriter(tempFile);

            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] doctorDetails = line.split(", ");
                if (count == doctorChoice && doctorDetails.length >= 3) {
                    String name = doctorDetails[0];
                    String specialization = doctorDetails[1];
                    String phoneNumber = doctorDetails[2];
                    System.out.println("\n-------Update Doctor Details-------");
                    if (fieldChoice == 1) {
                        System.out.print("Enter new name: ");
                        Scanner inputScanner = new Scanner(System.in);
                        String newName = inputScanner.nextLine();
                        name = newName;
                    } else if (fieldChoice == 2) {
                        System.out.print("Enter new specialization: ");
                        Scanner inputScanner = new Scanner(System.in);
                        String newSpecialization = inputScanner.nextLine();
                        specialization = newSpecialization;
                    } else if (fieldChoice == 3) {
                        System.out.print("Enter new phone number: ");
                        Scanner inputScanner = new Scanner(System.in);
                        String newPhoneNumber = inputScanner.nextLine();
                        phoneNumber = newPhoneNumber;
                    }

                    String updatedDoctorDetails = name + ", " + specialization + ", " + phoneNumber;
                    fileWriter.write(updatedDoctorDetails + "\n");
                } else {
                    fileWriter.write(line + "\n");
                }
                count++;
            }

            scanner.close();
            fileWriter.close();

            file.delete();
            tempFile.renameTo(file);
        } catch (IOException e) {
            System.out.println("An error occurred while updating the doctor details: " + e.getMessage());
        }
    }

    private void deleteDoctor(int doctorChoice) {
        // Implement the logic to delete a doctor based on the user's choice here
        try {
            File file = new File("doctors.txt");
            File tempFile = new File("temp.txt");

            Scanner scanner = new Scanner(file);
            FileWriter fileWriter = new FileWriter(tempFile);

            int count = 1;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (count != doctorChoice) {
                    fileWriter.write(line + "\n");
                }
                count++;
            }
            scanner.close();
            fileWriter.close();

            // Replace the original file with the updated temp file
            file.delete();
            tempFile.renameTo(file);
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the doctor: " + e.getMessage());
        }
    }
}