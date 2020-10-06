class Loader{
    private final int identifier;
    private final Cruise cruise;
    private final int serveendtime;
    //constructor
    Loader(int something, Cruise boat){
        this.identifier = something;
        this.cruise = boat;
        this.serveendtime = boat.getServiceCompletionTime();
    }
    
    boolean canServe(Cruise boat){
        if( boat.getArrivalTime() >= serveendtime){
            return true;
        } else {
            return false;
        }
    }

    Loader serve(Cruise cruise){
        if (canServe(cruise)){
            return new Loader(identifier, cruise);
        } else{
            return this;
        }
    }

    int getIdentifier(){
        return identifier;
        }

    int getNextAvailableTime(){
        return this.cruise.getServiceCompletionTime();
    }
    
    int giveID() {
    	return this.identifier;
    }
    
    Cruise giveCruise() {
    	return this.cruise;
    }
    
    @Override
    public String toString(){
        return String.format("Loader %d serving ", identifier) + cruise.toString();
    }
}

