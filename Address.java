public class Address {
    private String street;
    private String city;
    private int postcode;
    private String state;

    public Address(String street, String city, int postcode, String state){
        this.street=street;
        this.city=city;
        this.postcode=postcode;
        this.state=state;
    }

    public void setStreet(String street){
        this.street=street;
    }
    public void setCity(String city){
        this.city=city;
    }
    public void setPostcode(int postcode){
        this.postcode=postcode;
    }
    public void setState(String state){
        this.state=state;
    }
    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }
    public int getPostcode(){
        return postcode;
    }
    public String getState() {
        return state;
    }
    public String toString(){
        return  street + ", " + city + ", "+ postcode + ", " +state;
                
    }  

}
