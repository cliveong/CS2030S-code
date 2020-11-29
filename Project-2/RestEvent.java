package cs2030.simulator;

public class RestEvent extends Event {
    
    /**
     * Makes the server go rest, unavail to serve.
     * 
     * @param customer    the customer
     * @param replacement the server
     * @param rng         rng
     * @param est         event starting time
     */
    
    public RestEvent(Customer customer, Server replacement, RNG rng, double est) {
        super(x -> {
            Server temp = x.find(y -> y.getId() == replacement.getId()).get();
            Server newServer = new Server(temp.getId(), true, temp.hasWaiting(), temp.whenFree(),
                    temp.maxQueue(), temp.queue(), false, temp.probResting());
            return Pair.of(x.replace(newServer),
                    new ReturnEvent(customer, newServer, rng, temp.whenFree()));
        }, customer, rng, est);
    }
}
