package cs2030.simulator;

public class FakeStats {
    private final double wait;
    private final int served;
    private final int left;

    /**
     * Constructor for stat tracker taking in the current time.
     * 
     * @param wait     wait time accumulated
     * @param served   served customers 
     * @param left     customers that left
     */
    
    public FakeStats(double wait, int served, int left) {
        this.wait = wait;
        this.served = served;
        this.left = left;
    }

    public double giveWait() {
        return this.wait;
    }

    public int giveServed() {
        return this.served;
    }

    public int giveLeft() {
        return this.left;
    }

    public FakeStats addWait(double wait) {
        return new FakeStats(this.wait + wait, this.served, this.left);
    }

    public FakeStats addServed() {
        return new FakeStats(this.wait, this.served + 1, this.left);
    }

    public FakeStats addLeft() {
        return new FakeStats(this.wait, this.served, this.left + 1);
    }

}
