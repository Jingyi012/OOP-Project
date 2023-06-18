import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Admin admin;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);

        // Initialize the admin
        Address address = new Address("UTM", "Johor Bahru", 80990, "Johor");
        admin = new Admin("admin", "password", "OOP", "020816-080612", "2222", "Famale", address, "011-1235678",
                "ShootingStart@gmail.com");
        //doctor list
        ArrayList<Doctor> doctorList = admin.getDoctorList();
        //patient list
        ArrayList<Patient> patientList = new ArrayList<Patient>();
        //appointment list
        ArrayList<Appointment> appointmentList = new ArrayList<Appointment>();

        boolean exit = false;
        while (!exit) {
            displayMenu();
            int op = in.nextInt();
            in.nextLine();
            switch (op) {
                case 1:
                    // Code for handling Patient role
                    int patientChoice;
                    String pname, pICNum, pDOB, gender, pPhone, pEmail, eName, ePhone, eRelation;
                    String street, city, state;
                    int postcode;
                    Address pAddress;
                    do{
                        patientChoice = patientMenu(in);

                        switch(patientChoice){
                            case 1: 
                                //insert patient details
                                System.out.print("Enter your IC number: ");
                                pICNum = in.nextLine();
                                //check if patient exist
                                int patientNo = -1;
                                
                                for(int i=0; i<patientList.size(); i++){
                                    if(pICNum.equals(patientList.get(i).getIcNum())){
                                        patientNo = i;
                                    }
                                }
                                Patient tempPatient;
                                //if patient exist use the data from record
                                char changeInfo = 'n';
                                if(patientNo >= 0){
                                    tempPatient = patientList.get(patientNo);
                                    tempPatient.displayInfo();
                                    System.out.print("Do you want to change information? (y/n): ");
                                    changeInfo = in.next().toUpperCase().charAt(0);
                                    in.nextLine();
                                    if(changeInfo == 'Y'){
                                        tempPatient.editPatientInfo();
                                        changeInfo = 'n';
                                    }

                                } else { //if patient not found, create new patient instance
                                    System.out.print("Enter your name: ");
                                    pname = in.nextLine();
                                    System.out.print("Enter your Date of Birth: ");
                                    pDOB = in.nextLine();
                                    System.out.print("Enter your gender: ");
                                    gender = in.nextLine();
                                    System.out.print("Enter your phone number: ");
                                    pPhone = in.nextLine();
                                    System.out.print("Enter your email: ");
                                    pEmail = in.nextLine();
                                    System.out.print("Enter your street: ");
                                    street = in.nextLine();
                                    System.out.print("Enter your city: ");
                                    city = in.nextLine();
                                    System.out.print("Enter your state: ");
                                    state = in.nextLine();
                                    System.out.print("Enter your postcode: ");
                                    postcode = in.nextInt();
                                    in.nextLine();
                                    System.out.print("Enter your emergency contact name: ");
                                    eName = in.nextLine();
                                    System.out.print("Enter your emergency contact phone number: ");
                                    ePhone = in.nextLine();
                                    System.out.print("Enter your relationship to emergency contact: ");
                                    eRelation = in.nextLine();

                                    pAddress = new Address(street, city, postcode, state);
                                    tempPatient = new Patient(pname, pICNum, pDOB, gender, pAddress, pPhone, pEmail, eName, ePhone, eRelation);
                                    patientList.add(tempPatient);
                                }
                                
                                char option='n';
                                do{
                                    Appointment ap = makeAppointment(in, tempPatient, doctorList);
                                    appointmentList.add(ap);
                                    System.out.println("\nAppointment successfully made.");
                                    System.out.print("Do you want to add another appointment?(y/n) : ");
                                    option = in.next().toUpperCase().charAt(0);
                                    in.nextLine();
                                }while(option == 'Y');
                                
                                break;

                            case 2:
                                System.out.println("Search appointment"); 
                                System.out.print("Enter your ic: ");
                                String icNum = in.nextLine();
                                int pNo = -1;
                                for(int i=0; i<patientList.size(); i++){
                                    if(patientList.get(i).getIcNum().equals(icNum)){
                                        pNo = i;
                                        break;
                                    }
                                }

                                if(pNo >= 0){
                                    Patient tpatient = patientList.get(pNo);
                                    char conEdit = 'n';
                                    do{

                                    if(tpatient.getAppointment().size()>0){
                                        tpatient.displayAppointment();
                                    } else {
                                        System.out.println("No appointment yet.");
                                        break;
                                    }
                                    
                                    System.out.print("Enter the id of the appointment you want to edit: ");
                                    int aid = in.nextInt();
                                    in.nextLine();

                                    boolean idfound=false;
                                    Appointment tapp = null;
                                    for(int i=0; i<tpatient.getAppointment().size(); i++){
                                        if(tpatient.getAppointment().get(i).getAppointmentID() == aid){
                                            idfound = true;
                                            tapp = tpatient.getAppointment().get(i);
                                            break;
                                        }
                                    }
                                    if(idfound){
                                        int ch;
                                        do{
                                        System.out.println("Please choose the appointment detail you want to update.");
                                        System.out.println("1. Personal details\n2. Appointment date and time\n3. Reason\n4. Doctor\n5. Return");
                                        System.out.print("Enter your choice: ");
                                        ch = in.nextInt();
                                        in.nextLine();

                                        switch(ch){
                                            case 1: 
                                                tpatient.editPatientInfo();
                                                break;
                                            case 2:
                                                System.out.print("Enter appointment date: ");
                                                String date = in.nextLine();
                                                System.out.print("Enter appointment time: ");
                                                String time = in.nextLine();
                                                tapp.setDateTime(date, time);
                                                break;
                                            case 3:
                                                System.out.println("Enter appointment reason: ");
                                                String r = in.nextLine();
                                                tapp.setReason(r);
                                                break;
                                            case 4:
                                                displayDoctorList(doctorList);
                                                System.out.print("Enter your choice (No.): ");
                                                int choiceDoc = in.nextInt();
                                                tapp.setDoctor(doctorList.get(choiceDoc-1));
                                                break;
                                            case 5:
                                                break;

                                        }

                                        }while(ch != 5);
                                    } else {
                                        System.out.println("Appointment not found. Please try again.");
                                    }

                                        System.out.println("Do you want to continue to edit appointment? (Y/n)");
                                        conEdit = in.next().toUpperCase().charAt(0);
                                        in.nextLine();

                                    }while(conEdit == 'Y');

                                } else {
                                    System.out.println("You haven't make appointment yet. Please add appointment first.");
                                }
                                
                                break;
                            case 3:
                                System.out.println("Search appointment"); 
                                System.out.print("Enter your ic: ");
                                String delIC = in.nextLine();
                                //check if patient exist
                                int delPNo = -1;
                                for(int i=0; i<patientList.size(); i++){
                                    if(patientList.get(i).getIcNum().equals(delIC)){
                                        delPNo = i;
                                        break;
                                    }
                                }
                                //patient exist
                                if(delPNo >= 0){
                                    Patient delp = patientList.get(delPNo);
                                    char delopt = 'Y';
                                    while(delopt == 'Y'){
                                        //check patient's appointment list has appointment or not
                                        if(delp.getAppointment().size() > 0){
                                            delp.displayAppointment();
                                        } else {
                                            System.out.println("No appointment yet.");
                                            break;
                                        }
                                        
                                        System.out.print("Enter the id of the appointment you want to delete: ");
                                        int delappID = in.nextInt();

                                        boolean founddelapp = false;
                                        for(int i=0; i<delp.getAppointment().size(); i++){
                                            if(delappID == delp.getAppointment().get(i).getAppointmentID()){
                                                founddelapp = true;
                                                delp.getAppointment().remove(i); //remove appointment from patient's appointment list
                                                break;
                                            }
                                        }
                                        //delete from all appointment list
                                        if(founddelapp == true){
                                            for(int i=0; i<appointmentList.size(); i++){
                                                if(appointmentList.get(i).getAppointmentID() == delappID){
                                                    appointmentList.remove(i);
                                                }
                                            }
                                            System.out.println("Appointment successfully deleted.");

                                        } else {
                                            System.out.println("Appointment ID not found from your appointment list.");
                                        }

                                        System.out.println("Do you want to continue delete appointment? (Y/n)");
                                        delopt = in.next().toUpperCase().charAt(0);
                                        in.nextLine();

                                    }


                                }else{
                                    System.out.println("You haven't make appointment yet. Please try again.");
                                }
                                break;
                            case 4:
                                System.out.println("Search appointment"); 
                                System.out.print("Enter your ic: ");
                                String viewIc = in.nextLine();

                                //check is it patient exist
                                int viewPNo = -1;
                                boolean vfound = false;
                                for(int i=0; i<patientList.size(); i++){
                                    if(patientList.get(i).getIcNum().equals(viewIc)){
                                        viewPNo = i;
                                        vfound = true;
                                        break;
                                    }
                                }
                                //if patient exist
                                if(vfound == true){
                                    Patient vPatient = patientList.get(viewPNo);
                                    //check patient's appointment list
                                    if(vPatient.getAppointment().size() > 0){
                                        vPatient.displayAppointment();
                                    } else {
                                        System.out.println("No appointment yet");
                                        break;
                                    }
                                    
                                } else {
                                    System.out.println("No appointment found.");
                                }
                                
                            break;
                            case 5: 
                                displayAppointmentList(appointmentList);
                            break;
                            default: 
                        }



  
                    }while(patientChoice !=5);

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
        System.out.println("       Hospital System        ");
        System.out.println("============================= ");
        System.out.println("----- Your Role -----");
        System.out.println("[1] Patient");
        System.out.println("[2] Doctor");
        System.out.println("[3] Admin");
        System.out.println("[4] Exit");
        System.out.print("Enter Your Choice:");

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
            try{
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
            } catch (IOException e){
                System.out.println("Error occur when perform operation.");
            }
            
        }
    }

    public static int patientMenu(Scanner in){
        int choice = 0;
        try{
            System.out.println("============================= ");
            System.out.println("         Patient Menu         ");
            System.out.println("============================= ");
            System.out.println("1. Make appointment");
            System.out.println("2. Edit appointment");
            System.out.println("3. Cancel appointment");
            System.out.println("4. View appointment");
            System.out.println("5. Back");
            System.out.print("Enter your choice: ");
            choice = in.nextInt();
            in.nextLine();
        } catch(InputMismatchException ex){
            System.out.println("Please enter number from 1 to 5.");
        }
        
        return choice;
    }

    public static Appointment makeAppointment(Scanner in, Patient patient, ArrayList<Doctor> doc){
        int choiceDoc;
        System.out.print("Enter appointment date: ");
        String date = in.nextLine();
        System.out.print("Enter appointment time: ");
        String time = in.nextLine();
        System.out.print("Enter reason of making appointment: ");
        String reason = in.nextLine();

        displayDoctorList(doc);
        System.out.print("Enter your choice (No.): ");
        choiceDoc = in.nextInt();

        Appointment appointment = new Appointment(date, time, reason, doc.get(choiceDoc-1), patient);
        patient.addAppointment(appointment);
        //show appointment info
        System.out.println("Appointment details");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-50s%-15s\n", "ID", "Name", "Phone", "IC", "Date", "Time", "Doctor", "Reason", "Status");
        appointment.displayAppointmentInfo();

        return appointment;
    }

    public static void displayDoctorList(ArrayList<Doctor> doc){
        System.out.println("-------------Doctor List-------------");
        System.out.printf("%-5s%-15s%-15s\n","No.", "Name", "Phone Number");
        for(int i=0; i<doc.size(); i++){
            System.out.printf("%-5d%-15s%-15s\n", (i+1), doc.get(i).getName(), doc.get(i).getPhone());
        }
    }

    public static void displayAppointmentList(ArrayList<Appointment> app){
        System.out.println("--------------Appointment List--------------");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-15s%-50s%-15s\n", "ID", "Name", "Phone", "IC", "Date", "Time", "Doctor", "Reason", "Status");
        for(Appointment a:app){
            a.displayAppointmentInfo();
        }

    }


}
