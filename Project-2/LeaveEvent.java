package cs2030.simulator;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.Optional;


public class LeaveEvent extends Event {

	public LeaveEvent(Customer customer) {
		super(x-> Pair.of(x.addLeft(),null),customer);
	}

	@Override
	public String toString() {
		return String.format("%.3f %d leaves",
							 this.getCustomer().getArrivalTime(),
							 this.getCustomer().getIdentifier());
	}
}