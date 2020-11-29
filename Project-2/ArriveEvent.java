package cs2030.simulator;

import java.util.Optional;

public class ArriveEvent extends Event {
    
    /**
     * Takes in a customer. will update the state of
     * server and output a either a Serve/Wait/leave event
     * 
     * @param customer the customer
     */

    public ArriveEvent(Customer customer) {
        super(x -> { 
            Optional<Server> freeServer = x.find(y -> y.isAvailable());
            Optional<Server> busyNoWaitingServer = x.find(y -> 
                !(y.isAvailable()) && !(y.hasWaiting()));
            if (freeServer.isPresent()) {

                Server tempServe = freeServer.get();
                Server serveReplacement = 
                        new Server(tempServe.getId(),false,false,customer.getArrTime());
                        
                return Pair.of(x.replace(serveReplacement), 
                        new ServeEvent(customer,serveReplacement));
            } else {

                if (busyNoWaitingServer.isEmpty()) {
                    return Pair.of(x, new LeaveEvent(customer));
                            
                } else {
                            
                    Server temp = busyNoWaitingServer.get();
                    Server replacement = 
                            new Server(temp.getId(),false,temp.hasWaiting(),temp.whenFree());
                            
                    return Pair.of(x.replace(replacement), new WaitEvent(customer,replacement));
                }
            }
        },customer);
    }

    /**
     * Takes in a customer. will update the state of
     * server and output a either a Serve/Wait/leave event
     * using rng for timings
     * 
     * @param customer the customer
     * @param rng the rng
     */


    public ArriveEvent(Customer customer, RNG rng) {
        super(x -> { 
            Optional<Server> freeServer = 
                    x.find(y -> y.isAvailable() && y.sizeQueue() == 0);
            Optional<Server> busyNoWaitingServer = 
                    x.find(y -> !(y.isAvailable()) && (y.sizeQueue() + 1 <= y.maxQueue()));

            if (freeServer.isPresent()) {
                Server tempServe = freeServer.get();
                if (freeServer.get() instanceof SelfServer) {
                    SelfServer serveReplacement = 
                            new SelfServer(tempServe.getId(), false, false, 
                                    customer.getArrTime(), tempServe.maxQueue());
                    return Pair.of(x.replace(serveReplacement), 
                            new ServeEvent(customer,serveReplacement,rng,customer.getArrTime()));
                } else {
                    Server serveReplacement = 
                            new Server(tempServe.getId(),false,false,customer.getArrTime(),
                                    tempServe.maxQueue(),tempServe.queue(),
                                    tempServe.isResting(),tempServe.probResting());
                    return Pair.of(x.replace(serveReplacement), 
                            new ServeEvent(customer,serveReplacement,rng,customer.getArrTime()));
                }
            } else {
                if (busyNoWaitingServer.isPresent()) {
                    if (customer instanceof GreedyCustomer) {
                        Server temp = x.lowestQueue();
                        if (temp instanceof SelfServer) {
                            SelfServer replacement = 
                                    new SelfServer(temp.getId(),false, true, 
                                            temp.whenFree(),temp.maxQueue());
                            replacement.addCustomer(customer);
                            return Pair.of(x.replace(replacement), 
                                    new WaitEvent(customer,replacement,rng,customer.getArrTime()));
                        } else {
                            Server replacement = 
                                    new Server(temp.getId(),false, true, temp.whenFree(),
                                            temp.maxQueue(),temp.queue(),temp.isResting(),
                                            temp.probResting()).addCustomer(customer);
                            return Pair.of(x.replace(replacement), 
                                    new WaitEvent(customer,replacement,rng,customer.getArrTime()));
                        }
                    } else if (busyNoWaitingServer.get() instanceof SelfServer) {
                        SelfServer temp = (SelfServer) busyNoWaitingServer.get();
                        SelfServer replacement = 
                                new SelfServer(temp.getId(),false, true, temp.whenFree(),
                                        temp.maxQueue());
                        replacement.addCustomer(customer);
                        return Pair.of(x.replace(replacement), 
                                new WaitEvent(customer,replacement,rng,customer.getArrTime()));
                    } else {
                        Server temp = busyNoWaitingServer.get();
                        Server replacement = new Server(temp.getId(),false, true, 
                                temp.whenFree(),temp.maxQueue(),temp.queue(),
                                temp.isResting(),temp.probResting()).addCustomer(customer);
                        return Pair.of(x.replace(replacement), 
                                new WaitEvent(customer,replacement,rng,customer.getArrTime()));
                    }
                } else {
                    return Pair.of(x, new LeaveEvent(customer));
                }
            }
        },customer,rng);
    }

    @Override
    public String toString() {
        if (this.getCustomer() instanceof GreedyCustomer) {
            return String.format("%.3f %s arrives",                             
                    this.getCustomer().getArrTime(), 
                    this.getCustomer());
        } else {
            return String.format("%.3f %d arrives", 
                    this.getCustomer().getArrTime(), 
                    this.getCustomer().getID());
        }
    }
}