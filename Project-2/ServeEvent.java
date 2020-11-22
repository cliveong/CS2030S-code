package cs2030.simulator;

public class ServeEvent extends Event {
	private final Server replacement;


	public ServeEvent(Customer customer, Server replacement) {
		super(x -> {
					Server newReplacement = new Server(replacement.getIdentifier(),
													   false, //isavail
													   false, //haswaitingcustomer
													   replacement.whenFree() + 1.00);
					return Pair.of(x.replace(newReplacement), new DoneEvent(customer,newReplacement));			
		}
		, customer);

		this.replacement = replacement;
		
	}

	public ServeEvent(Customer customer, Server replacement, copiedRNG rng) {
		super(x -> {
					double newReplacementTime  = replacement.whenFree() + rng.genServiceTime();
					Server newReplacement = new Server(replacement.getIdentifier(),
													   false, //isavail
													   false, //haswaitingcustomer
													   newReplacementTime);
					Customer newCustomer = new Customer(customer.getIdentifier(), newReplacementTime);
					return Pair.of(x.replace(newReplacement).addServed(), new DoneEvent(newCustomer,newReplacement,rng));
		}
		, customer,rng);
		this.replacement = replacement;
		
	}


	public ServeEvent(Customer customer, Server replacement, copiedRNG rng, double est) {
		super(x -> {
					Server temp = x.find(y -> y.getIdentifier() == replacement.getIdentifier()).get();
					double newReplacementTime  = temp.whenFree() + rng.genServiceTime();
					Server newReplacement = new Server(replacement.getIdentifier(),
													   false, //isavail
													   temp.hasWaitingCustomer(), //haswaitingcustomer
													   newReplacementTime,
													   temp.maxQueue(),
													   temp.queue(),
													   temp.isResting(),
													   temp.probResting());

					return Pair.of(x.replace(newReplacement).addServed(), new DoneEvent(customer,newReplacement,rng,newReplacementTime));
		}
		, customer,rng,est);
		this.replacement = replacement;
		
	}

	@Override
	public String toString() {
		return String.format("%.3f %d served by server %d",
							 this.eventStartTime(),
							 this.getCustomer().getIdentifier(),
							 this.replacement.getIdentifier());
							 
	}

}