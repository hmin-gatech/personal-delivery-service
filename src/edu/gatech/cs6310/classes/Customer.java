package edu.gatech.cs6310.classes;

public class Customer extends User {
    private String customerId;
    private int rating;
    private int credit;

    public String getCustomerId(){
        return this.customerId;
    }

    public String getFullName(){
        return super.getFullName();
    }

    public int getCredit() { return this.credit; }

    public void setCredit(int newCredit) { this.credit = newCredit; }

    public Customer(String newCustomerId, String newFirstName, String newLastName, String newPhoneNumber, int newRating, int newCredit) {
        super(newFirstName, newLastName, newPhoneNumber);
        this.customerId = newCustomerId;
        this.rating = newRating;
        this.credit = newCredit;
    }

    public String toString(){
        return "name:" + getFullName() + ",phone:" + super.getPhoneNumber() + ",rating:" + this.rating + ",credit:" + this.credit;
    }
}
