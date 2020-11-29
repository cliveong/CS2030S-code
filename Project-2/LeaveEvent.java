package cs2030.simulator;

public class LeaveEvent extends Event {

    public LeaveEvent(Customer customer) {
        super(x -> Pair.of(x.incrementLeft(), null), customer);
    }

    @Override
    public String toString() {
        if (this.getCustomer() instanceof GreedyCustomer) {
            return String.format("%.3f %s leaves", this.getCustomer().getArrTime(),
                    this.getCustomer());
        } else {
            return String.format("%.3f %d leaves", this.getCustomer().getArrTime(),
                    this.getCustomer().getID());
        }

    }
}