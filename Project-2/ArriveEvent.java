package cs2030.simulator;
import java.util.Optional;

public class ArriveEvent extends Event {

	public ArriveEvent(Customer customer) {
		super(x -> { 
					Optional<Server> freeServer = x.find(y -> y.isAvailable());
					Optional<Server> busyNoWaitingServer = x.find(y -> !(y.isAvailable()) && !(y.hasWaitingCustomer()));
					if (freeServer.isPresent()) {
					    
					    Server tempServe = freeServer.get();
                        Server serveReplacement = new Server(tempServe.getIdentifier(),false,false,customer.getArrivalTime());
                        
                        return Pair.of(x.replace(serveReplacement), new ServeEvent(customer,serveReplacement));
					} else {
					    
					    if (busyNoWaitingServer.isEmpty()) {
                            return Pair.of(x, new LeaveEvent(customer));
                            
                        } else {
                            
                            Server temp = busyNoWaitingServer.get();
                            Server replacement = new Server(temp.getIdentifier(),false,temp.hasWaitingCustomer(),temp.whenFree());
                            
                            return Pair.of(x.replace(replacement), new WaitEvent(customer,replacement));
                        }
					}
			} , customer);
	}


	public ArriveEvent(Customer customer, copiedRNG rng) {
		super(x -> { 
					Optional<Server> freeServer = x.find(y -> y.isAvailable() && y.sizeQueue() == 0);
					Optional<Server> busyNoWaitingServer = x.find(y -> !(y.isAvailable()) && (y.sizeQueue() + 1 <= y.maxQueue()) );
					
					
					if (freeServer.isPresent()) {
					    Server tempServe = freeServer.get();
                        Server serveReplacement = new Server(tempServe.getIdentifier(),false,false,customer.getArrivalTime(),
                                                             tempServe.maxQueue(),tempServe.queue(),tempServe.isResting(),tempServe.probResting());
                        
                        return Pair.of(x.replace(serveReplacement), new ServeEvent(customer,serveReplacement,rng,customer.getArrivalTime()));
                        
					} else {
					    
					    if (busyNoWaitingServer.isPresent()) {
					        Server temp = busyNoWaitingServer.get();
                            Server replacement = new Server(temp.getIdentifier(),false, true, temp.whenFree(),temp.maxQueue(),
                                                            temp.queue(),temp.isResting(),temp.probResting()).addCustomer(customer);
                            
                            return Pair.of(x.replace(replacement), new WaitEvent(customer,replacement,rng,customer.getArrivalTime()));
                            
					    } else {
					        
					        return Pair.of(x, new LeaveEvent(customer));
					    }
					}
			} , customer, rng);
	}

	@Override
	public String toString() {
		return String.format("%.3f %d arrives", 
							 this.getCustomer().getArrivalTime(), 
							 this.getCustomer().getIdentifier());
	}
}