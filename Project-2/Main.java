import cs2030.simulator.Event;
import cs2030.simulator.Shop;
import cs2030.simulator.Customer;
import cs2030.simulator.Pair;
import cs2030.simulator.Server;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.copiedRNG;
import cs2030.simulator.EventComparator;
import cs2030.simulator.FakeStats;
import cs2030.simulator.RescheduleEvent;
import cs2030.simulator.RestEvent;
import cs2030.simulator.ReturnEvent;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Optional;

public class Main {

	public static void main(String[] args) {
		int seed = 0; 
		int numServers = 0; 
		int maxQ = 1; 
		int nCustomers = 0; 
		double lambda = 0.0; 
		double mu = 0.0; 
		double rho = 0.0; 
		double pr = 0.0;

		if (args.length == 5) {
			seed = Integer.parseInt(args[0]);
			numServers = Integer.parseInt(args[1]);
			maxQ = 1;
			nCustomers = Integer.parseInt(args[2]);
			lambda = Double.parseDouble(args[3]);
			mu = Double.parseDouble(args[4]);
		    rho = 0.0; 
		    pr = 0.0;
		}

		if (args.length == 6) {
			seed = Integer.parseInt(args[0]);
			numServers = Integer.parseInt(args[1]);
			maxQ = Integer.parseInt(args[2]);
			nCustomers = Integer.parseInt(args[3]);
			lambda = Double.parseDouble(args[4]);
			mu = Double.parseDouble(args[5]);	
		    rho = 0.0; 
		    pr = 0.0;
		}

		if (args.length == 8) {
			seed = Integer.parseInt(args[0]);
			numServers = Integer.parseInt(args[1]);
			maxQ = Integer.parseInt(args[2]);
			nCustomers = Integer.parseInt(args[3]);
			lambda = Double.parseDouble(args[4]);
			mu = Double.parseDouble(args[5]);
			rho = Double.parseDouble(args[6]);
			pr = Double.parseDouble(args[7]);			
		}

		copiedRNG rng = new copiedRNG(seed,lambda,mu,rho);

		PriorityQueue<Event> pq = new PriorityQueue<>(new EventComparator());

		//pq.add(new ArriveEvent(new Customer(1,0.00),rng));

		double arrivalRate = 0.00;

		for (int i = 1/*2*/ ; i <= nCustomers ; i++) {
		    pq.add(new ArriveEvent(new Customer(i,arrivalRate),rng));
			arrivalRate += rng.genInterArrivalTime();
			//pq.add(new ArriveEvent(new Customer(i,arrivalRate),rng));
		}

		Shop shop = new Shop(numServers,maxQ,pr);
		while (pq.size() > 0) {

			Event first = pq.poll();
			if (!(first instanceof RescheduleEvent) && !(first instanceof RestEvent) && !(first instanceof ReturnEvent)) {
				System.out.println(first);
			}
			
			Pair<Shop,Event> addBack = first.execute(shop);
			

			Event newEvent = addBack.second();

			if (Optional.ofNullable(newEvent).map(x->x.giveFunction()).isPresent()) {
				pq.add(newEvent);
			}
			
			//Shop updatedShop = addBack.first();
			shop = addBack.first();


		}

		//double[] statistics = shop.getStatistics();
		FakeStats stats = shop.getStatistics();
		double waitTime = stats.giveWait() / (double) stats.giveServed();
		//double avgWaitTime = statistics[0] / statistics[1];
		//int nServed = (int) statistics[1];
		//int nLeft = (int) statistics[2];
		System.out.println(String.format("[%.3f %d %d]", Double.isNaN(waitTime) ? 0.00 : waitTime,stats.giveServed(),stats.giveLeft()));
		//System.out.printf("[%.3f %d %d]\n",Double.isNaN(avgWaitTime) ? 0.00 : avgWaitTime,nServed,nLeft);

	}
}