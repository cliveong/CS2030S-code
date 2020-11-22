package cs2030.simulator;

public class DoneEvent extends Event {
	private final Server replacement;

	public DoneEvent(Customer customer, Server replacement) {
		super(x -> {
					Server newReplacement = new Server(replacement.getIdentifier(),
													   true,
													   false,
													   replacement.whenFree());
					return Pair.of(x.replace(newReplacement), null);
		}, customer);
		this.replacement = new Server(replacement.getIdentifier(),true,false,replacement.whenFree());
	}


	public DoneEvent(Customer customer, Server replacement, copiedRNG rng) {
		super(x -> {
					Server newReplacement = new Server(replacement.getIdentifier(),
													   true,
													   false,
													   replacement.whenFree());
					return Pair.of(x.replace(newReplacement), null);
		}, customer, rng);
		this.replacement = new Server(replacement.getIdentifier(),true,false,replacement.whenFree());
	}

	public DoneEvent(Customer customer, Server replacement, copiedRNG rng, double est) {
		super(x -> {
					Server temp = x.find(y -> y.getIdentifier() == replacement.getIdentifier()).get();
					
					double randomRest = rng.genRandomRest();
					boolean willRest = randomRest < temp.probResting() ? true : false;
					if (willRest) {
						double restPeriod = rng.genRestPeriod();
						double nowAndRestPeriod = est + restPeriod; //next available time for server
						Server newServer = new Server(temp.getIdentifier(),
													  false,
													  temp.hasWaitingCustomer(),
													  nowAndRestPeriod,
													  temp.maxQueue(),
													  temp.queue(),
													  true,
													  temp.probResting());

						return Pair.of(x.replace(newServer), new RestEvent(customer,newServer,rng,nowAndRestPeriod));
					}
					else {
						Server newReplacement = new Server(temp.getIdentifier(),
														   true,
														   temp.hasWaitingCustomer(),
														   temp.whenFree(),
														   temp.maxQueue(),
														   temp.queue(),
														   false,
														   temp.probResting());

						return Pair.of(x.replace(newReplacement), null);						
					}



		}, customer, rng, est);
		this.replacement = replacement;
	}





	@Override
	public String toString() {
		return String.format("%.3f %d done serving by server %d",
							 this.eventStartTime(),
							 this.getCustomer().getIdentifier(),
							 this.replacement.getIdentifier());
	}
}