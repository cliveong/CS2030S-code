package cs2030.simulator;
import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    public int compare(Event e1, Event e2) {
        if (e1.eventStartTime() == e2.eventStartTime()) {
            return e1.getCustomer().getIdentifier() < e2.getCustomer().getIdentifier()? -1 : 1;
        } else {
            return e1.eventStartTime() < e2.eventStartTime()? -1 : 1;
        }
    }
}