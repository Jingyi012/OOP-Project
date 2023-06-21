import java.util.*;

public class WalkInPatient {
    private String name;
    private String IC;
    private String phonenumber;
    Scanner input;

    public WalkInPatient() {
        name = "";
        IC = "";
        phonenumber = "";
        input = new Scanner(System.in);
    }

    public void getPersonalDetail() {
        System.out.print("Please enter your name: ");
        name = input.nextLine();
        System.out.print("Please enter your IC: ");
        IC = input.nextLine();
        System.out.print("Please enter your phone number: ");
        phonenumber = input.nextLine();

    }

    public String getName() {
        return name;
    }

    public String getIC() {
        return IC;
    }

    public String getPhone() {
        return phonenumber;
    }
}
