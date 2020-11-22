package cs2030.simulator;

public class RescheduleEvent extends Event {
	private final Server replacement;


	public RescheduleEvent(Customer customer, Server replacement, copiedRNG rng, double est) {
		super(x -> {
					Server temp = x.find(y -> y.getIdentifier() == replacement.getIdentifier()).get();
					if (temp.isAvailable()) {
			  			Server newReplacement = new Server(temp.getIdentifier(),
			  											   false,
			  											   temp.hasWaitingCustomer(),
			  											   temp.whenFree(),
			  											   temp.maxQueue(),
			  											   temp.queue(),
			  											   temp.isResting(),
			  											   temp.probResting()).removeCustomer();

			  			double waitingTime = temp.whenFree() - customer.getArrivalTime();

			  			return Pair.of(x.replace(newReplacement).addWait(waitingTime), new ServeEvent(customer,newReplacement,rng,temp.whenFree()));
					}
					else {
			  			Server newReplacement = new Server(temp.getIdentifier(),
			  											   false,
			  											   temp.hasWaitingCustomer(),
			  											   temp.whenFree(),
			  											   temp.maxQueue(),
			  											   temp.queue(),
			  											   temp.isResting(),
			  											   temp.probResting());
			  			return Pair.of(x.replace(newReplacement), new RescheduleEvent(customer,newReplacement,rng,temp.whenFree()));

					}

		},customer,rng,est);
		this.replacement = replacement;
	}

	@Override
	public String toString() {
		return "RescheduleEvent";
	}
}



