abstract class Items{
    private final String name;
    private final int maxstate;
    private final int currentstate;
    
    Items(String name, int maxstate, int currentstate){
        this.name = name;
        this.maxstate = maxstate;
        this.currentstate = currentstate;
    }

    int getMax(){
        return this.maxstate;
    }

    int getCurrent(){
        return this.currentstate;
    }

    String getName(){
        return this.name;
    }

    abstract String getStatus();
}


