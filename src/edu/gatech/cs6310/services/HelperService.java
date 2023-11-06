package edu.gatech.cs6310.services;

import edu.gatech.cs6310.classes.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class HelperService {
    public void sortPilots(ArrayList<DronePilot> incomingDronePilotList){
        incomingDronePilotList.sort(Comparator.comparing(DronePilot::getAccountId));
    }

    public void sortCustomers(ArrayList<Customer> incomingCustomers){
        incomingCustomers.sort(Comparator.comparing(Customer::getCustomerId));
    }

    public boolean checkIfStoreExists(ArrayList<Store> stores, String targetStore){
        for (Store currStore : stores) {
            if (targetStore.equals(currStore.getName())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfCustomerExists(ArrayList<Customer> customers, String targetCustomer){
        for (Customer currCustomer : customers) {
            if (targetCustomer.equals(currCustomer.getCustomerId())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfDroneExists(ArrayList<Drone> drones, String targetDroneId){
        for (Drone currDrone : drones) {
            if (targetDroneId.equals(currDrone.getDroneId())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfPilotExists(ArrayList<DronePilot> dronePilots, String targetPilotId){
        for (DronePilot currPilot : dronePilots) {
            if (targetPilotId.equals(currPilot.getAccountId())) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfOrderExists(ArrayList<Order> orderList, String targetOrder){
        for (Order currOrder : orderList) {
            if (targetOrder.equals(currOrder.getOrderId())) {
                return true;
            }
        }
        return false;
    }

    public DronePilot getDronePilot(ArrayList<DronePilot> dronePilots, String targetPilotId)  {
        if (targetPilotId != null){
            for (DronePilot currPilot : dronePilots) {
                if (targetPilotId.equals(currPilot.getAccountId())) {
                    return currPilot;
                }
            }
        }
        return null;
    }

    public Item getItem(ArrayList<Item> items, String itemName)  {
        for (Item currItem : items) {
            if (itemName.equals(currItem.getName())) {
                return currItem;
            }
        }
        return null;
    }

    public Store getStore(ArrayList<Store> stores, String storeName)  {
        for (Store currStore : stores) {
            if (storeName.equals(currStore.getName())) {
                return currStore;
            }
        }
        return null;
    }

    public Order getOrder(ArrayList<Order> orders, String orderId)  {
        for (Order currOrder : orders) {
            if (orderId.equals(currOrder.getOrderId())) {
                return currOrder;
            }
        }
        return null;
    }

    public Drone getDrone(ArrayList<Drone> drones, String droneId) {
        for (Drone currDrone : drones) {
            if (droneId.equals(currDrone.getDroneId())) {
                return currDrone;
            }
        }
        return null;
    }

    public Customer getCustomer(ArrayList<Customer> customers, String targetCustomer) {
        for (Customer currCustomer : customers) {
            if (targetCustomer.equals(currCustomer.getCustomerId())) {
                return currCustomer;
            }
        }
        return null;
    }

    public boolean checkIfItemExists(Store targetStore, String targetItem) {
        for (Item currItem : targetStore.getItems()) {
            if (currItem.getName().equals(targetItem)) {
                return true;
            }
        }
        return false;
    }

    public int calculateOverloads(Store store, ArrayList<Order> orders){
        int counter = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (Order currOrder : orders){
            if (currOrder.getStoreId().equals(store.getName())){
                if (map.containsKey(currOrder.getDroneId())){
                    counter += 1;
                } else {
                    map.put(currOrder.getDroneId(), 1);
                }
            }
        }
        return counter;
    }
}
