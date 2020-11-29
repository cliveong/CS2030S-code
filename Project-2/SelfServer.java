package cs2030.simulator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SelfServer extends Server {
    static LinkedList<Customer> singlequeue = new LinkedList<>();

    SelfServer(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
            double nextAvailableTime, int maxQ) {
        super(identifier, isAvailable, hasWaitingCustomer, nextAvailableTime, maxQ);
    }

    public List<Customer> queue() {
        return singlequeue;
    }

    public int sizeQueue() {
        return singlequeue.size();
    }

    public Customer nextCustomer() {
        return singlequeue.get(0);
    }

    public Server addCustomer(Customer c) {
        singlequeue.add(c);
        return this;
    }

    public Server removeCustomer() {
        singlequeue.remove();
        return this;
    }

    public Server removeCustomer(Customer c) {
        singlequeue.remove(c);
        return this;
    }
}
