package cs2030.simulator;

public class Customer {
    private final int customerID;
    private final double arrivalTime;

    public Customer(int customerID, double arrivalTime) {
        this.customerID = customerID;
        this.arrivalTime = arrivalTime;
    }

    public int getID() {
        return this.customerID;
    }

    public double getArrTime() {
        return this.arrivalTime;
    }
}