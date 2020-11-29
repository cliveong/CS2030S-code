package cs2030.simulator;

import java.util.function.Function;

public class Event {
    private final Function<Shop, Pair<Shop, Event>> fn;
    private final Customer customer;
    private final RNG rng;
    private final double eventStartTime;
    
    /**
     * The parent class for event.
     * 
     * @param fn  function
     * @param customer customer
     */
    
    public Event(Function<Shop, Pair<Shop, Event>> fn, Customer customer) {
        this.fn = fn;
        this.customer = customer;
        this.rng = null;
        this.eventStartTime = customer.getArrTime();
    }

    /**
     * The parent class for event.
     * 
     * @param fn  function
     * @param customer customer
     * @param rng rng
     */
    
    public Event(Function<Shop, Pair<Shop, Event>> fn, Customer customer, RNG rng) {
        this.fn = fn;
        this.customer = customer;
        this.rng = rng;
        this.eventStartTime = customer.getArrTime();
    }

    /**
     * The parent class for event.
     * 
     * @param fn  function
     * @param customer customer
     * @param rng rng
     * @param est event starting time
     */
    
    public Event(Function<Shop, Pair<Shop, Event>> fn, Customer customer, RNG rng, double est) {
        this.fn = fn;
        this.customer = customer;
        this.rng = rng;
        this.eventStartTime = est;
    }

    public Function<Shop, Pair<Shop, Event>> giveFunction() {
        return this.fn;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public double eventStartTime() {
        return this.eventStartTime;
    }

    public RNG getRNG() {
        return this.rng;
    }

    public final Pair<Shop, Event> execute(Shop shop) {
        return this.giveFunction().apply(shop);
    }
}