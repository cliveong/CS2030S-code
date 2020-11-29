package cs2030.simulator;

public class DoneEvent extends Event {
    private final Server replacement;
    
    /**
     * When the server has finished serving.
     * 
     * @param customer the customer
     * @param replacement the current server
     */
    
    public DoneEvent(Customer customer, Server replacement) {
        super(x -> {
            Server newReplacement = new Server(replacement.getId(), true, false,
                    replacement.whenFree());
            return Pair.of(x.replace(newReplacement), null);
        }, customer);
        this.replacement = new Server(replacement.getId(), true, false, replacement.whenFree());
    }

    /**
     * The done event when the server has finished serving.
     * 
     * @param customer     customer
     * @param replacement  server
     * @param rng          rng
     * @param est          event starting time
     */
    
    public DoneEvent(Customer customer, Server replacement, RNG rng, double est) {
        super(x -> {
            Server temp = x.find(y -> y.getId() == replacement.getId()).get();

            if (temp instanceof SelfServer) {
                SelfServer newReplacement = new SelfServer(temp.getId(), true, temp.hasWaiting(),
                        temp.whenFree(), temp.maxQueue());

                return Pair.of(x.replace(newReplacement), null);

            } else {
                double randomRest = rng.genRandomRest();
                boolean willRest = randomRest < temp.probResting() ? true : false;
                if (willRest) {
                    double restPeriod = rng.genRestPeriod();
                    double nextavail = est + restPeriod; // next available time for server
                    Server newServer = new Server(temp.getId(), false, temp.hasWaiting(), nextavail,
                            temp.maxQueue(), temp.queue(), true, temp.probResting());

                    return Pair.of(x.replace(newServer),
                            new RestEvent(customer, newServer, rng, nextavail));
                } else {
                    Server newReplacement = new Server(temp.getId(), true, temp.hasWaiting(),
                            temp.whenFree(), temp.maxQueue(), temp.queue(), false,
                            temp.probResting());

                    return Pair.of(x.replace(newReplacement), null);
                }
            }
        }, customer, rng, est);
        this.replacement = replacement;
    }

    @Override
    public String toString() {
        if (!(replacement instanceof SelfServer)) {
            if (this.getCustomer() instanceof GreedyCustomer) {
                return String.format("%.3f %s done serving by server %d", this.eventStartTime(),
                        this.getCustomer(), this.replacement.getId());
            } else {
                return String.format("%.3f %d done serving by server %d", this.eventStartTime(),
                        this.getCustomer().getID(), this.replacement.getId());
            }

        } else {
            if (this.getCustomer() instanceof GreedyCustomer) {
                return String.format("%.3f %s done serving by self-check %d", this.eventStartTime(),
                        this.getCustomer(), this.replacement.getId());
            } else {
                return String.format("%.3f %d done serving by self-check %d", this.eventStartTime(),
                        this.getCustomer().getID(), this.replacement.getId());
            }
        }
    }
}