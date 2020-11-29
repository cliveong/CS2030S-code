class Candle extends Items{
    Candle(){
        super("Candle", 3, 0);
    }
    Candle(int max, int current){
        super("Candle", max, current);
    }
    
    public String getStatus(){
        if (this.getCurrent() == 0){
            return "Candle flickers.";
        } else if (this.getCurrent() == 1){
            return "Candle is getting shorter.";
        } else if (this.getCurrent() == 2){
            return "Candle is about to burn out.";
        } else {
            return "Candle has burned out.";
        }
    }
}

