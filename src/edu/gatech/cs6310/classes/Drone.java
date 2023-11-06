package edu.gatech.cs6310.classes;

public class Drone {
    private String assignedStore;
    private String droneId;
    private int tripsLeft;
    private int weightCapacity;
    private int currWeight = 0;
    private String assignedDronePilot;
    private int numOfOrders = 0;

    public String getDroneId() {
        return this.droneId;
    }

    public String getAssignedStore(){
        return this.assignedStore;
    }

    public String getAssignedDronePilot(){
        return this.assignedDronePilot;
    }

    public int getTripsLeft() { return this.tripsLeft; }

    public void incrementNumOfOrders() { this.numOfOrders++; }

    public void decrementNumOfOrders() { this.numOfOrders--; }

    public void updateCurrentWeight(int newWeight) {
        if (newWeight > weightCapacity) {
            this.currWeight = weightCapacity;
        } else this.currWeight = Math.max(newWeight, 0);
    }

    public int getCurrentWeight() { return this.currWeight; }

    public void assignNewPilot(String newDronePilotId){ this.assignedDronePilot = newDronePilotId;}

    public void completeTrip(int weightToSubtract) {
        this.tripsLeft -= 1;
        this.decrementNumOfOrders();
        this.currWeight -= weightToSubtract;
    }

    public Drone(String newAssignedStore, String newDroneId, int newWeightCapacity, int newTripsLeft){
        this.assignedStore = newAssignedStore;
        this.droneId = newDroneId;
        this.tripsLeft = newTripsLeft;
        this.weightCapacity = newWeightCapacity;
    }

    public String toString() {
        return "droneID:" + this.droneId + ",total_cap:" + this.weightCapacity + ",num_orders:" + this.numOfOrders + ",remaining_cap:" + (weightCapacity - currWeight) + ",trips_left:" + this.tripsLeft;
    }

    public int getWeightCapacity() { return this.weightCapacity; }
}
