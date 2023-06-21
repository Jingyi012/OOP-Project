public class Appointment implements Schedule{
    private int appointmentID;
    private String appointmentDate;
    private String appointmentTime;
    private String reasonMakeAppointment;
    private Doctor doctor;
    private Patient patient;
    private static int numAppointment=0;
    private String appointmentStatus;

    public Appointment(String date, String time, String reason, Doctor doc, Patient pat){
        appointmentDate = date;
        appointmentTime = time;
        reasonMakeAppointment = reason;
        doctor = doc;
        patient = pat;
        numAppointment += 1; 
        appointmentID = numAppointment;
        appointmentStatus = "Made";
    }

    public void setAppointmentDate(String date){
        appointmentDate = date;
    }
    public void setAppointmentTime(String time){
        appointmentTime = time;
    }
    public void setReason(String reason){
        reasonMakeAppointment = reason;
    }
    public void setDoctor(Doctor doc) {
        doctor = doc;
    }
    public void setPatient(Patient pat) {
        patient = pat;
    }
    public String getAppointmentDate(){
        return appointmentDate;
    }
    public String getAppointmentTime(){
        return appointmentTime;
    }
    public String getReason(){
        return reasonMakeAppointment;
    }
    public int getAppointmentID(){
        return appointmentID;
    }
    public Patient getPatient(){
        return patient;
    }
    public Doctor getDoctor(){
        return doctor;
    }
    public void setDateTime(String date, String time){
        appointmentDate=date;
        appointmentTime=time;
    }
    public void setAppointmentStatus(String status){
        appointmentStatus = status;
    }
    public String getAppointmentStatus(){
        return appointmentStatus;
    }
    public void displayAppointmentInfo(){
        System.out.printf("%-5d%-15s%-15s%-15s%-15s%-15s%-30s%-15s%-15s\n", appointmentID, patient.getName(), patient.getPhone(), patient.getIcNum(), appointmentDate, appointmentTime, reasonMakeAppointment, doctor.getName(), getAppointmentStatus());
        /* 
        System.out.println("Appointment Id: "+ appointmentID);
        System.out.println("Appointment Date: "+ appointmentDate);
        System.out.println("Appointment Time: "+ appointmentTime);
        System.out.println("Appointment Reason: "+ reasonMakeAppointment);
        System.out.println("Doctor assigned: " + doctor.getName());
        System.out.println("Patient Name: " + patient.getName());
        System.out.println("Patient IC: " + patient.getIcNum());
        System.out.println("Patient Phone Num: " + patient.getPhone());
        */
    }

}