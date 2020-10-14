package cs2030.simulator;
import java.util.List;

/**
 * @params ppl list for inheritance, server for info to update base on situation
 *
 */
public class WaitEvent extends Event {
    private final Server daServer;

    public WaitEvent(Customer ppl, List<Server> list, Server server) {
        super(ppl, list);
        this.daServer = server;
    }


    public Event execute() {
        List<Server> list = giveList();
        list = updateList(list, new Server(this.daServer.giveID(), false, daServer.giveWaiting(), daServer.whenFree()));
        return new ServeEvent(giveCust(), list, new Server(this.daServer.giveID(), false, daServer.giveWaiting(), daServer.whenFree()));
    }


    public String toString() {
        return String.format("%.3f %d waits to be served by %d", getCurrentTime(), giveCust().getID(), daServer.giveID());
    }
    
    public String fakegetClass() {
    	return "Wait";
    }

}