package edu.gatech.cs6310.classes;

public class DronePilot extends User {
    private String accountId;
    private String taxId;
    private String licenseId;
    private int successfulDeliveries;

    public DronePilot(
        String newAccountId,
        String newFirstName,
        String newLastName,
        String newPhoneNumber,
        String newTaxId,
        String newLicenseId,
        int newSuccessfulDeliveries
    ){
        super(newFirstName, newLastName, newPhoneNumber);
        this.accountId = newAccountId;
        this.taxId = newTaxId;
        this.licenseId = newLicenseId;
        this.successfulDeliveries = newSuccessfulDeliveries;
    }

    public String getAccountId(){
        return this.accountId;
    }

    public String getLicenseId(){
        return this.licenseId;
    }

    public String getFullName() {
        return super.getFullName();
    }

    public void incrementDeliveries() {
        this.successfulDeliveries += 1;
    }

    public String toString() {
        return "name:"
                + getFullName()
                + ",phone:"
                + super.getPhoneNumber()
                + ",taxID:"
                + this.taxId
                + ",licenseID:"
                + this.licenseId
                + ",experience:"
                + this.successfulDeliveries;
    }
}
