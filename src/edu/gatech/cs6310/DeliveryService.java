package edu.gatech.cs6310;
import edu.gatech.cs6310.classes.*;
import edu.gatech.cs6310.services.HelperService;
import edu.gatech.cs6310.services.MessageService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DeliveryService {

    public void commandLoop() {
        Scanner commandLineInput = new Scanner(System.in);
        String wholeInputLine;
        String[] tokens;
        final String DELIMITER = ",";

        ArrayList<Store> storeList = new ArrayList<>();
        ArrayList<DronePilot> dronePilotList = new ArrayList<>();
        ArrayList<Drone> droneList = new ArrayList<>();
        ArrayList<Customer> customerList = new ArrayList<>();
        ArrayList<Order> orderList = new ArrayList<>();

        HelperService helper = new HelperService();
        MessageService messageService = new MessageService();

        while (true) {
            try {
                // Determine the next command and echo it to the monitor for testing purposes
                wholeInputLine = commandLineInput.nextLine();
                tokens = wholeInputLine.split(DELIMITER);
                System.out.println("> " + wholeInputLine);

                if (tokens[0].equals("make_store")) {
                    Store newStore = new Store(tokens[1], Integer.parseInt(tokens[2]));
                    storeList.add(newStore);
                    messageService.changeCompletedMessage();

                } else if (tokens[0].equals("display_stores")) {
                    for (Store currStore : storeList) {
                        System.out.println(currStore);
                    }
                    messageService.displayCompletedMessage();

                } else if (tokens[0].equals("sell_item")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null){
                        boolean actionCompleted = targetStore.addItem(tokens[2], Integer.parseInt(tokens[3]));
                        if (actionCompleted){
                            messageService.changeCompletedMessage();
                        } else {
                            messageService.itemIdentifierDuplicateError();
                        }
                    } else {
                        messageService.storeIdentifierError();
                    }

                } else if (tokens[0].equals("display_items")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null){
                        targetStore.displayItems();
                    } else {
                        messageService.storeIdentifierError();
                    }
                    messageService.displayCompletedMessage();

                } else if (tokens[0].equals("make_pilot")) {
                    boolean flag = false;

                    for (DronePilot currPilot : dronePilotList){
                        if (currPilot.getAccountId().equals(tokens[1])){
                            messageService.pilotIdentifierError();
                            flag = true;
                        } else if (currPilot.getLicenseId().equals(tokens[6])){
                            messageService.pilotLicenseError();
                            flag = true;
                        }
                    }

                    if (!flag){
                        dronePilotList.add(new DronePilot(tokens[1],tokens[2], tokens[3],tokens[4],tokens[5],tokens[6],Integer.parseInt(tokens[7])));
                        messageService.changeCompletedMessage();
                    }

                } else if (tokens[0].equals("display_pilots")) {
                    helper.sortPilots(dronePilotList);

                    for (DronePilot currPilot : dronePilotList){
                        System.out.println(currPilot);
                    }

                    messageService.displayCompletedMessage();

                } else if (tokens[0].equals("make_drone")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null) {
                        if (!helper.checkIfDroneExists(droneList, tokens[2])){
                            droneList.add(new Drone(
                                    tokens[1],tokens[2],Integer.parseInt(tokens[3]),Integer.parseInt(tokens[4])
                            ));
                            messageService.changeCompletedMessage();
                        } else {
                            messageService.droneIdentifierDuplicateError();
                        }
                    }

                } else if (tokens[0].equals("display_drones")) {
                    if (helper.checkIfStoreExists(storeList, tokens[1])){
                        for (Drone currDrone : droneList) {
                            if (tokens[1].equals(currDrone.getAssignedStore())) {
                                DronePilot targetPilot = helper.getDronePilot(dronePilotList, currDrone.getAssignedDronePilot());
                                String name = targetPilot != null ? ",flown_by:" + targetPilot.getFullName() : "";
                                System.out.println(currDrone + name);
                            }
                        }
                        messageService.displayCompletedMessage();
                    } else {
                        messageService.storeIdentifierError();
                    }


                } else if (tokens[0].equals("fly_drone")) {
                    if (!helper.checkIfStoreExists(storeList,tokens[1])){
                        messageService.storeIdentifierError();
                    } else if (!helper.checkIfDroneExists(droneList,tokens[2])){
                        messageService.droneIdentifierExistError();
                    } else if (!helper.checkIfPilotExists(dronePilotList,tokens[3])){
                       messageService.pilotIdentifierExistsError();
                    } else {
                        for (Drone currDrone : droneList) {
                            if (tokens[2].equals(currDrone.getDroneId())) {
                                currDrone.assignNewPilot(tokens[3]);
                                messageService.changeCompletedMessage();
                            }
                        }
                    }


                } else if (tokens[0].equals("make_customer")) {
                    if (!helper.checkIfCustomerExists(customerList, tokens[1])){
                        customerList.add(new Customer(tokens[1], tokens[2], tokens[3], tokens[4], Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6])));
                        messageService.changeCompletedMessage();
                    } else {
                        messageService.customerIdentifierExistError();
                    }


                } else if (tokens[0].equals("display_customers")) {
                    helper.sortCustomers(customerList);
                    for (Customer currCustomer : customerList){
                        System.out.println(currCustomer);
                    }
                    messageService.displayCompletedMessage();

                } else if (tokens[0].equals("start_order")) {
                    Drone targetDrone = helper.getDrone(droneList, tokens[3]);
                    if (!helper.checkIfStoreExists(storeList,tokens[1])){
                        messageService.storeIdentifierError();
                    } else if (helper.checkIfOrderExists(orderList, tokens[2])) {
                        messageService.orderIdentifierAlreadyExists();
                    } else if (targetDrone == null){
                        messageService.droneIdentifierExistError();
                    } else if (!helper.checkIfCustomerExists(customerList,tokens[4])){
                        messageService.customerIdentfierError();
                    } else {
                        orderList.add(new Order(tokens[2], tokens[4],tokens[1],tokens[3]));
                        targetDrone.incrementNumOfOrders();
                        messageService.changeCompletedMessage();
                    }

                } else if (tokens[0].equals("display_orders")) {
                    if (helper.checkIfStoreExists(storeList,tokens[1])){
                        for (Order currOrder : orderList){
                            if (currOrder.getStoreId().equals(tokens[1])){
                                System.out.print(currOrder);
                            }
                        }
                        messageService.displayCompletedMessage();
                    } else {
                        messageService.storeIdentifierError();
                    }

                } else if (tokens[0].equals("request_item")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null){
                        Order targetOrder = helper.getOrder(orderList, tokens[2]);
                        if (targetOrder != null){
                            Item targetItem = helper.getItem(targetStore.getItems(), tokens[3]);
                            if (targetItem != null){
                                if (!targetOrder.doesOrderContainItemAlready(tokens[3])) {
                                    LineItem itemToAdd = new LineItem(targetItem.getName(), targetItem.getWeight(), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                                    Customer targetCustomer = helper.getCustomer(customerList, targetOrder.getCustomerId());
                                    if (targetCustomer.getCredit() > targetOrder.calculateOrderCost() + itemToAdd.calculateCost()){
                                        Drone targetDrone = helper.getDrone(droneList, targetOrder.getDroneId());
                                        if (itemToAdd.calculateWeight() + targetOrder.calculateOrderWeight() < targetDrone.getWeightCapacity()){
                                            targetOrder.addLineItem(itemToAdd);
                                            targetDrone.updateCurrentWeight(targetDrone.getCurrentWeight() + itemToAdd.calculateWeight());
                                            messageService.changeCompletedMessage();
                                        } else {
                                            messageService.currentDroneWeightError();
                                        }
                                    } else {
                                        messageService.customerInsufficientFundsError();
                                    }
                                } else {
                                    messageService.itemAlreadyOrderedError();
                                }
                            } else {
                                messageService.itemIdentifierError();
                            }
                        } else {
                            messageService.orderIdentifierError();
                        }
                    } else {
                        messageService.storeIdentifierError();
                    }

                } else if (tokens[0].equals("purchase_order")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null) {
                        Order targetOrder = helper.getOrder(orderList, tokens[2]);
                        if (targetOrder != null) {
                            Drone targetDrone = helper.getDrone(droneList, targetOrder.getDroneId());
                            if (targetDrone.getAssignedDronePilot() != null) {
                                Customer targetCustomer = helper.getCustomer(customerList, targetOrder.getCustomerId());
                                if (targetDrone.getTripsLeft() - 1 >= 0) {
                                    DronePilot targetDronePilot = helper.getDronePilot(dronePilotList,targetDrone.getAssignedDronePilot());
                                    targetCustomer.setCredit(targetCustomer.getCredit() - targetOrder.calculateOrderCost());
                                    targetStore.completeOrder(targetOrder.calculateOrderCost());
                                    targetDronePilot.incrementDeliveries();
                                    targetDrone.completeTrip(targetOrder.calculateOrderWeight());
                                    orderList.removeIf(item -> item.getOrderId().equals(targetOrder.getOrderId()));
                                    messageService.changeCompletedMessage();
                                } else {
                                    messageService.droneNeedsFuelError();
                                }
                            } else {
                                messageService.droneNeedsPilotError();
                            }
                        } else {
                            messageService.orderIdentifierError();
                        }
                    } else {
                        messageService.storeIdentifierError();
                    }

                } else if (tokens[0].equals("cancel_order")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null) {
                        Order targetOrder = helper.getOrder(orderList, tokens[2]);
                        if (targetOrder != null) {
                            Drone targetDrone = helper.getDrone(droneList,targetOrder.getDroneId());
                            targetDrone.decrementNumOfOrders();
                            targetDrone.updateCurrentWeight(targetDrone.getCurrentWeight() - targetOrder.calculateOrderCost());
                            orderList.removeIf(item -> item.getOrderId().equals(targetOrder.getOrderId()));
                            messageService.changeCompletedMessage();
                        } else {
                            messageService.orderIdentifierError();
                        }
                    } else {
                        messageService.storeIdentifierError();
                    }

                } else if (tokens[0].equals("transfer_order")) {
                    Store targetStore = helper.getStore(storeList, tokens[1]);
                    if (targetStore != null) {
                        Order targetOrder = helper.getOrder(orderList, tokens[2]);
                        if (targetOrder != null) {
                            Drone oldDrone = helper.getDrone(droneList, targetOrder.getDroneId());
                            Drone newDrone = helper.getDrone(droneList, tokens[3]);
                            if (oldDrone != null && newDrone != null){
                                if (oldDrone.getWeightCapacity() < newDrone.getWeightCapacity()){
                                    if (!oldDrone.getDroneId().equals(newDrone.getDroneId())){
                                        targetOrder.updateAssignedDrone(newDrone.getDroneId());
                                        targetStore.updateSuccessfulTransfers();
                                        oldDrone.decrementNumOfOrders();
                                        newDrone.incrementNumOfOrders();
                                        oldDrone.updateCurrentWeight(oldDrone.getCurrentWeight() - targetOrder.calculateOrderWeight());
                                        newDrone.updateCurrentWeight(newDrone.getCurrentWeight() + targetOrder.calculateOrderWeight());
                                        messageService.changeCompletedMessage();
                                    } else {
                                        messageService.duplicateDrone();
                                    }
                                } else {
                                    messageService.droneCapacityError();
                                }
                            } else {
                                messageService.droneIdentifierExistError();
                            }
                        } else {
                            messageService.orderIdentifierError();
                        }
                    } else {
                        messageService.storeIdentifierError();
                    }

                } else if (tokens[0].equals("display_efficiency")) {
                    System.out.println("no parameters needed");
                    for (Store currStore : storeList) {
                        System.out.println("name:" + currStore.getName() + ",purchases:" + currStore.getSuccessfulOrders() + ",overloads:"
                                + helper.calculateOverloads(currStore, orderList) + ",transfers:" + currStore.getSuccessfulTransfers());
                    }

                } else if (tokens[0].equals("stop")) {
                    System.out.println("stop acknowledged");
                    break;

                } else if (!tokens[0].contains("//")) {
                    System.out.println("command " + tokens[0] + " NOT acknowledged");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }
        }

        System.out.println("simulation terminated");
        commandLineInput.close();
    }
}