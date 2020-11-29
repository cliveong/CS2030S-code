package cs2030.simulator;

import java.util.Optional;

public class ModEvent extends Event {

    /**
     * Check whether servers can take in more customers.
     * 
     * @param customer    the customer
     * @param replacement the server
     * @param rng         the customer
     * @param est         event start time
     */

    public ModEvent(Customer customer, Server replacement, RNG rng, double est) {
        super(x -> {
            Server temp = x.find(y -> y.getId() == replacement.getId()).get();

            if (temp instanceof SelfServer) {
                if (temp.isAvailable()) { // serve event
                    SelfServer newReplacement = new SelfServer(temp.getId(), false,
                            temp.hasWaiting(), temp.whenFree(), temp.maxQueue());

                    double waitingTime = temp.whenFree() - customer.getArrTime();
                    newReplacement.removeCustomer(customer);
                    return Pair.of(x.replace(newReplacement).incrementWait(waitingTime),
                            new ServeEvent(customer, newReplacement, rng, temp.whenFree()));
                } else {
                    Optional<Server> selfServers = x
                            .find(y -> y instanceof SelfServer && y.isAvailable());
                    if (selfServers.isPresent()) { // check other free servesr
                        Server selfsever = selfServers.get();
                        SelfServer newReplacement = new SelfServer(selfsever.getId(), false,
                                selfsever.hasWaiting(), selfsever.whenFree(), selfsever.maxQueue());
                        newReplacement.removeCustomer(customer);
                        double waitingTime = selfsever.whenFree() - customer.getArrTime();
                        return Pair.of(x.replace(newReplacement).incrementWait(waitingTime),
                                new ServeEvent(customer, newReplacement, rng,
                                        selfsever.whenFree()));
                    } else { // reschedule event again
                        Server shortest = x.getList().stream()
                                .filter(y -> y instanceof SelfServer && !(y.isAvailable()))
                                .reduce((a, b) -> a.whenFree() < b.whenFree() ? a : b).get();
                        SelfServer newReplacement = new SelfServer(shortest.getId(), false,
                                shortest.hasWaiting(), shortest.whenFree(), shortest.maxQueue());
                        return Pair.of(x.replace(newReplacement),
                                new ModEvent(customer, newReplacement, rng, shortest.whenFree()));

                    }
                }

            } else { // all non self from here
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
    }
}
