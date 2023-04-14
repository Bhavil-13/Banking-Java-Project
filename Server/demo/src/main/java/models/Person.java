package models;
public class Person {
    int personID;
    String name, address;
    public void setPersonID(int personID) {
        this.personID = personID;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public int getPersonID() {
        return personID;
    }
}
