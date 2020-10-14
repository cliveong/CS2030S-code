package cs2030.simulator;
/**
 * @params id and arrivalTime for keep track of customer info
 *
 */
public class Customer {
	private final int ID;
	private final double arrivalTime;

	public Customer(int custID, double custarrivalTime) {
		this.ID = custID;
		this.arrivalTime = custarrivalTime;
	}

	int getID() {
		return this.ID;
	}

	double getArrivalTime() {
		return this.arrivalTime;
	}

	public String toString() {
		return String.format("%d arrives at %.1f", this.ID, this.arrivalTime);
	}
}	