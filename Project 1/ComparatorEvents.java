package cs2030.simulator;
import java.util.Comparator;

public class ComparatorEvents implements Comparator<Event>  {

    /**
     *compares time as priority, then customer id
     */
    @Override
    public int compare(Event e1, Event e2) {
        if (e1.getCurrentTime() == e2.getCurrentTime()) {
        	return e1.giveCust().getID() < e2.giveCust().getID() ? -1 : 1;
        } else {
            return e1.getCurrentTime() < e2.getCurrentTime() ? -1 : 1;
        }
    }

}
