package cs2030.simulator;
public class Customer {
	private final int ID;
	private final double arrivalTime;

	public Customer(int customerID, double arrivalTime) {
		this.ID = customerID;
		this.arrivalTime = arrivalTime;
	}

	public int getIdentifier() {
		return this.ID;
	}

	public double getArrivalTime() {
		return this.arrivalTime;
	} 
}