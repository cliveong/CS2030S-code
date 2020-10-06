class NormalCab extends Driver{
    NormalCab(String id, int wait){
        super(id, wait, new JustRide(), new TakeACab());
    }

    //@Override
    public String toString(){
        return this.giveID() + String.format(" (%d mins away) NormalCab", this.giveWait());
    }
}