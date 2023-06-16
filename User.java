public class User {
    private String name;
    private String icNum;
    private String DOB;
    private String gender;
    protected Address address;
    private String phone;
    private String email;

    public User(String name, String icNum, String DOB, String gender, Address address, String phone, String email) {
        this.name = name;
        this.icNum = icNum;
        this.DOB = DOB;
        this.gender = gender;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getIcNum() {
        return icNum;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return gender;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIcNum(String icNum) {
        this.icNum = icNum;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void displayInfo(){
        System.out.println("Name: " + name);
        System.out.println("IC Number: " + icNum);
        System.out.println("Date of Birth: " + DOB);
        System.out.println("Gender: " + gender);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
    }

}
