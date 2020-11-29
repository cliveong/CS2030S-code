package cs2030.simulator;

public class ReturnEvent extends Event {

    /**
     * Makes the server avail to work again after resting.
     * 
     * @param customer    the customer
     * @param replacement the server
     * @param rng         rng
     * @param est         event starting time
     */

    public ReturnEvent(Customer customer, Server replacement, RNG rng, double est) {
        super(x -> {
            return Pair.of(x, null);
        }, customer, rng, est);
    }
}