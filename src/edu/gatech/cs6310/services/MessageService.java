package edu.gatech.cs6310.services;

public class MessageService {
    public void changeCompletedMessage() {
        System.out.println("OK:change_completed");
    }

    public void displayCompletedMessage() {
        System.out.println("OK:display_completed");
    }

    public void pilotIdentifierError() {
        System.out.println("ERROR:pilot_identifier_already_exists");
    }

    public void itemIdentifierDuplicateError() {
        System.out.println("ERROR:item_identifier_already_exists");
    }

    public void itemIdentifierError() {
        System.out.println("ERROR:item_identifier_does_not_exist");
    }

    public void pilotLicenseError() {
        System.out.println("ERROR:pilot_license_already_exists");
    }

    public void pilotIdentifierExistsError() {
        System.out.println("ERROR:pilot_identifier_does_not_exist");
    }

    public void storeIdentifierError() {
        System.out.println("ERROR:store_identifier_does_not_exist");
    }

    public void droneIdentifierDuplicateError() {
        System.out.println("ERROR:drone_identifier_already_exists");
    }

    public void droneIdentifierExistError() {
        System.out.println("ERROR:drone_identifier_does_not_exist");
    }

    public void customerIdentifierExistError() {
        System.out.println("ERROR:customer_identifier_already_exists");
    }

    public void customerIdentfierError() {
        System.out.println("ERROR:customer_identifier_does_not_exist");
    }

    public void orderIdentifierError() {
        System.out.println("ERROR:order_identifier_does_not_exist");
    }

    public void orderIdentifierAlreadyExists() {
        System.out.println("ERROR:order_identifier_already_exists");
    }

    public void droneCapacityError() {
        System.out.println("ERROR:new_drone_does_not_have_enough_capacity");
    }

    public void duplicateDrone() {
        System.out.println("OK:new_drone_is_current_drone_no_change");
    }

    public void droneNeedsPilotError() {
        System.out.println("ERROR:drone_needs_pilot");
    }

    public void droneNeedsFuelError() {
        System.out.println("ERROR:drone_needs_fuel");
    }

    public void itemAlreadyOrderedError() {
        System.out.println("ERROR:item_already_ordered");
    }

    public void customerInsufficientFundsError() {
        System.out.println("ERROR:customer_cant_afford_new_item");
    }

    public void currentDroneWeightError() {
        System.out.println("ERROR:drone_cant_carry_new_item");
    }

}
