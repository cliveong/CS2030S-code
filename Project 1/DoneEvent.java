package cs2030.simulator;
import java.util.List;

/**
 *execute @return null for class checking in pq
 *@param takes in additional server to manipulate data base on server
 *
 */
public class DoneEvent extends Event {
    private final Server daServer;

    public DoneEvent(Customer ppl, List<Server> list, Server server) {
        super(ppl, list);
        this.daServer = server;
    }

    public Event execute() {
        return null;
    }

    public String toString() {
        return String.format("%.3f %d done serving by %d", getCurrentTime(), giveCust().getID(), daServer.giveID());
    }

    @Override
    public double getCurrentTime() {
        return daServer.whenFree();
    }

    public String fakegetClass() {
    	return "Done";
    }
    
    @Override
    public List<Server> updatedList() {
        List<Server> list = giveList();
        list = updateList(list, new Server(this.daServer.giveID(), true, false, daServer.whenFree()));
        return list;
    }
}
