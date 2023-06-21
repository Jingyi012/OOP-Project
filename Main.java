import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {

    public static Admin admin;

    public static Appointment findAppointment(List<Appointment> appointmentList, int appointmentID) {
        for (Appointment appointment : appointmentList) {
            if (appointment.getAppointmentID() == appointmentID) {
                return appointment;
            }
        }
        return null; // Appointment not found
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        QueueList queue = new QueueList();
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
                    int cho = 0;
                    int opt=1;
                    while(opt!=3){
                        System.out.println("-------Patient-------");
                        System.out.println("1. Walk In\n2. Take Appointment\n3. Return");
                        try{
                            System.out.print("Enter your choice: ");
                            opt = in.nextInt();
                            in.nextLine();
                        } catch(InputMismatchException ex){
                            in.nextLine();
                            System.out.println("Invalid choice, Please enter again.");
                        }
                        if(opt==2){
                            do{
                                System.out.println("\n\n1. Login\n2. Register\n3. Return");
                                try{
                                    System.out.print("Enter your choice: ");
                                    cho = in.nextInt();
                                    in.nextLine();
                                } catch(InputMismatchException ex){
                                    in.nextLine();
                                    System.out.println("Invalid choice, Please enter again.");
                                }
                                

                                switch(cho){
                                    case 1:
                                        
                                        System.out.println("--------Login--------");
                                        System.out.print("Enter your IC number: ");
                                        String ic = in.nextLine();
                                        boolean findPatient = false;
                                        Patient patient = null;
                                        for(int i=0; i< patientList.size(); i++){
                                            if(patientList.get(i).getIcNum().equals(ic)){
                                                findPatient = true;
                                                patient = patientList.get(i);
                                                break;
                                            }
                                        }
                                        if(findPatient){
                                            int patientChoice;
                                            do{
                                                patientChoice = patientMenu(in);
                                                switch(patientChoice){
                                                    //make appointment
                                                    case 1: 
                                                        char option='n';
                                                        do{
                                                            makeAppointment(in, patient, doctorList, appointmentList);
                                                            
                                                            System.out.print("\nDo you want to add another appointment?(y/n) : ");
                                                            option = in.next().toUpperCase().charAt(0);
                                                            in.nextLine();
                                                        }while(option == 'Y');
                                                        break;
                                                    
                                                    //edit appointment
                                                    case 2:
                                                        char conEdit = 'n';
                                                        do{
                                                            if(patient.getAppointment().size()>0){
                                                                patient.displayAppointment();
                                                            } else {
                                                                System.out.println("\nNo appointment yet.");
                                                                break;
                                                            }

                                                            try{

                                                            System.out.print("\nEnter the id of the appointment you want to edit: ");
                                                            int aid = in.nextInt();
                                                            in.nextLine();

                                                            boolean idfound=false;
                                                            Appointment tapp = null;
                                                            for(int i=0; i<patient.getAppointment().size(); i++){
                                                                if(patient.getAppointment().get(i).getAppointmentID() == aid){
                                                                    idfound = true;
                                                                    tapp = patient.getAppointment().get(i);
                                                                    break;
                                                                }
                                                            }

                                                            if(idfound){
                                                                int ch;
                                                                do{
                                                                    ch=0;
                                                                    displayAppointmentInfo(tapp);
                                                                    System.out.println("\nPlease choose the appointment detail you want to update.");
                                                                    System.out.println("1. Personal details\n2. Appointment date and time\n3. Reason\n4. Doctor\n5. Return");
                                                                    try{
                                                                    System.out.print("Enter your choice: ");
                                                                        ch = in.nextInt();
                                                                        in.nextLine(); 
                                                                    } catch (InputMismatchException ex){
                                                                        in.nextLine();
                                                                        System.out.println("\nInvalid choice. The input must be in number.");
                                                                        continue;
                                                                    }
                                                                    
                                                                    switch(ch){
                                                                        case 1: 
                                                                            patient.editPatientInfo();
                                                                            break;
                                                                        case 2:
                                                                            System.out.print("\nEnter appointment date: ");
                                                                            String date = in.nextLine();
                                                                            System.out.print("Enter appointment time: ");
                                                                            String time = in.nextLine();
                                                                            tapp.setDateTime(date, time);
                                                                            break;
                                                                        case 3:
                                                                            System.out.print("Enter appointment reason: ");
                                                                            String r = in.nextLine();
                                                                            tapp.setReason(r);
                                                                            break;
                                                                        case 4:
                                                                            displayDoctorList(doctorList);
                                                                            try{
                                                                                System.out.print("Enter the No. of the doctor you selected: ");
                                                                                int choiceDoc = in.nextInt();
                                                                                tapp.setDoctor(doctorList.get(choiceDoc-1));
                                                                            } catch (IndexOutOfBoundsException ex){
                                                                                System.out.println("\nThe doctor No. not found. Please try again.");
                                                                            } catch (InputMismatchException ex){
                                                                                in.nextLine();
                                                                                System.out.println("\nInvalid choice. The doctor No. must be in number.");
                                                                            }
                                                                            
                                                                            break;
                                                                        case 5:
                                                                            break;
                                                                        default:
                                                                            System.out.println("Invalid choice, please enter choice from 1-5");
                                                                            break;
                                                                    }

                                                                }while(ch != 5);
                                                            } else {
                                                                System.out.println("\nAppointment not found. Please try again.");
                                                            }

                                                            } catch (InputMismatchException ex){ //handling execption for id not in int type
                                                                in.nextLine();
                                                                System.out.println("\nInvalid id. The id must be in number only.");
                                                            }

                                                            System.out.print("\nDo you want to continue to edit another appointment? (Y/n): ");
                                                            conEdit = in.next().toUpperCase().charAt(0);
                                                            in.nextLine();

                                                        }while(conEdit == 'Y');

                                                        break;
                                                        //delete appointment
                                                        case 3: 
                                                            char delopt = 'Y';
                                                            while(delopt == 'Y'){
                                                                //check patient's appointment list has appointment or not
                                                                if(patient.getAppointment().size() > 0){
                                                                    patient.displayAppointment();
                                                                } else {
                                                                    System.out.println("\nNo appointment yet.");
                                                                    break;
                                                                }
                                                                
                                                                try{

                                                                System.out.print("\nEnter the id of the appointment you want to delete: ");
                                                                int delappID = in.nextInt();
                                                                in.nextLine();

                                                                boolean founddelapp = false;
                                                                for(int i=0; i<patient.getAppointment().size(); i++){
                                                                    if(delappID == patient.getAppointment().get(i).getAppointmentID()){
                                                                        founddelapp = true;
                                                                        patient.getAppointment().remove(i); //remove appointment from patient's appointment list
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
                                                                    System.out.println("\nAppointment successfully deleted.");

                                                                } else {
                                                                    System.out.println("\nAppointment ID not found from your appointment list.");
                                                                }
                                                                
                                                                }catch(InputMismatchException ex){
                                                                    in.nextLine();
                                                                    System.out.println("\nInvalid id. The id must be in number only.");
                                                                }

                                                                System.out.print("\nDo you want to continue delete appointment? (Y/n): ");
                                                                delopt = in.next().toUpperCase().charAt(0);
                                                                in.nextLine();

                                                            }
                                                            break;
                                                        
                                                        case 4: 
                                                            if(patient.getAppointment().size() > 0){
                                                                patient.displayAppointment();
                                                            } else {
                                                                System.out.println("\nNo appointment yet.");
                                                            }
                                                            break;
                                                        
                                                        case 5:
                                                            break;
                                                        
                                                        default:
                                                            System.out.println("\nPlease enter number from 1-5");
                                                                
                                                    }
                                                }while(patientChoice != 5);

                                    } else {
                                        System.out.println("You haven't register yet. Please register first.");
                                        break;
                                    }
                                break;
                                case 2:
                                    System.out.println("\n--------Register--------");
                                    System.out.print("Enter your IC number: ");
                                    String ric = in.nextLine();
                                    boolean regPatientFound = false;
                                    for(int i=0; i<patientList.size(); i++){
                                        if(ric.equals(patientList.get(i).getIcNum())){
                                            System.out.println("You already registered. Please log in.");
                                            regPatientFound = true;
                                            break;
                                        }
                                    }
                                    if(regPatientFound){
                                        break;
                                    }
                                    else{
                                        String pname, pDOB, gender, pPhone, pEmail, eName, ePhone, eRelation;
                                        String street, city, state;
                                        int postcode;
                                        Address pAddress;

                                        try{
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
                                        System.out.print("Enter your postcode: ");
                                        postcode = in.nextInt();
                                        in.nextLine();
                                        System.out.print("Enter your city: ");
                                        city = in.nextLine();
                                        System.out.print("Enter your state: ");
                                        state = in.nextLine();
                                        System.out.print("Enter your emergency contact name: ");
                                        eName = in.nextLine();
                                        System.out.print("Enter your emergency contact phone number: ");
                                        ePhone = in.nextLine();
                                        System.out.print("Enter your relationship to emergency contact: ");
                                        eRelation = in.nextLine();

                                        pAddress = new Address(street, city, postcode, state);
                                        patientList.add(new Patient(pname, ric, pDOB, gender, pAddress, pPhone, pEmail, eName, ePhone, eRelation));
                                        System.out.println("\nSuccessfully Registered. \n");
                                        
                                        } catch(InputMismatchException ex){
                                            in.nextLine();
                                            System.out.println("\nError data type. Postcode must be in number only\n");
                                        }
                                    }
                                break;
                                case 3:
                                break; 
                                default:
                                    System.out.println("Please enter number from 1-3"); 
                                break;
                            }
                        

                        }while(cho != 3);
                    }
                    else if(opt==1){
                        
                        Scanner input = new Scanner(System.in);
                        int option = 1;
                        while (option != 3) {
                            System.out.println("-----------Queue System-----------");
                            System.out.println("1. Get the queue number");
                            System.out.println("2. Check the queue number");
                            System.out.println("3. Exit");
                            
                            System.out.print("Pleae enter your choice: ");
                            try{
                            option = input.nextInt();
                            input.nextLine();
                        
                            }
                            catch(InputMismatchException ex){
                                input.nextLine();
                                System.out.println("Invalid choice, Please enter again");
                            }
                            System.out.println();
                            System.out.println();
                            if (option == 1) {
                                WalkInPatient patient = new WalkInPatient();
                                patient.getPersonalDetail();
                                queue.addQueue(patient);
                                System.out.println();
                                System.out.println("----Successfully Added---");
                                queue.getQueueNumber(patient);
                                System.out.println();
                                System.out.println();
                                System.out.println();
                            } else if (option == 2) {
                                System.out.print("Please key in your IC: ");
                                String ic = input.nextLine();
                                queue.checkQueue(ic);
                                System.out.println();
                                System.out.println();
                                System.out.println();

                            }

                        }
                    }
                }
                    
                    break;
                case 2:
                    // Code for handling Doctor role
                    System.out.print("Hi Doctor, Please enter your IC: ");
                    String doctorIC=in.nextLine();
                    Doctor doctor=null;

                    for(Doctor d: doctorList){
                      if(d.getIcNum().equals(doctorIC)){
                        doctor=d;
                        System.out.println("\n<< << << << Welcome to the appointment system >> >> >> >>\n");

                        break;
                    }
                  }

                  if(doctor!=null){
                    boolean doctorExit=false;

                    do{
                        
                        int doctorChoice=displayDoctorMenu(in);
                    

                      switch(doctorChoice){
                        case 1:
                          System.out.println("Appointments assigned to Dr " +doctor.getName());
                          boolean foundAppointment=false;
                          System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "ID", "Name", "Phone", "IC", "Date", "Time", "Reason", "Doctor", "Status");
                            System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "--", "----", "-----", "--", "----", "----", "------", "------", "------");
                          for(Appointment appointment : appointmentList){
                            if(appointment.getDoctor().getIcNum().equals(doctor.getIcNum())){
                                appointment.displayAppointmentInfo();
                              foundAppointment=true;
                            }
                          }

                          if(!foundAppointment){
                            System.out.println("\n\nNo appointments found!\n\n");
                          }
                        
                          break;

                        case 2:
                          System.out.println("All appointments: ");
                          if(appointmentList.isEmpty()){
                            System.out.println("\n\nNo appointments found!\n\n");
                          }
                          else{
                            System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "ID", "Name", "Phone", "IC", "Date", "Time", "Reason", "Doctor", "Status");
                            System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "--", "----", "-----", "--", "----", "----", "------", "------", "------");
                             for (Appointment appointment: appointmentList){
                            appointment.displayAppointmentInfo();

                            }
                          }
                          break;

                        case 3:
                        char cancopt;
                        do{
                          if(appointmentList.isEmpty()){
                            System.out.println("\n\nNo appointments found!\n\n");
                            break;
                          }
                          else{
                            displayAppointmentList(appointmentList);
                            System.out.print("Enter the appointment ID to cancel: ");

                            try{
                            int cancelappointmentId = in.nextInt();
                            in.nextLine();
                            boolean canceled=false;

                            for(Appointment app :appointmentList){
                                if(app.getAppointmentID() == cancelappointmentId){
                                app.getPatient().getAppointment().remove(app);
                                appointmentList.remove(app);
                              
                                canceled=true;
                                break;
                            }
                        
                        }
                    

                          if(canceled){
                            System.out.println("Appointment has cancelled.");
                          }
                          else{
                            System.out.println("Appointment not found. Cancel Failed");
                          }
                        }
                        catch(IndexOutOfBoundsException ex){
                                System.out.println("\n The Patient ID not found. Please try again.");
                            }catch(InputMismatchException ex){
                                in.nextLine();
                                System.out.println("Invalid choice. The ID must in number");
                            }
                        }

                        boolean validInput=false;
                        do{ 
                            
                            System.out.println("\nDo you want to continue cancelled appointment?(Y/N):");
                            String choice=in.nextLine().toUpperCase();
                            cancopt=choice.charAt(0);
                            if(cancopt=='Y'|| cancopt=='N'){
                                validInput=true;
                            }
                            else{
                                System.out.println("Invalid input, Please enter either 'Y' or 'N'");
                            }
                        }while(!validInput);
                        }while(cancopt=='Y');
                          break;

                          case 4:
                           if(appointmentList.isEmpty()){
                            System.out.println("\n\nNo appointments found!\n");
                          }
                          else{
                            boolean cancelApp=true;
                            while(cancelApp){
                            try{
                                    displayAppointmentList(appointmentList);
                                    System.out.print("Enter the appointment ID to update the status: ");
                                    int editAppointmentId = in.nextInt(); 
                                    in.nextLine();
                                    
                          
                            Appointment appointment = findAppointment(appointmentList, editAppointmentId);

                            if(appointment!=null){
                              displayAppointmentInfo(appointment);
                              System.out.print("Approve appointment? (Y/N):");
                              String approval=in.nextLine().toUpperCase();

                              if(approval.equals("Y")){
                                appointment.setAppointmentStatus("Approved");
                                System.out.println("Appointment Approved.");
                                System.out.println("Updated Appointment List:");

                                displayAppointmentList(appointmentList);
                              }
                              else{
                                appointment.setAppointmentStatus("Not Approved");
                                System.out.println("Appointment not approved");
                                System.out.println("Updated Appointment List:");

                                displayAppointmentList(appointmentList);

                              }
                             
                            }else{
                              System.out.println("Appointment not found.");
                            }
                            boolean validResponse=false;
                            while(!validResponse){
                                System.out.print("Do you want to continue update another appointment status? (Y/N): ");
                                String response=in.nextLine().toUpperCase();

                                if(response.equals("Y")){
                                    cancelApp=true;
                                    validResponse=true;
                                }else if(response.equals("N")){
                                    cancelApp=false;
                                    validResponse=true;
                                }else{
                                    System.out.println("Invalid Input. Please enter 'Y' or 'N'. ");
                                }
                            }

         
                        }catch(InputMismatchException ex){
                            in.nextLine();
                            System.out.println("Invalid Input. Please enter a valid appointment ID (in number)");
                        }
                    }


                        }
                            break;

                            case 5:
                                queue.updateQueue();
                                System.out.println();
                                System.out.println();
                                System.out.println();

                            case 6:
                            doctorExit=true;
                            break;

                            default:
                            System.out.println("Invalid option. Please try again.\n");
                            break;

                      }
                    }while(!doctorExit);
                  }else{
                    System.out.println("Doctor not found.");
                  }

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
            try {
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
            } catch (IOException e) {
                System.out.println("Error occur when perform operation.");
            }

        }
    }

    public static int patientMenu(Scanner in) {
        int choice = 0;

        System.out.println("\n-----Patient Menu-----");
        System.out.println("1. Make appointment");
        System.out.println("2. Edit appointment");
        System.out.println("3. Cancel appointment");
        System.out.println("4. View appointment");
        System.out.println("5. Back");
        System.out.print("Enter your choice: ");
        try {
            choice = in.nextInt();
            in.nextLine();
            if (choice < 1 && choice > 5) {
                System.out.println("Please enter number from 1 to 5.");
            }
        } catch (InputMismatchException ex) {
            in.nextLine();
            System.out.println("Please enter number from 1 to 5.");
        }

        return choice;
    }

    public static int displayDoctorMenu(Scanner in) {
        int dchoice = 0;

        System.out.println("=============================");
        System.out.println("       Doctor Menu           ");
        System.out.println("=============================");
        System.out.println("[1] View Your Appointments");
        System.out.println("[2] View All Appointments");
        System.out.println("[3] Cancel Appointment");
        System.out.println("[4] Update Appointment");
        System.out.println("[5] Update the queue list");
        System.out.println("[6] Exit");
        System.out.print("\nEnter Your Choice: ");

        try {
            dchoice = in.nextInt();
            in.nextLine();
            if (dchoice < 1 && dchoice > 6) {
                System.out.println("Please enter number from 1 to 6");
            }
        } catch (InputMismatchException ex) {
            in.nextLine();
            System.out.println("Please enter number from 1 to 6");
        }
        return dchoice;
    }

    public static void makeAppointment(Scanner in, Patient patient, ArrayList<Doctor> doc,
            ArrayList<Appointment> appointmentList) {

        System.out.print("Enter appointment date: ");
        String date = in.nextLine();
        System.out.print("Enter appointment time: ");
        String time = in.nextLine();
        System.out.print("Enter reason of making appointment: ");
        String reason = in.nextLine();

        displayDoctorList(doc);
        int choiceDoc;
        do {
            choiceDoc = 0;
            try {
                System.out.print("\nEnter the No. of the doctor you selected: ");
                choiceDoc = in.nextInt();
                if (choiceDoc < 1 || choiceDoc > doc.size()) {
                    System.out.println("The doctor number not found. Please try again.");
                }

            } catch (InputMismatchException ex) {
                in.nextLine();
                System.out.println("Invalid choice. The doctor No. must be in number.");
            }
        } while (choiceDoc < 1 || choiceDoc > doc.size());

        Appointment appointment = new Appointment(date, time, reason, doc.get(choiceDoc - 1), patient);
        patient.addAppointment(appointment);
        appointmentList.add(appointment);
        // show appointment info
        displayAppointmentInfo(appointment);

        System.out.println("\nAppointment successfully made.");

    }

    public static void displayDoctorList(ArrayList<Doctor> doc) {
        System.out.println("-------------Doctor List-------------");
        System.out.printf("%-5s%-15s%-15s\n", "No.", "Name", "Phone Number");
        for (int i = 0; i < doc.size(); i++) {
            System.out.printf("%-5d%-15s%-15s\n", (i + 1), doc.get(i).getName(), doc.get(i).getPhone());
        }
    }

    public static void displayAppointmentList(ArrayList<Appointment> app) {
        System.out.println("--------------Appointment List--------------");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "ID", "Name", "Phone", "IC", "Date", "Time",
                "Reason", "Doctor", "Status");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "--", "----", "-----", "--", "----", "----",
                "------", "------", "------");
        for (Appointment a : app) {
            a.displayAppointmentInfo();
        }

    }

    public static void displayAppointmentInfo(Appointment a) {
        System.out.println("\nAppointment Info");
        System.out.println("================");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "ID", "Name", "Phone", "IC", "Date", "Time",
                "Reason", "Doctor", "Status");
        System.out.printf("%-5s%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", "--", "----", "-----", "--", "----", "----",
                "------", "------", "------");
        a.displayAppointmentInfo();
    }

}
