class Driver{
    private final String id;
    private final int wait;
    private final Ride class1;
    private final Ride class2;

    Driver(String nricrankandname, int time, Ride classone, Ride classtwo){
        this.id = nricrankandname;
        this.wait = time;
        this.class1 = classone;
        this.class2 = classtwo;
    }
    
    public String giveID(){
        return this.id;
    }

    public int giveWait(){
        return this.wait;
    }
    
    public Ride[] giveServices() {
    	Ride[] a = {class1,class2};
    	return a;
    }
}




