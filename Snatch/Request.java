class Request{
    private final int distance;
    private final int passengercount;
    private final int time;

    Request(int dist, int pass, int ticktock){
        this.distance = dist;
        this.passengercount = pass;
        this.time = ticktock;
    }

    public int giveDistance(){
        return distance;
    }

    public int giveppl(){
        return passengercount;
    }

    public int giveTime(){
        return time;
    }

    @Override
    public String toString(){
        return String.format( "%dkm for %dpax @ %dhrs",distance, passengercount, time);
    }
}
