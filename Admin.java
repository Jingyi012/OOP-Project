import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends User {
    private String username;
    private String password;
    private ArrayList<Doctor> doctorList;

    public Admin(String username, String password, String name, String icNum, String DOB, String gender,
            Address address,
            String phone, String email) {
        super(name, icNum, DOB, gender, address, phone, email);
        this.username = username;
        this.password = password;
        this.doctorList = new ArrayList<>();
        // Open the doctor file when creating an Admin object
        readDoctorFile(); // Read the doctor details from the file (only first time)
    }

    public ArrayList<Doctor> getDoctorList(){
        return doctorList;
    }

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

    public void addDoctor() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n-------Add Doctor Details-------");
        System.out.print("Enter doctor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter doctor IC number: ");
        String icNum = scanner.nextLine();
        System.out.print("Enter doctor date of birth (DOB): ");
        String DOB = scanner.nextLine();
        System.out.print("Enter doctor gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter doctor street: ");
        String street = scanner.nextLine();
        System.out.print("Enter doctor city: ");

        String city = scanner.nextLine();
        System.out.print("Enter doctor postcode: ");
        int postcode = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        System.out.print("Enter doctor state: ");
        String state = scanner.nextLine();
        Address address = new Address(street, city, postcode, state);

        System.out.print("Enter doctor phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter doctor email: ");
        String email = scanner.nextLine();

        Doctor doctor = new Doctor(name, icNum, DOB, gender, address, phone, email);
        doctorList.add(doctor);

        try {
            System.out.println("\nDoctor added successfully!");
            updateDoctorFile(); // Add this line to update the file after adding a doctor
        } catch (IOException e) {
            System.out.println("An error occurred while saving the doctor details: " + e.getMessage());
        }
    }

    public void editDoctor() throws IOException {
        displayDoctorList();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the doctor you want to edit: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.println("\nChoose the field you want to edit:");
        System.out.println("1. Name");
        System.out.println("2. IC Number");
        System.out.println("3. DOB");
        System.out.println("4. Gender");
        System.out.println("5. Address");
        System.out.println("6. Phone Number");
        System.out.println("7. Email");
        System.out.println("8. Cancel");
        System.out.print("Enter your choice: ");
        int fieldChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        updateDoctor(doctorChoice, fieldChoice);

        System.out.println("Doctor details updated successfully!");
        updateDoctorFile();
        closeDoctorFile();
    }

    public void deleteDoctor() throws IOException {
        System.out.println("\n-------Delete Doctor -------");
        displayDoctorList();
 
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the doctor you want to delete: ");
        int doctorChoice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        deleteDoctor(doctorChoice);

        System.out.println("Doctor deleted successfully!");
        updateDoctorFile();
    }

    public void openDoctorFile() throws IOException {
        try {
            File file = new File("doctors.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while opening the doctor file: " + e.getMessage());
            throw e; // Throw the exception to the calling code
        }
    }

    public void closeDoctorFile() {
        // No need to explicitly close the file as it's closed automatically after
        // writing
    }

   public void readDoctorFile() {

    try {
        BufferedReader reader = new BufferedReader(new FileReader("doctors.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] details = line.split("\t");
            if (details.length >= 7) {
                String name = details[0];
                String icNum = details[1];
                String DOB = details[2];
                String gender = details[3];
                String addressString = details[4];
                String phone = details[5];
                String email = details[6];

                String[] addressDetails = addressString.split(", ");
                if (addressDetails.length == 4) {
                    String street = addressDetails[0];
                    String city = addressDetails[1];
                    String postcodeString = addressDetails[2];
                    String state = addressDetails[3];
                    int postcode = Integer.parseInt(postcodeString);

                    Address address = new Address(street, city, postcode, state);
                    Doctor doctor = new Doctor(name, icNum, DOB, gender, address, phone, email);
                    doctorList.add(doctor);
                }
            }
        }
        reader.close();
        
    } catch (IOException e) {
        System.out.println("An error occurred while reading the doctor file: " + e.getMessage());
    } catch (NumberFormatException e) {
        System.out.println("Invalid format in doctor file: " + e.getMessage());
    }
}

public void displayDoctorList() {

    System.out.println("\nList of Doctors: ");
    System.out.println("================\n");
    System.out.printf("%-6S %-25S %-15S %-15S %-15S %-40S %-20S %-25S%n", "No.", "Name", "IC Number", "DOB",
            "Gender", "Address", "Phone Number", "Email");
    System.out.printf("%-6S %-25S %-15S %-15S %-15S %-40S %-20S %-25S%n", "---", "----", "---------", "---",
            "------", "-------", "-------------", "-----");
    int count = 1;
    for (Doctor doctor : doctorList) {
        System.out.printf("%-6S %-25S %-15S %-15S %-15S %-40S %-20S %-25S%n", count, doctor.getName(),
                doctor.getIcNum(),
                doctor.getDOB(), doctor.getGender(), doctor.getAddress(), doctor.getPhone(), doctor.getEmail());
        count++;
    }
}


    public void updateDoctor(int doctorChoice, int fieldChoice) {
        Doctor doctor = doctorList.get(doctorChoice - 1);
        System.out.println("\n-------Update Doctor Details-------");
        Scanner inputScanner = new Scanner(System.in);

        switch (fieldChoice) {
            case 1:
                System.out.print("Enter new name: ");
                String name = inputScanner.nextLine();
                doctor.setName(name);
                break;
            case 2:
                System.out.print("Enter new IC number: ");
                String icNum = inputScanner.nextLine();
                doctor.setIcNum(icNum);
                break;
            case 3:
                System.out.print("Enter new DOB: ");
                String DOB = inputScanner.nextLine();
                doctor.setDOB(DOB);
                break;
            case 4:
                System.out.print("Enter new gender: ");
                String gender = inputScanner.nextLine();
                doctor.setGender(gender);
                break;
            case 5:
                System.out.println("Enter new address details:");
                System.out.print("Street: ");
                String street = inputScanner.nextLine();
                System.out.print("City: ");
                String city = inputScanner.nextLine();
                System.out.print("Postcode: ");
                int postcode = inputScanner.nextInt();
                inputScanner.nextLine(); // Consume the newline character
                System.out.print("State: ");
                String state = inputScanner.nextLine();
                Address newAddress = new Address(street, city, postcode, state);
                doctor.setAddress(newAddress);
                break;
            case 6:
                System.out.print("Enter new phone number: ");
                String phone = inputScanner.nextLine();
                doctor.setPhone(phone);
                break;
            case 7:
                System.out.print("Enter new email: ");
                String email = inputScanner.nextLine();
                doctor.setEmail(email);
                break;
            default:
                System.out.println("Invalid field choice!");
                return;
        }
    }

    public void deleteDoctor(int doctorChoice) {
        if (doctorChoice >= 1 && doctorChoice <= doctorList.size()) {
            doctorList.remove(doctorChoice - 1);
        } else {
            System.out.println("Invalid doctor choice!");
        }
    }

    public void updateDoctorFile() throws IOException{
        try{
            FileWriter fileWriter = new FileWriter("doctors.txt");

            for (Doctor doctor : doctorList) {
            String doctorDetails = doctor.getName() + "\t" + doctor.getIcNum() + "\t" + doctor.getDOB() + "\t" + doctor.getGender() + "\t" 
                    + doctor.getAddress() + "\t" + doctor.getPhone() + "\t" + doctor.getEmail();

            fileWriter.write(doctorDetails + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred while updating the doctor file: " + e.getMessage());
            throw e; // Throw the exception to the calling code
        }
    }

   /*public void updateDoctorFile() throws IOException {
     try {
        File file = new File("doctors.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        ArrayList<String> lines = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();

        FileWriter fileWriter = new FileWriter(file);

        for (Doctor doctor : doctorList) {
            String doctorDetails = doctor.getName() + "\t" + doctor.getIcNum() + "\t" + doctor.getDOB() + "\t" + doctor.getGender() + "\t" 
                    + doctor.getAddress() + "\t" + doctor.getPhone() + "\t" + doctor.getEmail();

            // Find the line to update
            boolean updated = false;
            for (int i = 0; i < lines.size(); i++) {
                String currentLine = lines.get(i);
                if (currentLine.startsWith(doctor.getName())) {
                    lines.set(i, doctorDetails); // Replace the line with the updated details
                    updated = true;
                    break;
                }
            }

            // If the doctor was not found in the file, add the new line
            if (!updated) {
                lines.add(doctorDetails);
            }
        }

        // Write the modified content back to the file
        for (String lineToUpdate : lines) {
            fileWriter.write("\n" + lineToUpdate);
        }
        fileWriter.close();
    } catch (IOException e) {
        System.out.println("An error occurred while updating the doctor file: " + e.getMessage());
        throw e; // Throw the exception to the calling code
    }
}*/
}