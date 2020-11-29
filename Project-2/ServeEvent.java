package cs2030.simulator;

public class ServeEvent extends Event {
    private final Server replacement;
    
    /**
     * The server serves the customer.
     * 
     * @param customer    the customer
     * @param replacement the server
     */
    
    public ServeEvent(Customer customer, Server replacement) {
        super(x -> {
            Server newReplacement = new Server(replacement.getId(), false, false,
                    replacement.whenFree() + 1.00);
            return Pair.of(x.replace(newReplacement), new DoneEvent(customer, newReplacement));
        }, customer);

        this.replacement = replacement;

    }
    
    /**
     * The server serves the customer.
     * 
     * @param customer    the customer
     * @param replacement the server
     * @param rng         rng
     * @param est         event starting time
     */
    public ServeEvent(Customer customer, Server replacement, RNG rng, double est) {
        super(x -> {
            Server temp = x.find(y -> y.getId() == replacement.getId()).get();
            double newReplacementTime = temp.whenFree() + rng.genServiceTime();

            if (!(temp instanceof SelfServer)) {
                Server newReplacement = new Server(replacement.getId(), false, temp.hasWaiting(),
                        newReplacementTime, temp.maxQueue(), temp.queue(), temp.isResting(),
                        temp.probResting());

                return Pair.of(x.replace(newReplacement).incrementServed(),
                        new DoneEvent(customer, newReplacement, rng, newReplacementTime));
            } else {
                SelfServer newReplacement = new SelfServer(replacement.getId(), false,
                        temp.hasWaiting(), newReplacementTime, temp.maxQueue());
                return Pair.of(x.replace(newReplacement).incrementServed(),
                        new DoneEvent(customer, newReplacement, rng, newReplacementTime));
            }
        }, customer, rng, est);
        this.replacement = replacement;

    }

    @Override
    public String toString() {
        if (!(replacement instanceof SelfServer)) {
            if (this.getCustomer() instanceof GreedyCustomer) {
                return String.format("%.3f %s served by server %d", this.eventStartTime(),
                        this.getCustomer(), this.replacement.getId());
            } else {
                return String.format("%.3f %d served by server %d", this.eventStartTime(),
                        this.getCustomer().getID(), this.replacement.getId());
            }

        } else {
            if (this.getCustomer() instanceof GreedyCustomer) {
                return String.format("%.3f %s served by self-check %d", this.eventStartTime(),
                        this.getCustomer(), this.replacement.getId());
            } else {
                return String.format("%.3f %d served by self-check %d", this.eventStartTime(),
                        this.getCustomer().getID(), this.replacement.getId());
            }

        }
    }

}