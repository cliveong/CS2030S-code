package cs2030.simulator;
import java.util.List;

/**
 * @param cust, list for inheritance
 *
 */
public abstract class Event {
    private final Customer cust;
    private final List<Server> list;

    public Event(Customer ppl, List<Server> list) {
        this.cust = ppl;
        this.list = list;
    }

    public Customer giveCust() {
        return this.cust;
    }

    public List<Server> giveList() {
        return this.list;
    }

    public double getCurrentTime() {
        return giveCust().getArrivalTime();
    }

    /**
     * @return events base on the current server status
     */
    public abstract Event execute();

    public abstract String toString();
    
    /**
     * @return string of the class, server as getclass substitude for stats counting
     */
    public abstract String fakegetClass();//getClass substitute

    /**
     * @return list to update server status
     */
    public List<Server> updatedList() {
        return list;
    }

    public List<Server> updateList(List<Server> list, Server newServer) {
        list.set(newServer.giveID() - 1, newServer);
        return list;
    }
}
