package edu.gatech.cs6310.classes;

public class Item {
    private String name;
    private int weight;

    public String getName() {
        return this.name;
    }

    public Item(String newName, int newWeight){
        this.name = newName;
        this.weight = newWeight;
    }

    public String toString() {
        return this.name + "," + this.weight;
    }

    public int getWeight(){ return this.weight; }
}

