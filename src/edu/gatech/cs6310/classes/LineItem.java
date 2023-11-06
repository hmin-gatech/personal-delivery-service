package edu.gatech.cs6310.classes;

public class LineItem extends Item {
    private int quantity;
    private int unitPrice;

    public LineItem(String newName, int newWeight, int newQuantity, int newUnitPrice) {
        super(newName, newWeight);
        this.quantity = newQuantity;
        this.unitPrice = newUnitPrice;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public int getUnitPrice() {
        return this.unitPrice;
    }

    public int calculateWeight() {
        return this.quantity * this.getWeight();
    }
    public int calculateCost() {
        return this.quantity * this.unitPrice;
    }
}
