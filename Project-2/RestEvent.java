package cs2030.simulator;

public class RestEvent extends Event {
	private final Server replacement;



	public RestEvent(Customer customer, Server replacement, copiedRNG rng, double est) {
		super(x -> {
			  Server temp = x.find(y -> y.getIdentifier() == replacement.getIdentifier()).get();
			  Server newServer = new Server(temp.getIdentifier(),
										    true,
										    temp.hasWaitingCustomer(),
										    temp.whenFree(),
										    temp.maxQueue(),
										    temp.queue(),
										    false,
										    temp.probResting());
			return Pair.of(x.replace(newServer), new ReturnEvent(customer,newServer,rng,temp.whenFree()));			  
		},customer,rng,est);
		this.replacement = replacement;
	}


	@Override 
	public String toString() {
		return "RestEvent";
	}
}




