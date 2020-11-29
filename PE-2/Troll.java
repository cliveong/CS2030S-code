class Troll extends Items{

    Troll(){
        super("Troll", 4, 0);
    }

    Troll(int max, int current){
        super("Troll", max, current);
    }

    String getStatus(){
        if (this.getCurrent() == 0){
            return "Troll lurks in the shadows.";
        } else if (this.getCurrent() == 1){
            return "Troll is getting hungry.";
        } else if (this.getCurrent() == 2){
            return "Troll is VERY hungry.";
        } else if (this.getCurrent() == 3){
            return "Troll is SUPER HUNGRY and is about to ATTACK!";
        } else {
            return "Troll attacks!";
        }
    }

}
