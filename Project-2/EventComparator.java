package cs2030.simulator;

import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    
    /**
     * Compares event start time for priorityqueue.
     * 
     * @param e1 first event 
     * @param e2 second event
     */
    
    public int compare(Event e1, Event e2) {
        if (e1.eventStartTime() == e2.eventStartTime()) {
            return e1.getCustomer().getID() < e2.getCustomer().getID() ? -1 : 1;
        } else {
            return e1.eventStartTime() < e2.eventStartTime() ? -1 : 1;
        }
    }
}