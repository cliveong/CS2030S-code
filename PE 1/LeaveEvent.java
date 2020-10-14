package cs2030.simulator;
import java.util.List;

/**
 * @params ppl list, inheritance
 *
 */
public class LeaveEvent extends Event {

    public LeaveEvent(Customer ppl, List<Server> list) {
        super(ppl, list);
    }

    public Event execute() {
        return null;
    }

    public String toString() {
        return String.format("%.3f %d leaves", giveCust().getArrivalTime(), giveCust().getID());
    }
    
    public String fakegetClass() {
    	return "Leave";
    }

}
