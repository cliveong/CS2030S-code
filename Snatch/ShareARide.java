class ShareARide extends Ride{
	ShareARide(){
		super(50, 0, 500);
	}
	
	@Override
    public String toString(){
        return "ShareARide";
    }
	
	public int computeFare(Request something) {
		return ShareARidecomputeFare(something);
	}
}