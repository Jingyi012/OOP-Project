public class Appointment implements Schedule{
    private int appointmentID;
    private String appointmentDate;
    private String appointmentTime;
    private String reasonMakeAppointment;
    private Doctor doctor;

    public Appointment(String date, String time, String reason, Doctor doc){
        appointmentDate = date;
        appointmentTime = time;
        doctor = doc;
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
    public void setDateTime(String date, String time){
        appointmentDate=date;
        appointmentTime=time;
    }
    public void displayAppointmentInfo(){
        System.out.println("Appointment Id: "+ appointmentID);
        System.out.println("Appointment Time: "+ appointmentTime);
        System.out.println("Appointment Reason: "+ reasonMakeAppointment);
        System.out.println("Doctor assigned: " + doctor.getName());
        
    }

}