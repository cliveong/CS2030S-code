package cs2030.simulator; //done

import java.util.Optional;

public class WaitEvent extends Event {
    private final Server replacement;

    /**
     * Takes in a customer, server updates the server and return doneevent.
     * 
     * @param customer    the customer
     * @param replacement the server
     */
    public WaitEvent(Customer customer, Server replacement) {
        super(x -> {
            Server newReplacement = new Server(replacement.getId(), false, replacement.hasWaiting(),
                    replacement.whenFree());
            return Pair.of(x.replace(newReplacement), new ServeEvent(customer, newReplacement));
        }, customer);
        this.replacement = replacement;
    }

    /**
     * Takes in a customer, server updates the server and return doneevent.
     * 
     * @param customer the customer
     * @param temp     the server
     * @param rng      the rng
     * @param est      event starting time
     */
    public WaitEvent(Customer customer, Server temp, RNG rng, double est) {
        super(x -> {

            if (temp instanceof SelfServer) {

                Optional<Server> selfServers = x
                        .find(y -> y instanceof SelfServer && y.isAvailable());

                if (selfServers.isPresent()) {
                    Server selfServer = selfServers.get();
                    SelfServer newServer = new SelfServer(selfServer.getId(), false,
                            selfServer.hasWaiting(), selfServer.whenFree(), selfServer.maxQueue());

                    newServer.removeCustomer(customer);
                    double waitingTime = selfServer.whenFree() - customer.getArrTime();
                    return Pair.of(x.replace(newServer).incrementWait(waitingTime),
                            new ServeEvent(customer, newServer, rng, selfServer.whenFree()));
                } else {
                    Server temptwo = x.getList().stream().filter(y -> y instanceof SelfServer)
                            .reduce((y, z) -> y.whenFree() < z.whenFree() ? y : z).get();
                    SelfServer newServer = new SelfServer(temptwo.getId(), false,
                            temptwo.hasWaiting(), temptwo.whenFree(), temptwo.maxQueue());
                    return Pair.of(x.replace(newServer),
                            new ModEvent(customer, newServer, rng, newServer.whenFree()));

                }

            }

            if (temp.isResting()) {
                Server newReplacement = new Server(temp.getId(), false, temp.hasWaiting(),
                        temp.whenFree(), temp.maxQueue(), temp.queue(), temp.isResting(),
                        temp.probResting());
                return Pair.of(x.replace(newReplacement),
                        new ModEvent(customer, newReplacement, rng, temp.whenFree()));
            } else {
                if (temp.isAvailable()) {
                    Server newReplacement = new Server(temp.getId(), false, temp.hasWaiting(),
                            temp.whenFree(), temp.maxQueue(), temp.queue(), temp.isResting(),
                            temp.probResting()).removeCustomer();

                    double waitingTime = temp.whenFree() - customer.getArrTime();

                    return Pair.of(x.replace(newReplacement).incrementWait(waitingTime),
                            new ServeEvent(customer, newReplacement, rng, temp.whenFree()));
                } else {
                    Server newReplacement = new Server(temp.getId(), false, temp.hasWaiting(),
                            temp.whenFree(), temp.maxQueue(), temp.queue(), temp.isResting(),
                            temp.probResting());
                    return Pair.of(x.replace(newReplacement),
                            new ModEvent(customer, newReplacement, rng, temp.whenFree()));
                }
            }
        }, customer, rng, est);
        this.replacement = temp;
    }

    @Override
    public String toString() {
        if (!(replacement instanceof SelfServer)) {
            if (this.getCustomer() instanceof GreedyCustomer) {
                return String.format("%.3f %s waits to be served by server %d",
                        this.getCustomer().getArrTime(), this.getCustomer(),
                        this.replacement.getId());
            } else {
                return String.format("%.3f %d waits to be served by server %d",
                        this.getCustomer().getArrTime(), this.getCustomer().getID(),
                        this.replacement.getId());
            }

        } else {
            if (this.getCustomer() instanceof GreedyCustomer) {
                return String.format("%.3f %s waits to be served by self-check %d",
                        this.getCustomer().getArrTime(), this.getCustomer(),
                        this.replacement.getId());
            } else {
                return String.format("%.3f %d waits to be served by self-check %d",
                        this.getCustomer().getArrTime(), this.getCustomer().getID(),
                        this.replacement.getId());
            }
        }
    }
}