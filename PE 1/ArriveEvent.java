package cs2030.simulator;
import java.util.List;

/**
 * @params ppl list for inheritance
 *
 */
public class ArriveEvent extends Event {

    public ArriveEvent(Customer ppl, List<Server> list) {
        super(ppl, list);
    }

    /**
     * @return Server base on avail or can queue
     */
    public Server giveServer() {
        Server best = giveList().get(0);
        for (int i = 0; i < giveList().size(); i++) {
            if (giveList().get(i).giveStatus() == 1) {
            	best = giveList().get(i);
                return best;
            }
        }
        for (int j = 0; j < giveList().size(); j++) {
            if (giveList().get(j).giveStatus() == 2) {
            	best = giveList().get(j);
                return best;
            }
        }
        return best;
    }

    public Event execute() {
        List<Server> list = giveList();
        Server server = giveServer();
        if (server.giveStatus() == 1) {
        	list = updateList(list, new Server(server.giveID(), false, false, giveCust().getArrivalTime()));
            return new ServeEvent(giveCust(), list, new Server(server.giveID(), false, false, giveCust().getArrivalTime()));
        } else if (server.giveStatus() == 2) {
            server = server.updateServer(false, true, server.whenFree());
            list = updateList(list, new Server(server.giveID(), false, true, server.whenFree()));
            return new WaitEvent(giveCust(), list, new Server(server.giveID(), false, true, server.whenFree()));
        } else {
            return new LeaveEvent(giveCust(), list);
        }
    }

    public String toString() {
        return String.format("%.3f %d arrives", getCurrentTime(), giveCust().getID());
    }
    
    public String fakegetClass() {
    	return "Arrive";
    }

}
