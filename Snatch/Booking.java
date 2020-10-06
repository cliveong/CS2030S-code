class Booking {
    private final Driver driver;
    private final Request request;
    private final int price1;
    private final int price2;
    private final int wait;

    Booking(Driver diver, Request rquest){
        this.driver = diver;
        this.request = rquest;
        this.price1 = driver.giveServices()[0].computeFare(request);
        this.price2 = driver.giveServices()[1].computeFare(request);
        this.wait = driver.giveWait();
        }
    

    Ride whichOne(){
    	if (price1 > price2) {
    		return driver.giveServices()[1];
    	} else {
    		return driver.giveServices()[0];
    	}
    }
    
    int minprice() {
    	return Math.min(price1, price2);
    }
    
    public int compareTo(Booking something) {
    	if (this.minprice() < something.minprice()) {
    		return -1;
    	} else if ((this.minprice() == something.minprice()) && this.wait < something.wait) {
    		return -1;
    	} else if (this.minprice() > something.minprice()) {
    		return 1;
    	} else if ((this.minprice() == something.minprice()) && this.wait > something.wait) {
    		return 1;
    	} else {
    		return 0;
    	}
    }
    
    double pricing() {
    	double someprice = Math.min(price1, price2);
    	return someprice/100;
    	
   
    }
    @Override
    public String toString() {
    	return String.format("$%.2f using ", this.pricing()) + driver.toString() + " (" + this.whichOne().toString() + ")"; 
    }
}
