package cs2030.simulator;

public class ReturnEvent extends Event {
	private final Server replacement;

	public ReturnEvent(Customer customer, Server replacement, copiedRNG rng, double est) {
		super(x -> {
			return Pair.of(x, null);			  
		},customer,rng,est);
		this.replacement = replacement;
	}


	@Override 
	public String toString() {
		return "ReturnEvent";
	}
}