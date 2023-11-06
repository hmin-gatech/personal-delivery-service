package edu.gatech.cs6310.classes;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    private String orderId;
    private String customerId;
    private String storeId;
    private String droneId;
    private ArrayList<LineItem> items = new ArrayList<>();

    public Order(String newOrderId, String newCustomerId, String newStoreId, String newDroneId){
        this.orderId = newOrderId;
        this.customerId = newCustomerId;
        this.storeId = newStoreId;
        this.droneId = newDroneId;
    }

    public String toString(){
        StringBuilder output = new StringBuilder("orderID:" + this.orderId + "\n");
        for (LineItem currItem : items){
            output.append("item_name:").append(currItem.getName()).append(",total_quantity:").append(currItem.getQuantity()).append(",total_cost:").append(currItem.getQuantity() * currItem.getUnitPrice()).append(",total_weight:").append(currItem.getQuantity() * currItem.getWeight()).append("\n");
        }
        return output.toString();
    }

    public void addLineItem(LineItem newLineItem){
        items.add(newLineItem);
    }

    public void updateAssignedDrone(String newDroneId) {
        this.droneId = newDroneId;
    }

    public int calculateOrderWeight(){
        int totalWeight = 0;
        for (LineItem currItem : items){
            totalWeight += currItem.calculateWeight();
        }
        return totalWeight;
    }

    public int calculateOrderCost(){
        int totalCost = 0;
        for (LineItem currItem : items){
            totalCost += currItem.calculateCost();
        }
        return totalCost;
    }

    public boolean doesOrderContainItemAlready(String itemName){
        for (LineItem currItem: items){
            if (Objects.equals(currItem.getName(), itemName)){
                return true;
            }
        }
        return false;
    }

    public String getStoreId(){
        return this.storeId;
    }

    public String getOrderId(){
        return this.orderId;
    }

    public String getDroneId() { return this.droneId; }

    public String getCustomerId() { return this.customerId; }
}
