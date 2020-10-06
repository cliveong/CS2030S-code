class Cruise{
    private final String identifier;
    private final int arrivalTime;
    private final int numOfLoader;
    private final int serviceTime;

    //constructor
    Cruise(String id, int arrival, int loader, int service){
        this.identifier = id;
        this.arrivalTime = arrival;
        this.numOfLoader = loader;
        this.serviceTime = service;
    }

    //methods
    int getServiceCompletionTime(){ //return mins
        int arrivaltimehours = arrivalTime/100;
        int arrivaltimemins = arrivalTime;
        while(arrivaltimemins >= 100){
            arrivaltimemins = arrivaltimemins - 100;
        }
        return serviceTime + (arrivaltimehours * 60) + arrivaltimemins;
    }

    int getArrivalTime(){ //HHMM to minutes
        int arrivaltimehours = arrivalTime/100;
        int arrivaltimemins = arrivalTime;
        while(arrivaltimemins >= 100){
            arrivaltimemins = arrivaltimemins - 100;
        }
        return arrivaltimehours*60 + arrivaltimemins;
    }

    int getNumOfLoadersRequired(){
        return numOfLoader;
    }
    @Override
    public String toString(){
        return String.format(identifier + "@%04d", arrivalTime);
    }
}
