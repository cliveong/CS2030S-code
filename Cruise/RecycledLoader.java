
class RecycledLoader extends Loader {
    //private final int identifier;
    //private final Cruise cruise;
    //private int serveendtime;
    //constructor
    RecycledLoader(int something, Cruise boat){
    	super(something, boat);
    }
    
    //@Override
    int getNextAvailableTime(){
        return this.giveCruise().getServiceCompletionTime()+60;
    }
    
   // @Override
    boolean canServe(Cruise boat){
        if( boat.getArrivalTime() >= this.giveCruise().getServiceCompletionTime()+60){
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public String toString(){
        return String.format("Recycled Loader %d serving ", this.giveID()) + this.giveCruise().toString();
    }
}
