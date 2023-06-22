import java.util.*;

public class Patient extends User {
    private EmergencyContact eContact;
    private ArrayList<Appointment> appointment;
    
    public Patient(String name, String icNum, String DOB, String gender, Address address, String phone, String email, String cname, String cphone, String relation){
        super(name, icNum, DOB, gender, address, phone, email);
        setEmergencyContact(cname, cphone, relation);
        appointment = new ArrayList<Appointment>();
    }

    public void setEmergencyContact(String cname, String cphone, String relation){
        eContact = new EmergencyContact(cname, cphone, relation);
    }

    public void addAppointment(Appointment ap){
        appointment.add(ap);
    }

    public ArrayList<Appointment> getAppointment(){
        return appointment;
    }

    public void displayAppointment(){
        displayInfo();
        System.out.println("\nAppointment List");
        System.out.println("================");
        System.out.printf("%-5s%-15s%-15s%-30s%-15s%-15s\n", "ID","Date", "Time", "Reason", "Doctor", "Status");
        System.out.printf("%-5s%-15s%-15s%-30s%-15s%-15s\n", "--","----", "----", "------", "------", "------");
        for(Appointment a: appointment){
            System.out.printf("%-5d%-15s%-15s%-30s%-15s%-15s\n", a.getAppointmentID(), a.getAppointmentDate(), a.getAppointmentTime(), a.getReason(), a.getDoctor().getName(), a.getAppointmentStatus());
        }
    }

    public void editPatientInfo(){
        Scanner in = new Scanner(System.in);
        int choice=0;
        do{
            System.out.println("---------Edit Patient Info--------");
            System.out.println("1. Name\n2. IC\n3. DOB\n4. Gender\n5. Phone\n6. Email\n7. Address\n8. Emergency Contact Name\n9. Emergency Contact Phone\n10. Relation to emergency contact\n11. Return");
            try{
                System.out.print("Enter your choice: ");
                choice = in.nextInt();
                in.nextLine();
            }catch(InputMismatchException ex){
                in.nextLine();
                System.out.println("Invalid choice, the input must be in number.");
            }
            switch(choice){
                case 1: 
                    System.out.print("Enter name: ");
                    String name = in.nextLine();
                    setName(name);
                    break;
                case 2: 
                    System.out.print("Enter IC: ");
                    String ic = in.nextLine();
                    setIcNum(ic);
                    break;
                case 3: 
                    System.out.print("Enter DOB: ");
                    String DOB = in.nextLine();
                    setDOB(DOB);
                    break;
                case 4: 
                    System.out.print("Enter gender: ");
                    String gender = in.nextLine();
                    setGender(gender);
                    break;
                case 5: 
                    System.out.print("Enter phone: ");
                    String phone = in.nextLine();
                    setPhone(phone);
                    break;
                case 6: 
                    System.out.print("Enter email: ");
                    String email = in.nextLine();
                    setEmail(email);
                    break;
                case 7: 
                    System.out.print("Enter street: ");
                    String street = in.nextLine();
                    System.out.print("Enter postcode: ");
                    int postcode = in.nextInt();
                    in.nextLine();
                    System.out.print("Enter city: ");
                    String city = in.nextLine();
                    System.out.print("Enter state: ");
                    String state = in.nextLine();
                    getAddress().setStreet(street);
                    getAddress().setCity(city);
                    getAddress().setPostcode(postcode);
                    getAddress().setState(state);
                    break;
                case 8: 
                    System.out.print("Enter emergency contact name: ");
                    String ename = in.nextLine();
                    eContact.setName(ename);
                    break;
                case 9: 
                    System.out.print("Enter emergency contact phone: ");
                    String ephone = in.nextLine();
                    eContact.setPhoneNumber(ephone);
                    break;
                case 10: 
                    System.out.print("Enter relationship to emergency contact: ");
                    String rel = in.nextLine();
                    eContact.setRelationship(rel);
                    break;
                case 11: 
                    break;
                default: 
                    System.out.println("Please enter number from 1-11.");
                    break;
                
            }
            if(choice > 0 && choice < 11){
                System.out.println("\nUpdated Patient Info: ");
                displayInfo();
            }
            

        }while(choice != 11);

    }

    public void displayInfo(){
        System.out.println("\n-----------Patient Info-----------");
        super.displayInfo();
        System.out.println("Emergency Contact Name: " + eContact.getName());
        System.out.println("Emergency Contact Phone: " + eContact.getPhoneNumber());
        System.out.println("Relation to emergency contact: " + eContact.getRelationship());
    }
}