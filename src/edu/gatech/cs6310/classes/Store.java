package edu.gatech.cs6310.classes;

import java.util.ArrayList;
import java.util.Comparator;

public class Store {
    private String name;
    private int revenue;
    private ArrayList<Item> itemList = new ArrayList<>();
    private int successfulOrders = 0;
    private int successfulTransfers = 0;

    public void setRevenue(int amount) {
        this.revenue = this.revenue + amount;
    }

    public int getRevenue() {
        return this.revenue;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Item> getItems() { return this.itemList;}

    public int getSuccessfulOrders() {
        return this.successfulOrders;
    }

    private void updateSuccessfulOrders() {
        this.successfulOrders += 1;
    }

    public void updateSuccessfulTransfers(){
        this.successfulTransfers += 1;
    }

    public int getSuccessfulTransfers(){
        return this.successfulTransfers;
    }

    public void completeOrder(int incomingRevenue) {
        updateSuccessfulOrders();
        setRevenue(incomingRevenue);
    }

    public Store(String name, int revenue){
        this.name = name;
        this.revenue = revenue;
    }

    public String toString() {
        return "name:" + this.name + ",revenue:" + this.revenue;
    }

    public boolean addItem(String itemName, int weight) {
        for (Item item : this.itemList){
            if (item.getName().equals(itemName)){
                return false;
            }
        }
        this.itemList.add(new Item(itemName, weight));
        return true;
    }

    public void displayItems(){
        itemList.sort(Comparator.comparing(Item::getName));
        for (Item i : itemList){
            System.out.println(i);
        }
    }
}
