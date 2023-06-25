import java.util.*;
public class QueueList {
    ArrayList<WalkInPatient> patients = new ArrayList<WalkInPatient>();
    public static int index=0;
    public QueueList() {
    }

    public void addQueue(WalkInPatient p) {
        patients.add(p);
        index++;
    }

    public int getQueueNumber(WalkInPatient p) {
        System.out.println("Your Queue Number is " + index);
        return index;
    }

    public void checkQueue(String ic, WalkInPatient p) {
        boolean found = false;
        for (WalkInPatient patient : patients) {
            if (patient.getIC().equals(ic)) {
                int index = patients.indexOf(patient);
                System.out.println("Your Queue Number is " + patient.getQueue());
                System.out.println("There are " + index+  " patient(s) in front of you");
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