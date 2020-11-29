package cs2030.simulator;

import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Shop {
    private final List<Server> list;
    private final FakeStats statz;

    /**
     * Class constructor for shop.
     * 
     * @param n num of Server.
     */
    public Shop(int n) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach(x -> test.add(new Server(x, true, false, 0.00)));
        this.list = test;
        this.statz = new FakeStats(0, 0, 0);
    }

    /**
     * Class constructor for shop.
     * 
     * @param n    num of Server.
     * @param maxQ max length of queue
     */
    public Shop(int n, int maxQ) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach(x -> test.add(new Server(x, true, false, 0.0, maxQ)));
        this.list = test;
        this.statz = new FakeStats(0, 0, 0);
    }

    /**
     * Class constructor for shop.
     * 
     * @param n           num of Server.
     * @param maxQ        max length of queue
     * @param probResting odds of resting after doneevent
     */
    public Shop(int n, int maxQ, double probResting) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach(x -> test.add(new Server(x, true, false, 0.00, maxQ, probResting)));
        this.list = test;
        this.statz = new FakeStats(0, 0, 0);
    }

    /**
     * Class constructor for shop.
     * 
     * @param n            num of Server.
     * @param maxQ         max length of queue
     * @param probResting  odds of resting after doneevent
     * @param selfCheckers num of self checkout counters
     */
    public Shop(int n, int maxQ, double probResting, int selfCheckers) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach(x -> test.add(new Server(x, true, false, 0.00, maxQ, probResting)));
        IntStream selfStream = IntStream.range(n + 1, n + 1 + selfCheckers);
        selfStream.forEach(x -> test.add(new SelfServer(x, true, false, 0, maxQ)));
        this.list = test;
        this.statz = new FakeStats(0, 0, 0);
    }

    /**
     * Class constructor for shop.
     * 
     * @param listServers list of servers
     */
    public Shop(List<Server> listServers) {
        this.list = listServers;
        this.statz = new FakeStats(0, 0, 0);
    }

    /**
     * Class constructor for shop.
     * 
     * @param listServers list of servers
     * @param statistics  counter of wait time/served customers/customers left
     */
    public Shop(List<Server> listServers, FakeStats statistics) {
        this.list = listServers;
        this.statz = statistics;
    }

    public Shop incrementServed() {
        return new Shop(this.getList(), this.statz.addServed());
    }

    public Shop incrementLeft() {
        return new Shop(this.getList(), this.statz.addLeft());
    }

    public Shop incrementWait(double wait) {
        return new Shop(this.getList(), this.statz.addWait(wait));
    }

    public List<Server> getList() {
        return this.list;
    }

    public FakeStats getStatistics() {
        return this.statz;
    }

    /**
     * Method to find server base on filter condition.
     * 
     * @param filter the predicate
     */
    public Optional<Server> find(Predicate<? super Server> filter) {
        Optional<Server> result = this.getList().stream().filter(filter).findFirst();
        return result;

    }

    /**
     * Method to update server base on the server id.
     * 
     * @param replacement the new server
     */
    public Shop replace(Server replacement) {
        Shop result = new Shop(this.getList().stream()
                .map(x -> (x.getId() == replacement.getId()) ? replacement : x)
                .collect(Collectors.toList()), this.getStatistics());
        return result;
    }

    /**
     * Method to find server with shortest queue for greedy customers.
     */
    public Server lowestQueue() {
        PriorityQueue<Server> pq = new PriorityQueue<Server>(new Comparator<Server>() {
            @Override
            public int compare(Server s1, Server s2) {
                if (s1.sizeQueue() == s2.sizeQueue()) {
                    if (s1.getId() < s2.getId()) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    if (s1.sizeQueue() < s2.sizeQueue()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
        });
        list.stream().forEach(x -> pq.add(x));
        return pq.poll();
    }

    public String toString() {
        return this.getList().toString();
    }
}