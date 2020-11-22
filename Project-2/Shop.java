package cs2030.simulator;
import java.util.stream.Stream;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.Optional;



public class Shop {
	private final List<Server> list;
	//private final double[] statistics;
	private final FakeStats statz;

	public Shop(int n) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach( x -> test.add(new Server(x, true, false, 0.00)));
        this.list = test;
        /*
         * this.list = IntStream.range(1,n+1) .mapToObj(x -> new
         * Server(x,true,false,0.0)) .collect(Collectors.toList());
         */
		//this.statistics = new double[]{0.00,0.00,0.00};
		this.statz = new FakeStats(0,0,0);
	}

	public Shop(int n,int maxQ) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach( x -> test.add(new Server(x, true, false, 0.00, maxQ, new ArrayList<>(), false, 0)));
        this.list = test; 
        /*
         * this.list = IntStream.range(1,n+1) .mapToObj(x -> new
         * Server(x,true,false,0.0,maxQ)) .collect(Collectors.toList());
         */
		//this.statistics = new double[]{0.00,0.00,0.00};
		this.statz = new FakeStats(0,0,0);
	}

	public Shop(int n,int maxQ, double restingOdds) {
        List<Server> test = new ArrayList<Server>();
        IntStream stream = IntStream.rangeClosed(1, n);
        stream.forEach( x -> test.add(new Server(x, true, false, 0.00, maxQ, restingOdds)));
        this.list = test; 
        /*
         * this.list = IntStream.range(1,n+1) .mapToObj(x -> new
         * Server(x,true,false,0.0,maxQ,probResting)) .collect(Collectors.toList());
         */
		//this.statistics = new double[]{0.00,0.00,0.00};
		this.statz = new FakeStats(0,0,0);
	}


	public Shop(List<Server> listServers) {
		this.list = listServers;
		//this.statistics = new double[]{0.00,0.00,0.00};
		this.statz = new FakeStats(0,0,0);
	}

    public Shop(List<Server> listServers, /*double[]*/FakeStats statistics) {
		this.list = listServers;
		//this.statistics = statistics;
		this.statz = statistics;
	}

	public Shop addServed() {
        /*
         * double[] result = new double[3]; result[0] = this.getStatistics()[0];
         * result[1] = this.getStatistics()[1] + 1; result[2] = this.getStatistics()[2];
         */

		return new Shop(this.getList(), this.statz.addServed());
	}

	public Shop addLeft() {
        /*
         * double[] result = new double[3]; result[0] = this.getStatistics()[0];
         * result[1] = this.getStatistics()[1]; result[2] = this.getStatistics()[2]+1;
         */

		return new Shop(this.getList(), this.statz.addLeft());
	}

	public Shop addWait(double wait) {
        /*
         * double[] result = new double[3]; result[0] = this.getStatistics()[0] + wait;
         * result[1] = this.getStatistics()[1]; result[2] = this.getStatistics()[2];
         */
		return new Shop(this.getList(), this.statz.addWait(wait));	
	}

	public List<Server> getList() {
		return this.list;
	}

	public FakeStats getStatistics() {
		//return this.statistics;
	    return this.statz;
	}

	public Optional<Server> find(Predicate<? super Server> filter) {
		Optional<Server> result = this.getList().stream()
							 .filter(filter)
							 .findFirst();
		//System.out.println("this is result of find: "+result);
		return result;


	}

	public Shop replace(Server replacement) {
		Shop result = new Shop(this.getList().stream()
									  .map(x -> (x.getIdentifier() == replacement.getIdentifier()) ? replacement : x )
									  .collect(Collectors.toList()), this.getStatistics()
									  );
		//System.out.println("this is result of replace: "+result);
		return result;
	}

	public String toString() {
		return this.getList().toString();
	} 
}