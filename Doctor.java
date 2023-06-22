import java.util.*;
public class Doctor extends User{
  public ArrayList<Appointment> appointments;

    public Doctor(String name, String icNum, String DOB, String gender, Address address, String phone, String email) {
         super(name, icNum, DOB, gender, address, phone, email);
         this.appointments=new ArrayList<>();
    }

    public ArrayList<Appointment> getAppointments(){
      return appointments;
    }


     public void displayInfo(){
        System.out.println("\n-----------Doctor Info-----------");
        super.displayInfo();
    }

   

}