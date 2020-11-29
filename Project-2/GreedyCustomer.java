package cs2030.simulator;

public class GreedyCustomer extends Customer {
    public GreedyCustomer(int customerID, double arrivalTime) {
        super(customerID, arrivalTime);
    }

    @Override
    public String toString() {
        return String.format("%d(greedy)", super.getID());
    }
}
