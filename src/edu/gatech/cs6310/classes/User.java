package edu.gatech.cs6310.classes;

public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;

    protected String getFullName(){
        return this.firstName + "_" + this.lastName;
    }
    protected String getPhoneNumber() { return this.phoneNumber; }

    public User(String newFirstName, String newLastName, String newPhoneNumber) {
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.phoneNumber = newPhoneNumber;
    }
}
