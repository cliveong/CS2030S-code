package cs2030.simulator;

import java.util.List;
import java.util.ArrayList;

public class Server {
    private final int iD;
    private final boolean isAvailable;
    private final boolean hasWaiting;
    private final double whenFree;
    private final int maxQ;
    private final List<Customer> list;
    private final boolean isResting;
    private final double probResting;

    /**
     * Class constructor for a Server.
     * 
     * @param identifier         the identifier of the Server.
     * @param isAvailable        availability of the Server.
     * @param hasWaitingCustomer if the Server has a waiting customer
     * @param nextAvailableTime  next timing where the Server is ready.
     */

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
            double nextAvailableTime) {
        this.iD = identifier;
        this.isAvailable = isAvailable;
        this.hasWaiting = hasWaitingCustomer;
        this.whenFree = nextAvailableTime;
        this.maxQ = 1;
        this.list = new ArrayList<>();
        this.isResting = false;
        this.probResting = 0.0;
    }

    /**
     * Class constructor for a Server.
     * 
     * @param identifier         the identifier of the Server.
     * @param isAvailable        availability of the Server.
     * @param hasWaitingCustomer if the Server has a waiting customer
     * @param nextAvailableTime  next timing where the Server is ready.
     * @param maxQ               maxqueue length of server
     */

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
            double nextAvailableTime, int maxQ) {
        this.iD = identifier;
        this.isAvailable = isAvailable;
        this.hasWaiting = hasWaitingCustomer;
        this.whenFree = nextAvailableTime;
        this.maxQ = maxQ;
        this.list = new ArrayList<>();
        this.isResting = false;
        this.probResting = 0.0;
    }

    /**
     * Class constructor for a Server.
     * 
     * @param identifier         the identifier of the Server.
     * @param isAvailable        availability of the Server.
     * @param hasWaitingCustomer if the Server has a waiting customer
     * @param nextAvailableTime  next timing where the Server is ready.
     * @param maxQ               maxqueue length of server
     * @param probResting        odds of server resting after doneevent
     */

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
            double nextAvailableTime, int maxQ, double probResting) {
        this.iD = identifier;
        this.isAvailable = isAvailable;
        this.hasWaiting = hasWaitingCustomer;
        this.whenFree = nextAvailableTime;
        this.maxQ = maxQ;
        this.list = new ArrayList<>();
        this.isResting = false;
        this.probResting = probResting;
    }

    /**
     * Class constructor for a Server.
     * 
     * @param identifier         the identifier of the Server.
     * @param isAvailable        availability of the Server.
     * @param hasWaitingCustomer if the Server has a waiting customer
     * @param nextAvailableTime  next timing where the Server is ready.
     * @param maxQ               maxqueue length of server
     * @param list               list of customers to serve
     * @param probResting        odds of server resting after doneevent
     */

    public Server(int identifier, boolean isAvailable, boolean hasWaitingCustomer,
            double nextAvailableTime, int maxQ, List<Customer> list, boolean isResting,
            double probResting) {
        this.iD = identifier;
        this.isAvailable = isAvailable;
        this.hasWaiting = hasWaitingCustomer;
        this.whenFree = nextAvailableTime;
        this.maxQ = maxQ;
        this.list = list;
        this.isResting = isResting;
        this.probResting = probResting;
    }

    public boolean isResting() {
        return this.isResting;
    }

    public double probResting() {
        return this.probResting;
    }

    public int maxQueue() {
        return this.maxQ;
    }

    public List<Customer> queue() {
        return this.list;
    }

    public int sizeQueue() {
        return this.list.size();
    }

    public Customer nextCustomer() {
        return this.queue().get(0);
    }

    /**
     * Add cust to list.
     * 
     * @param cust customer
     */
    public Server addCustomer(Customer cust) {
        List<Customer> temp = new ArrayList<>(this.queue());
        temp.add(cust);
        return new Server(this.getId(), this.isAvailable(), this.hasWaiting(), this.whenFree(),
                this.maxQueue(), temp, this.isResting(), this.probResting());
    }
    
    /**
     * Removes cust from list.
     */

    public Server removeCustomer() {
        List<Customer> temp = new ArrayList<>(this.queue());
        temp.remove(0);
        return new Server(this.getId(), this.isAvailable(), this.hasWaiting(), this.whenFree(),
                this.maxQueue(), temp, this.isResting(), this.probResting());
    }

    public int getId() {
        return this.iD;
    }

    public boolean isAvailable() {
        return this.isAvailable;
    }

    public boolean hasWaiting() {
        return this.hasWaiting;
    }

    public double whenFree() {
        return this.whenFree;
    }

    String giveStatus() {
        if (isAvailable) {
            return "avail";
        } else if (!hasWaiting) {
            return "queue";
        } else {
            return "leave";
        }
    }

    @Override
    public String toString() {
        if (giveStatus().equals("avail")) {
            return String.format("%d is available", iD);
        } else if (giveStatus().equals("queue")) {
            return String.format("%d is busy; available at %.3f", iD, whenFree);
        } else {
            return String.format("%d is busy; waiting customer to be served at %.3f", iD, whenFree);
        }
    }

}