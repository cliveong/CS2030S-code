package cs2030.simulator;
import java.util.List;

/**
 * @params ppl list for inheritance, server for info to update base on situation
 *
 */
public class ServeEvent extends Event {
    private final Server daServer;

    public ServeEvent(Customer ppl, List<Server> list, Server server) {
        super(ppl, list);
        this.daServer = server;
    }

    public Event execute() {
        List<Server> list = giveList();
        list = updateList(list, new Server(this.daServer.giveID(),false,false,daServer.whenFree()+1));
        return new DoneEvent(giveCust(), list, new Server(this.daServer.giveID(),false,false,daServer.whenFree()+1));
    }


    public String toString() {
        return String.format("%.3f %d served by %d", getCurrentTime(), giveCust().getID(), daServer.giveID());
    }

    @Override
    public double getCurrentTime() {
        return daServer.whenFree();
    }
    
    public String fakegetClass() {
    	return "Serve";
    }

}
