package cs2030.simulator;

public class WaitEvent extends Event {
	private final Server replacement;


	public WaitEvent(Customer customer, Server replacement) {
		super(x -> {
			Server newReplacement = new Server(replacement.getIdentifier(),
											   false,
											   replacement.hasWaitingCustomer(),
											   replacement.whenFree());
			return Pair.of(x.replace(newReplacement), new ServeEvent(customer, newReplacement));
		},
		customer);
		this.replacement = replacement;
	}

	public WaitEvent(Customer customer, Server replacement, copiedRNG rng) {
		super(x -> {
			Server newReplacement = new Server(replacement.getIdentifier(),
											   false,
											   replacement.hasWaitingCustomer(),
											   replacement.whenFree());

			Customer temp = new Customer(customer.getIdentifier(), replacement.whenFree() );

			double waitingTime = replacement.whenFree() - customer.getArrivalTime();

			return Pair.of(x.replace(newReplacement).addWait(waitingTime), new ServeEvent(temp, newReplacement, rng));
		} ,
		customer,rng);
		this.replacement = replacement;
	}


	public WaitEvent(Customer customer, Server replacement, copiedRNG rng, double est) {
		super(x -> {
			  		Server temp = x.find(y -> y.getIdentifier() == replacement.getIdentifier()).get();
			  		if (temp.isResting()) {
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

			  		else {
				  		if (temp.isAvailable() ) {
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
			  		}
		} ,
		customer,rng,est);
		this.replacement = replacement;
	}



	@Override
	public String toString() {
		return String.format("%.3f %d waits to be served by server %d",
							 this.getCustomer().getArrivalTime(),
							 this.getCustomer().getIdentifier(),
							 this.replacement.getIdentifier());
	}
}