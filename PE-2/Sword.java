class Sword extends Items{
    private final boolean taken;

    Sword(){
        super("Sword", 0, 0);
        taken = false;
    }

    Sword(int max, int current){
        super("Sword", max, current);
        taken = false;
    }

    Sword(int max, int current, boolean taken){
        super("Sword", max, current);
        this.taken = taken;
    }
    
    boolean getTaken(){
        return this.taken;
    }

    String getStatus(){
        if (getCurrent() == 0){
            return "Sword is shimmering.";
        }
        else{
            return "Sword is shimmering";
        }
    }
}
