package cs2030.simulator;
import java.util.List;
import java.util.ArrayList;


public class Server {
	private final int ID;
	private final boolean isAvailable;
	private final boolean hasWaiting;
	private final double whenFree;
	private final int maxQ;
	private final List<Customer> list;
	private final boolean isResting;
	private final double probResting;


	public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime) {
		this.ID = identifier;
		this.isAvailable = isAvailable;
		this.hasWaiting = hasWaitingCustomer;
		this.whenFree = nextAvailableTime;
		this.maxQ = 1;
		this.list = new ArrayList<>();
		this.isResting = false;
		this.probResting = 0.0;
	}

	public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime, int maxQ) {
		this.ID = identifier;
		this.isAvailable = isAvailable;
		this.hasWaiting = hasWaitingCustomer;
		this.whenFree = nextAvailableTime;
		this.maxQ = maxQ;
		this.list = new ArrayList<>();
		this.isResting = false;
		this.probResting = 0.0;
	}

	public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime, int maxQ, double probResting) {
		this.ID = identifier;
		this.isAvailable = isAvailable;
		this.hasWaiting = hasWaitingCustomer;
		this.whenFree = nextAvailableTime;
		this.maxQ = maxQ;
		this.list = new ArrayList<>();
		this.isResting = false;
		this.probResting = probResting;
	}

	public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime, int maxQ, List<Customer> list) {
		this.ID = identifier;
		this.isAvailable = isAvailable;
		this.hasWaiting = hasWaitingCustomer;
		this.whenFree = nextAvailableTime;
		this.maxQ = maxQ;
		this.list = list;
		this.isResting = false;
		this.probResting = 0.0;

	}

	public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer, double nextAvailableTime, int maxQ, List<Customer> list, boolean isResting, double probResting) {
		this.ID = identifier;
		this.isAvailable = isAvailable;
		this.hasWaiting = hasWaitingCustomer;
		this.whenFree = nextAvailableTime;
		this.maxQ = maxQ;
		this.list = list;
		this.isResting = isResting;
		this.probResting = probResting;
	}

	public boolean isResting() {
		return this.isResting;
	}

	public double probResting() {
		return this.probResting;
	}



	public int maxQueue() {
		return this.maxQ;
	}

	public List<Customer> queue() {
		return this.list;
	}

	public int sizeQueue() {
		return this.list.size();
	}

	public Customer nextCustomer() {
		return this.queue().get(0);
	}

	public Server addCustomer(Customer c) {
		List<Customer> temp = new ArrayList<>(this.queue());
		temp.add(c);
		return new Server(this.getIdentifier(),this.isAvailable(),this.hasWaitingCustomer(),this.whenFree(),this.maxQueue(),temp,this.isResting(),this.probResting());
	}

	public Server removeCustomer() {
		List<Customer> temp = new ArrayList<>(this.queue());
		temp.remove(0);
		return new Server(this.getIdentifier(),this.isAvailable(),this.hasWaitingCustomer(),this.whenFree(),this.maxQueue(),temp,this.isResting(),this.probResting());
	}

	public int getIdentifier() {
		return this.ID;
	}

	public boolean isAvailable() {
		return this.isAvailable;
	}

	public boolean hasWaitingCustomer() {
		return this.hasWaiting;
	}

	public double whenFree() {
		return this.whenFree;
	}


	
    String giveStatus() {
        if (isAvailable) {
            return "avail";
        } else if (!hasWaiting) {
            return "queue";
        } else {
            return "leave";
        }
    }

    @Override 
    public String toString() {
        if (giveStatus().equals("avail")){
            return String.format("%d is available", ID);
        } else if (giveStatus().equals("queue")) {
            return String.format("%d is busy; available at %.3f", ID, whenFree);
        } else {
            return String.format("%d is busy; waiting customer to be served at %.3f", ID, whenFree);
        }
    }	

}