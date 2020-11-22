package cs2030.simulator;
import java.util.function.Function;

public class Event {
	private final Function<Shop, Pair<Shop,Event>> fn;
	private final Customer customer;
	private final copiedRNG rngStore;
	private final double eventStartTime;

	public Event(Function<Shop, Pair<Shop,Event>> fn, Customer customer) {
		this.fn = fn;
		this.customer = customer;
		this.rngStore = null;
		this.eventStartTime = customer.getArrivalTime();
	}

	public Event(Function<Shop, Pair<Shop,Event>> fn, Customer customer, copiedRNG rng ) {
		this.fn = fn;
		this.customer = customer;
		this.rngStore = rng;
		this.eventStartTime = customer.getArrivalTime();
	}

	public Event(Function<Shop, Pair<Shop,Event>> fn, Customer customer, copiedRNG rng, double est ) {
		this.fn = fn;
		this.customer = customer;
		this.rngStore = rng;
		this.eventStartTime = est;
	}

	public Function<Shop, Pair<Shop,Event>> giveFunction() {
		return this.fn;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public double eventStartTime() {
		return this.eventStartTime;
	}


	public copiedRNG getRNG() {
		return this.rngStore;
	}

    final public Pair<Shop, Event> execute(Shop shop) {
        return this.giveFunction().apply(shop);
    }	
}