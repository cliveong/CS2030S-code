import cs2030.simulator.Event;
import cs2030.simulator.Shop;
import cs2030.simulator.Customer;
import cs2030.simulator.Pair;
import cs2030.simulator.Server;
import cs2030.simulator.ArriveEvent;
import cs2030.simulator.WaitEvent;
import cs2030.simulator.LeaveEvent;
import cs2030.simulator.DoneEvent;
import cs2030.simulator.RNG;
import cs2030.simulator.EventComparator;
import cs2030.simulator.FakeStats;
import cs2030.simulator.GreedyCustomer;
import cs2030.simulator.ModEvent;
import cs2030.simulator.RestEvent;
import cs2030.simulator.ReturnEvent;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.Optional;

public class Main {
    
    /**
     * pair servers with customers, prints the output,
     * prints stats.
     */
    public static void main(String[] args) {
        int seed = 0;
        int numServers = 0;
        int maxQ = 1;
        int numCust = 0;
        double lambda = 0;
        double mu = 0;
        double rho = 0;
        double pr = 0;
        int numSelfCheckers = 0;
        double probGreed = 0;

        if (args.length == 5) {
            seed = Integer.parseInt(args[0]);
            numServers = Integer.parseInt(args[1]);
            maxQ = 1;
            numCust = Integer.parseInt(args[2]);
            lambda = Double.parseDouble(args[3]);
            mu = Double.parseDouble(args[4]);
            rho = 0.0;
            pr = 0.0;
            
        } else if (args.length == 6) {
            seed = Integer.parseInt(args[0]);
            numServers = Integer.parseInt(args[1]);
            maxQ = Integer.parseInt(args[2]);
            numCust = Integer.parseInt(args[3]);
            lambda = Double.parseDouble(args[4]);
            mu = Double.parseDouble(args[5]);
            rho = 0.0;
            pr = 0.0;
            
        } else if (args.length == 8) {
            seed = Integer.parseInt(args[0]);
            numServers = Integer.parseInt(args[1]);
            maxQ = Integer.parseInt(args[2]);
            numCust = Integer.parseInt(args[3]);
            lambda = Double.parseDouble(args[4]);
            mu = Double.parseDouble(args[5]);
            rho = Double.parseDouble(args[6]);
            pr = Double.parseDouble(args[7]);
            
        } else if (args.length == 9) {
            seed = Integer.parseInt(args[0]);
            numServers = Integer.parseInt(args[1]);
            numSelfCheckers = Integer.parseInt(args[2]);
            maxQ = Integer.parseInt(args[3]);
            numCust = Integer.parseInt(args[4]);
            lambda = Double.parseDouble(args[5]);
            mu = Double.parseDouble(args[6]);
            rho = Double.parseDouble(args[7]);
            pr = Double.parseDouble(args[8]);
            
        } else if (args.length == 9) {
            seed = Integer.parseInt(args[0]);
            numServers = Integer.parseInt(args[1]);
            numSelfCheckers = Integer.parseInt(args[2]);
            maxQ = Integer.parseInt(args[3]);
            numCust = Integer.parseInt(args[4]);
            lambda = Double.parseDouble(args[5]);
            mu = Double.parseDouble(args[6]);
            rho = Double.parseDouble(args[7]);
            pr = Double.parseDouble(args[8]);
            
        } else if (args.length == 10) {
            seed = Integer.parseInt(args[0]);
            numServers = Integer.parseInt(args[1]);
            numSelfCheckers = Integer.parseInt(args[2]);
            maxQ = Integer.parseInt(args[3]);
            numCust = Integer.parseInt(args[4]);
            lambda = Double.parseDouble(args[5]);
            mu = Double.parseDouble(args[6]);
            rho = Double.parseDouble(args[7]);
            pr = Double.parseDouble(args[8]);
            probGreed = Double.parseDouble(args[9]);
        }

        RNG rng = new RNG(seed, lambda, mu, rho);

        PriorityQueue<Event> pq = new PriorityQueue<>(new EventComparator());

        double arrivalRate = 0.00;
        if (args.length == 10) {
            for (int i = 1; i <= numCust; i++) {
                double temp = rng.genCustomerType();
                if (temp < probGreed) {
                    pq.add(new ArriveEvent(new GreedyCustomer(i, arrivalRate), rng));
                    arrivalRate = arrivalRate + rng.genInterArrivalTime();
                } else {
                    pq.add(new ArriveEvent(new Customer(i, arrivalRate), rng));
                    arrivalRate = arrivalRate + rng.genInterArrivalTime();
                }

            }
        } else {
            for (int i = 1; i <= numCust; i++) {
                pq.add(new ArriveEvent(new Customer(i, arrivalRate), rng));
                arrivalRate = arrivalRate + rng.genInterArrivalTime();
            }
        }

        Shop shop = new Shop(numServers, maxQ, pr);
        if (args.length > 8) {
            shop = new Shop(numServers, maxQ, pr, numSelfCheckers);
        }
        while (pq.size() > 0) {

            Event first = pq.poll();
            if (!(first instanceof ModEvent) && !(first instanceof RestEvent)
                    && !(first instanceof ReturnEvent)) {
                System.out.println(first);
            }

            Pair<Shop, Event> addBack = first.execute(shop);

            Event newEvent = addBack.second();

            if (Optional.ofNullable(newEvent).map(x -> x.giveFunction()).isPresent()) {
                pq.add(newEvent);
            }

            shop = addBack.first();

        }

        FakeStats stats = shop.getStatistics();
        double waitTime = stats.giveWait() / (double) stats.giveServed();
        System.out.println(String.format("[%.3f %d %d]", Double.isNaN(waitTime) ? 0.00 : waitTime,
                stats.giveServed(), stats.giveLeft()));

    }
}