class PrivateCar extends Driver{
	PrivateCar(String id, int wait){
		super(id, wait, new JustRide(), new ShareARide());
	}
	
    @Override
    public String toString(){
        return this.giveID() + String.format(" (%d mins away) PrivateCar", this.giveWait());
    }
}
