import java.util.*;
public class Doctor extends User{
    public Doctor(String name, String icNum, String DOB, String gender, Address address, String phone, String email) {
         super(name, icNum, DOB, gender, address, phone, email);
    }

     public void displayInfo(){
        System.out.println("\n-----------Doctor Info-----------");
        super.displayInfo();
    }

}