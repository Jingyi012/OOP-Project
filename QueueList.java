import java.util.*;

public class QueueList {
    ArrayList<WalkInPatient> patients = new ArrayList<WalkInPatient>();
    public QueueList() {

    }

    public void addQueue(WalkInPatient p) {
        patients.add(p);
    }

    public void getQueueNumber(WalkInPatient p) {
        int index = patients.indexOf(p);
        System.out.println("Your Queue Number is " + (index + 1));

    }

    public void checkQueue(String ic) {
        boolean found = false;
        for (WalkInPatient patient : patients) {
            if (patient.getIC().equals(ic)) {
                int index = patients.indexOf(patient);
                System.out.println("Your current Queue Number is " + (index + 1));
                found = true;
            }
        }
        if (found == false) {
            System.out.println("No Record, please make take the queue number first");
        }
    }

    public void updateQueue() {
        Scanner input = new Scanner(System.in);
        if(patients.size()==0){
            System.out.println("There is no patient in the queue list");
            System.out.println("Press Enter to continue...");
            input.nextLine(); 
        }
        else{
            boolean validChoice = false;
            int choice = 0;
            while (!validChoice) {
                System.out.println("Would you like to call this patient to your room?");
                System.out.println("Name: " + patients.get(0).getName());
                System.out.println("IC: " + patients.get(0).getIC());
                System.out.println("Phone Number: " + patients.get(0).getPhone());
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Choice: ");
                try {
                    choice = input.nextInt();
                    validChoice = true; 
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid choice, Please enter again");
                    input.nextLine(); 
                }
            }
            if (choice == 1) {
                patients.remove(0);
            }
        }
    }

}